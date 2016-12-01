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
import models.*;
import play.api.mvc.Cookie;

public class Application extends Controller {

    private Database db;
    

    @Inject
    public void Application(Database db) {
        this.db = db;
    }
    public Application(){
        
    }
  
    public Users getUser()
    {
    	String username="";
    	Users user=null;

    	username=session("user");
    	{
    		user=Users.find.where().eq("id",username).findUnique();
       	}
    	
    	return user;
    }
  
    public void updateReviews( Set<Map.Entry<String,String[]>> entries){
        try {
        	Class.forName("org.postgresql.Driver");
    	} catch (ClassNotFoundException e) {
    			e.printStackTrace();
    		}
    		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost/donutfinder", "postgres",
					"donuts");
		} catch (SQLException e) {
			e.printStackTrace();
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
           
        }catch(Exception e){
            System.out.println("jjjjjj"+e); 
        }
        
    }
    public String removeFirstAndLastChar(String string){
         String s = string.substring(1,string.length() - 1); 
        return s; 
    }
    
}


