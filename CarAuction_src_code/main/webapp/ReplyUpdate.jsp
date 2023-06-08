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
<h1>Update Reply</h1>
	<form action="replyupdate" method="post">
		<p>
			<label for="replyid">Reply ID</label>
			<input id="replyid" name="replyid" value="${fn:escapeXml(param.replyid)}">
		</p>
		<p>
			<label for="newreply">New Reply:</label>
			<input id="newreply" name="newreply" value="">
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