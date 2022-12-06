package project_gui;

import eu.hansolo.medusa.Gauge;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;



public class FXMLDocumentController implements Initializable {
    //client creatation 
    Socket mySocket = new Socket("127.0.0.1",5005);                          
    DataInputStream dis = new DataInputStream(mySocket.getInputStream());                       
    PrintStream ps= new PrintStream(mySocket.getOutputStream());                            
    FireAccident fa= new FireAccident();  
    

    boolean flag=true;
    @FXML
    private AnchorPane A1;
    @FXML
    private Button history;
    @FXML
    private Button stop;
    @FXML
    private AnchorPane A2;
    
    public FXMLDocumentController() throws IOException {  
    }
    @FXML
    private Label label;
    @FXML
    private Gauge thermometer;
    @FXML
    private Gauge humidity;
    @FXML
    private AnchorPane Warring;
    
    @FXML
    private void handleButtonStop(ActionEvent event) 
    {
        
        Warring.setVisible(false);
        fa.stop();
        System.out.println("STOP");
       
    }
    @FXML
    private void handleButtonHistory(ActionEvent event) 
    {
      ps.println("2");
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
        ConnectJavaToArduinoSerial newConnection = new ConnectJavaToArduinoSerial();
        newConnection.serialEstablishmentConnectionToArduino();
        newConnection.receiveSerialData();
        thermometer.setSkinType(Gauge.SkinType.MODERN);
        humidity.setSkinType(Gauge.SkinType.MODERN);
        
        
             Thread gauges = new Thread(new Runnable() 
             {
               @Override
               public void run() 
               {
                while(true)
                {
                     System.out.println("receive data thread");
                     
                 try 
                 {
                    Thread.sleep(1000);
                    fa.checkFire();
                    humidity.setValue(fa.getHimuty());
                    thermometer.setValue(fa.getTemp());
                    
                    if(fa.getTemp()>25 && flag==true)
                    {
                         Warring.setVisible(true);
                         flag=false;
                    }
                    else if(fa.getTemp()<25 && flag==false)
                    {
                        flag=true;
                    }

                } 
                catch (InterruptedException e) 
                {
                    System.out.println(e);
                }
            }
        }
            
            
        });
        gauges.start();
        
    

   
    }    
    
}
