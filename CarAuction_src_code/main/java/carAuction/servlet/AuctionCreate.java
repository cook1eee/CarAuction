package carAuction.servlet;

import carAuction.dal.*;
import carAuction.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/auctioncreate")
public class AuctionCreate extends HttpServlet {
	
	protected AuctionsDao auctionsDao;
	protected CarsDao carsDao;
	protected UsersDao usersDao;
	protected CustomerServicesDao customerServicesDao;
	
	@Override
	public void init() throws ServletException {
		auctionsDao = AuctionsDao.getInstance();
		carsDao = CarsDao.getInstance();
		usersDao = UsersDao.getInstance();
		customerServicesDao = CustomerServicesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/AuctionCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String title = req.getParameter("title");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	String stringEndTime = req.getParameter("endTime");
    	Date endTime = new Date();
    	try {
    		endTime = dateFormat.parse(stringEndTime);
    	} catch (ParseException e) {
    		e.printStackTrace();
			throw new IOException(e);
    	}
        int carId = -1;
        Float minimumPrice = -1.0F;
        try {
        	carId = Integer.parseInt(req.getParameter("carId"));
            minimumPrice = Float.parseFloat(req.getParameter("minimumPrice"));
        } catch (NumberFormatException e) {
        	e.printStackTrace();
			throw new IOException(e);
        }
        
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Invalid Title");
        } else if (endTime == null || endTime.equals(new Date())) {
                messages.put("success", "Invalid End Time");
        } else if (carId < 0) {
            messages.put("success", "Invalid Car");
        } else if (minimumPrice < 0) {
            messages.put("success", "Invalid Start Price");
        } else {
        	// Create the Auction.
        	String highlights = req.getParameter("highlights");
        	String pictures = req.getParameter("pictures");
        	// dob must be in the format yyyy-mm-dd.
        	
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Auctions auction = new Auctions(title, new Date(), endTime, carsDao.getCarById(carId), usersDao.getUserFromCarId(carId), highlights, pictures, minimumPrice, minimumPrice, Auctions.AuctionStatusValue.Succeed, customerServicesDao.getCustomerServiceById(getRandom(customerServicesDao.getKeyRange())), true);
	        	auction = auctionsDao.create(auction);
	        	messages.put("success", "Successfully created " + title);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AuctionCreate.jsp").forward(req, resp);
    }
	
	public static int getRandom(List<Integer> list) {
	    int rnd = new Random().nextInt(list.size());
	    return list.get(rnd);
	}
}
