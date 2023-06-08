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


@WebServlet("/replyupdate")
public class ReplyUpdate extends HttpServlet {

	protected RepliesDao repliesDao;
	
	@Override
	public void init() throws ServletException {
		repliesDao = RepliesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String replyid = req.getParameter("replyid");
        if (replyid == null || replyid.trim().isEmpty()) {
            messages.put("success", "Invalid Reply ID.");
        } else {
        	try {
        		Replies reply = repliesDao.getReplyById(Integer.parseInt(replyid));
        		if(reply == null) {
        			messages.put("success", "Reply does not exist.");
        		}
        		req.setAttribute("reply", reply);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReplyUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		 // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String replyid = req.getParameter("replyid");
        if (replyid == null || replyid.trim().isEmpty()) {
            messages.put("success", "Invalid Reply ID.");
        } else {
        	try {
        		Replies reply = repliesDao.getReplyById(Integer.parseInt(replyid));
        		if(reply == null) {
        			messages.put("success", "Reply does not exist. No update to perform.");
        		} else {
        			String newReply = req.getParameter("newreply");
        			if (newReply == null || newReply.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid new reply.");
        	        } else {
        	        	reply = repliesDao.updateReply(reply, newReply);
        	        	messages.put("success", "Successfully updated " + reply.getReplyID());
        	        }
        		}
        		req.setAttribute("reply", reply);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReplyUpdate.jsp").forward(req, resp);
    }
}
