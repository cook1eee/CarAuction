package carAuction.model;

import java.util.Date;

public class Collections {
  protected int CollectionId;
  protected Users user;
  protected Auctions auction;
  protected Boolean PriceChangeAlert;
  protected Boolean StatusChangeAlert;

  public Collections(int collectionId, Users user, Auctions auction, Boolean priceChangeAlert,
      Boolean statusChangeAlert) {
    this.CollectionId = collectionId;
    this.user = user;
    this.auction = auction;
    this.PriceChangeAlert = priceChangeAlert;
    this.StatusChangeAlert = statusChangeAlert;
  }

  public Collections(Users user, Auctions auction, Boolean priceChangeAlert,
	      Boolean statusChangeAlert) {
	    this.user = user;
	    this.auction = auction;
	    this.PriceChangeAlert = priceChangeAlert;
	    this.StatusChangeAlert = statusChangeAlert;
	  }
  
  public Collections(int collectionId, Users user, Auctions auction) {
    this.CollectionId = collectionId;
    this.user = user;
    this.auction = auction;
  }
  
  public Collections(int collectionId) {
	    this.CollectionId = collectionId;
	    
	  }

  public int getCollectionId() {
    return this.CollectionId;
  }

  public void setCollectionId(int collectionId) {
    this.CollectionId = collectionId;
  }

  public Users getuser() {
    return this.user;
  }

  public void setuser(Users user) {
    this.user = user;
  }

  public Auctions getauction() {
    return this.auction;
  }

  public void setauction(Auctions auction) {
    this.auction = auction;
  }

  public Boolean getPriceChangeAlert() {
    return this.PriceChangeAlert;
  }

  public void setPriceChangeAlert(Boolean priceChangeAlert) {
    this.PriceChangeAlert = priceChangeAlert;
  }

  public Boolean getStatusChangeAlert() {
    return this.StatusChangeAlert;
  }

  public void setStatusChangeAlert(Boolean statusChangeAlert) {
    this.StatusChangeAlert = statusChangeAlert;
  }
}
