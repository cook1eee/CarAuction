package carAuction.servlet;

import carAuction.dal.*;
import carAuction.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/collectiondelete")
public class CollectionDelete extends HttpServlet {
	
	protected CollectionsDao collectionsDao;
	
	@Override
	public void init() throws ServletException {
		collectionsDao = CollectionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        
        messages.put("title", "Delete a Collection");        
        req.getRequestDispatcher("/CollectionDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

       
        String collectionID = req.getParameter("collectionID");
        if (collectionID == null || collectionID.trim().isEmpty()) {
            messages.put("title", "Invalid collectionID");
            messages.put("disableSubmit", "true");
        } else {
        	
        	Collections collection = new Collections(Integer.parseInt(collectionID));
	        try {
	        	collection = collectionsDao.delete(collection);
	        	// Update the message.
		        if (collection == null) {
		            messages.put("title", "Successfully deleted Collection with ID " + collectionID);
		            messages.put("disableSubmit", "true");
		            
		            req.getRequestDispatcher("/UserCollections.jsp").forward(req, resp);
		        } else {
		        	messages.put("title", "Failed to Collection with ID" + collectionID);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        
    }
}
