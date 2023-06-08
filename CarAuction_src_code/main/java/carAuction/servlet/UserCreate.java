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


@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Users> usersList = new ArrayList<Users>();
        
        String Password = req.getParameter("password");
        String Password2 = req.getParameter("password2"); 
        String Email = req.getParameter("email"); 
        
        if (Password == null || Password.trim().isEmpty() ) {
        	 messages.put("success", "You have to enter the password !");
        } else if ( !Password.equals(Password2)) {
        	// 输入相同的密码会报错, 输入不同的密码会成功
            messages.put("success", "Password inconsistency !");
        } else {
        	String FirstName = req.getParameter("firstname"); 
        	String LastName = req.getParameter("lastname"); 
			String Address1 = req.getParameter("address1"); 
			String Address2 = req.getParameter("address2"); 
			String City = req.getParameter("city"); 
            String State = req.getParameter("state"); 
            String Zipcode = req.getParameter("zipcode"); 
            String Country = req.getParameter("contry"); 
            String Phone = req.getParameter("phone"); 
            Date date = new Date();
            Date SignUp = date;
            try {
            	usersList = usersDao.getUsersFromEmail(Email);
            	
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
            if (!usersList.isEmpty()) {
				messages.put("success", "Email has already been used.");
			} else {
				try {
					Users user = new Users(FirstName, LastName, Address1, Address2, 
				               City, State,  Zipcode,  Country, Phone, Email, Password, SignUp);
					user = usersDao.create(user);			
	        		messages.put("success", "Successfully created user for " + FirstName + ", Please Login.");
		        } catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }	
        }   
        }req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
    }
}
