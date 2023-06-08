<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Auction</title>
</head>
<body>
	<form action="getmonthlysales" method="post">
		<div id="showcshomepage"><a href="showcshomepage">Back To Customer Service HomePage</a></div>
		<h1>Get monthly total sales</h1>
	</form>
	<br/>
	<!-- <div id="userCreate"><a href="usercreate">Create BlogUser</a></div> -->
	<br/>
	<h1>Monthly total sales</h1>
        <table border="1">
            <tr>
                <th>Year</th>
                <th>Month</th>
                <th>Total sales</th>
            </tr>
            <c:forEach items="${rows}" var="row" >
                <tr>
                    <td><c:out value='${row.get("year")}' /></td>
                    <td><c:out value='${row.get("month")}' /></td>
            		<td><c:out value='${row.get("total_sales")}' /></td>
            
                </tr>
            </c:forEach>
       </table>
</body>
</html>
