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
import java.util.HashMap;

public class BidsDao {
	protected ConnectionManager connectionManager;

	private static BidsDao instance = null;
	protected BidsDao() {
		connectionManager = new ConnectionManager();
	}
	public static BidsDao getInstance() {
		if(instance == null) {
			instance = new BidsDao();
		}
		return instance;
	}

	public Bids create(Bids bids) throws SQLException {
		String insertBid =
			"INSERT INTO Bids(AuctionID,UserID,BidTime,BidPrice) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// BlogPosts has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertBid,
				Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, bids.getAuction().getAuctionID());
			insertStmt.setInt(2, bids.getUser().getUserID());
			insertStmt.setTimestamp(3, new Timestamp(bids.getBidTime().getTime()));
			insertStmt.setFloat(4, bids.getBidPrice());
			insertStmt.executeUpdate();
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int bidID = -1;
			if(resultKey.next()) {
				bidID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			bids.setBidID(bidID);
			return bids;
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
	 * Update the BidPrice of the Bid instance.
	 * This runs a UPDATE statement.
	 */
	public Bids updateBidPrice(Bids bids, float BidPrice) throws SQLException {
		String updateeBidPrice = "UPDATE Bids SET BidPrice=? WHERE BidID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateeBidPrice);
			updateStmt.setFloat(1, BidPrice);
			updateStmt.setInt(2, bids.getBidID());
			updateStmt.executeUpdate();

			// Update the bid param before returning to the caller.
			bids.setBidPrice(BidPrice);
			return bids;
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
	 * Delete the Bid instance.
	 * This runs a DELETE statement.
	 */
	public Bids delete(Bids bids) throws SQLException {
		String deleteBid = "DELETE FROM Bids WHERE BidID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBid);
			deleteStmt.setInt(1, bids.getBidID());
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
	 * Get the Bid record by fetching it from your MySQL instance.
	 */
	public Bids getBidById(int bidID) throws SQLException {
		String selectBid =
			"SELECT BidID,AuctionID,UserID,BidTime,BidPrice " +
			"FROM Bids " +
			"WHERE BidID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBid);
			selectStmt.setInt(1, bidID);
			results = selectStmt.executeQuery();
			AuctionsDao auctionsDao = AuctionsDao.getInstance();
			UsersDao userDao = UsersDao.getInstance();
			if(results.next()) {
				int resultBidID = results.getInt("bidID");
				Date bidTime =  new Date(results.getTimestamp("BidTime").getTime());
				float bidPrice = results.getFloat("BidPrice");
				Auctions auction = auctionsDao.getAuctionById(results.getInt("AuctionID"));
				Users user = userDao.getUserFromUserID(results.getInt("UserID"));
				Bids bids = new Bids(resultBidID, auction,
						user, bidTime, bidPrice);
				return bids;
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
	 * Get the all the Bid for a user.
	 */
	public List<Bids> getBidsForUser(Users user) throws SQLException {
		List<Bids> bids = new ArrayList<Bids>();
		String selectBid =
			"SELECT BidID,AuctionID,UserID,BidTime,BidPrice " +
			"FROM Bids " +
			"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBid);
			selectStmt.setInt(1, user.getUserID());
			results = selectStmt.executeQuery();
			AuctionsDao auctionsDao = AuctionsDao.getInstance();
			while(results.next()) {
				int bidID = results.getInt("BidID");
				Date bidTime =  new Date(results.getTimestamp("BidTime").getTime());
				float bidPrice = results.getFloat("BidPrice");
				Auctions auction = auctionsDao.getAuctionById(results.getInt("AuctionID"));
				Bids bid = new Bids(bidID, auction, user,
						bidTime, bidPrice);
				bids.add(bid);
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
		return bids;
	}
	
	/**
	 * Get the all the Bid for an auction.
	 */
	public List<Bids> getBidsForAuction(Auctions auctions) throws SQLException {
		List<Bids> bids = new ArrayList<Bids>();
		String selectBid =
			"SELECT BidID,AuctionID,UserID,BidTime,BidPrice " +
			"FROM Bids " +
			"WHERE AuctionID=? " +
			"ORDER BY BidTime;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBid);
			selectStmt.setInt(1, auctions.getAuctionID());
			results = selectStmt.executeQuery();
			UsersDao userDao = UsersDao.getInstance();
			while(results.next()) {
				int bidID = results.getInt("BidID");
				Date bidTime =  new Date(results.getTimestamp("BidTime").getTime());
				float bidPrice = results.getFloat("BidPrice");
				Users user = userDao.getUserFromUserID(results.getInt("UserID"));
				Bids bid = new Bids(bidID, auctions, user,
						bidTime, bidPrice);
				bids.add(bid);
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
		return bids;
	}
	
	public Float getMaxPriceForAuction(int auctionID) throws SQLException {
		Float maxPrice = -1.0F;
		String selectBid =
			"select AuctionID, max(BidPrice) as maxPrice from Bids where AuctionID = ? group by AuctionID;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBid);
			selectStmt.setInt(1, auctionID);
			results = selectStmt.executeQuery();
			UsersDao userDao = UsersDao.getInstance();
			if (results.next()) {
				maxPrice = results.getFloat("maxPrice");
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
		return maxPrice;
	}
	
	public List<HashMap<String, String>> getHighestBids() throws SQLException {
		String selectBid =
				"SELECT AuctionID, Title, BidID, Bids.BidPrice as HighestBid, Bids.UserID as BidderID, CONCAT(Users.FirstName, ' ', Users.LastName) as Bidder " +
				"FROM Auctions " +
				"LEFT JOIN Bids USING (AuctionID) " +
				"LEFT JOIN Users ON Bids.UserID = Users.UserID " +
				"WHERE Bids.BidPrice = ( " +
				"SELECT MAX(BidPrice) FROM Bids WHERE Bids.AuctionID = Auctions.AuctionID);";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<HashMap<String, String>> resultList = new ArrayList<>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBid);
			results = selectStmt.executeQuery();
			while(results.next()) {
				HashMap<String, String> resultMap = new HashMap<>();
				String bidID = Integer.toString(results.getInt("BidID"));
				String highestBidPrice = Float.toString(results.getFloat("HighestBid"));
				String auctionId = Integer.toString(results.getInt("AuctionID"));
				String bidderId = Integer.toString(results.getInt("BidderID"));
				String bidder = results.getString("Bidder");
				String title = results.getString("Title");
//				String auction = auctionsDao.getAuctionById(results.getInt("AuctionID"));	
				// String bidPrice = results.getFloat("HighestBid");
//				String user = userDao.getUserFromUserID(results.getInt("Bidder"));
				resultMap.put("BidID", bidID);
				resultMap.put("AuctionID", auctionId);
				resultMap.put("HighestBidPrice", highestBidPrice);
				resultMap.put("BidderId", bidderId);
				resultMap.put("Bidder", bidder);
				resultMap.put("Title", title);
				resultList.add(resultMap);
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
		return resultList;
	}

	public List<HashMap<String, String>> getHighestBidsByAuctionID(Auctions auction) throws SQLException {
		String selectBid =
				"SELECT AuctionID, Title, BidID, Bids.BidPrice as HighestBid, Bids.UserID as BidderID, CONCAT(FirstName, ' ', LastName) as Bidder " +
				"FROM Auctions " +
				"LEFT JOIN Bids USING (AuctionID) " +
				"LEFT JOIN Users ON Bids.UserID = Users.UserID " +
				"WHERE Bids.BidPrice = ( " +
				"SELECT MAX(BidPrice) FROM Bids WHERE Bids.AuctionID = Auctions.AuctionID) && Bids.AuctionID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<HashMap<String, String>> resultList = new ArrayList<>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBid);
			selectStmt.setInt(1, auction.getAuctionID());
			results = selectStmt.executeQuery();
			while(results.next()) {
				HashMap<String, String> resultMap = new HashMap<>();
				String bidID = Integer.toString(results.getInt("BidID"));
				String highestBidPrice = Float.toString(results.getFloat("HighestBid"));
				String auctionId = Integer.toString(results.getInt("AuctionID"));
				String bidderId = Integer.toString(results.getInt("BidderID"));
				String bidder = results.getString("Bidder");
				String title = results.getString("Title");
//				String auction = auctionsDao.getAuctionById(results.getInt("AuctionID"));	
				// String bidPrice = results.getFloat("HighestBid");
//				String user = userDao.getUserFromUserID(results.getInt("Bidder"));
				resultMap.put("BidID", bidID);
				resultMap.put("AuctionID", auctionId);
				resultMap.put("HighestBidPrice", highestBidPrice);
				resultMap.put("BidderId", bidderId);
				resultMap.put("Bidder", bidder);
				resultMap.put("Title", title);
				resultList.add(resultMap);
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
}
