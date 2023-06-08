<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Service HomePage</title>
</head>
<body>
	<h1>Customer Service HomePage</h1>
	<form action="showcshomepage" method="get">
		<p>
			<div id="findUsers"><a href="findusers">Find Users</a></div>
			<div id="findAuctionsForCS"><a href="findauctionsforcs">Find Auctions</a></div>
			<div id="findChatHistories"><a href="findchathistories">Find ChatHistory</a></div>
			<div id="generateReports"><a href="generatereports">Generate Reports</a></div>
		</p>
	</form>
	<br/><br/>
	
</body>
</html>