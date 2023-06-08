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


@WebServlet("/carcreate")
public class CarCreate extends HttpServlet {
	
	protected CarsDao carsDao;
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		carsDao = CarsDao.getInstance();
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/CarCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        int userId = -1;
        try {
        	userId = Integer.parseInt(req.getParameter("userID"));
        } catch (NumberFormatException e) {
        	e.printStackTrace();
			throw new IOException(e);
        }
        
        if (userId < 0) {
            messages.put("success", "Invalid User");
        } else {
        	// Create the Car.
        	String maker = req.getParameter("maker");
        	String model = req.getParameter("model");
        	String trim = req.getParameter("trim");
        	String body = req.getParameter("body");
        	String transmission = req.getParameter("transmission");
        	String vin = req.getParameter("vin");
        	String state = req.getParameter("state");
        	String color = req.getParameter("color");
        	String interior = req.getParameter("interior");
        	int year = -1;
            Float conditionScore = -1.0F;
            int odoMeter = -1;
            int mmr = -1;
            try {
            	year = Integer.parseInt(req.getParameter("year"));
            	conditionScore = Float.parseFloat(req.getParameter("conditionScore"));
            	odoMeter = Integer.parseInt(req.getParameter("odoMeter"));
            	mmr = Integer.parseInt(req.getParameter("mmr"));
            } catch (NumberFormatException e) {
            	e.printStackTrace();
    			throw new IOException(e);
            }
            
        	
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Cars car = new Cars(usersDao.getUserFromUserID(userId),year,maker,model,trim,body,transmission,vin,state,conditionScore,odoMeter,color,interior,mmr);
				car = carsDao.create(car);
	        	messages.put("success", "Successfully created Car with ID: " + car.getCarID());
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CarCreate.jsp").forward(req, resp);
    }
}
