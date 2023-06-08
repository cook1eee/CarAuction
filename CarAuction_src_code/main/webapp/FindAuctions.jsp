<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Auctions</title>
</head>
<body>
	<table border="1" style="border:none;">
        <tr>
            <th style="text-align:left;vertical-align:top;border:none;padding-right:50px;">
	            <form action="findauctions_id?currentUserID=<%= request.getParameter("currentUserID") %>" method="post">
					<h3>Search for an Auction by Auction ID</h3>
						<p>
							<label for="auctionID">Auction ID</label>
							<input id="auctionID" name="auctionID" value="${fn:escapeXml(param.auctionID)}">
						</p>
						<p>
							<input type="submit" value="submit">
						</p>
				</form>
			</th>
            <th style="text-align:left;vertical-align:top;border:none;padding-right:50px;">
	            <form action="findauctions_makerandmodel?currentUserID=<%= request.getParameter("currentUserID") %>" method="post">
					<h3>Search for Auctions by Maker and Model</h3>
					<p>
						<label for="maker">Maker</label>
						<input id="maker" name="maker" value="${fn:escapeXml(param.maker)}">
					</p>
					<p>
						<label for="model">Model</label>
						<input id="model" name="model" value="${fn:escapeXml(param.model)}">
					</p>
					<p>
						<input type="submit" value="submit">
					</p>
				</form>
			</th>
            <th style="text-align:left;vertical-align:top;border:none;padding-right:50px;">
            	<form action="findauctions_maxbidprice?currentUserID=<%= request.getParameter("currentUserID") %>" method="post">
					<h3>Search for Auctions Between Bid Price Range</h3>
					<p>
						<label for="lower">Bid Price Lower Limit</label>
						<input id="lower" name="lower" value="${fn:escapeXml(param.lower)}">
					</p>
					<p>
						<label for="upper">Bid Price Upper Limit</label>
						<input id="upper" name="upper" value="${fn:escapeXml(param.upper)}">
					</p>
					<p>
						<input type="submit" name="asc" value="From Low To High" />
						<input type="submit" name="desc" value="From High To Low" />
					</p>
				</form>
			</th>
        </tr>
    </table>

	<form action="special_search?currentUserID=<%= request.getParameter("currentUserID") %>" method="post">
		<h3>Specialized Recommendation</h3>
		<p>
			<input type="submit" name="button1" value="Successful/Closed Auctions" style="margin-right:35px"/>
			<input type="submit" name="button2" value="Auctions about Current Top 20 Best Sell Cars" style="margin-right:35px" />
			<input type="submit" name="button3" value="Auctions about Top 10 Most Collected Cars" style="margin-right:35px" />
		</p>
	</form>
	</br>
	<span id="successMessage"><b>${messages.success}</b></span>

	<br/>
	<div id="auctionCreate"><a href="auctioncreate">Create Auction</a></div>
	<div id="carCreate"><a href="carcreate">Create Car</a></div>
	<div><a href="WebsiteHomePage.jsp">Back to Home Page</a></div>
	</br>
	<form action="userloginpage?userID=<%= request.getParameter("currentUserID") %>" method="post">
		<input type="submit" name="userloginpage" value="Return to Current User Page" style="margin-right:35px"/>
	</form>
	<h1>Auction Results</h1>
        <table border="1">
            <tr>
                <th>AuctionID</th>
                <th>Title</th>
                <th>StartTime</th>
                <th>EndTime</th>
                <th>CarID</th>
                <th>UserID</th>
                <th>Highlights</th>
                <th>Pictures</th>
                <th>StartPrice</th>
                <th>CurrentPrice</th>
                <th>AuctionStatus</th>
                <th>CustomerServiceID</th>
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
            </tr>
            <c:forEach items="${auctionsAndCars}" var="pair" >
                <tr>
                	<td><a href="findbids?currentUserID=<%= request.getParameter("currentUserID") %>&auctionid=<c:out value="${pair.getT().getAuctionID()}"/>"><c:out value="${pair.getT().getAuctionID()}" /></a></td>
                    <td><c:out value="${pair.getT().getTitle()}" /></td>
                    <td><fmt:formatDate value="${pair.getT().getStartTime()}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${pair.getT().getEndTime()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${pair.getT().getCar().getCarID()}" /></td>
                    <td><c:out value="${pair.getT().getUser().getUserID()}" /></td>
                    <td><c:out value="${pair.getT().getHighlights()}" /></td>
                    <td><img src="${pair.getT().getPictures()}" alt="carPicture"></img></td>
                    <td><c:out value="${pair.getT().getMinimumPrice()}" /></td>
                    <td><c:out value="${pair.getT().getCurrentHighestPrice()}" /></td>
                    <td><c:out value="${pair.getT().getAuctionStatus().name()}" /></td>
                    <td><c:out value="${pair.getT().getCustomerService().getCustomerServiceID()}" /></td>
                	<td><c:out value="${pair.getU().getYear()}" /></td>
                	<td><c:out value="${pair.getU().getMaker()}" /></td>
                	<td><c:out value="${pair.getU().getModel()}" /></td>
                	<td><c:out value="${pair.getU().getTrim()}" /></td>
                	<td><c:out value="${pair.getU().getBody()}" /></td>
                	<td><c:out value="${pair.getU().getTransmission()}" /></td>
                	<td><c:out value="${pair.getU().getVIN()}" /></td>
                	<td><c:out value="${pair.getU().getState()}" /></td>
                	<td><c:out value="${pair.getU().getConditionScore()}" /></td>
                	<td><c:out value="${pair.getU().getOdoMeter()}" /></td>
                	<td><c:out value="${pair.getU().getColor()}" /></td>
                	<td><c:out value="${pair.getU().getInterior()}" /></td>
                	<td><c:out value="${pair.getU().getMMR()}" /></td>
                </tr>
            </c:forEach>
       </table>
       </br>
       
</body>
</html>
