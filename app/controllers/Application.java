package controllers;

import javax.inject.Inject;

import play.mvc.*;
import play.db.*;
import java.sql.*;
import views.html.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;



public class Application extends Controller {

    private Database db;
    

    @Inject
    public void Application(Database db) {
        this.db = db;
    }
    public Application(){
        
    }
    
    public void updateReviews( Set<Map.Entry<String,String[]>> entries){
        try {
        	Class.forName("org.postgresql.Driver");
    		System.out.println("yay!!!"); 
    	} catch (ClassNotFoundException e) {
    		System.out.println("Where is your PostgreSQL JDBC Driver? "
    				+ "Include in your library path!");
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost/donutfinder", "postgres",
					"p0stGre$$Pa$$1");
			System.out.println("YAY!! Again"); 

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
        String tuples[][] = new String[20][2];
        int i = 0; 
        for (Map.Entry<String,String[]> entry : entries) {
            tuples[i][0] = entry.getKey(); 
            tuples[i][1] = Arrays.toString(entry.getValue()); 
            i++;    
        }
        
        ResultSet rs = null; 
        try{
            Statement stmt = connection.createStatement();
            //get shop name 
            String userId =""; 
            String shopId =""; 
            for(int k = 0; k<tuples.length;k++){
                if (tuples[k][0] == null){
                    break;
                } else if (tuples[k][0].equalsIgnoreCase("Shop")){
                    shopId = this.removeFirstAndLastChar(tuples[k][1]); 
                }else if (tuples[k][0].equalsIgnoreCase("uname")){
                    userId = this.removeFirstAndLastChar(tuples[k][1]);
                }
            }
 System.out.println("user= " + userId); 
  System.out.println("shop= " + shopId); 
            for(int j = 0; j < tuples.length; j++){
                if (tuples[j][0] == null){
                    break; 
                }else if(tuples[j][0].equalsIgnoreCase("Shop")||tuples[j][0].equalsIgnoreCase("uname")){
                    
                }else if(tuples[j][0].equalsIgnoreCase("text1")){
                    String review = this.removeFirstAndLastChar(tuples[j][1]);
                    rs = stmt.executeQuery("select * from review where user_id = '" + userId + "' and shop_id=" + shopId);
                    if(rs.next()){
                         System.out.println("4"); 

                        stmt.executeUpdate("update review set reviewtext = '" + review + "' where shop_id = " +
                            shopId  + " and user_id = '" + userId +"'"); 
                    }else {
                         System.out.println("3"); 
                        stmt.executeUpdate("insert into review(reviewtext, shop_id, user_id) where " +
                            "values('" + review + ", " + shopId +", '" + userId +"')" );
                    }
                    System.out.println("text1:  " + tuples[j][1]); 
                }else if (tuples[j][0].equalsIgnoreCase("overall")){
                    String overallrating = this.removeFirstAndLastChar(tuples[j][1]);
                    rs = stmt.executeQuery("select * from review where user_id = '" + userId + "' and shop_id=" + shopId);
                    if(rs.next()){
                        System.out.println("1"); 

                        stmt.executeUpdate("update review set overallrating = '" + overallrating + "' where shop_id = " +
                            shopId  + " and user_id = '" + userId +"'"); 
                    }else {
                        System.out.println("2"); 
                        stmt.executeUpdate("insert into review(overallrating, shop_id, user_id) " +
                            "values('" + overallrating + "', " + shopId +", '" + userId +"')" );
                    }
                }else{
                    rs = stmt.executeQuery("select * from rating where userid = '" + userId + "' and shopid='"
                        +shopId+"' and ratingtype = '" + tuples[j][0] + "'" ); 
                    String rating = this.removeFirstAndLastChar(tuples[j][1]); 
                    if (rs.next()){
                        int q = stmt.executeUpdate("UPDATE rating SET ratingval = '" +rating+
                        "' WHERE shopid=" + shopId+ " and userid='" + userId+"' and ratingtype='"+tuples[j][0] +"'" ); 
                    }else {
                        int q = stmt.executeUpdate("insert into rating(userid, shopid,ratingtype,ratingval) values('"+
                        userId+"', " +shopId+",'"+tuples[j][0]+"', "+rating+")");
                    }
                }
                
            }
           /* String s = this.removeFirstAndLastChar(tuples[1][1]);//.substring(1,tuples[1][1].length() - 1); 
            System.out.println("tuple 11 : " + s);
            rs = stmt.executeQuery("select * from rating where userid = '" + s + "'" ); 
            while (rs.next()){
                System.out.print("Row num: " + rs.getRow()); 
                System.out.println("select *  -  " + rs.getString("userid") +" " + rs.getString("shopid") + " " +
                rs.getString("ratingtype") + " " + rs.getString("ratingval"));
            }*/
        }catch(Exception e){
            System.out.println("jjjjjj"+e); 
        }
        
    }
    public String removeFirstAndLastChar(String string){
         String s = string.substring(1,string.length() - 1); 
        return s; 
    }
    
    public MainPage getMainPageInfo(int shopId){
         try {
        	Class.forName("org.postgresql.Driver");
    		System.out.println("yay!!!"); 
    
    	} catch (ClassNotFoundException e) {
    
    			System.out.println("Where is your PostgreSQL JDBC Driver? "
    					+ "Include in your library path!");
    			e.printStackTrace();
    		}
    		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost/donutfinder", "postgres",
					"p0stGre$$Pa$$1");
					System.out.println("YAY!! Again"); 

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
        
        MainPage mp = new MainPage(); 
        ResultSet rs = null; 
        try{
            Statement stmt = connection.createStatement();
            //get shop name 
            rs = stmt.executeQuery("SELECT name FROM Shop WHERE id =" + shopId); 
            while (rs.next()){
                System.out.print("Row num: " + rs.getRow()); 
                System.out.println("   string: " + rs.getString("name") );
                mp.setName(rs.getString("name"));
                
            }
            //get location 
            rs = stmt.executeQuery("SELECT street, cityid FROM Shop WHERE id =" + shopId);
            while (rs.next()){
                System.out.print("Row num: " + rs.getRow()); 
                String street = rs.getString("street");
                String cityid = rs.getString("cityid"); 
                rs = stmt.executeQuery("select state, city from cities where id =" + cityid);
                rs.next();
                mp.setLoc(street,rs.getString("city"),rs.getString("state"));
                
            }
            //get social media 
            rs = stmt.executeQuery("SELECT platform FROM SocialMedia");
            String SM =""; 
            while (rs.next()){
                System.out.print("Row num: " + rs.getRow());
                SM += rs.getString("platform") + "\n";
            }
            mp.setSM(SM);
            //get ratings
            rs = stmt.executeQuery("SELECT ratingtype, avg(ratingval) from Rating where shopid = " + shopId + 
            " group by ratingtype"); 
            String ratings =""; 
            while (rs.next()){
                System.out.print("Row num: " + rs.getRow());
                ratings += rs.getString("ratingtype") + "---------------" 
                + rs.getString("avg") + "\n";
            }
            mp.setRatings(ratings);
            //get features 
            rs = stmt.executeQuery("select feature from feature where shopid = " + shopId);
            String features =""; 
            while (rs.next()){
                System.out.print("Row num: " + rs.getRow());
                features += rs.getString("feature") + "\n";
            }
            mp.setFeatures(features);
            
            rs = stmt.executeQuery("select r.overallrating,r.reviewtext,u.id from users u, review r"+
            " where r.shop_id = " + shopId+" and r.user_id = u.id ");
            String reviews =""; 
            while (rs.next()){
                System.out.print("Row num: " + rs.getRow());
                reviews += rs.getString("id") + "--------------" + rs.getString("overallrating")+"/5\n";
                reviews += rs.getString("reviewtext") +"\n------------------------------------------\n";
            }
            mp.setreview(reviews);
            
            rs = stmt.executeQuery("select  url from image where shopid = " + shopId);
            String url =""; 
            rs.next(); 
            url = rs.getString("url");
            
            mp.setIurl(url);
            
            connection.close(); 
        }catch(Exception e){
            System.out.println("helpme:  " + e.toString());
        }
        return mp;
    }
    
    public FriendsPage getFriendsPage(String uname){
        FriendsPage fp = new FriendsPage(uname); 
        ResultSet rs = null; 
        try {
    		Class.forName("org.postgresql.Driver");
    	} catch (ClassNotFoundException e) {
    		System.out.println("Where is your PostgreSQL JDBC Driver? "
    			+ "Include in your library path!");
    		e.printStackTrace();
    	}
    	Connection connection = null;

		try {
	    	connection = DriverManager.getConnection(
				"jdbc:postgresql://localhost/donutfinder", "postgres",
				"p0stGre$$Pa$$1");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
        try{
            Statement stmt = connection.createStatement();
            //get shop name 
            rs = stmt.executeQuery("SELECT s.name, s.street, c.city, c.state FROM Shop s, favorites f, cities c "+
                "WHERE f.shopid = s.id and f.userid='" + uname +"' and s.cityid=c.id");
            int counter = 1; 
            while (rs.next()){
                fp.addFavorites(counter + ". " + rs.getString("name")+ " - " +  rs.getString("street") +
                    ", " + rs.getString("city") +", " + rs.getString("state"));
                counter++;
            }
            rs = stmt.executeQuery("SELECT r.reviewtext, r.overallrating, s.name "+ 
                "from shop s, review r " +
                "where r.user_id = '"+uname+"' and r.shop_id = s.id");
            
            while (rs.next()){
                fp.setReviews(rs.getString("name"),  rs.getString("reviewtext"), rs.getString("overallrating"));
                counter++;
            }
        }catch(Exception e){
            System.out.println("help: " + e);
        }
        return fp;
    }
}
class MainPage{
    String shopName; 
    String location; 
    String socialMedia;
    String ratings; 
    String features; 
    String reviews; 
    String Iurl; 
    
    public void MainPage(){
    }
    public void setIurl(String url){
        Iurl= url; 
    }

    public void setName(String name){
        shopName = name; 
    }
    public void setLoc(String street, String city, String state){
        location = street +", " + city+ " " + state;
    }
    public void setSM(String sm){
        socialMedia = sm; 
    }
    public void setRatings(String rate){
        ratings = rate;
    }
    
    public void setFeatures(String f){
        features = f; 
    }
    public void setreview(String r){
        reviews = r; 
    }
    public String getName(){
        return shopName; 
    }
    public String getLoc(){
        return location;
    }
    public String getSM(){
        return socialMedia; 
    }
    public String getRatings(){
        return ratings; 
    }
    public String getFeatures(){
        return features;
    }
    public String getReviews(){
        return reviews; 
    }
    public String getImage(){
        return Iurl; 
    }
}
class FriendsPage{
    String id; 
    String favorites; 
    String reviews;
    public FriendsPage(String name){
        id = name;
        favorites =""; 
        reviews = ""; 
    }
    public void addFavorites(String favs){
        favorites += favs+"\n"; 
    }
    public void setReviews(String shopName, String review, String overall){
        reviews += shopName + "---" + overall + "/5\n\t" + review +"\n\n"  ; 
    }
    public String getName(){
        return id;
    }
    public String getFavs(){
        return favorites; 
    }
    public String getReviews(){
        return reviews; 
    }
}

