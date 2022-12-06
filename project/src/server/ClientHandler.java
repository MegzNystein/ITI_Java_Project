package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
/************************************************************************************************************
 * @author Group3 
************************************************************************************************************/
  public class ClientHandler extends Thread
  {
    DataInputStream dis;
    PrintStream ps;
    ListofFireAccidents fa = new ListofFireAccidents();
    
    static Vector<ClientHandler> clientsVector = new Vector<ClientHandler>();
    
    
     public ClientHandler(Socket mysocket) 
     {
        try 
        {
            dis = new DataInputStream(mysocket.getInputStream());
            ps = new PrintStream(mysocket.getOutputStream());
            clientsVector.add(this);                                                                                  // the word 'this' indicates the chatHandler object socket.
            System.out.println("New client");
            start();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
  }
     
  public void run() 
  {
    while (true) 
    {
        try 
        {
            System.out.print("thread request: ");
            String request = dis.readLine();
            System.out.println(request);

            if (request.equals("1")) 
            {
                System.out.println("New fire accidents!!");	
                FireAccident newAccident = new FireAccident(FireAccident.totalFireAccidents + 1); 
                fa.appendAccident(newAccident);                                                                     
            } 
            else if (request.equals("2")) 
            {
                System.out.println("Display the List of Fire Accidents.");
                fa.setFilePath("ListofFireAccidents.txt");		
                fa.recordData();																				// save recorded data in the file 'History.txt' on the pc harddisk that include all fire events records 
                fa.openListofFireAccident();																			// show history on UI in a txt opened from harddisk
            }
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
    }
  }
    
}
