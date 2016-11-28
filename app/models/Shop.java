package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import play.data.format.*;
import play.data.validation.*;

@Entity
public class Shop extends Model {
 
    public Integer id;
    public String name;
    public String street;
    public Integer cityid;
    public String phone_number;
    //public openhours;
    //public closedhours;
    public String menu_url;
    public Integer varieties;
    //public ppdzn;
    //public ppglzd;
    public double overallrating;
 	
 	public static Finder<Integer, Shop> find
            = new Model.Finder<>(Integer.class, Shop.class);
 
 	     
}
