package carAuction.model;

import java.util.Date;


public class Bids {
	protected int BidID;
	protected Auctions Auction;
	protected Users User;
	protected Date BidTime;
	protected Float BidPrice;
	
	public Bids(int BidID, Auctions Auction, Users User, Date BidTime, Float BidPrice) {
		this.BidID = BidID;
		this.Auction = Auction;
		this.User = User;
		this.BidTime = BidTime;
		this.BidPrice = BidPrice;
	}

	public Bids(int BidID) {
		this.BidID = BidID;
	}
	
	public Bids(Auctions Auction, Users User, Date BidTime, Float BidPrice) {
		this.Auction = Auction;
		this.User = User;
		this.BidTime = BidTime;
		this.BidPrice = BidPrice;
	}
	
	public int getBidID() {
		return BidID;
	}
	public void setBidID(int BidID) {
		this.BidID = BidID;
	}
	public Auctions getAuction() {
		return Auction;
	}
	public void setAuction(Auctions Auction) {
		this.Auction = Auction;
	}
	public Users getUser() {
		return User;
	}
	public void setUser(Users User) {
		this.User = User;
	}
	public Date getBidTime() {
		return BidTime;
	}
	public void setBidTime(Date bidTime) {
		BidTime = bidTime;
	}
	public Float getBidPrice() {
		return BidPrice;
	}
	public void setBidPrice(Float bidPrice) {
		BidPrice = bidPrice;
	}
	

}
