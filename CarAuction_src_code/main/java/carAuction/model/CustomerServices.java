package carAuction.model;


public class CustomerServices {
	protected int CustomerServiceID;
	protected String FirstName;
	protected String LastName;
	
	public CustomerServices(int customerServiceID, String firstName, String lastName) {
		this.CustomerServiceID = customerServiceID;
		this.FirstName = firstName;
		this.LastName = lastName;
	}
	public CustomerServices(int customerServiceID) {
		this.CustomerServiceID = customerServiceID;
		
	}
	public CustomerServices(String firstName, String lastName) {
		this.FirstName = firstName;
		this.LastName = lastName;
	}
	
	
	public int getCustomerServiceID() {
		return CustomerServiceID;
	}
	public void setCustomerServiceID(int customerServiceID) {
		CustomerServiceID = customerServiceID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
}