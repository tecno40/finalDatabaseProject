package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import play.data.format.*;
import play.data.validation.*;

@Entity
public class Rating extends Model {
 
    public Integer shopid;
    public String feature;
 
 	public static Finder<Integer, Rating> find = new Model.Finder<>(Integer.class, Rating.class);
 

     
}
