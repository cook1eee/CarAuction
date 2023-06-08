<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Car</title>
</head>
<body>
	<h1>Create Car</h1>
	<form action="carcreate" method="post">
		<p>
			<label for="userID">User ID</label>
			<input id="userID" name="userID" value="">
		</p>
		<p>
			<label for="year">Year</label>
			<input id="year" name="year" value="">
		</p>
		<p>
			<label for="maker">Maker</label>
			<input id="maker" name="maker" value="">
		</p>
		<p>
			<label for="model">Model</label>
			<input id="model" name="model" value="">
		</p>
		<p>
			<label for="trim">Trim</label>
			<input id="trim" name="trim" value="">
		</p>
		<p>
			<label for="body">Body</label>
			<input id="body" name="body" value="">
		</p>
		<p>
			<label for="transmission">Transmission</label>
			<input id="transmission" name="transmission" value="">
		</p>
		<p>
			<label for="vin">VIN</label>
			<input id="vin" name="vin" value="">
		</p>
		<p>
			<label for="state">State</label>
			<input id="state" name="state" value="">
		</p>
		<p>
			<label for="conditionScore">ConditionScore</label>
			<input id="conditionScore" name="conditionScore" value="">
		</p>
		<p>
			<label for="odoMeter">OdoMeter</label>
			<input id="odoMeter" name="odoMeter" value="">
		</p>
		<p>
			<label for="color">Color</label>
			<input id="color" name="color" value="">
		</p>
		<p>
			<label for="interior">Interior</label>
			<input id="interior" name="interior" value="">
		</p>
		<p>
			<label for="mmr">MMR</label>
			<input id="mmr" name="mmr" value="">
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