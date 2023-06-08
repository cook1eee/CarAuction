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
<h1>${messages.title}</h1>
	<table border="1">
	    <tr>
	    
	    <th>ReplyID</th>
	    <th>ForumID</th>
	    <th>UserID</th>
	    <th>Created Time</th>
	    <th>Content</th>
	    <th>Update Replies</th>
	    <th>Delete Replies</th>
	
	    </tr>
	    <c:forEach items="${replies}" var="replies" >
	        <tr>
	            <td><c:out value="${replies.getReplyID()}" /></td>
	            <td><c:out value="${replies.getForum().getForumID()}" /></td>
	            <td><c:out value="${replies.getUser().getUserID()}" /></td>
	            <td><fmt:formatDate value="${replies.getTimeStamp()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
	            <td><c:out value="${replies.getContent()}" /></td>
	            <td><a href="replyupdate?replyid=<c:out value="${replies.getReplyID()}"/>">Update</a></td>
	            <td><a href="replydelete?replyid=<c:out value="${replies.getReplyID()}"/>">Delete</a></td>
	            
	        </tr>
	    </c:forEach>
	</table>
	<br>
	
	
</body>
</html>