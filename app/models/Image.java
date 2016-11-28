package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import play.data.format.*;
import play.data.validation.*;

@Entity
public class Image extends Model {
 
    public Integer id;
    public Integer shopid;
    public String userid;
    public String url;
 
 	public static Finder<Integer, Image> find
            = new Model.Finder<>(Integer.class, Image.class);
 

     
}
