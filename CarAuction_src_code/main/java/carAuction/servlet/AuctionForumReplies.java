package carAuction.servlet;


import carAuction.dal.*;
import carAuction.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/auctionforumreplies")
public class AuctionForumReplies extends HttpServlet {
	protected RepliesDao repliesDao;
	protected ForumsDao forumsDao;
	
	public void init() throws ServletException {
		repliesDao = RepliesDao.getInstance();
		forumsDao = ForumsDao.getInstance();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        List<Replies> replies = new ArrayList<Replies>();
        
		// Retrieve BlogComments depending on valid PostId or UserName.
        String forumid = req.getParameter("forumid");
        
        try {
	        if (forumid != null && !forumid.trim().isEmpty()) {
	        	Forums forum = forumsDao.getForumById(Integer.parseInt(forumid));
	        	replies = repliesDao.getReplyForForum(forum);
	        	messages.put("title", "Replies for ForumID " + forumid);
	        } else {
	        	messages.put("title", "Invalid forum ID.");
	        }
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.setAttribute("replies", replies);
        req.getRequestDispatcher("/AuctionForumReplies.jsp").forward(req, resp);
	}
}
