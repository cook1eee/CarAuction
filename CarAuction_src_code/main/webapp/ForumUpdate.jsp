<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Update Forum</h1>
	<form action="forumupdate" method="post">
		<p>
			<label for="forumid">Reply ID</label>
			<input id="forumid" name="forumid" value="${fn:escapeXml(param.forumid)}">
		</p>
		<p>
			<label for="newforum">New Question:</label>
			<input id="newforum" name="newforum" value="">
		</p>
	
		<p>
			<input type="submit" value="submit">
		</p>
	</form>
	<br/><br/>
	
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>