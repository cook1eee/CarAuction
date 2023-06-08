package carAuction.servlet;

import carAuction.dal.*;
import carAuction.dal.AuctionsDao.Pair;
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
@WebServlet("/findauctions_id")
public class FindAuctionsByAuctionID extends HttpServlet {
	
	protected AuctionsDao auctionsDao;
	protected CarsDao carsDao;
	
	@Override
	public void init() throws ServletException {
		auctionsDao = AuctionsDao.getInstance();
		carsDao = CarsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Pair<Auctions, Cars>> auctionsAndCars = new ArrayList<>();
        
        int auctionID = -1;
        try {
        	auctionID = Integer.parseInt(req.getParameter("auctionID"));
        } catch (NumberFormatException e) {
        	e.printStackTrace();
			throw new IOException(e);
        }
        if (auctionID < 0) {
            messages.put("success", "Please enter valid auction ID.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		Auctions auction = auctionsDao.getAuctionById(auctionID);
        		Cars car = carsDao.getCarById(auction.getCar().getCarID());
        		auctionsAndCars.add(auctionsDao.new Pair<Auctions, Cars>(auction, car));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for Auction" + auctionID);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousAuctionID", Integer.toString(auctionID));
        }
        req.setAttribute("auctionsAndCars", auctionsAndCars);
        
        req.getRequestDispatcher("/FindAuctions.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Pair<Auctions, Cars>> auctionsAndCars = new ArrayList<>();
        
        int auctionID = -1;
        try {
        	auctionID = Integer.parseInt(req.getParameter("auctionID"));
        } catch (NumberFormatException e) {
        	e.printStackTrace();
			throw new IOException(e);
        }
        if (auctionID < 0) {
            messages.put("success", "Please enter valid auction ID.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		Auctions auction = auctionsDao.getAuctionById(auctionID);
        		Cars car = carsDao.getCarById(auction.getCar().getCarID());
        		auctionsAndCars.add(auctionsDao.new Pair<Auctions, Cars>(auction, car));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for Auction" + auctionID);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousAuctionID", Integer.toString(auctionID));
        }
        req.setAttribute("auctionsAndCars", auctionsAndCars);
        
        req.getRequestDispatcher("/FindAuctions.jsp").forward(req, resp);
    }
}
