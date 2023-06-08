package carAuction.model;

public class Cars {
	protected int CarID;
	protected Users user;
	protected Integer Year;
	protected String Maker;
	protected String Model;
	protected String Trim;
	protected String Body;
	protected String Transmission;
	protected String VIN;
	protected String State;
	protected Float ConditionScore;
	protected Integer OdoMeter;
	protected String Color;
	protected String Interior;
	protected Integer MMR;
	
	public Cars(int carID, Users user, Integer year, String maker, String model, String trim, String body,
			String transmission, String vIN, String state, Float conditionScore, Integer odoMeter, String color,
			String interior, Integer mMR) {
		this.CarID = carID;
		this.user = user;
		this.Year = year;
		this.Maker = maker;
		this.Model = model;
		this.Trim = trim;
		this.Body = body;
		this.Transmission = transmission;
		this.VIN = vIN;
		this.State = state;
		this.ConditionScore = conditionScore;
		this.OdoMeter = odoMeter;
		this.Color = color;
		this.Interior = interior;
		this.MMR = mMR;
	}
	
	public Cars(Users user, Integer year, String maker, String model, String trim, String body,
			String transmission, String vIN, String state, Float conditionScore, Integer odoMeter, String color,
			String interior, Integer mMR) {
		this.user = user;
		this.Year = year;
		this.Maker = maker;
		this.Model = model;
		this.Trim = trim;
		this.Body = body;
		this.Transmission = transmission;
		this.VIN = vIN;
		this.State = state;
		this.ConditionScore = conditionScore;
		this.OdoMeter = odoMeter;
		this.Color = color;
		this.Interior = interior;
		this.MMR = mMR;
	}
	
	public Cars(int carID, Users user) {
		super();
		this.CarID = carID;
		this.user = user;
	}
	
	public Cars(int carID) {
		super();
		this.CarID = carID;
	}
	
	public int getCarID() {
		return this.CarID;
	}
	
	public void setCarID(int carID) {
		this.CarID = carID;
	}
	
	public Users getUser() {
		return this.user;
	}
	
	public void setUser(Users user) {
		this.user = user;
	}
	
	public Integer getYear() {
		return this.Year;
	}
	
	public void setYear(Integer year) {
		this.Year = year;
	}
	
	public String getMaker() {
		return this.Maker;
	}
	
	public void setMaker(String maker) {
		this.Maker = maker;
	}
	
	public String getModel() {
		return this.Model;
	}
	
	public void setModel(String model) {
		this.Model = model;
	}
	
	public String getTrim() {
		return this.Trim;
	}
	
	public void setTrim(String trim) {
		this.Trim = trim;
	}
	
	public String getBody() {
		return this.Body;
	}
	
	public void setBody(String body) {
		this.Body = body;
	}
	
	public String getTransmission() {
		return this.Transmission;
	}
	
	public void setTransmission(String transmission) {
		this.Transmission = transmission;
	}
	
	public String getVIN() {
		return this.VIN;
	}
	
	public void setVIN(String vIN) {
		this.VIN = vIN;
	}
	
	public String getState() {
		return this.State;
	}
	
	public void setState(String state) {
		this.State = state;
	}
	
	public Float getConditionScore() {
		return this.ConditionScore;
	}
	
	public void setConditionScore(Float conditionScore) {
		this.ConditionScore = conditionScore;
	}
	
	public Integer getOdoMeter() {
		return this.OdoMeter;
	}
	
	public void setOdoMeter(Integer odoMeter) {
		this.OdoMeter = odoMeter;
	}
	
	public String getColor() {
		return this.Color;
	}
	
	public void setColor(String color) {
		this.Color = color;
	}
	
	public String getInterior() {
		return this.Interior;
	}
	
	public void setInterior(String interior) {
		this.Interior = interior;
	}
	
	public Integer getMMR() {
		return this.MMR;
	}
	
	public void setMMR(Integer mMR) {
		this.MMR = mMR;
	}
	
	

}
