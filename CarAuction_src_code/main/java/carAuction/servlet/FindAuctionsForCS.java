package carAuction.servlet;

import carAuction.dal.*;
import carAuction.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * FindUsers is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/BlogApplication/findusers.
 */

// if the url end with "/findusers" then use this class
@WebServlet("/findauctionsforcs")
public class FindAuctionsForCS extends HttpServlet {
	
	protected AuctionsDao auctionsDao;
	
	@Override
	public void init() throws ServletException {
		auctionsDao = AuctionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Auctions> auctions = new ArrayList<Auctions>();
        
        // Retrieve and validate name.
        // customerServiceID is retrieved from the URL query string.
        String customerServiceIDString = req.getParameter("customerServiceID");
        if (customerServiceIDString == null || customerServiceIDString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid CustomerServiceID.");
            // present all users
//            try {
//            	users = usersDao.getAllUsers();
//            } catch (SQLException e) {
//    			e.printStackTrace();
//    			throw new IOException(e);
//            }
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		int customerServiceID = Integer.parseInt(customerServiceIDString);
        		CustomerServices customerService = new CustomerServices(customerServiceID);
        		auctions = auctionsDao.getAuctionForCustomerService(customerService);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for CustomerService " + customerServiceIDString);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousCustomerServiceID", customerServiceIDString);
        }
        
        req.setAttribute("auctions", auctions);
        
        req.getRequestDispatcher("/FindAuctionsForCS.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Auctions> auctions = new ArrayList<Auctions>();
        // Retrieve and validate name.
        // Auctions is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String customerServiceIDString = req.getParameter("customerServiceID");
        if (customerServiceIDString == null || customerServiceIDString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid customerServiceID.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		int customerServiceID = Integer.parseInt(customerServiceIDString);
        		CustomerServices customerService = new CustomerServices(customerServiceID);
        		auctions = auctionsDao.getAuctionForCustomerService(customerService);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for customerServiceID " + customerServiceIDString);
        }
        req.setAttribute("auctions", auctions);
        
        req.getRequestDispatcher("/FindAuctionsForCS.jsp").forward(req, resp);
    }
}
