<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update NameOnCard</title>
</head>
<body>
	<h1>Enter Your New Name On Card Below</h1>
	
	<form action="creditcardupdate?cardNumber=<%= request.getParameter("cardNumber") %>" method="post">
		<%-- <p>
			<label for="cardNumber">cardNumber</label>
			<input id="cardNumber" name="cardNumber" value="${fn:escapeXml(param.cardNumber)}">
		</p>  --%>
		<p>
			<label for="newnameoncard">New Name On Card</label>
			<input id="newnameoncard" name="newnameoncard" value="">
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