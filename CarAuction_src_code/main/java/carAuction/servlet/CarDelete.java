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


@WebServlet("/cardelete")
public class CarDelete extends HttpServlet {
	
	protected CarsDao carsDao;
	
	@Override
	public void init() throws ServletException {
		carsDao = CarsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        
        messages.put("title", "Delete a Car");        
        req.getRequestDispatcher("/CarDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

       
        String carID = req.getParameter("carID");
        if (carID == null || carID.trim().isEmpty()) {
            messages.put("title", "Invalid carID");
            messages.put("disableSubmit", "true");
        } else {
        	
        	Cars car = new Cars(Integer.parseInt(carID));
	        try {
	        	car = carsDao.delete(car);
	        	// Update the message.
		        if (car == null) {
		            messages.put("title", "Successfully deleted Car with ID " + carID);
		            messages.put("disableSubmit", "true");
		            
		            req.getRequestDispatcher("/UserCars.jsp").forward(req, resp);
		        } else {
		        	messages.put("title", "Failed to Car with ID" + carID);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        
    }
}
