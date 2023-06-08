<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="1" style="border:none;">
        <tr>
            <th style="text-align:left;vertical-align:top;border:none;padding-right:80px;">
	            <form action="findforumsbyid" method="post">
					<h3>Search for an Forum by ID</h3>
						<p>
							<label for="forumid">Forum ID</label>
							<input id="forumid" name="forumid" value="${fn:escapeXml(param.forumid)}">
						</p>
						<p>
							<input type="submit">
							
						</p>
				</form>
			</th>
            <th style="text-align:left;vertical-align:top;border:none;padding-right:80px;">
	            <form action="findforumsbyuser" method="post">
					<h3>Search for Forums posted by User</h3>
					<p>
						<label for="userid">User ID</label>
						<input id="userid" name="userid" value="${fn:escapeXml(param.userid)}">
					</p>
					<p>
						<input type="submit">
						
					</p>
				</form>
			</th>
            <th style="text-align:left;vertical-align:top;border:none;padding-right:80px;">
            	<form action="findforumsbyauction" method="post">
					<h3>Search for Forums For Auction: </h3>
					<p>
						<label for="auctionid">Auction ID</label>
						<input id="auctionid" name="auctionid" value="${fn:escapeXml(param.auctionid)}">
					</p>
					<p>
						<input type="submit">
						
					</p>
				</form>
			</th>
        </tr>
    </table>
    
    <br/><br/><br/>
	<span id="successMessage"><b>${messages.success}</b></span>
    
    <h1>${messages.title}</h1>
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
	            <td><a href="auctionforumreplies?forumid=<c:out value="${forums.getForumID()}"/>">Question Replies</a></td>
	            <td><a href="forumupdate?forumid=<c:out value="${forums.getForumID()}"/>">Update</a></td>
	            <td><a href="forumdelete?forumid=<c:out value="${forums.getForumID()}"/>">Delete</a></td>
	            
	        </tr>
	    </c:forEach>
	</table>
	<br>
	
	<div><a href="ShowCSHomePage.jsp">Back to Customer Home Page</a></div>
	
</body>
</html>