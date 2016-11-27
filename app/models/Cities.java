package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

@Entity
public class Cities extends Model {
 
    public Integer id;
    public String city;
    public String state;
 
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
