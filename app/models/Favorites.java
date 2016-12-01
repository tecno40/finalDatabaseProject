package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import play.data.format.*;
import play.data.validation.*;

@Entity
public class Favorites extends Model {
 
    public String userid;
    public Integer shopid;
 
 	public static Finder<String, Favorites> find
            = new Model.Finder<>(String.class, Favorites.class);
 

     
}
