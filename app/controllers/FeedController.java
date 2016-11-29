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
   

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result feed(){
         ShopController sc = new ShopController(); 
        return sc.find(111, "8");  
    }
    
   }

