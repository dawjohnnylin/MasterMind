package team.six.fxmlcontrollers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import team.six.mastermind.client.MMClientApp;
import team.six.mastermind.common.MMConfig;

/**
 * FXML Controller class
 *
 * @author 1437203
 */
public class FXMLConnectController {
    private MMConfig conf;
    private Stage stage;
    private MMClientApp app;
    
    @FXML
    private TextField in_host;
    
    @FXML
    public void initialize() {
        // Loaded
    }
    
    public FXMLConnectController(){
        this.conf = new MMConfig();
    }
    
    public void setContext(MMClientApp app, Stage stage, MMConfig conf){
        this.app = app;
        this.stage = stage;
        this.conf = conf;
        
        // Bind here because method runs AFTER initialize and would be binded to old object
        Bindings.bindBidirectional(in_host.textProperty(), conf.getHostProperty());
    }

    @FXML
    void onConnect(ActionEvent event) throws IOException {
        // Check for empty
        if(!Objects.equals(in_host.getText(), "")){
            // Prepare next scene
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("/fxml/FXMLMastermind.fxml"));
            loader.setResources(ResourceBundle.getBundle("MessagesBundle"));

            GridPane root = (GridPane) loader.load();

            FXMLMastermindController cont = cont = loader.getController();
            cont.setContext(app, stage, conf);
            loader.setController(cont);

            Scene scene = new Scene(root);

            stage.setScene(scene);
        }
    }

    @FXML
    void onExit(ActionEvent event) {
        Platform.exit();
    }
}
