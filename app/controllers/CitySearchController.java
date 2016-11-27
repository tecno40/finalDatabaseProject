package controllers;

import play.mvc.*;
import views.html.*;
import play.db.*;
import models.*;
import java.sql.*;
import java.util.*;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class CitySearchController extends Controller {
    Application ap; 

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result cityShops(){
        ap = new Application(); 
        
        Cities city=new Cities();
        city.city="Atlanta";
        city.state="GA";
        
        
        return ok(city_shops.render()); 
    }
    
   }

