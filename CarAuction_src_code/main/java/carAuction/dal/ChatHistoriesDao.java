package carAuction.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import carAuction.model.*;

	public class ChatHistoriesDao {
		protected ConnectionManager connectionManager;
		private static ChatHistoriesDao instance = null;
		protected ChatHistoriesDao() {
		connectionManager = new ConnectionManager();
	}
	public static ChatHistoriesDao getInstance() {
		if(instance == null) {
			instance = new ChatHistoriesDao();
		}
		return instance;
	}
	public ChatHistories create(ChatHistories chatHistories) throws SQLException {
		String insertChatHistories =
				"INSERT INTO ChatHistories(CustomerServiceID, UserID, TimeStamp, ServiceType) " +
						"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();

			insertStmt = connection.prepareStatement(insertChatHistories,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setInt(1, chatHistories.getCustomerService().getCustomerServiceID());
			insertStmt.setInt(2, chatHistories.getUser().getUserID());
			insertStmt.setTimestamp(3, new Timestamp(chatHistories.getTimeStamp().getTime()));
			insertStmt.setString(4, chatHistories.getServiceType().name());
			insertStmt.executeUpdate();

	 resultKey = insertStmt.getGeneratedKeys();
	 int chatHistorieID = -1;
	 if(resultKey.next()) {
		 chatHistorieID = resultKey.getInt(1);
	 } else {
		 throw new SQLException("Unable to retrieve auto-generated key.");
	 }
	 chatHistories.setChatID(chatHistorieID);
	return chatHistories;
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
			if(resultKey != null) {
		resultKey.close();
			}
		}
	}
	public ChatHistories updateServiceType(ChatHistories chatHistories, ChatHistories.ServiceTypeValue newServiceType) throws SQLException {
		String updateChatHistories = "UPDATE ChatHistories SET ServiceType=? WHERE ChatID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateChatHistories);
			updateStmt.setString(1, newServiceType.name());
		updateStmt.setInt(2, chatHistories.getChatID());
		updateStmt.executeUpdate();
		return chatHistories;
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
	public ChatHistories delete(ChatHistories chatHistories) throws SQLException {
		String deleteChatHistories = "DELETE FROM ChatHistories WHERE ChatID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteChatHistories);
			deleteStmt.setInt(1, chatHistories.getChatID());
			deleteStmt.executeUpdate();
	// Return null so the caller can no longer operate on the BlogPosts instance.
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
	public List<ChatHistories> getChatHistoriesByUserID (int userid) throws SQLException {
			List<ChatHistories> chatHistories = new ArrayList<ChatHistories>();
		
		String selectChatHistories =
		 "SELECT ChatID, CustomerServiceID, UserID, TimeStamp, ServiceType FROM ChatHistories WHERE UserID=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CustomerServicesDao customerServicesDao = CustomerServicesDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		Users u1 = usersDao.getUserFromUserID(userid);
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectChatHistories);
			selectStmt.setInt(1, userid);
			results = selectStmt.executeQuery();
			
			
			while(results.next()) {
				int ChatID = results.getInt("ChatID");
				
				CustomerServices customerService = customerServicesDao.
						getCustomerServiceById(results.getInt("CustomerServiceID"));
				
				Date TimeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				
				ChatHistories.ServiceTypeValue serviceType =
						ChatHistories.ServiceTypeValue.valueOf(results.getString("ServiceType"));
			
				
				ChatHistories chatHistory = new ChatHistories(ChatID,customerService, u1,
			 TimeStamp, serviceType);
				
				chatHistories.add(chatHistory);
			
				
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
		return chatHistories;
	}
	
	public List<ChatHistories> getChatHistoriesByCustomerServiceID (int customerServiceID) throws SQLException {
		List<ChatHistories> chatHistories = new ArrayList<ChatHistories>();

		String selectChatHistories =
				"SELECT ChatID, CustomerServiceID, UserID, TimeStamp, ServiceType FROM ChatHistories WHERE CustomerServiceID=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CustomerServicesDao customerServicesDao = CustomerServicesDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		CustomerServices cs1 = customerServicesDao.getCustomerServiceById(customerServiceID);
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectChatHistories);
			selectStmt.setInt(1, customerServiceID);
			results = selectStmt.executeQuery();


			while(results.next()) {
				int ChatID = results.getInt("ChatID");

				Users user = usersDao.getUserFromUserID((results.getInt("userID")));
//				CustomerServices customerService = customerServicesDao.
//						getCustomerServiceById(results.getInt("CustomerServiceID"));

				Date TimeStamp = new Date(results.getTimestamp("TimeStamp").getTime());

				ChatHistories.ServiceTypeValue serviceType =
						ChatHistories.ServiceTypeValue.valueOf(results.getString("ServiceType"));


				ChatHistories chatHistory = new ChatHistories(ChatID,cs1, user,
						TimeStamp, serviceType);

				chatHistories.add(chatHistory);


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
		return chatHistories;
	}
}


