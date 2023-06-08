<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Auctions List</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
            
            <th>AuctionID</th>
            <th>Title</th>
            <th>StartTime</th>
            <th>EndTime</th>
            <th>CarID</th>
            <th>Highlights</th>
            <th>MinimumPrice</th>
            <th>CurrentHighestPrice</th>
            <th>AuctionStatus</th>
            <th>Delete Record</th>
     
            </tr>
            <c:forEach items="${auctions}" var="auction" >
                <tr>
                    <td><a href="findbids?currentUserID=<%= request.getParameter("userID") %>&auctionid=<c:out value="${auction.getAuctionID()}"/>"><c:out value="${auction.getAuctionID()}" /></a></td>
                    <td><c:out value="${auction.getTitle()}" /></td>
                    <td><c:out value="${auction.getStartTime()}" /></td>
                    <td><c:out value="${auction.getEndTime()}" /></td>
                    <td><c:out value="${auction.getCar().getCarID()}" /></td>
                    <td><c:out value="${auction.getHighlights()}" /></td>
                    <td><c:out value="${auction.getMinimumPrice()}" /></td>
                    <td><c:out value="${auction.getCurrentHighestPrice()}" /></td>
                    <td><c:out value="${auction.getAuctionStatus()}" /></td>
                    <td><a href="auctiondelete?auctionID=<c:out value="${auction.getAuctionID()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
       <p>
		<a href="auctioncreate?userID=<c:out value="${user.getUserID()}"/>">add a new Auction</a>
		</p>
		<p>
		<button type="button" name="back" onclick="history.back()">back</button>
		</p>
</body>
</html>
