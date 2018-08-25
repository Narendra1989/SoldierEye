package BullsEye;
import java.util.Date;

public class Model {
	    
		public String modelammunition;
		//public String modeljtnameofshoot;
		
	    public Integer AddLoad; //set it to '0' if the loaded from Add new round and set it to 1 if loaded from LoadSavedShoot.
		public String  modeljtrange;
		public String  modeljtshootname;
		public Integer modelgridsize;
		public Integer modeljtatmtemp;
		public Integer modeljtchtemp;
		public String  modeljtdateandtime;
		public Integer modeljthwind;
		public Integer modeljtxwind;	
		public Integer OkCancel; //This is to check if Ok or cancel was pressed on the BullsEyeAddRounds form.
		public Integer noOfRounds;
		public Integer shootid;
		public Integer TankNo;
		public String GunnerName;
		public Integer AID;
		public Integer RID;
		
		public void setaid(Integer aid){
			this.AID = aid;
		}
		 
		public Integer getaid(){
			return AID;
		}
		
		public void setrid(Integer rid){
			this.RID = rid;
		}
		
		public Integer getrid(){
			return RID;
		}			
		
		
		public void setgunnername(String gname){
			this.GunnerName = gname;
		}
		
		public String getgunnername(){
			return GunnerName;
		}
		
		
		public void settankno(Integer tno){
			this.TankNo = tno;
		}
		
		public Integer gettankno(){
			return TankNo;
		}
		
		
		public void setshootid(Integer sid){
			this.shootid = sid;
		}
		
		public Integer getshootid(){
			return shootid;
		}
		
		
		
		public void setnoOfRounds(Integer rnds){
			this.noOfRounds = rnds;
		}
		
		public Integer getnoOfRounds(){
			return noOfRounds;
		}
		
		
		public void setOkCancel(Integer OC){
			this.OkCancel = OC;
		}
		
		public Integer getOkCancel(){
			return OkCancel;
		}
		
	    
		public void setAddorLoad(Integer AL){
			this.AddLoad = AL;
		}
		
		public Integer getAddorLoad(){
			return AddLoad;
		}
		
		public void setShootname(String shootname){
			this.modeljtshootname = shootname;
		}
		
		public String getShootname(){
			return modeljtshootname;
		}
		
		public void setAmmunition(String ammu ){
			this.modelammunition = ammu;
		}
		
		
		public String getAmmunition(){
	    	return modelammunition;    	  	
	    }
	    
		
		public void setRange(String range){
			this.modeljtrange = range;
		}
	   
	    public String getRange(){
	    	return modeljtrange;
	    }
	    
	    
	    public void setAtmtemp(Integer atemp){
	    	this.modeljtatmtemp = atemp;
	    }
	    
	    public Integer getAtmtemp(){
	    	return modeljtatmtemp;
	    }
	    
	    
	    public void setgridsize(Integer gridsz){
	    	this.modelgridsize = gridsz;
	    }
	    
	    public Integer getgridsize(){
	    	return modelgridsize;
	    }
	    
	    
	    public void setChtemp(Integer chtemp){
	    	this.modeljtchtemp = chtemp;
	    }
	    
	    public Integer getChtemp(){
	    	return modeljtchtemp;    	
	    }
	    
	    public void setHWind(Integer hwind){
	    	this.modeljthwind = hwind;
	    }
	    
	    public Integer getHWind(){
	    	return modeljthwind;
	    }
	    
	    public void setXWind(Integer xwind){
	    	this.modeljtxwind = xwind;
	    }
	    
	    public Integer getXWind(){
	    	return modeljtxwind;
	    }
	    
	    public void setdate(String dt){
	    	this.modeljtdateandtime = dt;
	    }
	    
	    public String getdate(){
	    	return modeljtdateandtime;
	    }
}
