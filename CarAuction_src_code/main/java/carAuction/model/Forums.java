package carAuction.model;

import java.util.Date;

public class Forums {
	protected int ForumID;
	protected Auctions auction;
	protected Users user;
	protected Date timeStamp;
	protected String content;
	
	public Forums(int ForumID, Auctions auction, Users user, Date timeStamp, String content) {
		this.ForumID = ForumID;
		this.auction = auction;
		this.user = user;
		this.timeStamp = timeStamp;
		this.content = content;
	}
	
	public Forums(int ForumID) {
		this.ForumID = ForumID;
	}
	
	public Forums(Auctions auction, Users user, Date timeStamp, String content) {
		this.auction = auction;
		this.user = user;
		this.timeStamp = timeStamp;
		this.content = content;
	}

	public int getForumID() {
		return ForumID;
	}

	public void setForumID(int ForumID) {
		this.ForumID = ForumID;
	}

	public Auctions getAuction() {
		return auction;
	}

	public void setAuction(Auctions auction) {
		this.auction = auction;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
