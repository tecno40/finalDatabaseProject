package controllers;

import play.mvc.*;
import views.html.*;
import play.db.*;
import java.sql.*;
import java.util.*;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    Application ap; 

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render("Index"));
    }
    public Result friends(){
        ap = new Application(); 
        FriendsPage fp = ap.getFriendsPage("8"); 
        return ok(friends.render(fp.getName(),fp.getFavs(), fp.getReviews()));
    }
    public Result main(){
        ap = new Application(); 
        MainPage i = ap.getMainPageInfo(111); 
        return ok(main.render(i.getName(), i.getLoc(), i.getSM(), i.getRatings(), i.getFeatures(), i.getReviews(),i.getImage(), "111", "8")); 
    }
    
    public  Result submitreviews() {
        Set<Map.Entry<String,String[]>> entries = request().queryString().entrySet();

        ap = new Application(); 
        ap.updateReviews(entries); 
    
        return ok(index.render("Your new application is ready."));
    }
    
    public Result searchFriends(){
        ap = new Application(); 
        Set<Map.Entry<String,String[]>> entries = request().queryString().entrySet();
        String friend = ap.getUsername(entries); 
        if (friend == null){
            return notFound("<h1>Friend not found</h1><h2>Please enter the full username</h2>").as("text/html");

        }
        System.out.println("friend: " + friend); 
        FriendsPage fp = ap.getFriendsPage(friend);
        return ok(friends.render(fp.getName(),fp.getFavs(), fp.getReviews()));

    }
    
    public Result searchPlaces(){
        ap = new Application(); 
        Set<Map.Entry<String,String[]>> entries = request().queryString().entrySet();
        String id = ap.getShopId(entries); 
        if (id == null){
            return notFound("<h1>Place not found</h1><h2>Please enter the Shop name , Street, City, State</h2>").as("text/html");
        }
        MainPage i = ap.getMainPageInfo(Integer.parseInt(id)); 
        return ok(main.render(i.getName(), i.getLoc(), i.getSM(), i.getRatings(), i.getFeatures(), i.getReviews(),i.getImage(), id, "8"));  
    }
}

