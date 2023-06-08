<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Activities</title>
</head>
<body>
	<form action="finduseractivities" method="post">
	<h1>Search User Activities for User</h1>
		<p>
			<label for="userID">User ID</label>
			<input id="userID" name="userID" value="${fn:escapeXml(param.userID)}">
		</p>
		
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br>
	
	<h1>${messages.title}</h1>
	<table border="1">
	    <tr>
	    
	    <th>ActivityID</th>
	    <th>UserID</th>
	    <th>ActivityType</th>
	    <th>Time</th>
	   
	
	    </tr>
	    <c:forEach items="${userActivities}" var="userActivitiy" >
	        <tr>
	            <td><c:out value="${userActivitiy.getActivityID()}" /></td>
	            <td><c:out value="${userActivitiy.getUser().getUserID()}" /></td>
	            <td><c:out value="${userActivitiy.getActivityType().name()}" /></td>
	            <td><c:out value="${userActivitiy.getTimeStamp()}" /></td>
	            
	        </tr>
	    </c:forEach>
	</table>
	<br>
	
	<div><a href="ShowCSHomePage.jsp">Back to Customer Home Page</a></div>
       
	
</body>
</html>