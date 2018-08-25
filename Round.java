package BullsEye;
import java.util.Date;

public class Round {
	    	
    public Integer Round_No, HWind, XWind, AtmTemp , ChTemp,Seriesindex;       
    public Double  XCoord ,YCoord;   
    public String Type, AddUpdate;
    public Date DateTime;
    
    
    public void setAddUpdate(String AU){
    	AddUpdate = AU;
    }
    
    public String getAddUpdate(){
        return AddUpdate;
    }
    
    public void setrno(Integer rno){
        Round_No = rno;
    }
    
    public int getrno(){
        return Round_No; 
    }
    
    public void setHWind(Integer hwind){
        HWind = hwind;
    }
    
    public int getHWind(){
        return HWind;
    }
    
    public void setXWind(Integer xwind){
    	XWind = xwind;
    }
    
        public int getXWind(){
        return XWind;
    }
    
    public void setatmtemp(Integer atemp){
    	AtmTemp = atemp;
    }

    
    public int getatmtemp(){
        return AtmTemp;
    }

    
    public void setchtemp(Integer chtemp){
    	ChTemp = chtemp;
    }
        
    public int getchTemp(){
        return ChTemp;
    }
    
    public void setseriesind(Integer sind){
        Seriesindex = sind;
    }
    
    public int getseriesind(){
        return Seriesindex;
    }

    public void setxcoord(double xc){
        XCoord = xc;
    }
    
    public double getxcoord(){
        return XCoord;
    }
    
    public void setycoord(double yc){
        YCoord = yc;
    }
    
    public double getycoord(){
        return YCoord;
    }
    
    public void settype(String t){
        Type = t;
    }
    
    public String gettype(){
        return Type;
    }
    
    public void setDateTime(Date dt){
    	DateTime = dt;
    }
    
    public Date getDateTime(){
    	return DateTime;
    }
    
    
}
