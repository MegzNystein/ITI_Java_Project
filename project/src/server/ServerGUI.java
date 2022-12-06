package server;
/************************************************************************************************************
 * @author Group3 
************************************************************************************************************/
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.Server;

public class ServerGUI extends Application
{
   @Override
    public void start(Stage stage) throws Exception 
    {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);

    } 
}


