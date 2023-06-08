package carAuction.dal;

import carAuction.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserActivitiesDao {
	protected ConnectionManager connectionManager;
	
	private static UserActivitiesDao instance = null;
	protected UserActivitiesDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static UserActivitiesDao getInstance() {
		if(instance == null) {
			instance = new UserActivitiesDao();
		}
		return instance;
	}
	
	public UserActivities create(UserActivities userActivities) throws SQLException {
		String insertUserActivity = "INSERT INTO UserActivities(UserID,ActivityType,TimeStamp) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUserActivity, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, userActivities.getUser().getUserID());
			insertStmt.setString(2, userActivities.getActivityType().name());
			insertStmt.setTimestamp(3, new Timestamp(userActivities.getTimeStamp().getTime()));
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int ActivityID = -1;
			if(resultKey.next()) {
				ActivityID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			userActivities.setActivityID(ActivityID);
			
			return userActivities;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public UserActivities delete(UserActivities userActivities) throws SQLException {
		String deleteUserActivity = "DELETE FROM UserActivities WHERE ActivityID =?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUserActivity);
			deleteStmt.setInt(1, userActivities.getActivityID());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for ActivityID=" + userActivities.getActivityID());
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	public UserActivities getUserActivityByID(int ActivityID) throws SQLException {
		String selectUserActivity = "SELECT ActivityID,UserID,ActivityType,TimeStamp FROM UserActivities WHERE ActivityID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUserActivity);
			selectStmt.setInt(1, ActivityID);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			
			if(results.next()) {
				int resultActivityID = results.getInt("ActivityID");
				Users user = usersDao.getUserFromUserID(results.getInt("UserID"));
				UserActivities.ActivityType activityType = UserActivities.ActivityType.valueOf(
						results.getString("ActivityType"));
				Date TimeStamp =  new Date(results.getTimestamp("TimeStamp").getTime());
				UserActivities userActivity = new UserActivities(resultActivityID, user, activityType,TimeStamp);
				
				return userActivity;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	
	// return all userActivities for specific user
	public List<UserActivities> getUserActivityForUser(Users user) throws SQLException {
		List<UserActivities> userActivities = new ArrayList<UserActivities>();
		String selectUserActivity =
			"SELECT ActivityID,UserID,ActivityType,TimeStamp " +
			"FROM UserActivities " +
			"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUserActivity);
			selectStmt.setInt(1, user.getUserID());
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				
				int resultActivityID = results.getInt("ActivityID");
				UserActivities.ActivityType activityType = UserActivities.ActivityType.valueOf(
						results.getString("ActivityType"));
				Date TimeStamp =  new Date(results.getTimestamp("TimeStamp").getTime());
				UserActivities userActivity = new UserActivities(resultActivityID, user, activityType,TimeStamp);
				
				userActivities.add(userActivity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return userActivities;
	}

}