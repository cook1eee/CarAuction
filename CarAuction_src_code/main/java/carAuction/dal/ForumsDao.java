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


public class ForumsDao {
	protected ConnectionManager connectionManager;
		
	private static ForumsDao instance = null;
	protected ForumsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ForumsDao getInstance() {
		if(instance == null) {
			instance = new ForumsDao();
		}
		return instance;
	}
	
	public Forums create(Forums forum) throws SQLException {
		String insertForums =
			"INSERT INTO Forums(AuctionID,UserID,TimeStamp,Content) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertForums, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, forum.getAuction().getAuctionID()); 
			insertStmt.setInt(2, forum.getUser().getUserID());
			insertStmt.setTimestamp(3, new Timestamp(forum.getTimeStamp().getTime()));
			insertStmt.setString(4, forum.getContent());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int forumID = -1;
			if(resultKey.next()) {
				forumID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			forum.setForumID(forumID);
			
			return forum;
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

	public Forums delete(Forums forum) throws SQLException {

		String deleteForums = "DELETE FROM Forums WHERE ForumID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteForums);
			deleteStmt.setInt(1, forum.getForumID());
			int affectedRows = deleteStmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for ForumsID=" + forum.getForumID());
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
	
	public Forums updateForum(Forums forum, String newContent) throws SQLException {
		String updateForum = "UPDATE Forums SET Content=?,TimeStamp=? WHERE ForumID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateForum);
			updateStmt.setString(1, newContent);
			Date newCreatedTimestamp = new Date();
			updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
			updateStmt.setInt(3, forum.getForumID());
			updateStmt.executeUpdate();

			forum.setContent(newContent);
			forum.setTimeStamp(newCreatedTimestamp);
			return forum;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Forums getForumById(int forumID) throws SQLException {
		String selectForums =
			"SELECT ForumID,AuctionID,UserID,TimeStamp,Content " +
			"FROM Forums " +
			"WHERE ForumID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectForums);
			selectStmt.setInt(1, forumID);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			
			AuctionsDao auctionDao = AuctionsDao.getInstance();
			
			if(results.next()) {
				int resultForumsID = results.getInt("ForumID");
				
				int auctionID = results.getInt("AuctionID");
				Auctions auction = auctionDao.getAuctionById(auctionID);
				
				int userID = results.getInt("UserID");
				Users user = usersDao.getUserFromUserID(userID);
				
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Forums forum = new Forums(resultForumsID, auction,
						user, timeStamp, content);
				
				return forum;
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
	
	public List<Forums> getForumForUser(Users user) throws SQLException {
		List<Forums> forums = new ArrayList<Forums>();
		String selectForums =
			"SELECT ForumID,AuctionID,UserID,TimeStamp,Content " +
			"FROM Forums " +
			"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectForums);
			selectStmt.setInt(1, user.getUserID());
			results = selectStmt.executeQuery();
			
			AuctionsDao auctionsDao = AuctionsDao.getInstance();
			
			while(results.next()) {
				int resultForumID = results.getInt("ForumID");
				
				int auctionID = results.getInt("AuctionID");
				Auctions auction = auctionsDao.getAuctionById(auctionID);
				
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Forums forum = new Forums(resultForumID, auction,
						user, timeStamp, content);
				
				forums.add(forum);
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
		return forums;
	}

	public List<Forums> getForumForAuction(Auctions auction) throws SQLException {
		List<Forums> forums = new ArrayList<Forums>();
		String selectForums =
			"SELECT ForumID,AuctionID,UserID,TimeStamp,Content " +
			"FROM Forums " +
			"WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectForums);
			selectStmt.setInt(1, auction.getAuctionID());
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			
			while(results.next()) {
				int forumID = results.getInt("ForumID");
				
				int userID = results.getInt("UserID");
				Users user = usersDao.getUserFromUserID(userID);
				
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Forums forum = new Forums(forumID, auction,
						user, timeStamp, content);
				
				forums.add(forum);
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
		return forums;
	}
	
}








