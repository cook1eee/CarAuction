<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CreditCards List</title>
</head>
<body>
	<h1>${messages.title}</h1>
	
	
        <table border="1">
            <tr>
            
             <th>CardNumber</th>
             <th>ExpirationMonth</th>
             <th>ExpirationYear</th>
             <th>NameOnCard</th>
             <th>ZipCode</th>
             <th>Remove Card</th>
             <th>Update Card</th>
            
            </tr>
            <c:forEach items="${creditCards}" var="creditCard" >
                <tr>
                    <td><c:out value="${creditCard.getCardNumber()}" /></td>
                    <td><c:out value="${creditCard.getExpirationMonth()}" /></td>
                    <td><c:out value="${creditCard.getExpirationYear()}" /></td>
                    <td><c:out value="${creditCard.getNameOnCard()}" /></td>
                    <td><c:out value="${creditCard.getZipCode()}" /></td>
 					<td><a href="creditcarddelete?cardNumber=<c:out value="${creditCard.getCardNumber()}"/>">Delete</a></td>
 					<td><a href="creditcardupdate?cardNumber=<c:out value="${creditCard.getCardNumber()}"/>">Update</a></td>

                </tr>
            </c:forEach>
       </table>
        <p>
		<a href="creditcardcreate?userID=<c:out value="${user.getUserID()}"/>">add a new card</a>
		</p>
		<p>
		<button type="button" name="back" onclick="history.back()">back</button>
		</p>
</body>
</html>
