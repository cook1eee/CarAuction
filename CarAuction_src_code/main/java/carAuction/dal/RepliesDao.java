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


public class RepliesDao {
	protected ConnectionManager connectionManager;
	
	private static RepliesDao instance = null;
	protected RepliesDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static RepliesDao getInstance() {
		if(instance == null) {
			instance = new RepliesDao();
		}
		return instance;
	}
	
    
	public Replies create(Replies reply) throws SQLException {
		String insertReplies =
			"INSERT INTO Replies(ForumID,UserID,TimeStamp,Content) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReplies, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, reply.getForum().getForumID());
			insertStmt.setInt(2, reply.getUser().getUserID());
			insertStmt.setTimestamp(3, new Timestamp(reply.getTimeStamp().getTime()));
			insertStmt.setString(4, reply.getContent());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int replyID = -1;
			if(resultKey.next()) {
				replyID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			reply.setReplyID(replyID);
			return reply;
			
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

	public Replies delete(Replies Replies) throws SQLException {

		String deleteReplies = "DELETE FROM Replies WHERE ReplyID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReplies);
			deleteStmt.setInt(1, Replies.getReplyID());
			int affectedRows = deleteStmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for ReplyID=" + Replies.getReplyID());
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

	public Replies updateReply(Replies Replies, String newContent) throws SQLException {
		String updateReplies = "UPDATE Replies SET Content=?,TimeStamp=? WHERE ReplyID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateReplies);
			updateStmt.setString(1, newContent);
			Date newCreatedTimestamp = new Date();
			updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
			updateStmt.setInt(3, Replies.getReplyID());
			updateStmt.executeUpdate();

			Replies.setContent(newContent);
			Replies.setTimeStamp(newCreatedTimestamp);
			return Replies;
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

	public Replies getReplyById(int replyID) throws SQLException {
		String selectReplies =
			"SELECT ReplyID,ForumID,UserID,TimeStamp,Content " +
			"FROM Replies " +
			"WHERE ReplyID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReplies);
			selectStmt.setInt(1, replyID);
			results = selectStmt.executeQuery();
			ForumsDao forumsDao = ForumsDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			
			if(results.next()) {
				int resultReplyID = results.getInt("ReplyID");
				
				int forumID = results.getInt("ForumID");
				Forums forum = forumsDao.getForumById(forumID);
				
				int userID = results.getInt("UserID");
				Users user = usersDao.getUserFromUserID(userID);
				
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Replies reply = new Replies(resultReplyID, forum,
						user, timeStamp, content);
				
				return reply;
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

	public List<Replies> getReplyForForum(Forums forum) throws SQLException {
		List<Replies> replies = new ArrayList<Replies>();
		String selectReplies =
			"SELECT ReplyID,ForumID,UserID,TimeStamp,Content " +
			"FROM Replies " +
			"WHERE ForumID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReplies);
			selectStmt.setInt(1, forum.getForumID());
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			
			while(results.next()) {
				int ReplyID = results.getInt("ReplyID");
				int userID = results.getInt("UserID");
				Users user = usersDao.getUserFromUserID(userID);
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Replies reply = new Replies(ReplyID, forum, user, timeStamp, content);
				
				replies.add(reply);
				
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
		return replies;
	}

	public List<Replies> getReplyForUser(Users user) throws SQLException {
		List<Replies> replies = new ArrayList<Replies>();
		String selectReplies =
			"SELECT ReplyID,ForumID,UserID,TimeStamp,Content " +
			"FROM Replies " +
			"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReplies);
			selectStmt.setInt(1, user.getUserID());
			results = selectStmt.executeQuery();
			
			ForumsDao forumDao = ForumsDao.getInstance();
			
			while(results.next()) {
				int replyID = results.getInt("ReplyID");
				
				int forumID = results.getInt("ForumID");
				Forums forum = forumDao.getForumById(forumID);
				
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Replies reply = new Replies(replyID, forum, user, timeStamp, content);
				
				replies.add(reply);
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
		return replies;
	}

}