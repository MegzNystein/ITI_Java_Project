package fireserverg3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import server.Server;

public class FXMLDocumentController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        new Server();
    }    


    
}
