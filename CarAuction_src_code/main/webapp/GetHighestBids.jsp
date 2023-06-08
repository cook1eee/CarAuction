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
	<form action="gethighestbids" method="post">
		<h1>Get the highest Bid and bidder for all auctions</h1>
		<%-- <p>
			<label for="auctionID">AuctionID</label>
			<input id="auctionID" name="auctionID" value="${fn:escapeXml(param.auctionID)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p> --%>
	</form>
	<br/>
	<!-- <div id="userCreate"><a href="usercreate">Create BlogUser</a></div> -->
	<br/>
	<h1>Matching Auctions</h1>
        <table border="1">
            <tr>
                <th>AuctionID</th>
                <th>Title</th>
                <th>BidID</th>
                <th>HighestBidPrice</th>
                <th>HighestBidder</th>
            </tr>
            <c:forEach items="${rows}" var="row" >
                <tr>
                    <td><c:out value='${row.get("AuctionID")}' /></td>
                    <td><c:out value='${row.get("Title")}' /></td>
            		<td><c:out value='${row.get("BidID")}' /></td>
                <%--     <td><a href="findCar?carID=<c:out value="${car.getCarID()}"/>"><c:out value="${auction.getCarID()}" /></a></td> --%>
              	<%-- 	<td><a href="findUsers?carID=<c:out value="${car.getCarID()}"/>"><c:out value="${auction.getCarID()}" /></a></td>  from user --%>
                    <td><c:out value='${row.get("HighestBidPrice")}' /></td>
                	<td><c:out value='${row.get("Bidder")}' /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
