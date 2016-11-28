
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


public class ShopController extends Controller {
    public Result Shopfinder(){
       System.out.println("1");
        int shopId = 111;
        String userId = "8"; 
        userId = request().getQueryString("userid");
        System.out.println("2");
        shopId = Integer.parseInt(request().getQueryString("shopid"));
        System.out.println("3");
        
        return find(shopId, userId);
    }
    
    public Result searchShops(){
        int shopId; 
        String location = request().getQueryString("loc");
        String[] splitvalues = location.split(",");
        String myuserId = "8"; 
        myuserId = request().getQueryString("userid");

        String sql = "SELECT s.id from shop s, cities c where s.name = :name and s.cityid = c.id and s.street = :street" +
            "  and c.city = :city and c.state = :state";
         
        // Will get the list of Sql rows.
        SqlRow sqlrow = Ebean.createSqlQuery(sql).setParameter("name",splitvalues[0].trim()).setParameter(
            "street", splitvalues[1].trim()).setParameter("city", splitvalues[2].trim() ).setParameter(
                "state", splitvalues[3].trim()).findUnique();
        if (sqlrow == null){
            return notFound("<h1>Place not found</h1><h2>Please enter the Shop_name , Street, City, State</h2>").as("text/html"); 
        }else{
            return find(Integer.parseInt(sqlrow.getString("id")), myuserId); 
        }
    }
   
   public Result find(int shopId, String userId){
       System.out.println("4");
       models.Shop shop = null; 
        Cities city = null; 
        List <Socialmedia> mediaList=new ArrayList<Socialmedia>();
        List <Feature> features = new ArrayList<Feature>(); 
        List <Image> images = new ArrayList<Image>(); 
        
        shop = models.Shop.find.where().eq("id",shopId).findUnique();
        System.out.println("5");
	    if (shop != null){
	        city = Cities.find.where().eq("id", shop.cityid).findUnique(); 
	        System.out.println("Shop: " + shop.name + " -- " + city.state ); 
	        mediaList = Socialmedia.find.where().eq("shopid", shop.id).findList(); 
	        System.out.println("6.1");
	        features = Feature.find.where().eq("shopid", shop.id).findList(); 
	        System.out.println("7.1");
	        images = Image.find.where().eq("shopid", shop.id).findList(); 
	        System.out.println("8.1");
	    }  
	    
	  //get ratings 
        // SQL query.
        String sql = "SELECT ratingtype, avg(ratingval) as averageVal from Rating where shopid = :shopid group by ratingtype";
        
        // Will get the list of Sql rows.
        List<SqlRow> sqlavg = Ebean.createSqlQuery(sql).setParameter("shopid", shop.id).findList();
        List<AverageRatings> ratings = new ArrayList<AverageRatings>(); 
        for(SqlRow sql_row: sqlavg){
            AverageRatings ar = new AverageRatings(); 
            ar.ratingtype = sql_row.getString("ratingtype"); 
            ar.averageVal = sql_row.getInteger("averageVal");
            ratings.add(ar); 
        }
       System.out.println("7");
        // SQL query.
        String sql2 = "select r.overallrating,r.reviewtext,u.id from users u, review r where r.shop_id = :shopid and r.user_id = u.id";
        List<SqlRow> sql3 = Ebean.createSqlQuery(sql2).setParameter("shopid", shop.id).findList();
        List<Reviewsofshop> reviews = new ArrayList<Reviewsofshop>(); 
        for(SqlRow sql_row2: sql3){
            Reviewsofshop ar = new Reviewsofshop(); 
            
            ar.overallrating = sql_row2.getString("overallrating"); 
            ar.reviewtext = sql_row2.getString("reviewtext");
            ar.userid = sql_row2.getString("id"); 
            reviews.add(ar); 
        }
         System.out.println("8"); 
         Image image; 
         try{
              image = images.get(0);
         }catch(Exception e){
             image = new Image(); 
             image.url = "http://media1.fdncms.com/sfweekly/imager/dynamo-teams-up-with-act-to-make-simps/u/blog/3383339/homer_donut.png?cb=1471312065";
         }
        // Will get the list of Sql rows.
        return ok(shopview.render(shop,userId,city,mediaList, features, image, ratings, reviews));
   }
    
}
