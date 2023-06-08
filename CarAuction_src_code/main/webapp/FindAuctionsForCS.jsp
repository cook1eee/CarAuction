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
	<div id="showcshomepage"><a href="showcshomepage">Back To Customer Service HomePage</a></div>
	<form action="findauctionsforcs" method="post">
		<h1>Search for auctions by customerServiceID</h1>
		<p>
			<label for="customerServiceID">CustomerServiceID</label>
			<input id="customerServiceID" name="customerServiceID" value="${fn:escapeXml(param.firstname)}">
		</p>
		<p>
			<input type="submit" value="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<!-- <div id="userCreate"><a href="usercreate">Create BlogUser</a></div> -->
	<br/>
	<h1>Matching Auctions</h1>
        <table border="1">
            <tr>
                <th>AuctionID</th>
                <th>Title</th>
                <th style="width:30px">StartTime</th>
                <th>EndTime</th>
                <th>car</th>
                <th>user</th>
                <th>Highlights</th>
                <th>Pictures</th>
                <th>MinimumPrice</th>
                <th>CurrentHighestPrice</th>
                <th>AuctionStatus</th>
                <th>customerService</th>
                <th>PriceChangeAlert</th>
                <th>Delete Auction</th>
            </tr>
            <c:forEach items="${auctions}" var="auction" >
                <tr>
                    <td><c:out value="${auction.getAuctionID()}" /></td>
                    <td><c:out value="${auction.getTitle()}" /></td>
                    <td style="width:30px"><fmt:formatDate value="${auction.getStartTime()}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${auction.getEndTime()}" pattern="yyyy-MM-dd"/></td>
            		<td><c:out value="${auction.getCar().getCarID()}" /></td>
                <%--     <td><a href="findCar?carID=<c:out value="${car.getCarID()}"/>"><c:out value="${auction.getCarID()}" /></a></td> --%>
                 	<td><c:out value="${auction.getUser().getFirstName().concat(' ').concat(auction.getUser().getLastName())}" /></td>
              	<%-- 	<td><a href="findUsers?carID=<c:out value="${car.getCarID()}"/>"><c:out value="${auction.getCarID()}" /></a></td>  from user --%>
                    <td><c:out value="${auction.getHighlights()}" /></td>
                    <td><img src="${auction.getPictures()}" width="220" height="220"/></td>
                    <td><c:out value="${auction.getMinimumPrice()}" /></td>
                    <td><c:out value="${auction.getCurrentHighestPrice()}" /></td>
                    <td><c:out value="${auction.getAuctionStatus()}" /></td>
                    <td><c:out value="${auction.getCustomerService().getFirstName().concat(' ').concat(auction.getCustomerService().getLastName())}" /></td>
                    <td><c:out value="${auction.getPriceChangeAlert()}" /></td>
                    <td><a href="auctiondelete?auctionID=<c:out value="${auction.getAuctionID()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
