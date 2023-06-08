<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add CreditCard</title>
</head>
<body>
	<h1>Add a New CreditCard</h1>
	
	<form action="creditcardcreate?userID=<%= request.getParameter("userID") %>" method="post">
		<table border="1">
		   
			<tr>
			    <td>
				<label for="CardNumber">CardNumber</label>
				 </td>
				 <td>
				<input id="CardNumber" name="CardNumber" value="">
				</td>				 
			</tr>
			<tr>
			    <td>
				<label for="ExpirationMonth">ExpirationMonth</label>
				 </td>
				 <td>
				<input id="ExpirationMonth" name="ExpirationMonth" value="">
				</td>
			</tr>
			<tr>
			    <td>
				<label for="ExpirationYear">ExpirationYear</label>
				 </td>
				 <td>
				<input id="ExpirationYear" name="ExpirationYear" value="">
				</td>
			</tr>
			<tr>
			    <td>
				<label for="NameOnCard">NameOnCard</label>
				 </td>
				 <td>
				<input id="NameOnCard" name="NameOnCard" value="">
				</td>
			</tr>
			<tr>
			    <td>
				<label for="ZipCode">ZipCode</label>
				 </td>
				 <td>
				<input id="ZipCode" name="ZipCode" value="">
				</td>
			</tr>
			
			</table>
		<p>
			<input type="submit" value="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	
	<p>
	<a href="usercreditcards?userID=<%= request.getParameter("userID") %>">CreditCards List</a>
	</p>
</body>
</html>