<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<div id="showcshomepage"><a href="showcshomepage">Back To Customer Service HomePage</a></div>
	<form action="findusers" method="post">
		<h1>Search for a User by FirstName</h1>
		<p>
			<label for="firstname">FirstName</label>
			<input id="firstname" name="firstname" value="${fn:escapeXml(param.firstname)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<br/>
	<h1>Matching BlogUsers</h1>
        <table border="1">
            <tr>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Address1</th>
                <th>Address2</th>
                <th>City</th>
                <th>State</th>
                <th>Zipcode</th>
                <th>Country</th>
                <th>Phone</th>
                <th>Email</th>
                <th>password</th>
                <th>SignUp</th>
                <th>Delete</th>
            </tr>
            <!-- pass in count to show limited number of user and create nextPage button -->
            <c:forEach items="${users}" var="user" >
                <tr>
                    <td><c:out value="${user.getFirstName()}" /></td>
                    <td><c:out value="${user.getLastName()}" /></td>
                    <td><c:out value="${user.getAddress1()}" /></td>
                    <td><c:out value="${user.getAddress2()}" /></td>
                    <td><c:out value="${user.getCity()}" /></td>
                    <td><c:out value="${user.getState()}" /></td>
                    <td><c:out value="${user.getZipcode()}" /></td>
                    <td><c:out value="${user.getCountry()}" /></td>
                    <td><c:out value="${user.getPhone()}" /></td>
                    <td><c:out value="${user.getEmail()}" /></td>
                     <td><c:out value="${user.getpassword()}" /></td>
                    <td><fmt:formatDate value="${user.getSignUp()}" pattern="yyyy-MM-dd"/></td>
                    <td><a href="userdelete?email=<c:out value="${user.getEmail()}"/>">Delete</a></td>
                <%--     <td><a href="useractivities?userID=<c:out value="${user.getUserID()}"/>">UserActivities</a></td> --%>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
