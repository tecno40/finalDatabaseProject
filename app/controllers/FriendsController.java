
package controllers;

import play.mvc.*;
import views.html.*;
import play.db.*;
import models.*;
import java.sql.*;
import java.util.*;

import javax.inject.Inject;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.*;


public class FriendsController extends Controller {
    public Result FriendPage(){
     
        String userId = "8"; 
        userId = request().getQueryString("friend");
        
        Application ap;
		Users user2;		
		ap=new Application();
		user2=ap.getUser();
		if (user2==null)
		{
			return temporaryRedirect("/");
		}


        String sql = "select id, first_name from users where id =:uname"; 
        List<SqlRow> sqlavg = Ebean.createSqlQuery(sql).setParameter("uname", userId).findList();
        boolean b = false; 
        for(SqlRow sql_row: sqlavg){
            if(sql_row.getString("first_name") != null && !sql_row.getString("first_name").equals("") ){
                b=true; 
                break; 
            }
        }
        if(b == false){
           return notFound("<h1>Friend not found</h1><h2>Please enter the valid username</h2>").as("text/html");
        }
        return find(userId); 
        
    }
    
     public Result find(String userId){
     
     	Application ap;
		Users user2;		
		ap=new Application();
		user2=ap.getUser();
		if (user2==null)
		{
			return temporaryRedirect("/");
		}

     
         String user = user2.id;
         
         // SQL query.
        String sql = "SELECT s.name, s.street, c.city, c.state FROM Shop s, favorites f, cities c "+
                "WHERE f.shopid = s.id and f.userid=:userid and s.cityid=c.id";
        // Will get the list of Sql rows.
        List<SqlRow> sqlavg = Ebean.createSqlQuery(sql).setParameter("userid", userId).findList();
        List<Friendsfavs> favs = new ArrayList<Friendsfavs>(); 
        for(SqlRow sql_row: sqlavg){
            Friendsfavs ar = new Friendsfavs(); 
            ar.name = sql_row.getString("name"); 
            ar.street = sql_row.getString("street");
            ar.city = sql_row.getString("city"); 
            ar.state = sql_row.getString("state");
            favs.add(ar); 
            System.out.println("-- " + ar.name + " -- " + ar.street + " -- " + ar.state);
        }
        
        String sql2 = "SELECT r.reviewtext, r.overallrating, s.name "+ 
                "from shop s, review r " +
                "where r.user_id = :uname and r.shop_id = s.id";
        List<SqlRow> sqlavg2 = Ebean.createSqlQuery(sql2).setParameter("uname", userId).findList();
        List<Friendsfavs> reviews = new ArrayList<Friendsfavs>(); 
        for(SqlRow sql_row: sqlavg2){
            Friendsfavs ar = new Friendsfavs(); 
            ar.name = sql_row.getString("name"); 
            ar.overallrating = sql_row.getString("overallrating");
            ar.reviewtext = sql_row.getString("reviewtext"); 
            reviews.add(ar); 
            System.out.println("-- " + ar.name + " -- " + ar.overallrating + " -- " + ar.reviewtext);
        }
        return ok(friends.render(userId, favs, reviews, user )); 
     }
    
}