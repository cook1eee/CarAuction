package carAuction.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import carAuction.model.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CollectionsDao {
  protected ConnectionManager connectionManager;
  private static CollectionsDao instance = null;
  protected CollectionsDao() {
    connectionManager = new ConnectionManager();
  }
  public static CollectionsDao getInstance() {
    if(instance == null) {
      instance = new CollectionsDao();
    }
    return instance;
  }

  public Collections create(Collections collection) throws SQLException {
    String insertCollection =
        "INSERT INTO Collections(userID,auctionID,priceChangeAlert,"
            + "statusChangeAlert) " +
            "VALUES(?,?,?,?);";

    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCollection,
          Statement.RETURN_GENERATED_KEYS);
      
      insertStmt.setInt(1, collection.getuser().getUserID());
      insertStmt.setInt(2, collection.getauction().getAuctionID());
      insertStmt.setBoolean(3, collection.getPriceChangeAlert());
      insertStmt.setBoolean(4, collection.getStatusChangeAlert());
      insertStmt.executeUpdate();

      return collection;

    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }
      if (resultKey != null) {
        resultKey.close();
      }
    }
  }


  public Collections updatePriceChangeAlert(Collections collection, Boolean newPriceChangeAlert)
      throws SQLException {
    String updateCollection = "UPDATE Collections SET PriceChangeAlert =? WHERE CollectionId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateCollection);
      updateStmt.setBoolean(1, newPriceChangeAlert);
      updateStmt.setInt(2, collection.getCollectionId());
      updateStmt.executeUpdate();

      // Update the blogComment param before returning to the caller.
      collection.setPriceChangeAlert(newPriceChangeAlert);
      return collection;
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

  public Collections delete(Collections collection) throws SQLException {
    String deleteCollection = "DELETE FROM Collections WHERE CollectionId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteCollection);
      deleteStmt.setInt(1, collection.getCollectionId());
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

  public Collections getCollectionById(int CollectionId) throws SQLException {
    String selectCollection =
        "SELECT CollectionId,UserID,AuctionID,PriceChangeAlert,StatusChangeAlert " +
            "FROM Collections " +
//            "WHERE CollectionId = ?;";
    		"WHERE CollectionId = 1;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    AuctionsDao auctionsDao = AuctionsDao.getInstance();
	UsersDao usersDao = UsersDao.getInstance();
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCollection);
//      selectStmt.setInt(1, CollectionId);
      results = selectStmt.executeQuery();

      if(results.next()) {
        int resultCollectionId = results.getInt("CollectionId");
        Auctions auction = auctionsDao.getAuctionById(results.getInt("AuctionID"));
		Users user = usersDao.getUserFromUserID(results.getInt("UserID"));
        Boolean PriceChangeAlert = results.getBoolean("PriceChangeAlert");
        Boolean StatusChangeAlert = results.getBoolean("StatusChangeAlert");

        Collections collection = new Collections(resultCollectionId, user,
            auction, PriceChangeAlert, StatusChangeAlert);
        return collection;
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
  
	public List<Collections> getCollectionForUser(Users user) throws SQLException {
		List<Collections> Collections = new ArrayList<Collections>();
		String selectCollection =
			"SELECT CollectionId,UserID,AuctionID,PriceChangeAlert,StatusChangeAlert " +
			"FROM Collections " +
			"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCollection);
			selectStmt.setInt(1, user.getUserID());
			results = selectStmt.executeQuery();
			
			//!!!-----------no auction class yet-------------------!!!
			AuctionsDao auctionsDao = AuctionsDao.getInstance();
			
			while(results.next()) {
				int resultCollectionID = results.getInt("CollectionID");
				
				//!!!-----------no auction class yet-------------------!!!
				int auctionID = results.getInt("AuctionID");
				Auctions auction = auctionsDao.getAuctionById(auctionID);
				
				Boolean PriceChangeAlert = results.getBoolean("PriceChangeAlert");
		        Boolean StatusChangeAlert = results.getBoolean("StatusChangeAlert");
		        
				Collections Collection = new Collections(resultCollectionID, user,
			            auction, PriceChangeAlert, StatusChangeAlert);
				
				Collections.add(Collection);
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
		return Collections;
	}
  
}



