package carAuction.model;

import java.util.Date;

public class ChatHistories {
  protected int ChatID;
  protected CustomerServices CustomerService;
  protected Users User;
  protected Date TimeStamp;
  protected ServiceTypeValue ServiceType;
  public enum ServiceTypeValue {
    Account, Payment, Auction, Bidding, Legal,Security,Privacy, Other
  };

  public ChatHistories(int chatID, CustomerServices customerService, Users user, Date timeStamp, ServiceTypeValue serviceType) {
    this.ChatID = chatID;
    this.CustomerService = customerService;
    this.User = user;
    this.TimeStamp = timeStamp;
    this.ServiceType = serviceType;
  }
  
  public ChatHistories(CustomerServices customerService, Users user,
			Date timeStamp, ServiceTypeValue serviceType) {
	this.CustomerService = customerService;
	this.User = user;
	this.TimeStamp = timeStamp;
	this.ServiceType = serviceType;}


  public ChatHistories(int chatID, CustomerServices customerService, Users user) {
    this.ChatID = chatID;
    this.CustomerService = customerService;
    this.User = user;
  }

  public ChatHistories.ServiceTypeValue getServiceType() {
    return this.ServiceType;
  }

  public void setServiceType(ChatHistories.ServiceTypeValue serviceType) {
    this.ServiceType = serviceType;
  }

  public int getChatID() {
    return this.ChatID;
  }

  public CustomerServices getCustomerService() {
    return this.CustomerService;
  }

  public Users getUser() {
    return this.User;
  }

  public Date getTimeStamp() {
    return this.TimeStamp;
  }

  public void setChatID(int chatID) {
    this.ChatID = chatID;
  }

  public void setCustomerService(CustomerServices customerService) {
    this.CustomerService = customerService;
  }

  public void setUser(Users user) {
    this.User = user;
  }

  public void setTimeStamp(Date timeStamp) {
    this.TimeStamp = timeStamp;
  }
}