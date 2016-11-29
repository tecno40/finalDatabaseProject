package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import play.data.format.*;
import play.data.validation.*;

@Entity
public class Feature extends Model {
 
    public Integer shopid;
    public String feature;
 
 	public static Finder<Integer, Feature> find = new Model.Finder<>(Integer.class, Feature.class);
 

     
}
