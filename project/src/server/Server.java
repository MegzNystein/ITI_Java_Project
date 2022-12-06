package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/************************************************************************************************************
 * @author Group3
 ************************************************************************************************************/
public class Server 
{
    ServerSocket serversock;
    Socket mysocket;
    
       public Server()
       {
            try
            {
                serversock = new ServerSocket(5005);
                while(true)
                {
                     System.out.println("server __starts__");
                     mysocket = serversock.accept();
                     new ClientHandler(mysocket);
                }
            } 
            catch (IOException e)
            {
                System.out.println(e);
            }
    
    
       }
}
