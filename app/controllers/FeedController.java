package controllers;

import play.mvc.*;
import views.html.*;
import play.db.*;
import java.sql.*;
import java.util.*;
import models.*;

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
    	List <Favorites> favoritesList=new ArrayList<Favorites>();
		List <Following> followingList=new ArrayList<Following>();
		List <models.Shop> shopList=new ArrayList<models.Shop>();
		List <Users> userList=new ArrayList<Users>();
		models.Shop addShop;
		Users addUser;
		String friendsError="";
		String shopsError="";
		
		
		Application ap;
		Users user;		
		ap=new Application();
		user=ap.getUser();
		if (user==null)
		{
			return temporaryRedirect("/");
		}
		
		favoritesList = models.Favorites.find.where().eq("userid",user.id).findList();
    	followingList = models.Following.find.where().eq("followerid",user.id).findList();
    	
    	System.out.println("Printing favs...");
    	System.out.println(favoritesList.size());
    	for (Favorites favorite:favoritesList)
    	{
    		addShop=models.Shop.find.where().eq("id",favorite.shopid).findUnique();
    		shopList.add(addShop);
    	}
    	for (Following following:followingList)
    	{
    		addUser=Users.find.where().eq("id",following.followedid).findUnique();
    		userList.add(addUser);
    	}
    	
    	if (shopList.size()==0)
    	{
    		shopsError="No favorite shops.";
    	}
    	
    	if (userList.size()==0)
    	{
    		friendsError="You have no friends.";
    	}
    
        return ok(feed.render(shopList,userList,shopsError,friendsError));
 
    }
    
   }

