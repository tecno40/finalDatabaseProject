package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import play.data.format.*;
import play.data.validation.*;

@Entity
public class User extends Model {
 
    public String id;
    public String password;
    public String first_name;
    public String last_name;
    public Integer cityid;
    public String city;
    public String state;
    public String email;
     	
 	public static Finder<String, User> find
            = new Model.Finder<>(String.class, User.class);
 
 	     
}
