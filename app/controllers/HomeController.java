package controllers;

import play.mvc.*;
import views.html.*;
import play.db.*;
import java.sql.*;
import java.util.*;
import models.*;
import org.apache.commons.codec.digest.DigestUtils;
import com.avaje.ebean.*;
import play.api.mvc.Cookie;
import play.data.DynamicForm;
import play.data.Form;

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
        return ok(Home.render("Donut Finder",""));
    }
   
   public Result login(){

		DynamicForm dynamicForm = Form.form().bindFromRequest();    
   		//String userId = request().getQueryString("user");
   		//String pass = request().getQueryString("pass");
   		String userId=dynamicForm.get("user");
   		String pass=dynamicForm.get("pass");
   		String passwordError="";
   		Users user;
   		   		
   		user=Users.find.where().eq("id",userId).findUnique();
   		if (user==null)
   		{
   			passwordError="Your username or password is incorrect. The Donut Finder is a strict no hacking zone. Hackers will be prosecuted to the fullest extent of the nation which they reside.";
   			System.out.println("cannot find user");
   		}
   		else
   		{
        	String sql = "select hashed_password AS raw_crypt_pass,crypt(:password,users.salt) AS calculated_crypt_pass,:password AS pass from users WHERE id=:userid;"; 
        	List<SqlRow> sqlPass = Ebean.createSqlQuery(sql).setParameter("password", pass).setParameter("userid",user.id).findList();
        	for (SqlRow row: sqlPass)
        	{
        		if (row.getString("calculated_crypt_pass").equals(row.getString("raw_crypt_pass")))
        		{
        			System.out.println("user valid");
        			//response().setCookie("user",user.id);
        			session("user", user.id);
        			return temporaryRedirect("feed");
        		}
        		else
        		{
        			passwordError="Your username or password is incorrect. The Donut Finder is a strict no hacking zone. Hackers will be prosecuted to the fullest extent of the nation which they reside.";
        			System.out.println("incorrect password");
        			return ok(Home.render("Donut Finder",passwordError));
        		}
        	}
		}
   		
   		System.out.println("last");
   		return ok(Home.render("Donut Finder",passwordError));
   		
   }
    
    
     public  Result submitreviews() {
        Set<Map.Entry<String,String[]>> entries = request().queryString().entrySet();

        ap = new Application(); 
        return ap.updateReviews(entries); 
    
    }

    
   
}

