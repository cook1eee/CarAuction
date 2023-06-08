<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a User</title>
</head>
<body>
	<h1>Create User</h1>

	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	
	<p>
		<a href="userloginpage">User Login</a>
	</p>
	
	<form action="usercreate" method="post">
		<table border="1">
            <tr>
             <th colspan="2" >Basic Information</th>
             <th colspan="2">Address</th>
            </tr>
			<tr>
			    <td>
				<label for="firstname">FirstName</label>
				 </td>
				 <td>
				<input id="firstname" name="firstname" value="">
				</td>
				 <td>
				<label for="address1">Address1</label>
				</td>
				 <td>
				<input id="address1" name="address1" value="">
				</td>
			</tr>
			<tr>
			    <td>
				<label for="lastname">LastName</label>
				 </td>
				 <td>
				<input id="lastname" name="lastname" value="">
				</td>
				 <td>
				<label for="address2">Address2</label>
				</td>
				 <td>
				<input id="address2" name="address2" value="">
				</td>
			</tr>
			<tr>
			    <td>
				<label for="phone">Phone</label>
				 </td>
				 <td>
				<input id="phone" name="phone" value="">
				</td>
				 <td>
				<label for="city">City</label>
				</td>
				 <td>
				<input id="city" name="city" value="">
				</td>
			</tr>
			<tr>
			    <td>
				<label for="email">Email</label>
				 </td>
				 <td>
				<input id="email" name="email" value="">
				</td>
				 <td>
				<label for="state">State</label>
				</td>
				 <td>
				<input id="state" name="state" value="">
				</td>
			</tr>
			<tr>
			    <td>
				<label for="password">Password</label>
				 </td>
				 <td>
				<input id="password" name="password" value="">
				</td>
				 <td>
				<label for="zipcode">Zipcode</label>
				</td>
				 <td>
				<input id="zipcode" name="zipcode" value="">
				</td>
			</tr>
			<tr>
			    <td>
				<label for="password2">Re-Enter Password</label>
				 </td>
				 <td>
				<input id="password2" name="password2" value="">
				</td>
				 <td>
				<label for="country">Country</label>
				</td>
				 <td>
				<input id="country" name="country" value="">
				</td>
			</tr>
			</table>
		<p>
			<input type="submit" value="submit">
		</p>
		
		<p>
        <a href="WebsiteHomePage.jsp">Back to Website HomePage</a>
        </p>
	</form>
	
</body>
</html>