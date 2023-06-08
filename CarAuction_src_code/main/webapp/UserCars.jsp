<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cars List</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
            
            <th>CarID</th>
            <th>Year</th>
            <th>Maker</th>
            <th>Model</th>
            <th>Trim</th>
            <th>Body</th>
            <th>Transmission</th>
            <th>VIN</th>
            <th>State</th>
            <th>ConditionScore</th>
            <th>OdoMeter</th>
            <th>Color</th>
            <th>Interior</th>
            <th>MMR</th>
            <th>Delete Record</th>
     
            </tr>
            <c:forEach items="${cars}" var="car" >
                <tr>
                    <td><c:out value="${car.getCarID()}" /></td>
                    <td><c:out value="${car.getYear()}" /></td>
                    <td><c:out value="${car.getMaker()}" /></td>
                    <td><c:out value="${car.getModel()}" /></td>
                    <td><c:out value="${car.getTrim()}" /></td>
                    <td><c:out value="${car.getBody()}" /></td>
                    <td><c:out value="${car.getTransmission()}" /></td>
                    <td><c:out value="${car.getVIN()}" /></td>
                    <td><c:out value="${car.getState()}" /></td>
                    <td><c:out value="${car.getConditionScore()}" /></td>
                    <td><c:out value="${car.getOdoMeter()}" /></td>
                    <td><c:out value="${car.getColor()}" /></td>
                    <td><c:out value="${car.getInterior()}" /></td>
                    <td><c:out value="${car.getMMR()}" /></td>
                    <td><a href="cardelete?carID=<c:out value="${car.getCarID()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
       <p>
		<a href="carcreate?userID=<c:out value="${user.getUserID()}"/>">add a new car</a>
		</p>
		<p>
		<button type="button" name="back" onclick="history.back()">back</button>
		</p>
</body>
</html>
