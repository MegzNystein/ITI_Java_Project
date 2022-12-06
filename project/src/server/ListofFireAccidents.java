package server;
/************************************************************************************************************
 * @author Group3
************************************************************************************************************/
import java.awt.Desktop;
import java.io.*;


public class ListofFireAccidents 
{
    private FireAccident start; 
    private String filepath ;      
    File file;                     

    
    ListofFireAccidents()
    {
	start=null;
    }
    
    public void appendAccident(FireAccident data)
    {

        FireAccident accident= data;
        if (start==null)
        { 
            start = accident;
        }else
        {
            FireAccident fire=start;
            while(fire.nextFireAccident !=null)
            {
                fire=fire.nextFireAccident;
            }
        fire.nextFireAccident=accident;
        }
        
        FireAccident.totalFireAccidents++; 
    }

    public FireAccident getAccident(int id){
        FireAccident accident=start;   
        while(accident.nextFireAccident !=null)
        {
            if (accident.getFireId()== id)
            {
                return accident; 
            }
            accident=accident.nextFireAccident; 
        }
        
        if (accident.getFireId()== id)
        {
            return accident; 
        }
        return null; 
    }

    public void deleteAccident(int id)
    {
      if(id==1)
      {
        FireAccident newHead=start;
        start=newHead.nextFireAccident;
        newHead.nextFireAccident=null;
      }
      else if(id == FireAccident.totalFireAccidents)
      {
            FireAccident previous=start;
            for(int i=1;i<FireAccident.totalFireAccidents;i++)
            {
                previous=previous.nextFireAccident; 
            }
            previous.nextFireAccident=null;

      }else
      {
            FireAccident previous=start;
            int count=FireAccident.totalFireAccidents -id;
            while(count<(id-1))
            {
                previous=previous.nextFireAccident;    
                count++;
            }
            FireAccident current=previous.nextFireAccident;
            previous.nextFireAccident=current.nextFireAccident;
            current.nextFireAccident=null;
      }
      FireAccident.totalFireAccidents--; 

    }
     
    public void setFilePath(String fp) throws FileNotFoundException
    {
        filepath= fp; 
        this.file=new File(fp); // 
    }

    public String getListofFireAccident(){

        String fileContents="";
        String fireId="";             
        FireAccident accident=start;
        if(accident==null){
            System.out.println("No Fire Accident");
            fileContents= fileContents.concat("No Fire Accident");
        }

       while(accident.nextFireAccident !=null)
       {
            fileContents=fileContents.concat("fireID:"+fireId.valueOf(accident.getFireId())+"\t fireDate:"+
            accident.getFireAccidentDate()+"\t fireTime:"+
            accident.getFireAccidentTime());
            accident=accident.nextFireAccident;
        }
        fileContents=fileContents.concat("fireID:"+fireId.valueOf(accident.getFireId())+"\t fireDate:"+
          accident.getFireAccidentDate()+"\t fireTime:"+accident.getFireAccidentTime());
         return  fileContents;   
    }

    
    public void recordData() throws IOException
    {
    	FileWriter writer=new FileWriter(filepath);
    	String fileContents=""; 
        String fireId="";       
        FireAccident accident=start; 
        if(accident==null)
        {
            System.out.println("No Fire Accident");
            fileContents= fileContents.concat("No Fire Accident");//set the empty string to no fires 
            writer.write(fileContents);
            writer.close(); 
            return; 
        }
        
       while(accident.nextFireAccident !=null)
       {
            fileContents= fileContents.concat("fireID:"+fireId.valueOf(accident.getFireId())+"\t fireDate:"+
                accident.getFireAccidentDate()+"\t fireTime:"+accident.getFireAccidentTime()+"\n"); 
            accident=accident.nextFireAccident; 

        }
        System.out.println("Enter First Fire Accident");
        fileContents=fileContents.concat("fireID:"+fireId.valueOf(accident.getFireId())+"\t fireDate:"+
        accident.getFireAccidentDate()+"\t fireTime:"+accident.getFireAccidentTime()+"\n"); 
        writer.write(fileContents);
        writer.close(); 

    }
    
    public void openListofFireAccident() throws IOException
    {
        Desktop listofFireAccidentFile=Desktop.getDesktop();
        listofFireAccidentFile.open(file);
    }

}
