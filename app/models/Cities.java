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
 
 	public Integer getId(){
 		return id;
 	}
 
 	public void setId(Integer id){
 		this.id=id;
 	}
 	
 	public String getCity(){
 		return city;
 	}
 
 	public void setCity(String city){
 		this.city=city;
 	}

	public String getState(){
 		return state;
 	}
 
 	public void setState(String state){
 		this.state=state;
 	}

     
}
