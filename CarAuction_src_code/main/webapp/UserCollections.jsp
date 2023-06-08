<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Collections List</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
            
            <th>CollectionID</th>
            <th>AuctionID</th>
            <th>PriceChangeAlert</th>
            <th>StatusChangeAlert</th>
            <th>Update PriceChangeAlert</th>
            <th>Delete Record</th>
     
            </tr>
            <c:forEach items="${collections}" var="collection" >
                <tr>
                    
                    <td><c:out value="${collection.getCollectionId()}" /></td>
                    <td><c:out value="${collection.getauction().getAuctionID()}" /></td>
                    <td><c:out value="${collection.getPriceChangeAlert()}" /></td>
                    <td><c:out value="${collection.getStatusChangeAlert()}" /></td>
                    <td><a href="collectionupdate?collectionID=<c:out value="${collection.getCollectionId()}"/>">Update</a></td>
				    <td><a href="collectiondelete?collectionID=<c:out value="${collection.getCollectionId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
       <p>
		<a href="collectioncreate?userID=<c:out value="${user.getUserID()}"/>">add a new collection</a>
		</p>
		<p>
		<button type="button" name="back" onclick="history.back()">back</button>
		</p>
</body>
</html>
