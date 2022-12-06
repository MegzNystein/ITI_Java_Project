package project_gui;


import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;




public class FireAccident extends ConnectJavaToArduinoSerial{
      
    File file;                           
    Media media;                      
    MediaPlayer mediaPlayer;              
    boolean status = false;           
    boolean FireaccidentFlag = false;         
    
    FireAccident()
    {
        file = new File("alert.mpeg"); 
        media = new Media(file.toURI().toString());                                    
        mediaPlayer = new MediaPlayer(media);
    }
   
    public void fireWaraing() 
    { 
        boolean checkFireAccident = checkFire();         
        
        if (( checkFireAccident == true) && ( FireaccidentFlag == false)){ 
                 
            FireaccidentFlag = true;
            status = true;
            
            playMusic();                 
            LedBuzzer();                                      
        }
        else if((checkFireAccident == false) && ( FireaccidentFlag == true)){
            FireaccidentFlag=false;
        }
    }
    
    
    public boolean checkFire() 
    {
        boolean state = false;
        int tempr = temp;
        int humi = hum;
        System.out.println("The cuurent temp is: " + tempr);
        System.out.println("The current humidity is: " + humi);
        if (tempr > 25){
            state = true;
        }
        return state;
    }
    

    private void playMusic() 
    {
        mediaPlayer.setAutoPlay(true);              
    }
    
    public void stop(){
        this.sendSerialData(2);
        this.stopMusic();
    }
    
    void LedBuzzer()
    {
        this.sendSerialData(1);
    }
    
    public float getTemp()
    {
        return temp;
    }
    public float getHimuty()
    {
       return hum; 
    }
    
    private void stopMusic()
    {
        mediaPlayer.stop();
    }
}



