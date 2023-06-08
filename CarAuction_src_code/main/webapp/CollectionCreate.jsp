<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Collection</title>
</head>
<body>
	<h1>Create a New Collection </h1>

	<form action="collectioncreate?userID=<%= request.getParameter("userID") %>" method="post">
		<table border="1">

			<tr>
			    <td>
				<label for="AuctionsID">AuctionsID</label>
				 </td>
				 <td>
				<input id="AuctionsID" name="AuctionsID" value="">
				</td>				 
			</tr>
			<tr>
			    <td>
				<label for="PriceChangeAlert">PriceChangeAlert</label>
				 </td>
				 <td>
				<input id="PriceChangeAlert" name="PriceChangeAlert" value="">
				</td>
			</tr>
			<tr>
			    <td>
				<label for="StatusChangeAlert">StatusChangeAlert</label>
				 </td>
				 <td>
				<input id="StatusChangeAlert" name="StatusChangeAlert" value="">
				</td>
			</tr>
				
			</table>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>