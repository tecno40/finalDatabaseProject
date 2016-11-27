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
public class FeedController extends Controller {
    Application ap; 

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result feed(){
        ap = new Application(); 
        MainPage i = ap.getMainPageInfo(111); 
        return ok(feed.render(i.getName(), i.getLoc(), i.getSM(), i.getRatings(), i.getFeatures(), i.getReviews(),i.getImage(), "111", "8")); 
    }
    
   }

