package carAuction.servlet;

import carAuction.dal.*;
import carAuction.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/collectioncreate")
public class CollectionCreate extends HttpServlet {
	
	protected CollectionsDao collectionsDao;
	protected UsersDao usersDao;
	protected AuctionsDao auctionsDao;
	
	@Override
	public void init() throws ServletException {
		collectionsDao = CollectionsDao.getInstance();
		usersDao = UsersDao.getInstance();
		auctionsDao = AuctionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/CollectionCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String auctionsID = req.getParameter("AuctionsID");
        String userID = req.getParameter("userID");
     
        if (auctionsID == null || auctionsID.trim().isEmpty()) {
            messages.put("success", "Invalid auctionID");
        } else {
        		try {
                	Users user = usersDao.getUserFromUserID(Integer.parseInt(userID));
                	Auctions auction = auctionsDao.getAuctionById(Integer.parseInt(auctionsID));
                	
                	Boolean PriceChangeAlert = Boolean.parseBoolean(req.getParameter("PriceChangeAlert"));
                	Boolean StatusChangeAlert = Boolean.parseBoolean(req.getParameter("StatusChangeAlert"));
                	
                	Collections collection = new Collections(user, auction, PriceChangeAlert,  StatusChangeAlert);
                	collection = collectionsDao.create(collection);
    	        	
    	        	messages.put("success", "New collection has been added !");
    	        	
    	        	req.setAttribute("collection", collection);
        		}catch (SQLException e) {
        			e.printStackTrace();
        			throw new IOException(e);
        		}
        		      
        }
        req.getRequestDispatcher("/CollectionCreate.jsp").forward(req, resp);
        
    }
}