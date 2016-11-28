package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import play.data.format.*;
import play.data.validation.*;

@Entity
public class Cities extends Model {
 
    public Integer id;
    public String city;
    public String state;
 
 	public static Finder<Integer, Cities> find
            = new Model.Finder<>(Integer.class, Cities.class);
 

     
}
