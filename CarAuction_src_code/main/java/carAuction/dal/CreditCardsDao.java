package carAuction.dal;

import carAuction.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardsDao {
	protected ConnectionManager connectionManager;

	private static CreditCardsDao instance = null;
	protected CreditCardsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CreditCardsDao getInstance() {
		if(instance == null) {
			instance = new CreditCardsDao();
		}
		return instance;
	}
	
	
	public CreditCards create(CreditCards creditCard) throws SQLException {
		String insertCreditCard =
			"INSERT INTO CreditCards(CardNumber, UserID, ExpirationMonth, ExpirationYear, NameOnCard, ZipCode) " +
			"VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCreditCard);
            insertStmt.setString(1, creditCard.getCardNumber());
            insertStmt.setInt(2, creditCard.getUser().getUserID());
            insertStmt.setInt(3, creditCard.getExpirationMonth());
            insertStmt.setInt(4, creditCard.getExpirationYear());
            insertStmt.setString(5, creditCard.getNameOnCard());
            insertStmt.setString(6, creditCard.getZipCode());
			insertStmt.executeUpdate();

			return creditCard;
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

    /**
	 * Get the CreditCards record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single CreditCards instance.
	 * Note that we use UsersDao to retrieve the reference Users.
	 * to join the CreditCards, Users tables and then build each object.
	 */
	public CreditCards getCreditCardByCardNumber(String cardNumber) throws SQLException {
		String selectCreditCard =
			"SELECT CardNumber, UserID, ExpirationMonth, ExpirationYear, NameOnCard, ZipCode " +
			"FROM CreditCards " +
			"WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setString(1, cardNumber);
			results = selectStmt.executeQuery();
			UsersDao UserDao = UsersDao.getInstance();
			
			if(results.next()) {
				String resultcardNumber = results.getString("cardNumber");
				int userid = results.getInt("userID");
                int expirationMonth = results.getInt("ExpirationMonth");
                int expirationYear = results.getInt("ExpirationYear");
                String nameOnCard = results.getString("NameOnCard");
                String zipCode = results.getString("ZipCode");

				Users user = UserDao.getUserFromUserID(userid);

				CreditCards creditCard = new CreditCards(resultcardNumber, user, expirationMonth, 
						                                 expirationYear, nameOnCard, zipCode );
				return creditCard;
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


    /**
	 * Get the all the CreditCards for a user.
	 */	
	public List<CreditCards> getCreditCardsByUserID(int userid) throws SQLException {
		List<CreditCards> CreditCards = new ArrayList<CreditCards>();
		String selectCreditCards =
			"SELECT CardNumber, UserID, ExpirationMonth, ExpirationYear, NameOnCard, ZipCode " +
			"FROM CreditCards " + 
			"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCards);
			selectStmt.setInt(1, userid);
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			Users u1 = usersDao.getUserFromUserID(userid);
			
			while(results.next()) {
				String cardNumber = results.getString("cardNumber");
				int expirationMonth = results.getInt("ExpirationMonth");
                int expirationYear = results.getInt("ExpirationYear");
                String nameOnCard = results.getString("NameOnCard");
                String zipCode = results.getString("ZipCode");
				
				CreditCards CreditCard = new CreditCards(cardNumber, u1, expirationMonth, 
                        								 expirationYear, nameOnCard, zipCode);
				CreditCards.add(CreditCard);
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
		return CreditCards;
	}

    /**
	 * Update the Expirationdate of the CreditCards.
	 * This runs a UPDATE statement.
	 */
	public CreditCards updateNameOnCard(CreditCards creditCard, String newname) throws SQLException {
		String updateCard = "UPDATE CreditCards SET NameOnCard=? WHERE cardNumber=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCard);
			
			updateStmt.setString(1, newname);
			updateStmt.setString(2, creditCard.getCardNumber());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			creditCard.setNameOnCard(newname);
			return creditCard;
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


	/**
	 * Delete the CreditCards instance.
	 * This runs a DELETE statement.
	 */
	public CreditCards delete(CreditCards CreditCard) throws SQLException {
		String deleteCreditCard = "DELETE FROM CreditCards WHERE cardNumber=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCreditCard);
			deleteStmt.setString(1, CreditCard.getCardNumber());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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

	

	
}
