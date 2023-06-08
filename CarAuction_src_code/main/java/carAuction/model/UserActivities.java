package carAuction.model;

import java.util.Date;


public class UserActivities {
	protected int activityID;
	protected Users user;
	protected ActivityType activityType;
	protected Date timeStamp;
	
	
	public enum ActivityType{
		login,logout
	}


	public UserActivities(int activityID, Users user, ActivityType activityType, Date timeStamp) {
		this.activityID = activityID;
		this.user = user;
		this.activityType = activityType;
		this.timeStamp = timeStamp;
	}
	
	public UserActivities(int activityID) {
		this.activityID = activityID;
	}
	
	public UserActivities(Users user, ActivityType activityType, Date timeStamp) {
		this.user = user;
		this.activityType = activityType;
		this.timeStamp = timeStamp;
	}

	public int getActivityID() {
		return activityID;
	}


	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public ActivityType getActivityType() {
		return activityType;
	}


	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}


	public Date getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
