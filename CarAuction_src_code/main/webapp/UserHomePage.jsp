<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User's HomePage</title>
</head>
<body>
	
	<h1>Your Register Information</h1>
        <table border="1">
        <thead>
             <tr>
             <th colspan="2" >Basic Information</th>
             <th colspan="2">Address</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${users}" var="user" >
            
            <tr>
            <th>FirstName</th><td align="center" ><c:out value="${user.getFirstName()}" /></td>   	 
            <th>Address1</th><td align="center"><c:out value="${user.getAddress1()}" /></td>	
            </tr>				
			<tr>
			<th>LastName</th><td align="center"><c:out value="${user.getLastName()}" /></td>
			<th>Address2</th><td align="center"><c:out value="${user.getAddress2()}" /></td>
			</tr>				
			<tr>	
			<th>Phone</th><td align="center"><c:out value="${user.getPhone()}" /></td>
			<th>City</th><td align="center"><c:out value="${user.getCity()}" /></td>
			</tr>
		    
		    <tr>
		    <th>Email</th><td align="center"><c:out value="${user.getEmail()}" /></td>
		    <th>State</th><td align="center"><c:out value="${user.getState()}" /></td>
		    </tr>
			
		    <tr>
		    <tr><th>SignUp</th><td align="center"><c:out value="${user.getSignUp()}" /></td>
		    <th>Zipcode</th><td align="center"><c:out value="${user.getZipcode()}" /></td>
		    </tr>
				           
            </c:forEach>
		 </tbody>
       </table>
         <ul>
     		<c:forEach items="${users}" var="user" >
     		
     		<li><a href="findauctions?currentUserID=<c:out value="${user.getUserID()}"/>">&#128176; Auction Search </a></li>     
     		<br>
     		
     		<li><a href="userbids?userID=<c:out value="${user.getUserID()}"/>">&#128587; Your Bids List</a></li>  
			<br>   
     		
		    <li><a href="usercreditcards?userID=<c:out value="${user.getUserID()}"/>">&#128179; CreditCards List</a>
		    &nbsp;&nbsp;&nbsp;
		    <a href="creditcardcreate?userID=<c:out value="${user.getUserID()}"/>">&#128179; Add CreditCard</a></li>
			<br>
			
			<li><a href="usercars?userID=<c:out value="${user.getUserID()}"/>">&#128663; Cars List</a>
			&nbsp;&nbsp;&nbsp;
			<a href="carcreate?userID=<c:out value="${user.getUserID()}"/>">&#128663; Add Car</a></li>
			<br>
			
			<li><a href="userauctions?userID=<c:out value="${user.getUserID()}"/>">&#128176; Auctions List</a>
			&nbsp;&nbsp;&nbsp;
			<a href="auctioncreate?userID=<c:out value="${user.getUserID()}"/>">&#128176; Add Auction</a></li>
			<br>
			
			
            <li><a href="usercollections?userID=<c:out value="${user.getUserID()}"/>">&#128212; Collections List</a>
            &nbsp;&nbsp;&nbsp;
            <a href="collectioncreate?userID=<c:out value="${user.getUserID()}"/>">&#128212; Add Collection</a></li>
            <br> 
            
         	<li><a href="userpasswordupdate?userID=<c:out value="${user.getUserID()}"/>">&#128273; Update Password </a></li>         	
         	<br> 
         	
         	 	
         	</c:forEach>   	
       	</ul>
       	
       	<p>
        <a href="WebsiteHomePage.jsp">Back to Website Home Page</a>
        </p>
	    
</body>
</html>
