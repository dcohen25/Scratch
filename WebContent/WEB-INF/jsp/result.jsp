<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Codes</title>
</head>
<body>
<table>
<tbody>
<tr><th>Code</th></tr>
<c:forEach items="${topcodes}" var="topcode">
    <tr>      
        <td>${topcode.code}</td>
    </tr>
</c:forEach>
</tbody>
</table>
</body>
</html>