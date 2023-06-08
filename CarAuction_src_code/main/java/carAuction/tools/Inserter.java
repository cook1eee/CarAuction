package carAuction.tools;

import carAuction.dal.*;
import carAuction.model.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Inserter {
	public static void main(String[] args) throws SQLException, ParseException {
		
		// Users and CreditCards inserter
		UsersDao usersDao = UsersDao.getInstance();
		CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
		
		
		// INSERT objects from our model.
		Users user1 = new Users("first1", "last1", "user1 Address1", 
				                    "user1 Address2", "user1 City", "WA",
				                    "00000", "US", "000-000-0000", "user1@northeastern.edu", "***123",  new Date(125, 9, 3));
		user1 = usersDao.create(user1);
		
		Users user2 = new Users( "first2", "last2", "user2 Address1", 
                					"user2 Address2", "user2 City2", "WA",
                					"00000", "US", "000-000-0000", "user2@northeastern.edu", "***456", new Date(105, 3, 20));
		user2 = usersDao.create(user2);
		
		Users user3 = new Users("first3", "last3", "user3 Address1", 
									"user3 Address2", "user3 City", "WA",
									"00000", "US", "000-000-0000", "user3@northeastern.edu", "***789", new Date(110, 0, 17));
		user3 = usersDao.create(user3);
		
        
		CreditCards creditCard1 = new CreditCards("403003344455", user1, 3, 2026, "user1", "00000");
		creditCard1 = creditCardsDao.create(creditCard1);
		CreditCards creditCard2 = new CreditCards("439394030302", user2, 6, 2042, "user2", "00000");
		creditCard2 = creditCardsDao.create(creditCard2);
		CreditCards creditCard3 = new CreditCards("494904233344", user3, 3, 2026, "user3", "00000");
		creditCard3 = creditCardsDao.create(creditCard3);
		
		
		// READ.
		Users u1 = usersDao.getUserFromUserID(1);
		System.out.format("Reading user: u:%s p:%s f:%s l:%s e:%s p:%s \n",
			u1.getUserID(), u1.getFirstName(), u1.getLastName(),
			u1.getAddress1(), u1.getAddress2(), u1.getCity(),
			u1.getState(), u1.getZipcode(), u1.getCountry(),
			u1.getPhone(), u1.getEmail(), u1.getpassword(), u1.getSignUp());
	
		CreditCards cd1 = creditCardsDao.getCreditCardByCardNumber("403003344455");
		List<CreditCards> cdList1 = creditCardsDao.getCreditCardsByUserID(1);
		System.out.format("Reading c:%s u:%s e:%s e:%s n:%s z:%s \n",
			cd1.getCardNumber(), cd1.getUser().getUserID(), cd1.getExpirationMonth(),
			cd1.getExpirationYear(), cd1.getNameOnCard(), cd1.getZipCode());
		for(CreditCards cd : cdList1) {
			System.out.format("Looping creditCards: c:%s u:%s e:%s e:%s n:%s z:%s \n",
			cd.getCardNumber(), cd.getUser().getUserID(), cd.getExpirationMonth(),
			cd.getExpirationYear(), cd.getNameOnCard(), cd.getZipCode());
		}
		
		// UPDATE
		usersDao.updatepassword(user1, "123***");
		System.out.format("Reading user after updating password: u:%s p:%s f:%s l:%s e:%s p:%s \n",
				user1.getUserID(), user1.getFirstName(), user1.getLastName(),
				user1.getAddress1(), user1.getAddress2(), user1.getCity(),
				user1.getState(), user1.getZipcode(), user1.getCountry(),
				user1.getPhone(), user1.getEmail(), user1.getpassword(), user1.getSignUp());
		creditCardsDao.updateNameOnCard(creditCard1, "Bella");
		System.out.format("Reading creditCard after updating name of card: c:%s u:%s e:%s e:%s n:%s z:%s \n",
				creditCard1.getCardNumber(), creditCard1.getUser().getUserID(), creditCard1.getExpirationMonth(),
				creditCard1.getExpirationYear(), creditCard1.getNameOnCard(), creditCard1.getZipCode());
		
		//DELETE
//		creditCardsDao.delete(creditCard1);
//		usersDao.delete(user1);
		
		System.out.println();
		
		// UserActivity inserter
		Date date = new Date();
		UserActivitiesDao userActivitiesDao = UserActivitiesDao.getInstance();
		
		UserActivities userActivity1 = new UserActivities(user1, UserActivities.ActivityType.login, date);
		userActivity1 = userActivitiesDao.create(userActivity1);
		
		UserActivities userActivity2 = new UserActivities(user1, UserActivities.ActivityType.logout, date);
		userActivity2 = userActivitiesDao.create(userActivity2);
		
		UserActivities userActivity3 = new UserActivities(user2, UserActivities.ActivityType.login, date);
		userActivity3 = userActivitiesDao.create(userActivity3);
		
		UserActivities userActivity4 = new UserActivities(user2, UserActivities.ActivityType.logout, date);
		userActivity4 = userActivitiesDao.create(userActivity4);
		
		UserActivities userActivity5 = new UserActivities(user3, UserActivities.ActivityType.login, date);
		userActivity5 = userActivitiesDao.create(userActivity5);
		
		UserActivities userActivity6 = new UserActivities(user3, UserActivities.ActivityType.logout, date);
		userActivity6 = userActivitiesDao.create(userActivity6);
		
		UserActivities uA1 = userActivitiesDao.getUserActivityByID(userActivity1.getActivityID());
		System.out.format("Reading User Activity:  ID:%s  Type:%s  UserID:%s  Time:%s  \n",
				uA1.getActivityID(), uA1.getActivityType(), uA1.getUser().getUserID() , uA1.getTimeStamp());
		
		List<UserActivities> uAList = userActivitiesDao.getUserActivityForUser(user1);
		for(UserActivities u : uAList) {
			System.out.format("Looping User Activities:  ID:%s  Type:%s  UserID:%s  Time:%s \n",
					u.getActivityID(), u.getActivityType(), u.getUser().getUserID() , u.getTimeStamp());
		}
		
		System.out.println();
		
		// Car inserter
		CarsDao carDao = CarsDao.getInstance();
		
		Cars car1 = new Cars(user2, 2015, "Kia", "Sorento","LX","SUV","automatic","5xyktca69fg566472","ca",5.0F,16639,"white","black",20500);
		car1 = carDao.create(car1);
		
		Cars car2 = new Cars(user1,2015,"Kia","Sorento","LX","SUV","automatic","5xyktca69fg561319","ca",5.0F,9393,"white","beige",20800);
		car2 = carDao.create(car2);
		
		Cars car3 = new Cars(user2,2014,"BMW","3 Series","328i SULEV","Sedan","automatic","wba3c1c51ek116351","ca",4.5f,1331,"gray","black",31900);
		car3 = carDao.create(car3);
		
		Cars car4 = new Cars(user2,2015,"Volvo","S60","T5","Sedan","automatic","yv1612tb4f1310987","ca",4.1f,14282,"white","black",27500);
		car4 = carDao.create(car4);
		
		Cars car5 = new Cars(user3,2014,"BMW","6 Series Gran Coupe","650i","Sedan","automatic","wba6b2c57ed129731","ca",4.3f,2641,"gray","black",66000);
		car5 = carDao.create(car5);
		
		Cars car6 = new Cars(user3,2015,"Nissan","Altima","2.5 S","Sedan","automatic","1n4al3ap1fn326013","ca",1.0F,5554,"gray","black",15350);
		car6 = carDao.create(car6);

		System.out.format("Reading Car: CarID:%s UserID:%s Year:%s Maker:%s Model:%s Trim:%s Body:%s Transmission:%s VIN:%s State:%s ConditionScore:%s OdoMeter:%s Color:%s Interior:%s MMR:%s \n",
				car1.getCarID(), car1.getUser().getUserID(), car1.getYear(), car1.getMaker(), car1.getModel(), car1.getTrim(), car1.getBody(), car1.getTransmission(), 
				car1.getVIN(), car1.getState(), car1.getConditionScore(), car1.getOdoMeter(), car1.getColor(), car1.getInterior(), car1.getMMR());
		carDao.updateUser(car1,user1);
		carDao.updateConditionScore(car1,5.0F);
		carDao.updateOdoMeter(car1,16000);
		carDao.updateMMR(car1,20000);
		System.out.format("Reading Car after updateing: CarID:%s UserID:%s Year:%s Maker:%s Model:%s Trim:%s Body:%s Transmission:%s VIN:%s State:%s ConditionScore:%s OdoMeter:%s Color:%s Interior:%s MMR:%s \n",
				car1.getCarID(), car1.getUser().getUserID(), car1.getYear(), car1.getMaker(), car1.getModel(), car1.getTrim(), car1.getBody(), car1.getTransmission(), 
				car1.getVIN(), car1.getState(), car1.getConditionScore(), car1.getOdoMeter(), car1.getColor(), car1.getInterior(), car1.getMMR());
		
		Cars carSearchByID1 = carDao.getCarById(5);
		System.out.format("Reading Car5: CarID:%s UserID:%s Year:%s Maker:%s Model:%s Trim:%s Body:%s Transmission:%s VIN:%s State:%s ConditionScore:%s OdoMeter:%s Color:%s Interior:%s MMR:%s \n",
				carSearchByID1.getCarID(), carSearchByID1.getUser().getUserID(), carSearchByID1.getYear(), carSearchByID1.getMaker(), carSearchByID1.getModel(), carSearchByID1.getTrim(), carSearchByID1.getBody(), carSearchByID1.getTransmission(), 
				carSearchByID1.getVIN(), carSearchByID1.getState(), carSearchByID1.getConditionScore(), carSearchByID1.getOdoMeter(), carSearchByID1.getColor(), carSearchByID1.getInterior(), carSearchByID1.getMMR());
		
		
		List<Cars> carSearchByUser1 = carDao.getCarForUser(user1);
		for(Cars c : carSearchByUser1) {
			System.out.format("Reading User1's Cars: CarID:%s UserID:%s Year:%s Maker:%s Model:%s Trim:%s Body:%s Transmission:%s VIN:%s State:%s ConditionScore:%s OdoMeter:%s Color:%s Interior:%s MMR:%s \n",
					c.getCarID(), c.getUser().getUserID(), c.getYear(), c.getMaker(), c.getModel(), c.getTrim(), c.getBody(), c.getTransmission(), 
					c.getVIN(), c.getState(), c.getConditionScore(), c.getOdoMeter(), c.getColor(), c.getInterior(), c.getMMR());
		}
		
//		carDao.delete(car1);
		
		System.out.println();
		
		// CustomerService inserter
		CustomerServicesDao customerServicesDao = CustomerServicesDao.getInstance();
	
		CustomerServices cs1 = new CustomerServices(1, "Bill", "K");
		cs1 = customerServicesDao.create(cs1);
		
		CustomerServices cs2 = new CustomerServices(2, "B", "K");
		cs2 = customerServicesDao.create(cs2);
		
		CustomerServices csrRead = customerServicesDao.getCustomerServiceById(1);
		System.out.format("Reading CSById: CSID:%s first_name:%s last_name:%s\n",
				csrRead.getCustomerServiceID(), csrRead.getFirstName(), csrRead.getLastName());
		
//		customerServiceDao.delete(cs1);
		
		System.out.println();
		
		// Auction inserter
		AuctionsDao auctionsDao = AuctionsDao.getInstance();
		
		Auctions auction1 = new Auctions("auction1", new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-02-17 04:30:00"), new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-03-17 04:30:00"), car1, user1, "h1", "https://neu-cs5200-team-project.s3.us-east-2.amazonaws.com/all_car_picture/kia_k900.jpg", 40000F, 40000F, Auctions.AuctionStatusValue.Active, cs1, true);
		auction1 = auctionsDao.create(auction1);
		
		Auctions auction2 = new Auctions("auction2", new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-02-17 04:30:00"), new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-03-17 04:30:00"), car2, user1, "h2", "https://neu-cs5200-team-project.s3.us-east-2.amazonaws.com/all_car_picture/kia_k900.jpg", 40000F, 40000F, Auctions.AuctionStatusValue.Active, cs1, true);
		auction2 = auctionsDao.create(auction2);
		
		Auctions auction3 = new Auctions("auction3", new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-02-17 04:30:00"), new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-03-17 04:30:00"), car3, user2, "h3", "https://neu-cs5200-team-project.s3.us-east-2.amazonaws.com/all_car_picture/nissan_altima.jpg", 40000F, 40000F, Auctions.AuctionStatusValue.Failed, cs1, true);
		auction3 = auctionsDao.create(auction3);
		
		Auctions auction4 = new Auctions("auction4", new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-02-17 04:30:00"), new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-03-17 04:30:00"), car4, user2, "h4", "https://neu-cs5200-team-project.s3.us-east-2.amazonaws.com/all_car_picture/chevrolet_cruze.jpg", 40000F, 40000F, Auctions.AuctionStatusValue.Active, cs2, true);
		auction4 = auctionsDao.create(auction4);
		
		Auctions auction5 = new Auctions("auction5", new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-02-17 04:30:00"), new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-03-17 04:30:00"), car5, user3, "h5", "https://neu-cs5200-team-project.s3.us-east-2.amazonaws.com/all_car_picture/hyundai_elantra.jpg,", 40000F, 40000F, Auctions.AuctionStatusValue.Succeed, cs2, true);
		auction5 = auctionsDao.create(auction5);
		
		Auctions auction6 = new Auctions("auction6", new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-02-17 04:30:00"), new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-03-17 04:30:00"), car6, user3, "h6", "https://neu-cs5200-team-project.s3.us-east-2.amazonaws.com/all_car_picture/acura_mdx.jpg", 40000F, 40000F, Auctions.AuctionStatusValue.Active, cs2, true);
		auction6 = auctionsDao.create(auction6);
		
		System.out.format("Reading Auction1: AuctionID:%s Title:%s StartTime:%s EndTime:%s CarID:%s UserID:%s Highlights:%s Pictures:%s MinimumPrice:%s CurrentHighestPrice:%s AuctionStatus:%s CustomerServiceID:%s PriceChangeAlert:%s \n",
				auction1.getAuctionID(), auction1.getTitle(), auction1.getStartTime(), auction1.getEndTime(), auction1.getCar().getCarID(), auction1.getUser().getUserID(), auction1.getHighlights(), auction1.getPictures(),
				auction1.getMinimumPrice(), auction1.getCurrentHighestPrice(), auction1.getAuctionStatus(), auction1.getCustomerService().getCustomerServiceID(), auction1.getPriceChangeAlert());
		auctionsDao.updateTitle(auction1, "title1");
		auctionsDao.updateEndTime(auction1, new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-04-17 04:30:00"));
		auctionsDao.updateHighlights(auction1, "highlght1");
		auctionsDao.updatePictures(auction1, "https://neu-cs5200-team-project.s3.us-east-2.amazonaws.com/all_car_picture/nissan_altima.jpg");
		auctionsDao.updateCurrentHighestPrice(auction1, 50000F);
		auctionsDao.updateAuctionStatus(auction1, Auctions.AuctionStatusValue.Failed);
		auctionsDao.updateCustomerService(auction1, cs2);
		auctionsDao.updatePriceChangeAlert(auction1, false);
		System.out.format("Reading Auction1 after updating: AuctionID:%s Title:%s StartTime:%s EndTime:%s CarID:%s UserID:%s Highlights:%s Pictures:%s MinimumPrice:%s CurrentHighestPrice:%s AuctionStatus:%s CustomerServiceID:%s PriceChangeAlert:%s \n",
				auction1.getAuctionID(), auction1.getTitle(), auction1.getStartTime(), auction1.getEndTime(), auction1.getCar().getCarID(), auction1.getUser().getUserID(), auction1.getHighlights(), auction1.getPictures(),
				auction1.getMinimumPrice(), auction1.getCurrentHighestPrice(), auction1.getAuctionStatus(), auction1.getCustomerService().getCustomerServiceID(), auction1.getPriceChangeAlert());
		
		Auctions auctionSeachByID = auctionsDao.getAuctionById(2);
		System.out.format("Auction seached by ID: AuctionID:%s Title:%s StartTime:%s EndTime:%s CarID:%s UserID:%s Highlights:%s Pictures:%s MinimumPrice:%s CurrentHighestPrice:%s AuctionStatus:%s CustomerServiceID:%s PriceChangeAlert:%s \n",
				auctionSeachByID.getAuctionID(), auctionSeachByID.getTitle(), auctionSeachByID.getStartTime(), auctionSeachByID.getEndTime(), auctionSeachByID.getCar().getCarID(), auctionSeachByID.getUser().getUserID(), auctionSeachByID.getHighlights(), auctionSeachByID.getPictures(),
				auctionSeachByID.getMinimumPrice(), auctionSeachByID.getCurrentHighestPrice(), auctionSeachByID.getAuctionStatus(), auctionSeachByID.getCustomerService().getCustomerServiceID(), auctionSeachByID.getPriceChangeAlert());
		
		List<Auctions> auctionsSeachForUser = auctionsDao.getAuctionForUser(user1);
		for(Auctions au : auctionsSeachForUser) {
			System.out.format("Auction seached for User: AuctionID:%s Title:%s StartTime:%s EndTime:%s CarID:%s UserID:%s Highlights:%s Pictures:%s MinimumPrice:%s CurrentHighestPrice:%s AuctionStatus:%s CustomerServiceID:%s PriceChangeAlert:%s \n",
					au.getAuctionID(), au.getTitle(), au.getStartTime(), au.getEndTime(), au.getCar().getCarID(), au.getUser().getUserID(), au.getHighlights(), au.getPictures(),
					au.getMinimumPrice(), au.getCurrentHighestPrice(), au.getAuctionStatus(), au.getCustomerService().getCustomerServiceID(), au.getPriceChangeAlert());
		}
		
		List<Auctions> auctionsSeachForCar = auctionsDao.getAuctionForCar(car5);
		for(Auctions au : auctionsSeachForCar) {
			System.out.format("Auction seached for Car: AuctionID:%s Title:%s StartTime:%s EndTime:%s CarID:%s UserID:%s Highlights:%s Pictures:%s MinimumPrice:%s CurrentHighestPrice:%s AuctionStatus:%s CustomerServiceID:%s PriceChangeAlert:%s \n",
					au.getAuctionID(), au.getTitle(), au.getStartTime(), au.getEndTime(), au.getCar().getCarID(), au.getUser().getUserID(), au.getHighlights(), au.getPictures(),
					au.getMinimumPrice(), au.getCurrentHighestPrice(), au.getAuctionStatus(), au.getCustomerService().getCustomerServiceID(), au.getPriceChangeAlert());
		}
		
		List<Auctions> auctionsSeachForCustomerService = auctionsDao.getAuctionForCustomerService(cs2);
		for(Auctions au : auctionsSeachForCustomerService) {
			System.out.format("Auction seached for Customer Service: AuctionID:%s Title:%s StartTime:%s EndTime:%s CarID:%s UserID:%s Highlights:%s Pictures:%s MinimumPrice:%s CurrentHighestPrice:%s AuctionStatus:%s CustomerServiceID:%s PriceChangeAlert:%s \n",
					au.getAuctionID(), au.getTitle(), au.getStartTime(), au.getEndTime(), au.getCar().getCarID(), au.getUser().getUserID(), au.getHighlights(), au.getPictures(),
					au.getMinimumPrice(), au.getCurrentHighestPrice(), au.getAuctionStatus(), au.getCustomerService().getCustomerServiceID(), au.getPriceChangeAlert());
		}
		
//		auctionDao.delete(auction1);
		
		System.out.println();

		
		
		// Bid inserter
		BidsDao bidsDao = BidsDao.getInstance();
		// Bid inserter
		Bids bid1 = new Bids(auctionsDao.getAuctionById(1), user1,  new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-02-17 04:30:00"), 41000F);
		Bids bids1 = bidsDao.create(bid1);
		System.out.println(bids1.getBidID());
		Bids b1 = bidsDao.getBidById(1);
		System.out.format("Reading Bid: BidID:%s, AuctionID:%s, UserID:%s, BidTime:%s, BidPrice:%s \n ",
		b1.getBidID(), b1.getAuction().getAuctionID(), b1.getUser().getUserID(), b1.getBidTime(), b1.getBidPrice());
		List<Bids> b2 = bidsDao.getBidsForAuction(bid1.getAuction());
		for (Bids bid : b2) {
		System.out.format("Reading Bid: BidID:%s, AuctionID:%s, UserID:%s, BidTime:%s, BidPrice:%s \n ",
		bid.getBidID(), bid.getAuction().getAuctionID(), bid.getUser().getUserID(), bid.getBidTime(), bid.getBidPrice());
		}
		List<Bids> b3 = bidsDao.getBidsForUser(bid1.getUser());
		for (Bids bid : b3) {
		System.out.format("Reading Bid: BidID:%s, AuctionID:%s, UserID:%s, BidTime:%s, BidPrice:%s \n ",
		bid.getBidID(), bid.getAuction().getAuctionID(), bid.getUser().getUserID(), bid.getBidTime(), bid.getBidPrice());
		}
		Bids b4 = bidsDao.updateBidPrice(bid1, 42000F);
		System.out.format("Reading Bid: BidID:%s, AuctionID:%s, UserID:%s, BidTime:%s, BidPrice:%s \n ",
		b4.getBidID(), b4.getAuction().getAuctionID(), b4.getUser().getUserID(), b4.getBidTime(), b4.getBidPrice());
		// bidsDao.delete(bid1);

		
		// ChatHistory inserter
		
		// Collection inserter
		
		// Forums inserter
		ForumsDao ForumssDao = ForumsDao.getInstance();
		
		Forums Forum1 = new Forums(auction1, user1, date, "Forum1 content ............");
		Forum1 = ForumssDao.create(Forum1);
		
		Forums Forum2 = new Forums(auction2, user1, date, "Forum2 content ............");
		Forum2 = ForumssDao.create(Forum2);
		
		Forums Forum3 = new Forums(auction3, user2, date, "Forum3 content ............");
		Forum3 = ForumssDao.create(Forum3);
		
		Forums Forum4 = new Forums(auction4, user2, date, "Forum4 content ............");
		Forum4 = ForumssDao.create(Forum4);
		
		Forums Forum5 = new Forums(auction5, user3, date, "Forum5 content ............");
		Forum5 = ForumssDao.create(Forum5);

		
		Forums f1 = ForumssDao.getForumById(Forum3.getForumID());
		System.out.format("Reading Forums:  ID:%s   AuctionID:%s  UserID:%s  Time:%s  Content:%s  \n", 
				f1.getForumID(), f1.getAuction().getAuctionID(), f1.getUser().getUserID(), f1.getTimeStamp(), f1.getContent());
		
		f1 = ForumssDao.updateForum(f1, "updated Forum3 content...............");
		System.out.format("Reading Updated Forums:  ID:%s   AuctionID:%s  UserID:%s  Time:%s  Content:%s  \n", 
				f1.getForumID(), f1.getAuction().getAuctionID(), f1.getUser().getUserID(), f1.getTimeStamp(), f1.getContent());
		
		List<Forums> fList = ForumssDao.getForumForUser(user1);
		for (Forums f : fList) {
			System.out.format("Looping Forums for User %s:  ForumsID:%s,  AuctionID:%s,  Time:%s,  Content:%s    \n ",
					f.getUser().getUserID(), f.getForumID(), f.getAuction().getAuctionID(), f.getTimeStamp(), f.getContent());
		}
		
		List<Forums> fList1 = ForumssDao.getForumForAuction(auction4);
		for (Forums f : fList1) {
			System.out.format("Looping Forums for AuctionID:%s,  User %s: , ForumsID:%s, Time:%s,  Content:%s    \n ",
					f.getAuction().getAuctionID(), f.getUser().getUserID(), f.getForumID(), f.getTimeStamp(), f.getContent());
		}
		
		System.out.println();
		
		
		
		// Reply inserter
		
		RepliesDao replyDao = RepliesDao.getInstance();
		
		Replies reply1 = new Replies(Forum1, user3, date, "reply1 content ............");
		reply1 = replyDao.create(reply1);
		
		Replies reply2 = new Replies(Forum1, user2, date, "reply2 content ............");
		reply2 = replyDao.create(reply2);
		
		Replies reply3 = new Replies(Forum2, user1, date, "reply3 content ............");
		reply3 = replyDao.create(reply3);
				
		Replies reply4 = new Replies(Forum3, user3, date, "reply4 content ............");
		reply4 = replyDao.create(reply4);
		
		
		Replies r1 = replyDao.getReplyById(reply3.getReplyID());
		System.out.format("Reading Reply:  ID:%s, ForumsID:%s,  UserID:%s,  Time:%s,  Content:%s   \n ",
				r1.getReplyID(), r1.getForum().getForumID(), r1.getUser().getUserID(), r1.getTimeStamp(), r1.getContent());
		
		r1 = replyDao.updateReply(r1, "updated reply3 content..........");
		System.out.format("Reading Reply:  ID:%s, ForumsID:%s,  UserID:%s,  Time:%s,  Content:%s   \n ",
				r1.getReplyID(), r1.getForum().getForumID(), r1.getUser().getUserID(), r1.getTimeStamp(), r1.getContent());
		
		List<Replies> rList = replyDao.getReplyForForum(Forum1);
		for(Replies r : rList) {
			System.out.format("Looping Reply:  ID:%s, ForumsID:%s,  UserID:%s,  Time:%s,  Content:%s   \n ",
					r.getReplyID(), r.getForum().getForumID(), r.getUser().getUserID(), r.getTimeStamp(), r.getContent());
		}
		
		List<Replies> rList1 = replyDao.getReplyForUser(user3);
		for(Replies r : rList1) {
			System.out.format("Looping Reply:  ID:%s, ForumsID:%s,  UserID:%s,  Time:%s,  Content:%s   \n ",
					r.getReplyID(), r.getForum().getForumID(), r.getUser().getUserID(), r.getTimeStamp(), r.getContent());
		}
		
		
		System.out.println();
		
		// ChatHistories and Collections inserter
		
		ChatHistoriesDao chatHistoryDao = ChatHistoriesDao.getInstance();
	    CollectionsDao collectionDao = CollectionsDao.getInstance();


	     
	    ChatHistories chatHistory1 = new ChatHistories(cs1,user1,
	    		date, ChatHistories.ServiceTypeValue.Account);
	    
	    chatHistory1 = chatHistoryDao.create(chatHistory1);

	    ChatHistories chatHistory2 = new ChatHistories(cs2,user2,
	    		date, ChatHistories.ServiceTypeValue.Legal);
	    
	    
	    chatHistory2 = chatHistoryDao.create(chatHistory2);

	    ChatHistories chatHistory3= new ChatHistories(cs2,user3,
	    		date, ChatHistories.ServiceTypeValue.Auction);
	    
	    chatHistory3 = chatHistoryDao.create(chatHistory3);

	    Collections collection1 = new Collections(user1,auction1,
	        true,true);
	    collection1 = collectionDao.create(collection1);

	    Collections collection2 = new Collections(user2,auction2,
	        true,true);
	    collection2 = collectionDao.create(collection2);

	    Collections collection3 = new Collections(user3,auction3,
	        false,false);
	    collection3 = collectionDao.create(collection3);


	    // READ.
	    
	    
	    List<ChatHistories> ChatHistoriesSeachForUser = chatHistoryDao.getChatHistoriesByUserID(user1.getUserID());
	    
	    for(ChatHistories ch : ChatHistoriesSeachForUser) {
			System.out.format("ChatHistory seached for User: ChatID:%s CustomerServiceID:%s UserID:%s "
					+ " Time:%s ServiceType:%s  \n",
					ch.getChatID(), ch.getCustomerService().getCustomerServiceID(),
					ch.getUser().getUserID(), ch.getTimeStamp(), ch.getServiceType().name());
	    }
	    
		
	    ChatHistories c2 = chatHistoryDao.updateServiceType(chatHistory1, ChatHistories.ServiceTypeValue.Other);
	    System.out.format("Reading ChatHistories:  ChatID:%s   CustomerServiceID:%s"
	    		+ " UserID:%s Time:%s  ServiceType:%s \n",
	        c2.getChatID(), c2.getCustomerService().getCustomerServiceID(),
	        c2.getUser().getUserID(), c2.getTimeStamp(), c2.getServiceType().name());

	    
	    Collections cl1 = collectionDao.getCollectionById(10001);
	    System.out.format("Reading Collections:  CollectionId:%s   UserID:%s  AuctionID:%s "
	            + "  PriceChangeAlert: %s StatusChangeAlert:%s \n",
	        cl1.getCollectionId(), cl1.getuser().getUserID(),
	        cl1.getauction().getAuctionID(), cl1.getPriceChangeAlert(), cl1.getStatusChangeAlert());

	    Collections cl2 = collectionDao.updatePriceChangeAlert(collection3, true);
	    System.out.format("Reading Collections:   CollectionId:%s   UserID:%s  AuctionID:%s "
	            + "  PriceChangeAlert:%s StatusChangeAlert:%s \n",
	        cl2.getCollectionId(), cl1.getuser().getUserID(),
	        cl1.getauction().getAuctionID(),cl2.getPriceChangeAlert(), cl2.getStatusChangeAlert());
	    
	    System.out.println();
		
	}
}