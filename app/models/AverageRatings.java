package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

import play.data.format.*;
import play.data.validation.*;

@Entity
public class AverageRatings extends Model {
 
    public String ratingtype; 
    public int averageVal; 

}
