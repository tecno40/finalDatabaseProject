package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import play.data.format.*;
import play.data.validation.*;

@Entity
public class Friendsfavs extends Model {
    public String name; 
    public String street;
    public String state; 
    public String city; 

    public String overallrating; 
    public String reviewtext; 
}
