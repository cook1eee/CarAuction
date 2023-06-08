package carAuction.model;

public class CreditCards {
    protected String CardNumber;
    protected Users User;
    protected int ExpirationMonth;
    protected int ExpirationYear;
    protected String NameOnCard;
    protected String ZipCode;
    
    // This constructor can be used for reading records from MySQL, where we have all fields,
	// including the cardNumber.
	public CreditCards(String CardNumber, Users User, int ExpirationMonth,
			           int ExpirationYear, String NameOnCard, String ZipCode) {
		
		this.CardNumber = CardNumber;
		this.User = User;
		this.ExpirationMonth = ExpirationMonth;
		this.ExpirationYear = ExpirationYear;
		this.NameOnCard = NameOnCard;
		this.ZipCode = ZipCode;
	}
	
		public CreditCards(String CardNumber) {
		this.CardNumber = CardNumber;
	}
	
    /** Getters and setters. */
    
	public String getCardNumber() {
		return CardNumber;
	}

	public void setCardNumber(String CardNumber) {
		this.CardNumber = CardNumber;
	}

	public Users getUser() {
		return User;
	}

	public void setUser(Users User) {
		this.User = User;
	}
	
	public int getExpirationMonth() {
		return ExpirationMonth;
	}

	public void setExpirationMonth(int ExpirationMonth) {
		this.ExpirationMonth = ExpirationMonth;
	}
	
	public int getExpirationYear() {
		return ExpirationYear;
	}

	public void setExpirationYear(int ExpirationYear) {
		this.ExpirationYear = ExpirationYear;
	}

	public String getNameOnCard() {
		return NameOnCard;
	}

	public void setNameOnCard(String NameOnCard) {
		this.NameOnCard = NameOnCard;
	}
	
	public String getZipCode() {
		return ZipCode;
	}

	public void setZipCode(String ZipCode) {
		this.ZipCode = ZipCode;
	}

}