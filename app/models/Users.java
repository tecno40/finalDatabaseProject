package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import play.data.format.*;
import play.data.validation.*;

@Entity
public class Users extends Model {
 
    public String id;
    public String first_name;
    public String last_name;
    public Integer cityid;
    public String city;
    public String state;
    public String email;
    public String hashed_password;
     	
 	public static Finder<String, Users> find
            = new Model.Finder<>(String.class, Users.class);
 
 	     
}
