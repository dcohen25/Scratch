package service;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import data.ProgramData;
import exception.CommandNotFoundException;
import exception.DeviceNotFoundException;
import exception.TopCodesEmptyException;
import exception.ProcessImageException;
import topcodes.Scanner;
import topcodes.TopCode;

public class UploadImageService {

	public static ProgramData processImage(BufferedImage image) throws ProcessImageException {
		ProgramData programData;
		try {
			List<TopCode> topCodes = getTopCodes(image);
			if (topCodes.isEmpty()){
				throw new TopCodesEmptyException();
			}
			String device = getDevice(topCodes.get(0));
			List<String> commands = getCommands(topCodes);
			programData = new ProgramData(device, commands);
		}
		catch (TopCodesEmptyException e){
			e.printStackTrace();
			throw new ProcessImageException("No Topcodes found");
		}
		catch (DeviceNotFoundException e){
			e.printStackTrace();
			throw new ProcessImageException("Device not found: " + e.getMessage());
		}
		catch (CommandNotFoundException e){
			e.printStackTrace();
			throw new ProcessImageException("Command not found: " + e.getMessage());
		}
		catch (Exception e){
			e.printStackTrace();
			throw new ProcessImageException("Error: " + e.getMessage());
		}
		return programData;
	}
	
	private static String getDevice(TopCode topCode) throws DeviceNotFoundException {
		String device;
		switch (topCode.getCode()) {
			case 61:	device = "rccar";
					 	break;
			case 79:	device = "ardrone";
						break;
			case 87:	device = "phillipshue";
						break;
			case 91: 	device = "ifttt";
						break;
			case 103:	device = "sphero";
						break;
			default:	throw new DeviceNotFoundException("Invalid device code: "  + topCode.getCode());
		}
		return device;
	}
	
	private static List<TopCode> getTopCodes(BufferedImage image) {
		List<TopCode> topCodes;
		Scanner scanner = new Scanner();
		topCodes = scanner.scan(image);
		return topCodes;
	}
	
	private static List<String> getCommands(List<TopCode> topCodes) throws CommandNotFoundException{
		List<String> commands = new LinkedList<String>();
		for (int i = 1; i < topCodes.size(); i++){
				String command = parseCommand(topCodes.get(i));
				commands.add(command);
		}
		return commands;
	}
	
	private static String parseCommand(TopCode topcode) throws CommandNotFoundException {
		String command;
		switch (topcode.getCode()) {
			case 31:	command = "move-forward";
					 	break;
			case 47:	command = "move-backward";
						break;
			case 55:	command = "turn-left";
						break;
			case 59: 	command = "turn-right";
						break;
			default:	throw new CommandNotFoundException("Invalid command code: "  + topcode.getCode());
		}
		return command;
	}
}
