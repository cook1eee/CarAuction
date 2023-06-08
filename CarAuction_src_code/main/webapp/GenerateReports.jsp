<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report Generate HomePage</title>
</head>
<body>
	<h1>Report Generate HomePage</h1>
	<form action="generatereports" method="get">
		<p>
			<div id="gethighestbids"><a href="gethighestbids">Get the highest Bid and bidder for all auctions</a></div>
			<div id="getmonthlysales"><a href="getmonthlysales">Get Monthly Sales</a></div>
		
		</p>
	</form>
	<br/><br/>
	
</body>
</html>