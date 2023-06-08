<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Price Alert</title>
</head>
<body>
	<h1>Update Your New Price Alert Below</h1>
	
	<form action="collectionupdate?CollectionId=<%= request.getParameter("CollectionId") %>" method="post">

		<p>
			<label for="newPriceAlert">newPriceAlert(true or false)</label>
			<input id="newPriceAlert" name="newPriceAlert" value="">
		</p> 
		
		<p>
			<input type="submit" value="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	
	<p>
	<button type="button" name="back" onclick="history.back()">back</button>
	</p>
</body>
</html>