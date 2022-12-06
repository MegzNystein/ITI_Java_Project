package server;
/************************************************************************************************************
 * @author Group3
************************************************************************************************************/
import java.text.SimpleDateFormat;
import java.util.Date;


public class FireAccident 
{
    private int fireId; 
    FireAccident nextFireAccident; 
    private String fireAccidentDate; 
    private String fireAccidentTime; 
    static int totalFireAccidents = 0; 

     
    FireAccident()
    {
	this.fireId = 1000; 
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/Y");
        this.fireAccidentDate = dateFormat.format(currentDate);
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        this.fireAccidentTime = timeFormat.format(currentDate);
        this.nextFireAccident=null;
    }
    FireAccident(int id)
    {
        this.fireId=id; 
        Date currentDate=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/Y");
        this.fireAccidentDate=dateFormat.format(currentDate);
        SimpleDateFormat timeFormat=new SimpleDateFormat("hh:mm:ss a");
        this.fireAccidentTime=timeFormat.format(currentDate);
        this.nextFireAccident=null;
    }
    
    public void setFireId(int id)
    {
        fireId =id;
    }
    
    public int getFireId()
    {
       return fireId;
    }
    
    public String getFireAccidentDate()
    {
       return fireAccidentDate;
    }
    
    public String getFireAccidentTime()
    {
       return fireAccidentTime;
    }

}
