/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Event;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.EventService;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class ModifierEventController implements Initializable {

    @FXML
    private AnchorPane ajouterPane;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfDescription;
    @FXML
    private DatePicker tfDatedebut;
    @FXML
    private DatePicker tfDatefin;
    @FXML
    private Button modif;
    
    int iddEvv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

public void receive(Event e)
{
    tfTitle.setText( e.getTitle() );
    tfDescription.setText( e.getDescription() );
    
    iddEvv=e.getIdEvent();
}





    @FXML
    public void modif()
{

            String title = tfTitle.getText();
        String description= tfDescription.getText() ;
        String datedebut = tfDatedebut.getValue().toString();
        String datefin = tfDatefin.getValue().toString();
          Event e = new Event(title,description,datedebut,datefin);
    
    
    EventService es = new EventService();
            es.modifier(iddEvv,e);
    
    
}






    @FXML
    private void redirectToLogin(ActionEvent event) {
    }
    
}
