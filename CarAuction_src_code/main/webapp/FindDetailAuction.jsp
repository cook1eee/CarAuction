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
	<form action="findbids?currentUserID=<%= request.getParameter("currentUserID") %>&auctionid=<%= request.getParameter("auctionid") %>" method="post">
		<h1>Add a New Bid for Current Auction</h1>
		<p>
			<label for="userID">UserID</label>
			<input id="userID" name="userID" value="${fn:escapeXml(param.userID)}">
		</p>
		<p>
			<label for="bidPrice">Bid Price</label>
			<input id="bidPrice" name="bidPrice" value="${fn:escapeXml(param.bidPrice)}">
		</p>
		
		<p>
			<input type="submit" value="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Current Auction</h1>
        <table border="1">
            <c:forEach items="${auctionsAndCars}" var="pair" >
                <tr>
                
                </tr>
                    <th>AuctionID</th>
                	<td><a href="findbids?currentUserID=<%= request.getParameter("currentUserID") %>&auctionid=<c:out value="${pair.getT().getAuctionID()}"/>"><c:out value="${pair.getT().getAuctionID()}" /></a></td>
                	<th>Title</th>
                	<td><c:out value="${pair.getT().getTitle()}" /></td>
                </tr>
                
                <tr>	   
                	<th>StartTime</th>
                    <td><fmt:formatDate value="${pair.getT().getStartTime()}" pattern="yyyy-MM-dd"/></td>
                    <th>EndTime</th>
                    <td><fmt:formatDate value="${pair.getT().getEndTime()}" pattern="yyyy-MM-dd"/></td>
               </tr>
                
                <tr>     
                    <%-- <td><a href="finduser?userid=<c:out value="${pair.getT().getUser().getUserID()}"/>"><c:out value="${pair.getT().getUser().getUserID()}" /></a></td> --%>
                    <th>CarID</th>
                    <td><c:out value="${pair.getT().getCar().getCarID()}" /></td>
                     <th>UserID</th>
                    <td><c:out value="${pair.getT().getUser().getUserID()}" /></td>
               </tr>
                
                <tr> 
                	<th>Highlights</th>     
                    <td><c:out value="${pair.getT().getHighlights()}" /></td>
                    <th rowspan="6">Pictures</th>
                    <td rowspan="6"><img src="${pair.getT().getPictures()}" alt="carPicture"></img></td>
                </tr>
                
                <tr> 
                	<th>StartPrice</th>
                    <td><c:out value="${pair.getT().getMinimumPrice()}" /></td>
                </tr>
                
                <tr> 
                 	<th>CurrentPrice</th>
                    <td><c:out value="${pair.getT().getCurrentHighestPrice()}" /></td>
                 </tr>
                
                <tr> 
                	<th>AuctionStatus</th>
                    <td><c:out value="${pair.getT().getAuctionStatus().name()}" /></td>
                 </tr>
                
                
                
                <tr> 
                	<th>CustomerServiceID</th>
                    <%-- <td><a href="findcustomerservice?customerserviceid=<c:out value="${pair.getT().getCustomerService().getCustomerServiceID()}"/>"><c:out value="${pair.getT().getCustomerService().getCustomerServiceID()}" /></a></td> --%>
                	<td><c:out value="${pair.getT().getCustomerService().getCustomerServiceID()}" /></td>
                 </tr>
                
                <tr> 
                	<th>Year</th>
                	<td><c:out value="${pair.getU().getYear()}" /></td>
                	
                </tr>
                
                <tr> 
                	<th>Maker</th>
                	<td><c:out value="${pair.getU().getMaker()}" /></td>
                	<th>Model</th>
                	<td><c:out value="${pair.getU().getModel()}" /></td>
                	
                </tr>
                
                <tr> 
                	<th>Trim</th>
                	<td><c:out value="${pair.getU().getTrim()}" /></td>
                	<th>Body</th>
                	<td><c:out value="${pair.getU().getBody()}" /></td>
                	
                </tr>
                
                <tr> 
                	<th>Transmission</th>
                	<td><c:out value="${pair.getU().getTransmission()}" /></td>
                	<th>VIN</th>
                	<td><c:out value="${pair.getU().getVIN()}" /></td>
                	
                </tr>
                
                <tr> 
                	<th>State</th>
                	<td><c:out value="${pair.getU().getState()}" /></td>
                	<th>ConditionScore</th>
                	<td><c:out value="${pair.getU().getConditionScore()}" /></td>
                	
                </tr>
                
                <tr> 
                	<th>OdoMeter</th>
                	<td><c:out value="${pair.getU().getOdoMeter()}" /></td>
               		<th>Color</th>
                	<td><c:out value="${pair.getU().getColor()}" /></td>
                	
                </tr>
                
                <tr> 
               	    <th>Interior</th>
                	<td><c:out value="${pair.getU().getInterior()}" /></td>
                	<th>MMR</th>
                	<td><c:out value="${pair.getU().getMMR()}" /></td>
                </tr>
                
            </c:forEach>
       </table>
	<h1>Bid history for current Auction</h1>
        <table border="1">
            <tr>
                <th>BidID</th>
                <th>AuctionID</th>
                <th>UserID</th>
                <th>BidTime</th>
                <th>BidPrice</th>
            </tr>
            <c:forEach items="${bids}" var="bid" >
                <tr>
                    <td><c:out value="${bid.getBidID()}" /></td>
                    <td><c:out value="${bid.getAuction().getAuctionID()}" /></td>
                    <td><c:out value="${bid.getUser().getUserID()}" /></td>
                    <td><c:out value="${bid.getBidTime()}" /></td>
                    <td><c:out value="${bid.getBidPrice()}" /></td>
                </tr>
            </c:forEach>
       </table>
       <br>
       <h1>Forum for Current Auction</h1>
			<table border="1">
			    <tr>
			    
			    <th>ForumID</th>
			    <th>AuctionID</th>
			    <th>UserID</th>
			    <th>Created Time</th>
			    <th>Content</th>
			    <th>Replies</th>
			    <th>Update Question</th>
			    <th>Delete Question</th>
			
			    </tr>
			    <c:forEach items="${forums}" var="forums" >
			        <tr>
			            <td><c:out value="${forums.getForumID()}" /></td>
			            <td><c:out value="${forums.getAuction().getAuctionID()}" /></td>
			            <td><c:out value="${forums.getUser().getUserID()}" /></td>
			            <td><fmt:formatDate value="${forums.getTimeStamp()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
			            <td><c:out value="${forums.getContent()}" /></td>
			            <td><a href="auctionforumreplies?currentUserID=<%= request.getParameter("currentUserID") %>&forumid=<c:out value="${forums.getForumID()}"/>">Question Replies</a></td>
			            <td><a href="forumupdate?currentUserID=<%= request.getParameter("currentUserID") %>&forumid=<c:out value="${forums.getForumID()}"/>">Update</a></td>
			            <td><a href="forumdelete?currentUserID=<%= request.getParameter("currentUserID") %>&forumid=<c:out value="${forums.getForumID()}"/>">Delete</a></td>
			            
			        </tr>
			    </c:forEach>
			</table>
		<br>
       <a href="findauctions?currentUserID=<%= request.getParameter("currentUserID") %>&auctionid=<c:out value="${auctionsAndCars.get(0).getT().getAuctionID()}"/>"><c:out value="Back to Auctions Page" /></a>
</body>
</html>
