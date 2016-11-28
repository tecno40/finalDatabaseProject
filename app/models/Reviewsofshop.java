package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import play.data.format.*;
import play.data.validation.*;

@Entity
public class Reviewsofshop extends Model {
    public String overallrating; 
    public String reviewtext; 
    public String userid; 
}
