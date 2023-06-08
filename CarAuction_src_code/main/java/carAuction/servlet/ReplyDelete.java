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

@WebServlet("/replydelete")
public class ReplyDelete extends HttpServlet {
	
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
        
        messages.put("title", "Delete Reply");
        req.getRequestDispatcher("/ReplyDelete.jsp").forward(req, resp);
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
            messages.put("disableSubmit", "true");
        } else {
        	Replies reply = new Replies(Integer.parseInt(replyid));
        	try {
        		reply = repliesDao.delete(reply);
        		if(reply == null) {
        			messages.put("title", "Successfully deleted Reply " + replyid);
        			messages.put("disableSubmit", "true");
        		} else {
        	        messages.put("title", "Failed to delete Reply " + replyid);
        	        messages.put("disableSubmit", "false");
        		}
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReplyDelete.jsp").forward(req, resp);
    }
}
