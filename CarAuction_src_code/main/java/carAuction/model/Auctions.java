package carAuction.model;

import java.util.Date;

public class Auctions {
	protected int AuctionID;
	protected String Title;
	protected Date StartTime;
	protected Date EndTime;
	protected Cars car;
	protected Users user;
	protected String Highlights;
	protected String Pictures;
	protected Float MinimumPrice;
	protected Float CurrentHighestPrice;
	protected AuctionStatusValue AuctionStatus;
	protected CustomerServices customerService;
	protected Boolean PriceChangeAlert;
	
	public enum AuctionStatusValue {
		Active, Failed, Succeed
	}

	public Auctions(int auctionID, String title, Date startTime, Date endTime, Cars car, Users user,
			String highlights, String pictures, Float minimumPrice, Float currentHighestPrice,
			AuctionStatusValue auctionStatus, CustomerServices customerService, Boolean priceChangeAlert) {
		this.AuctionID = auctionID;
		this.Title = title;
		this.StartTime = startTime;
		this.EndTime = endTime;
		this.car = car;
		this.user = user;
		this.Highlights = highlights;
		this.Pictures = pictures;
		this.MinimumPrice = minimumPrice;
		this.CurrentHighestPrice = currentHighestPrice;
		this.AuctionStatus = auctionStatus;
		this.customerService = customerService;
		this.PriceChangeAlert = priceChangeAlert;
	}
	
	public Auctions(String title, Date startTime, Date endTime, Cars car, Users user,
			String highlights, String pictures, Float minimumPrice, Float currentHighestPrice,
			AuctionStatusValue auctionStatus, CustomerServices customerService, Boolean priceChangeAlert) {
		this.Title = title;
		this.StartTime = startTime;
		this.EndTime = endTime;
		this.car = car;
		this.user = user;
		this.Highlights = highlights;
		this.Pictures = pictures;
		this.MinimumPrice = minimumPrice;
		this.CurrentHighestPrice = currentHighestPrice;
		this.AuctionStatus = auctionStatus;
		this.customerService = customerService;
		this.PriceChangeAlert = priceChangeAlert;
	}

	public Auctions(int auctionID, Cars car, Users user, CustomerServices customerService) {
		this.AuctionID = auctionID;
		this.car = car;
		this.user = user;
		this.customerService = customerService;
	}
	
	public Auctions(int auctionID) {
		this.AuctionID = auctionID;
	}
	
	public Auctions(Cars car, Users user, CustomerServices customerService) {
		this.car = car;
		this.user = user;
		this.customerService = customerService;
	}

	public int getAuctionID() {
		return this.AuctionID;
	}

	public void setAuctionID(int auctionID) {
		this.AuctionID = auctionID;
	}

	public String getTitle() {
		return this.Title;
	}

	public void setTitle(String title) {
		this.Title = title;
	}

	public Date getStartTime() {
		return this.StartTime;
	}

	public void setStartTime(Date startTime) {
		this.StartTime = startTime;
	}

	public Date getEndTime() {
		return this.EndTime;
	}

	public void setEndTime(Date endTime) {
		this.EndTime = endTime;
	}

	public Cars getCar() {
		return this.car;
	}

	public void setCar(Cars car) {
		this.car = car;
	}

	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getHighlights() {
		return this.Highlights;
	}

	public void setHighlights(String highlights) {
		this.Highlights = highlights;
	}

	public String getPictures() {
		return this.Pictures;
	}

	public void setPictures(String pictures) {
		this.Pictures = pictures;
	}

	public Float getMinimumPrice() {
		return this.MinimumPrice;
	}

	public void setMinimumPrice(Float minimumPrice) {
		this.MinimumPrice = minimumPrice;
	}

	public Float getCurrentHighestPrice() {
		return this.CurrentHighestPrice;
	}

	public void setCurrentHighestPrice(Float currentHighestPrice) {
		this.CurrentHighestPrice = currentHighestPrice;
	}

	public AuctionStatusValue getAuctionStatus() {
		return this.AuctionStatus;
	}

	public void setAuctionStatus(AuctionStatusValue auctionStatus) {
		this.AuctionStatus = auctionStatus;
	}

	public CustomerServices getCustomerService() {
		return this.customerService;
	}

	public void setCustomerService(CustomerServices customerService) {
		this.customerService = customerService;
	}

	public Boolean getPriceChangeAlert() {
		return this.PriceChangeAlert;
	}

	public void setPriceChangeAlert(Boolean priceChangeAlert) {
		this.PriceChangeAlert = priceChangeAlert;
	}
}
