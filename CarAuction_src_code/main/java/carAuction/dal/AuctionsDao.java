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
import java.util.HashMap;

import carAuction.model.*;

public class AuctionsDao {
protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static AuctionsDao instance = null;
	protected AuctionsDao() {
		connectionManager = new ConnectionManager();
	}
	public static AuctionsDao getInstance() {
		if(instance == null) {
			instance = new AuctionsDao();
		}
		return instance;
	}
	
	public Auctions create(Auctions auctions) throws SQLException {
		String insertAuction =
				"INSERT INTO Auctions(Title,StartTime,EndTime,CarID,UserID,Highlights,Pictures,MinimumPrice,CurrentHighestPrice,AuctionStatus,CustomerServiceID,PriceChangeAlert) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// BlogPosts has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertAuction,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, auctions.getTitle());
			insertStmt.setTimestamp(2, new Timestamp(auctions.getStartTime().getTime()));
			insertStmt.setTimestamp(3, new Timestamp(auctions.getEndTime().getTime()));
			insertStmt.setInt(4, auctions.getCar().getCarID());
			insertStmt.setInt(5, auctions.getUser().getUserID());
			insertStmt.setString(6, auctions.getHighlights());
			insertStmt.setString(7, auctions.getPictures());
			insertStmt.setFloat(8, auctions.getMinimumPrice());
			insertStmt.setFloat(9, auctions.getCurrentHighestPrice());
			insertStmt.setString(10, auctions.getAuctionStatus().name());
			insertStmt.setInt(11, auctions.getCustomerService().getCustomerServiceID());
			insertStmt.setBoolean(12, auctions.getPriceChangeAlert());
			insertStmt.executeUpdate();
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int auctionID = -1;
			if(resultKey.next()) {
				auctionID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			auctions.setAuctionID(auctionID);
			
			return auctions;
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
	
	public Auctions updateTitle(Auctions auctions, String newTitle) throws SQLException {
		String updateAuction = "UPDATE Auctions SET Title=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAuction);
			updateStmt.setString(1, newTitle);
			updateStmt.setInt(2, auctions.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auctions.setTitle(newTitle);
			return auctions;
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
	
	public Auctions updateEndTime(Auctions auctions, Date newEndTime) throws SQLException {
		String updateAuction = "UPDATE Auctions SET EndTime=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAuction);
			updateStmt.setTimestamp(1, new Timestamp(newEndTime.getTime()));
			updateStmt.setInt(2, auctions.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auctions.setEndTime(newEndTime);
			return auctions;
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
	
	public Auctions updateHighlights(Auctions auction, String newHighlights) throws SQLException {
		String updateAuction = "UPDATE Auctions SET Highlights=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAuction);
			updateStmt.setString(1, newHighlights);
			updateStmt.setInt(2, auction.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auction.setHighlights(newHighlights);
			return auction;
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
	
	public Auctions updatePictures(Auctions auction, String newPictures) throws SQLException {
		String updateAuction = "UPDATE Auctions SET Pictures=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAuction);
			updateStmt.setString(1, newPictures);
			updateStmt.setInt(2, auction.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auction.setPictures(newPictures);
			return auction;
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
	
	public Auctions updateCurrentHighestPrice(Auctions auctions, Float newCurrentHighestPrice) throws SQLException {
		String updateAuction = "UPDATE Auctions SET CurrentHighestPrice=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAuction);
			updateStmt.setFloat(1, newCurrentHighestPrice);
			updateStmt.setInt(2, auctions.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auctions.setCurrentHighestPrice(newCurrentHighestPrice);
			return auctions;
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
	
	public Auctions updateAuctionStatus(Auctions auctions, Auctions.AuctionStatusValue newAuctionStatus) throws SQLException {
		String updateAuction = "UPDATE Auctions SET AuctionStatus=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAuction);
			updateStmt.setString(1, newAuctionStatus.name());
			updateStmt.setInt(2, auctions.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auctions.setAuctionStatus(newAuctionStatus);
			return auctions;
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
	
	public Auctions updateCustomerService(Auctions auctions, CustomerServices newCustomerService) throws SQLException {
		String updateAuction = "UPDATE Auctions SET CustomerServiceID=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAuction);
			updateStmt.setInt(1, newCustomerService.getCustomerServiceID());
			updateStmt.setInt(2, auctions.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auctions.setCustomerService(newCustomerService);
			return auctions;
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
	
	public Auctions updatePriceChangeAlert(Auctions auctions, Boolean newPriceChangeAlert) throws SQLException {
		String updateAuction = "UPDATE Auctions SET PriceChangeAlert=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAuction);
			updateStmt.setBoolean(1, newPriceChangeAlert);
			updateStmt.setInt(2, auctions.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auctions.setPriceChangeAlert(newPriceChangeAlert);
			return auctions;
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
	
	public Auctions delete(Auctions auction) throws SQLException {
		String deleteAuction = "DELETE FROM Auctions WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAuction);
			deleteStmt.setInt(1, auction.getAuctionID());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Car instance.
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
	
	public Auctions getAuctionById(int auctionID) throws SQLException {
		String selectAuction =
			"SELECT AuctionID,Title,StartTime,EndTime,CarID,UserID,Highlights,Pictures,MinimumPrice,CurrentHighestPrice,AuctionStatus,CustomerServiceID,PriceChangeAlert " +
			"FROM Auctions " +
			"WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CarsDao carsDao = CarsDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		CustomerServicesDao customerServicesDao = CustomerServicesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuction);
			selectStmt.setInt(1, auctionID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultAuctionID = results.getInt("AuctionID");
				String title = results.getString("Title");
				Date startTime =  new Date(results.getTimestamp("StartTime").getTime());
				Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
				Cars car = carsDao.getCarById(results.getInt("CarID"));
				Users user = usersDao.getUserFromUserID(results.getInt("UserID"));
				String highlights = results.getString("Highlights");
				String pictures = results.getString("Pictures");
				Float minimumPrice = results.getFloat("MinimumPrice");
				Float currentHighestPrice = results.getFloat("CurrentHighestPrice");
				Auctions.AuctionStatusValue auctionStatus = Auctions.AuctionStatusValue.valueOf(results.getString("AuctionStatus"));
				CustomerServices customerService = customerServicesDao.getCustomerServiceById(results.getInt("CustomerServiceID"));
				Boolean priceChangeAlert = results.getBoolean("PriceChangeAlert");
				
				Auctions auction = new Auctions(resultAuctionID,title,startTime,endTime,car,user,highlights,pictures,minimumPrice,currentHighestPrice,auctionStatus,customerService,priceChangeAlert);
				return auction;
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
	
	public List<Auctions> getAuctionForUser(Users users) throws SQLException {
		List<Auctions> auctions = new ArrayList<Auctions>();
		String selectAuctions =
				"SELECT AuctionID,Title,StartTime,EndTime,CarID,UserID,Highlights,Pictures,MinimumPrice,CurrentHighestPrice,AuctionStatus,CustomerServiceID,PriceChangeAlert " +
				"FROM Auctions " +
				"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CarsDao carsDao = CarsDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		CustomerServicesDao customerServicesDao = CustomerServicesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuctions);
			selectStmt.setInt(1, users.getUserID());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int auctionID = results.getInt("AuctionID");
				String title = results.getString("Title");
				Date startTime =  new Date(results.getTimestamp("StartTime").getTime());
				Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
				Cars cars = carsDao.getCarById(results.getInt("CarID"));
				Users user = usersDao.getUserFromUserID(results.getInt("UserID"));
				String highlights = results.getString("Highlights");
				String pictures = results.getString("Pictures");
				Float minimumPrice = results.getFloat("MinimumPrice");
				Float currentHighestPrice = results.getFloat("CurrentHighestPrice");
				Auctions.AuctionStatusValue auctionStatus = Auctions.AuctionStatusValue.valueOf(results.getString("AuctionStatus"));
				CustomerServices customerServices = customerServicesDao.getCustomerServiceById(results.getInt("CustomerServiceID"));
				Boolean priceChangeAlert = results.getBoolean("PriceChangeAlert");
				
				Auctions auction = new Auctions(auctionID,title,startTime,endTime,cars,user,highlights,pictures,minimumPrice,currentHighestPrice,auctionStatus,customerServices,priceChangeAlert);
				auctions.add(auction);
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
		return auctions;
	}
	
	public List<Auctions> getAuctionForCar(Cars cars) throws SQLException {
		List<Auctions> auctions = new ArrayList<Auctions>();
		String selectAuctions =
				"SELECT AuctionID,Title,StartTime,EndTime,CarID,UserID,Highlights,Pictures,MinimumPrice,CurrentHighestPrice,AuctionStatus,CustomerServiceID,PriceChangeAlert " +
				"FROM Auctions " +
				"WHERE CarID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CarsDao carsDao = CarsDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		CustomerServicesDao customerServicesDao = CustomerServicesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuctions);
			selectStmt.setInt(1, cars.getCarID());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int auctionID = results.getInt("AuctionID");
				String title = results.getString("Title");
				Date startTime =  new Date(results.getTimestamp("StartTime").getTime());
				Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
				Cars carByID = carsDao.getCarById(results.getInt("CarID"));
				Users user = usersDao.getUserFromUserID(results.getInt("UserID"));
				String highlights = results.getString("Highlights");
				String pictures = results.getString("Pictures");
				Float minimumPrice = results.getFloat("MinimumPrice");
				Float currentHighestPrice = results.getFloat("CurrentHighestPrice");
				Auctions.AuctionStatusValue auctionStatus = Auctions.AuctionStatusValue.valueOf(results.getString("AuctionStatus"));
				CustomerServices customerService = customerServicesDao.getCustomerServiceById(results.getInt("CustomerServiceID"));
				Boolean priceChangeAlert = results.getBoolean("PriceChangeAlert");
				
				Auctions auction = new Auctions(auctionID,title,startTime,endTime,carByID,user,highlights,pictures,minimumPrice,currentHighestPrice,auctionStatus,customerService,priceChangeAlert);
				auctions.add(auction);
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
		return auctions;
	}
	
	public List<Auctions> getAuctionForCustomerService(CustomerServices customerServices) throws SQLException {
		List<Auctions> auctions = new ArrayList<Auctions>();
		String selectAuctions =
				"SELECT AuctionID,Title,StartTime,EndTime,CarID,UserID,Highlights,Pictures,MinimumPrice,CurrentHighestPrice,AuctionStatus,CustomerServiceID,PriceChangeAlert " +
				"FROM Auctions " +
				"WHERE CustomerServiceID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CarsDao carDao = CarsDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		CustomerServicesDao customerServicesDao = CustomerServicesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuctions);
			selectStmt.setInt(1, customerServices.getCustomerServiceID());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int auctionID = results.getInt("AuctionID");
				String title = results.getString("Title");
				Date startTime =  new Date(results.getTimestamp("StartTime").getTime());
				Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
				Cars car = carDao.getCarById(results.getInt("CarID"));
				Users user = usersDao.getUserFromUserID(results.getInt("UserID"));
				String highlights = results.getString("Highlights");
				String pictures = results.getString("Pictures");
				Float minimumPrice = results.getFloat("MinimumPrice");
				Float currentHighestPrice = results.getFloat("CurrentHighestPrice");
				Auctions.AuctionStatusValue auctionStatus = Auctions.AuctionStatusValue.valueOf(results.getString("AuctionStatus"));
				CustomerServices resultCustomerService = customerServicesDao.getCustomerServiceById(results.getInt("CustomerServiceID"));
				Boolean priceChangeAlert = results.getBoolean("PriceChangeAlert");
				
				Auctions auction = new Auctions(auctionID,title,startTime,endTime,car,user,highlights,pictures,minimumPrice,currentHighestPrice,auctionStatus,resultCustomerService,priceChangeAlert);
				auctions.add(auction);
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
		return auctions;
	}
	
	public List<Pair<Auctions, Cars>> getAuctionByMakerAndModel(String maker, String model) throws SQLException {
		List<Pair<Auctions, Cars>> searchResults = new ArrayList<>();
		String selectAuctions =
				"SELECT AuctionID,Title,StartTime,EndTime,Auctions.CarID,Auctions.UserID,Highlights,Pictures,MinimumPrice,CurrentHighestPrice,AuctionStatus,CustomerServiceID,PriceChangeAlert,Cars.UserID,Year,Maker,Model,Trim,Body,Transmission,VIN,State,ConditionScore,OdoMeter,Color,Interior,MMR " +
				"FROM Auctions JOIN Cars USING (CarID)" +
				"WHERE maker=? and model=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CarsDao carDao = CarsDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		CustomerServicesDao customerServicesDao = CustomerServicesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuctions);
			selectStmt.setString(1, maker);
			selectStmt.setString(2, model);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int auctionID = results.getInt("AuctionID");
				String title = results.getString("Title");
				Date startTime =  new Date(results.getTimestamp("StartTime").getTime());
				Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
				Cars car = carDao.getCarById(results.getInt("Auctions.CarID"));
				Users auctionUser = usersDao.getUserFromUserID(results.getInt("Auctions.UserID"));
				String highlights = results.getString("Highlights");
				String pictures = results.getString("Pictures");
				Float minimumPrice = results.getFloat("MinimumPrice");
				Float currentHighestPrice = results.getFloat("CurrentHighestPrice");
				Auctions.AuctionStatusValue auctionStatus = Auctions.AuctionStatusValue.valueOf(results.getString("AuctionStatus"));
				CustomerServices customerService = customerServicesDao.getCustomerServiceById(results.getInt("CustomerServiceID"));
				Boolean priceChangeAlert = results.getBoolean("PriceChangeAlert");
				
				int resultCarID = results.getInt("CarID");
				Users carUser = usersDao.getUserFromUserID(results.getInt("Cars.UserID"));
				Integer year = results.getInt("Year");
				String resultMaker = results.getString("Maker");
				String resultModel = results.getString("Model");
				String trim = results.getString("Trim");
				String body = results.getString("Body");
				String transmission = results.getString("Transmission");
				String vin = results.getString("VIN");
				String state = results.getString("State");
				Float conditionScore = results.getFloat("ConditionScore");
				Integer odoMeter = results.getInt("OdoMeter");
				String color = results.getString("Color");
				String interior = results.getString("Interior");
				Integer mmr = results.getInt("MMR");
				
				Auctions auction = new Auctions(auctionID,title,startTime,endTime,car,auctionUser,highlights,pictures,minimumPrice,currentHighestPrice,auctionStatus,customerService,priceChangeAlert);
				Cars resuktCar = new Cars(resultCarID,carUser,year,maker,model,trim,body,transmission,vin,state,conditionScore,odoMeter,color,interior,mmr);
				
				searchResults.add(new Pair<Auctions, Cars>(auction, resuktCar));
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
		return searchResults;
	}
	
	public List<Pair<Auctions, Cars>> getAuctionByBidPriceRange(float lower, float upper, String order) throws SQLException {
		List<Pair<Auctions, Cars>> searchResults = new ArrayList<>();
		String selectAuctions =
				"SELECT AuctionID,Title,StartTime,EndTime,Auctions.CarID,Auctions.UserID,Highlights,Pictures,MinimumPrice,CurrentHighestPrice,AuctionStatus,CustomerServiceID,PriceChangeAlert,Cars.UserID,Year,Maker,Model,Trim,Body,Transmission,VIN,State,ConditionScore,OdoMeter,Color,Interior,MMR " +
				"FROM Auctions JOIN Cars USING (CarID)" +
				"WHERE CurrentHighestPrice >= ? and CurrentHighestPrice <= ? " + 
				"ORDER BY CurrentHighestPrice " + order + ";";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CarsDao carDao = CarsDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		CustomerServicesDao customerServicesDao = CustomerServicesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuctions);
			selectStmt.setFloat(1, lower);
			selectStmt.setFloat(2, upper);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int auctionID = results.getInt("AuctionID");
				String title = results.getString("Title");
				Date startTime =  new Date(results.getTimestamp("StartTime").getTime());
				Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
				Cars car = carDao.getCarById(results.getInt("Auctions.CarID"));
				Users auctionUser = usersDao.getUserFromUserID(results.getInt("Auctions.UserID"));
				String highlights = results.getString("Highlights");
				String pictures = results.getString("Pictures");
				Float minimumPrice = results.getFloat("MinimumPrice");
				Float currentHighestPrice = results.getFloat("CurrentHighestPrice");
				Auctions.AuctionStatusValue auctionStatus = Auctions.AuctionStatusValue.valueOf(results.getString("AuctionStatus"));
				CustomerServices customerService = customerServicesDao.getCustomerServiceById(results.getInt("CustomerServiceID"));
				Boolean priceChangeAlert = results.getBoolean("PriceChangeAlert");
				
				int resultCarID = results.getInt("CarID");
				Users carUser = usersDao.getUserFromUserID(results.getInt("Cars.UserID"));
				Integer year = results.getInt("Year");
				String resultMaker = results.getString("Maker");
				String resultModel = results.getString("Model");
				String trim = results.getString("Trim");
				String body = results.getString("Body");
				String transmission = results.getString("Transmission");
				String vin = results.getString("VIN");
				String state = results.getString("State");
				Float conditionScore = results.getFloat("ConditionScore");
				Integer odoMeter = results.getInt("OdoMeter");
				String color = results.getString("Color");
				String interior = results.getString("Interior");
				Integer mmr = results.getInt("MMR");
				
				Auctions auction = new Auctions(auctionID,title,startTime,endTime,car,auctionUser,highlights,pictures,minimumPrice,currentHighestPrice,auctionStatus,customerService,priceChangeAlert);
				Cars resuktCar = new Cars(resultCarID,carUser,year,resultMaker,resultModel,trim,body,transmission,vin,state,conditionScore,odoMeter,color,interior,mmr);
				
				searchResults.add(new Pair<Auctions, Cars>(auction, resuktCar));
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
		return searchResults;
	}
	
	public List<Pair<Auctions, Cars>> getClosedAuction() throws SQLException {
		List<Pair<Auctions, Cars>> searchResults = new ArrayList<>();
		String selectAuctions =
				"SELECT AuctionID,Title,StartTime,EndTime,Auctions.CarID,Auctions.UserID,Highlights,Pictures,MinimumPrice,CurrentHighestPrice,AuctionStatus,CustomerServiceID,PriceChangeAlert,Cars.UserID,Year,Maker,Model,Trim,Body,Transmission,VIN,State,ConditionScore,OdoMeter,Color,Interior,MMR " +
				"FROM Auctions JOIN Cars USING (CarID)" +
				"WHERE AuctionStatus = 'Succeed' " +
				"ORDER BY CurrentHighestPrice DESC";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CarsDao carDao = CarsDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		CustomerServicesDao customerServicesDao = CustomerServicesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuctions);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int auctionID = results.getInt("AuctionID");
				String title = results.getString("Title");
				Date startTime =  new Date(results.getTimestamp("StartTime").getTime());
				Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
				Cars car = carDao.getCarById(results.getInt("Auctions.CarID"));
				Users auctionUser = usersDao.getUserFromUserID(results.getInt("Auctions.UserID"));
				String highlights = results.getString("Highlights");
				String pictures = results.getString("Pictures");
				Float minimumPrice = results.getFloat("MinimumPrice");
				Float currentHighestPrice = results.getFloat("CurrentHighestPrice");
				Auctions.AuctionStatusValue auctionStatus = Auctions.AuctionStatusValue.valueOf(results.getString("AuctionStatus"));
				CustomerServices customerService = customerServicesDao.getCustomerServiceById(results.getInt("CustomerServiceID"));
				Boolean priceChangeAlert = results.getBoolean("PriceChangeAlert");
				
				int resultCarID = results.getInt("CarID");
				Users carUser = usersDao.getUserFromUserID(results.getInt("Cars.UserID"));
				Integer year = results.getInt("Year");
				String resultMaker = results.getString("Maker");
				String resultModel = results.getString("Model");
				String trim = results.getString("Trim");
				String body = results.getString("Body");
				String transmission = results.getString("Transmission");
				String vin = results.getString("VIN");
				String state = results.getString("State");
				Float conditionScore = results.getFloat("ConditionScore");
				Integer odoMeter = results.getInt("OdoMeter");
				String color = results.getString("Color");
				String interior = results.getString("Interior");
				Integer mmr = results.getInt("MMR");
				
				Auctions auction = new Auctions(auctionID,title,startTime,endTime,car,auctionUser,highlights,pictures,minimumPrice,currentHighestPrice,auctionStatus,customerService,priceChangeAlert);
				Cars resuktCar = new Cars(resultCarID,carUser,year,resultMaker,resultModel,trim,body,transmission,vin,state,conditionScore,odoMeter,color,interior,mmr);
				
				searchResults.add(new Pair<Auctions, Cars>(auction, resuktCar));
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
		return searchResults;
	}
	
	public List<Pair<String, String>> getTop20PopularCarInSell() throws SQLException {
		List<Pair<String, String>> searchResults = new ArrayList<>();
		String selectAuctions =
				"SELECT Maker, Model, COUNT(*) as numAuction "
				+ "FROM Auctions JOIN Cars USING (CarID) "
				+ "WHERE AuctionStatus = 'Succeed' OR AuctionStatus = 'Active' "
				+ "GROUP BY Maker, Model "
				+ "ORDER BY numAuction DESC "
				+ "LIMIT 20;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuctions);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String maker = results.getString("Maker");
				String model = results.getString("Model");
				
				searchResults.add(new Pair<String, String>(maker, model));
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
		return searchResults;
	}
	
	public List<Pair<String, String>> getTop10CollectedCarModel() throws SQLException {
		List<Pair<String, String>> searchResults = new ArrayList<>();
		String selectAuctions =
				"SELECT c.maker, c.model, count(col.collectionID) AS 'collection_count' "
				+ "FROM Cars c "
				+ "         INNER JOIN Auctions a "
				+ "                    ON c.carID = a.carID "
				+ "         INNER JOIN Collections col "
				+ "                    ON col.auctionID = a.auctionID "
				+ "GROUP BY c.maker, c.model "
				+ "ORDER BY count(col.collectionID) DESC "
				+ "LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuctions);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String maker = results.getString("c.maker");
				String model = results.getString("c.model");
				
				searchResults.add(new Pair<String, String>(maker, model));
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
		return searchResults;
	}
	
	public List<HashMap<String, String>> getMonthlySales() throws SQLException {
		List<HashMap<String, String>> resultList = new ArrayList<>();
		String selectAuctions =
				"SELECT YEAR(EndTime) AS year, MONTH(EndTime) AS month, SUM(CurrentHighestPrice) AS total_sales " +
				"FROM Auctions " +
				"WHERE AuctionStatus = 'Succeed' " +
				"GROUP BY year, month " + 
				" HAVING total_sales > 20000 " +
				" ORDER BY total_sales DESC; ";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuctions);
			results = selectStmt.executeQuery();
			while(results.next()) {
				HashMap<String, String> resultMap = new HashMap<>();
				String year = Integer.toString(results.getInt("year"));
				String month = Integer.toString(results.getInt("month"));
				String total_sales = Float.toString(results.getFloat("total_sales"));
				resultMap.put("year", year);
				resultMap.put("month", month);
				resultMap.put("total_sales", total_sales);
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
	
//	public List<Auctions> getHighestPriceForEachAuction(CustomerServices customerServices) throws SQLException {
//	List<Auctions> auctions = new ArrayList<Auctions>();
//	String selectAuctions =
//			"SELECT Auction.AuctionID, Auction.Title, Bid.BidPrice as HighestBid, Bid.UserID as Bidder" +
//			"FROM Auction" +
//			"LEFT JOIN Bid ON Auction.AuctionID = Bid.AuctionID" +
//			"WHERE Bid.BidPrice = (" +
//			"SELECT MAX(BidPrice) FROM Bid WHERE Bid.AuctionID = Auction.AuctionID" +
//			");";
//	Connection connection = null;
//	PreparedStatement selectStmt = null;
//	ResultSet results = null;
//	CarsDao carDao = CarsDao.getInstance();
//	UsersDao usersDao = UsersDao.getInstance();
//	CustomerServicesDao customerServicesDao = CustomerServicesDao.getInstance();
//	try {
//		
//	
//	}
//}

	public class Pair<T, U> {         
	    public final T t;
	    public final U u;

	    public Pair(T t, U u) {         
	        this.t= t;
	        this.u= u;
	    }

		public T getT() {
			return t;
		}

		public U getU() {
			return u;
		}
	 }
}
