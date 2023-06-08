<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Please Login Your Account</title>
</head>

<body>
<form action="userloginpage" method="post">
		<h1>Welcome to Car Auction</h1>
		
		<p>
		<br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		<br/>
		</p>
		
		<p>
		<a href="usercreate">Create User</a>
		</p>
		<p>
			<label for="email">Email</label>
			<input id="email" name="email" value="${fn:escapeXml(param.email)}">
		</p>
		<p>
			<label for="password">Password</label>
			<input id="password" name="password" value="${fn:escapeXml(param.password)}">
		</p>
		<p>
			<input type="submit" value="submit">
			
		</p>
	</form>
	
</body>

</html>