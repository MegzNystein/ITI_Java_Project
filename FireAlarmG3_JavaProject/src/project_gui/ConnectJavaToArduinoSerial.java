
package project_gui;

import com.fazecast.jSerialComm.SerialPort;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.Vector;

public class ConnectJavaToArduinoSerial {
    
    Scanner inputTojava;              
    OutputStream outputFromJava;        
    protected static String buffer;       
    protected static int temp;       
    protected static int hum;       
    static SerialPort pcSerialPort;  
    String[] records;
    
    public void serialEstablishmentConnectionToArduino() {
        Vector<String> portVector = new Vector<String>();
        SerialPort[] portID = SerialPort.getCommPorts();
        String pcCom = "";
                for (int i = 0; i < portID.length; i++) {
                    portVector.add(portID[i].getSystemPortName());
                    System.out.println(i + "- " + portID[i].getSystemPortName());
                    pcCom = portID[i].getSystemPortName();
                }
        pcSerialPort = SerialPort.getCommPort(pcCom);
        pcSerialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        inputTojava = new Scanner(pcSerialPort.getInputStream());
        pcSerialPort.openPort();
    }
    
        public void sendSerialData(int data) {
        Thread sendTh = new Thread() {
            @Override
            public void run() {
               try 
                {
                    Thread.sleep(1000);
                } 
                catch (Exception e) 
                {
                    System.out.println(e);
                }
                
                outputFromJava = pcSerialPort.getOutputStream();
                try 
                {
                     outputFromJava.write(data);
                }
                catch (Exception e) 
                {
                    System.out.println(e);
                }
            }
        };
        sendTh.start();
    }
    
    public void receiveSerialData() {
        Thread receiveTh = new Thread(new Runnable(){
           @Override
           public void run() {
               
               try
               {
                   System.out.println("Receive Data Form Arduino");
               }
               catch(Exception e)
               {
                   System.out.println(e);
               }                
                   
                while (inputTojava.hasNextLine()) 
                {
                    try 
                    {
                       buffer = inputTojava.nextLine();
                       records=buffer.split("-",2);
                       
                       temp=Integer.parseInt(records[0]);
                       hum=Integer.parseInt(records[1]);
                       
                       System.out.println(temp);
                       System.out.println(hum);
                       Thread.sleep(50);
                       buffer=null;
                    }
                    catch (Exception e) 
                    {
                        System.out.println(e);
                    }
                }               
            }
        });
        receiveTh.start();
    }
    
}
