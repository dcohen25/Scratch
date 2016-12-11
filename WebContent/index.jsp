<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ScratchBlocks</title>
<link rel="stylesheet" href="css/style.css" />
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
</head>
<body class="center c1">
	<header class="bg7">
		<div class="container">
			<div class="row">
	    		<h2 id="header-title" class="col-md-12">ScratchBlocks</h2>
	    	</div>
	    </div>
	</header>
	<div id="main">
    	<div class="container">
    		<div class="row">
				<div id="upload-image" class="bg5 col-md-6 col-md-offset-3">
					<div class="row">
						<div class="col-md-12">
							<h3>Snap a ScratchBlocks program!</h3>
							<c:choose>
							    <c:when test="${failureMessage ne null}">
							    	<div id="failure">
							    		<h4>${failureMessage}</h4>
							    	</div>
							    </c:when>
							    <c:when test="${successMessage ne null}">
							    	<div id="success">
							    		<h4>${successMessage}</h4>
							    	</div>
							    </c:when>
							</c:choose>
						</div>
					</div>
					<div class="row">
						<form id="upload-image-form" class="col-md-12" action="UploadImage" method="post" enctype="multipart/form-data">
						  	<div class="form-group col-md-10">
						  		<input id="image-file" class="bg6 c1 form-control" name="file" type="file" accept="image/*;capture=camera">	
						  	</div>
						  	<div class="form-group col-md-1 col-offset-1">
						  		<button id="image-submit" class="bg3 c1 form-control" type="submit">Snap!</button>
						  	</div>
						</form>	
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer class="bg4">
		<div class="container">
			<div class="row">
				<h3 class="col-md-12">By Rishab Singh and David Cohen</h3>
			</div>
		</div>
    </footer>
</body>
</html>