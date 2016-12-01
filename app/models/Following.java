package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import play.data.format.*;
import play.data.validation.*;

@Entity
public class Following extends Model {
 
    public String followerid;
    public String followedid;
 
 	public static Finder<String, Following> find
            = new Model.Finder<>(String.class, Following.class);
 

     
}
