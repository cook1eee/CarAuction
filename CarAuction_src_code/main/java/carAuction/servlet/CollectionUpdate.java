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

@WebServlet("/collectionupdate")
public class CollectionUpdate extends HttpServlet {

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

        // Retrieve user and validate.
        String CollectionId = req.getParameter("CollectionId");
        if (CollectionId == null || CollectionId.trim().isEmpty()) {
            messages.put("success", "Invalid Collection ID.");
        } else {
        	try {
        		Collections collection = collectionsDao.getCollectionById(Integer.parseInt(CollectionId));
        		if(collection == null) {
        			messages.put("success", "Collection does not exist.");
        		}
        		req.setAttribute("collection", collection);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CollectionUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		 // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String CollectionId = req.getParameter("CollectionId");
        if (CollectionId == null || CollectionId.trim().isEmpty()) {
            messages.put("success", "Invalid Collection ID.");
        } else {
        	try {
        		Collections collection = collectionsDao.getCollectionById(1);
        		if(collection == null) {
        			messages.put("success", "Collection does not exist. No update to perform.");
        		} else {
        			String newPriceAlert = req.getParameter("newPriceAlert");
        			if (newPriceAlert == null || newPriceAlert.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid new nameoncard.");
        	        } else {
        	        	collection = collectionsDao.updatePriceChangeAlert(collection, Boolean.parseBoolean(CollectionId));
        	        	messages.put("success", "Successfully updated PriceChangeAlert with the collection number " + collection.getCollectionId());
        	        }
        		}
        		req.setAttribute("collection", collection);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CollectionUpdate.jsp").forward(req, resp);
    }
}