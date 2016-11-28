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
        
        int cityId=0;
        String cityIdRaw;
        String cityString="";
        Cities city;
        List <Shop> shopList=new ArrayList<Shop>();
        List <Cities> cityList;
        
        cityIdRaw=request().getQueryString("city");
        if (cityIdRaw!=null)
	    {
	    	cityId=Integer.parseInt(cityIdRaw);
	        if (cityId>0)
	        {
	        	shopList = Shop.find.where().eq("cityid",cityId).findList();
	        	
	        	city=Cities.find.where().eq("id",cityId).findUnique();
	        	cityString=" in "+city.city+", "+city.state;
	        }
        }
        
        cityList = Cities.find.all();               
        
        return ok(city_shops.render(cityList,cityId,shopList,cityString)); 
    }
    
   }

