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

public class CarsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CarsDao instance = null;
	protected CarsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CarsDao getInstance() {
		if(instance == null) {
			instance = new CarsDao();
		}
		return instance;
	}
	
	public Cars create(Cars cars) throws SQLException {
		String insertCar =
				"INSERT INTO Cars(UserID,Year,Maker,Model,Trim,Body,Transmission,VIN,State,ConditionScore,OdoMeter,Color,Interior,MMR) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// BlogPosts has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertCar,
				Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, cars.getUser().getUserID());
			insertStmt.setInt(2, cars.getYear());
			insertStmt.setString(3, cars.getMaker());
			insertStmt.setString(4, cars.getModel());
			insertStmt.setString(5, cars.getTrim());
			insertStmt.setString(6, cars.getBody());
			insertStmt.setString(7, cars.getTransmission());
			insertStmt.setString(8, cars.getVIN());
			insertStmt.setString(9, cars.getState());
			insertStmt.setFloat(10, cars.getConditionScore());
			insertStmt.setInt(11, cars.getOdoMeter());
			insertStmt.setString(12, cars.getColor());
			insertStmt.setString(13, cars.getInterior());
			insertStmt.setInt(14, cars.getMMR());
			insertStmt.executeUpdate();
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int carID = -1;
			if(resultKey.next()) {
				carID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			cars.setCarID(carID);
			return cars;
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
	
	public Cars updateUser(Cars car, Users newUser) throws SQLException {
		String updateCar = "UPDATE Cars SET UserID=? WHERE CarID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setInt(1, newUser.getUserID());
			updateStmt.setInt(2, car.getCarID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			car.setUser(newUser);
			return car;
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
	
	public Cars updateConditionScore(Cars cars, Float newConditionScore) throws SQLException {
		String updateCar = "UPDATE Cars SET ConditionScore=? WHERE CarID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setFloat(1, newConditionScore);
			updateStmt.setInt(2, cars.getCarID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			cars.setConditionScore(newConditionScore);
			return cars;
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
	
	public Cars updateOdoMeter(Cars cars, Integer newOdoMeter) throws SQLException {
		String updateCar = "UPDATE Cars SET OdoMeter=? WHERE CarID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setInt(1, newOdoMeter);
			updateStmt.setInt(2, cars.getCarID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			cars.setOdoMeter(newOdoMeter);
			return cars;
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

	public Cars updateMMR(Cars cars, Integer newMMR) throws SQLException {
		String updateCar = "UPDATE Cars SET MMR=? WHERE CarID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setInt(1, newMMR);
			updateStmt.setInt(2, cars.getCarID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			cars.setMMR(newMMR);
			return cars;
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
	
	public Cars delete(Cars car) throws SQLException {
		String deleteCar = "DELETE FROM Cars WHERE CarID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCar);
			deleteStmt.setInt(1, car.getCarID());
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
	
	public Cars getCarById(int carID) throws SQLException {
		String selectCar =
			"SELECT CarID,UserID,Year,Maker,Model,Trim,Body,Transmission,VIN,State,ConditionScore,OdoMeter,Color,Interior,MMR " +
			"FROM Cars " +
			"WHERE CarID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UsersDao usersDao = UsersDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setInt(1, carID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultCarID = results.getInt("CarID");
				Users user = usersDao.getUserFromUserID(results.getInt("UserID"));
				Integer year = results.getInt("Year");
				String maker = results.getString("Maker");
				String model = results.getString("Model");
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
				
				Cars car = new Cars(resultCarID,user,year,maker,model,trim,body,transmission,vin,state,conditionScore,odoMeter,color,interior,mmr);
				return car;
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
	
	public List<Cars> getCarForUser(Users users) throws SQLException {
		List<Cars> cars = new ArrayList<Cars>();
		String selectCars =
				"SELECT CarID,UserID,Year,Maker,Model,Trim,Body,Transmission,VIN,State,ConditionScore,OdoMeter,Color,Interior,MMR " +
				"FROM Cars " +
				"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UsersDao usersDao = UsersDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCars);
			selectStmt.setInt(1, users.getUserID());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultCarID = results.getInt("CarID");
				Users user = usersDao.getUserFromUserID(results.getInt("UserID"));
				Integer year = results.getInt("Year");
				String maker = results.getString("Maker");
				String model = results.getString("Model");
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
				
				Cars car = new Cars(resultCarID,user,year,maker,model,trim,body,transmission,vin,state,conditionScore,odoMeter,color,interior,mmr);
				cars.add(car);
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
		return cars;
	}
}
