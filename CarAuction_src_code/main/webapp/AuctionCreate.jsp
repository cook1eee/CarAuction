<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Auction</title>
</head>
<body>
	<h1>Create Auction</h1>
	<form action="auctioncreate" method="post">
		<p>
			<label for="title">Title</label>
			<input id="title" name="title" value="">
		</p>
		<p>
			<label for="endTime">End Time (yyyy-MM-dd)</label>
			<input id="endTime" name="endTime" value="">
		</p>
		<p>
			<label for="carId">Car ID</label>
			<input id="carId" name="carId" value="">
		</p>
		<p>
			<label for="minimumPrice">Start Price</label>
			<input id="minimumPrice" name="minimumPrice" value="">
		</p>
		<p>
			<label for="highlights">Highlights</label>
			<input id="highlights" name="highlights" value="">
		</p>
		<p>
			<label for="pictures">Pictures</label>
			<input id="pictures" name="pictures" value="">
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
		<button type="button" name="Return to Auction Page" onclick="history.back()">back</button>
		</p>
</body>
</html>