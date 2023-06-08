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
@WebServlet("/findauctions_makerandmodel")
public class FindAuctionsByMakerAndModel extends HttpServlet {
	
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

        List<Pair<Auctions, Cars>> auctionsAndCars = new ArrayList<>();
        
        String maker = req.getParameter("maker");
        String model = req.getParameter("model");
        if ((maker == null || maker.trim().isEmpty()) && (model == null || model.trim().isEmpty())) {
            messages.put("success", "Please enter valid maker and model.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		auctionsAndCars = auctionsDao.getAuctionByMakerAndModel(maker, model);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + maker + " and " + model);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousMakerAndModel", maker + " and " + model);
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
        
        String maker = req.getParameter("maker");
        String model = req.getParameter("model");
        if ((maker == null || maker.trim().isEmpty()) && (model == null || model.trim().isEmpty())) {
            messages.put("success", "Please enter valid maker and model.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		auctionsAndCars = auctionsDao.getAuctionByMakerAndModel(maker, model);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + maker + " and " + model);
        }
        req.setAttribute("auctionsAndCars", auctionsAndCars);
        
        req.getRequestDispatcher("/FindAuctions.jsp").forward(req, resp);
    }
}
