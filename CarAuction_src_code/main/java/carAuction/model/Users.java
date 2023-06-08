package carAuction.model;

import java.util.Date;

/**
 * Users is a simple, plain old java objects (POJO).
 */
public class Users {
    protected int  UserID;
    protected String  FirstName;
    protected String  LastName;
    protected String  Address1;
    protected String  Address2;
    protected String  City;
    protected String  State;
    protected String  Zipcode;
    protected String  Country;
    protected String  Phone;
    protected String  Email;
    protected String  password;
    protected Date  SignUp;
    
 
	
	public Users(int UserID, String FirstName, String LastName,
				 String Address1, String Address2, String City,
                 String State, String Zipcode, String Country,
                 String Phone, String Email, String password, Date SignUp) {
		this.UserID = UserID;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Address1 = Address1;
		this.Address2 = Address2;
		this.City = City;
		this.State = State;
		this.Zipcode = Zipcode;
		this.Country = Country;
		this.Phone = Phone;
		this.Email = Email;
		this.password = password;
		this.SignUp = SignUp;
	}
	
	public Users(int UserID) {
		this.UserID = UserID;
	}
	
	public Users(String FirstName, String LastName,
			 String Address1, String Address2, String City,
            String State, String Zipcode, String Country,
            String Phone, String Email, String password, Date SignUp) {
	
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Address1 = Address1;
		this.Address2 = Address2;
		this.City = City;
		this.State = State;
		this.Zipcode = Zipcode;
		this.Country = Country;
		this.Phone = Phone;
		this.Email = Email;
		this.password = password;
		this.SignUp = SignUp;
}

	/** Getters and setters. */

    public int getUserID() {
		return UserID;
	}

	public void setUserID(int UserID) {
		this.UserID = UserID;
	}
	
    public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}

    public String getLastName() {
		return LastName;
	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}

    public String getAddress1() {
		return Address1;
	}

	public void setAddress1(String Address1) {
		this.Address1 = Address1;
	}

    public String getAddress2() {
		return Address2;
	}

	public void setAddress2(String Address2) {
		this.Address2 = Address2;
	}
	
    public String getCity() {
		return City;
	}

    public void setCity(String City) {
		this.City = City;
	}
	
    public String getState() {
		return State;
	}
    
	public void setState(String State) {
		this.State = State;
	}
	
	public String getZipcode() {
		return Zipcode;
	}

	public void setZipcode(String Zipcode) {
		this.Zipcode = Zipcode;
	}
	
	public String getCountry() {
		return Country;
	}

	public void setCountry(String Country) {
		this.Country = Country;
	}
	
	public String getPhone() {
		return Phone;
	}

	public void setPhone(String Phone) {
		this.Phone = Phone;
	}
	
	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}
	
	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}
	
	public Date getSignUp() {
		return SignUp;
	}

	public void setSignUp(Date SignUp) {
		this.SignUp = SignUp;
	}
	
	public Users(String  Email) {
		this.Email = Email;
	}
}
