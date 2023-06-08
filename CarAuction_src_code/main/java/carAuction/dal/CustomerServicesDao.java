package carAuction.dal;

import carAuction.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;


public class CustomerServicesDao {
	protected ConnectionManager connectionManager;

	private static CustomerServicesDao instance = null;
	protected CustomerServicesDao() {
		connectionManager = new ConnectionManager();
	}
	public static CustomerServicesDao getInstance() {
		if(instance == null) {
			instance = new CustomerServicesDao();
		}
		return instance;
	}

	public CustomerServices create(CustomerServices customerServices) throws SQLException {
		String insertCustomerService =
			"INSERT INTO CustomerServices(FirstName,LastName) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// BlogPosts has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertCustomerService, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, customerServices.getFirstName());
			insertStmt.setString(2, customerServices.getLastName());
			insertStmt.executeUpdate();
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int customerServiceID = -1;
			if(resultKey.next()) {
				customerServiceID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			customerServices.setCustomerServiceID(customerServiceID);
			return customerServices;
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

	/**
	 * Delete the CustomerService instance.
	 * This runs a DELETE statement.
	 */
	public CustomerServices delete(CustomerServices customerServices) throws SQLException {
		String deleteCustomerService = "DELETE FROM CustomerServices WHERE customerServiceID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCustomerService);
			deleteStmt.setInt(1, customerServices.getCustomerServiceID());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogComments instance.
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

	/**
	 * Get the CustomerService record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single CustomerService instance.
	 */
	public CustomerServices getCustomerServiceById(int CustomerServiceID) throws SQLException {
		String selectCustomerService =
			"SELECT CustomerServiceID,FirstName,LastName " +
			"FROM CustomerServices " +
			"WHERE CustomerServiceID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCustomerService);
			selectStmt.setInt(1, CustomerServiceID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultCustomerServiceID = results.getInt("CustomerServiceID");
				String FirstName = results.getString("FirstName");
				String LastName = results.getString("LastName");
				CustomerServices customerService = new CustomerServices(resultCustomerServiceID, FirstName,
						LastName);
				return customerService;
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
	
	// zephyr
	public List<Integer> getKeyRange() throws SQLException {
		List<Integer> keyRange = new ArrayList<>();
		String selectCustomerService =
				"SELECT CustomerServiceID " +
				"FROM CustomerServices ";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCustomerService);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Integer customerServiceID = results.getInt("CustomerServiceID");
				keyRange.add(customerServiceID);
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
		return keyRange;
	}

}