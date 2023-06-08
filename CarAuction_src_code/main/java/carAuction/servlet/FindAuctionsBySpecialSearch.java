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
@WebServlet("/special_search")
public class FindAuctionsBySpecialSearch extends HttpServlet {
	
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
        
        if (req.getParameter("button1") != null) {
        	try {
        		auctionsAndCars = auctionsDao.getClosedAuction();
        		messages.put("success", "Displaying results for Auctions that have been successful/closed");
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
		} else if (req.getParameter("button2") != null) {
			try {
				StringBuffer msg = new StringBuffer();
        		List<Pair<String, String>> makerAndModelPairs = auctionsDao.getTop20PopularCarInSell();
        		for (Pair<String, String> pair : makerAndModelPairs) {
        			auctionsAndCars.addAll(auctionsDao.getAuctionByMakerAndModel(pair.getT(), pair.getU()));
        			msg.append(pair.getT() + "-" + pair.getU());
        		}
        		messages.put("success", "Displaying results for Current Top 20 Best Sell Cars: \n" + msg.toString());
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
		} else if (req.getParameter("button3") != null) {
			try {
				StringBuffer msg = new StringBuffer();
        		List<Pair<String, String>> makerAndModelPairs = auctionsDao.getTop10CollectedCarModel();
        		for (Pair<String, String> pair : makerAndModelPairs) {
        			auctionsAndCars.addAll(auctionsDao.getAuctionByMakerAndModel(pair.getT(), pair.getU()));
        			msg.append(pair.getT() + "-" + pair.getU());
        		}
        		messages.put("success", "Displaying results for Current Top 20 Best Sell Cars: \n" + msg.toString());
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
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
        
        if (req.getParameter("button1") != null) {
        	try {
        		auctionsAndCars = auctionsDao.getClosedAuction();
        		messages.put("success", "Displaying results for Auctions that have been successful/closed");
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
		} else if (req.getParameter("button2") != null) {
			try {
				StringBuffer msg = new StringBuffer();
        		List<Pair<String, String>> makerAndModelPairs = auctionsDao.getTop20PopularCarInSell();
        		for (Pair<String, String> pair : makerAndModelPairs) {
        			auctionsAndCars.addAll(auctionsDao.getAuctionByMakerAndModel(pair.getT(), pair.getU()));
        			msg.append(pair.getT() + "-" + pair.getU() + ", ");
        		}
        		messages.put("success", "Displaying results for Current Top 20 Best Sell Cars: \n" + msg.toString());
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
		} else if (req.getParameter("button3") != null) {
			try {
				StringBuffer msg = new StringBuffer();
        		List<Pair<String, String>> makerAndModelPairs = auctionsDao.getTop10CollectedCarModel();
        		for (Pair<String, String> pair : makerAndModelPairs) {
        			auctionsAndCars.addAll(auctionsDao.getAuctionByMakerAndModel(pair.getT(), pair.getU()));
        			msg.append(pair.getT() + "-" + pair.getU() + ", ");
        		}
        		messages.put("success", "Displaying results for Current Top 20 Best Sell Cars: \n" + msg.toString());
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
		} 
        
        
        req.setAttribute("auctionsAndCars", auctionsAndCars);
        
        req.getRequestDispatcher("/FindAuctions.jsp").forward(req, resp);
    }
}
