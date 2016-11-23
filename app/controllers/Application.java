package controllers;

import javax.inject.Inject;

import play.mvc.*;
import play.db.*;
import java.sql.*;
import views.html.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class Application extends Controller {

    private Database db;
    

    @Inject
    public void Application(Database db) {
        this.db = db;
    }
    public Application(){
        
    }
    
    public Result testing(){
        try{
            try {
    
    			Class.forName("org.postgresql.Driver");
    
    		} catch (ClassNotFoundException e) {
    
    			System.out.println("Where is your PostgreSQL JDBC Driver? "
    					+ "Include in your library path!");
    			e.printStackTrace();
    		}
        /*String url = "jdbc:postgresql://localhost/donutfinder";
        Connection conn = DriverManager.getConnection(url,"trhansen","mySQLPa$$1"); 
        Statement stmt = conn.createStatement();
        ResultSet rs; 
        rs = stmt.executeQuery("SELECT * FROM student WHERE id = 999514"); 
        while (rs.next()){
            System.out.print("Row num: " + rs.getRow()); 
            System.out.println("   string: " + rs.getString("name") );
        }
        conn.close(); 
       */
        }catch(Exception e){
            System.out.println("helpme:  " + e.toString());
        }
         return ok(friends.render("friends"));
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
        
        rs = stmt.executeQuery("select r.overallrating,r.reviewtext,u.first_name from users u, review r"+
        " where r.shop_id = " + shopId+" and r.user_id = u.id ");
        String reviews =""; 
        while (rs.next()){
            System.out.print("Row num: " + rs.getRow());
            reviews += rs.getString("first_name") + "--------------" + rs.getString("overallrating")+"/5\n";
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