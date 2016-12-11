package servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import data.ProgramData;
import exception.ProcessImageException;
import se.nicklasgavelin.bluetooth.Bluetooth;
import se.nicklasgavelin.bluetooth.BluetoothDevice;
import se.nicklasgavelin.sphero.Robot;
import se.nicklasgavelin.sphero.command.RGBLEDCommand;
import se.nicklasgavelin.sphero.command.RollCommand;
import service.UploadImageService;
import sphero.ConnectThread;

/**
 * Servlet implementation class UploadImageServlet
 */
@WebServlet("/UploadImage")
@MultipartConfig
public class UploadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    Part filePart = request.getPart("file");
	    InputStream fileContent = filePart.getInputStream();
	    BufferedImage image = ImageIO.read(fileContent);
	    try {
			ProgramData programData = UploadImageService.processImage(image);
			ObjectWriter ow = new ObjectMapper().writer();
			String json = ow.writeValueAsString(programData);
			HttpClient httpClient =  HttpClients.createDefault();
			HttpPost httpPost = new HttpPost("http://138.16.161.41:8080");
			httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
	        List<NameValuePair> nvps = new ArrayList <NameValuePair>();
	        nvps.add(new BasicNameValuePair("data", json));
	        httpPost.setEntity(new UrlEncodedFormEntity(nvps));	       
	        httpClient.execute(httpPost);
	        HttpResponse httpResponse = httpClient.execute(httpPost);
	        HttpEntity responseEntity = httpResponse.getEntity();
	        if (responseEntity != null) {
	            String postResponse = EntityUtils.toString(responseEntity);
	            System.out.println(postResponse);
	        }
	        request.setAttribute("successMessage", "Succesfully processed image request");
		}
	    catch (ProcessImageException e) {
	    	request.setAttribute("failureMessage", "Failed to process image request: " + e.getMessage());
	    	e.printStackTrace();
		}
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
	}
}
