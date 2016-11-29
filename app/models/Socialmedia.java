package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import play.data.format.*;
import play.data.validation.*;

@Entity
public class Socialmedia extends Model {
 
    public Integer shopid;
    public String platform;
    public String handle; 
    
 
 	public static Finder<Integer, Socialmedia> find = new Model.Finder<>(Integer.class, Socialmedia.class);
 

     
}
