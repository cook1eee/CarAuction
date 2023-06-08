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
	<form action="forumsforauction" method="post">
		<h1>Search an Auction</h1>
		<p>
			<label for="auctionid">Auction ID</label>
			<input id="auctionid" name="auctionid" value="${fn:escapeXml(param.auctionid)}">
		</p>
		
		<p>
			<input type="submit" value="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br>
	
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
	
	<div><a href="FindForums.jsp">Back to Forums Page</a></div>
	
	
</body>
</html>