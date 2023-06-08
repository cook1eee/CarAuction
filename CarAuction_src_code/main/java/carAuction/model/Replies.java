package carAuction.model;

import java.util.Date;

public class Replies {
	protected int ReplyID;
	protected Forums forum;
	protected Users user;
	protected Date timeStamp;
	protected String content;
	
	public Replies(int ReplyID, Forums forum, Users user, Date timeStamp, String content) {
		this.ReplyID = ReplyID;
		this.forum = forum;
		this.user = user;
		this.timeStamp = timeStamp;
		this.content = content;
	}
	
	public Replies(int RepliesID) {
		this.ReplyID = RepliesID;
	}
	
	public Replies(Forums forum, Users user, Date timeStamp, String content) {
		this.forum = forum;
		this.user = user;
		this.timeStamp = timeStamp;
		this.content = content;
	}

	public int getReplyID() {
		return ReplyID;
	}

	public void setReplyID(int ReplyID) {
		this.ReplyID = ReplyID;
	}

	public Forums getForum() {
		return forum;
	}

	public void setForum(Forums forum) {
		this.forum = forum;
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
