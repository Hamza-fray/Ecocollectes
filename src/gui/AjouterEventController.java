/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Event;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.EventService;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class AjouterEventController implements Initializable {

    @FXML
    private AnchorPane ajouterPane;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfDescription;
    @FXML
    private Button tfAjouterBtn;
    @FXML
    private DatePicker tfDatedebut;
    @FXML
    private DatePicker tfDatefin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterEvent(ActionEvent event) {
    
            String title = tfTitle.getText();
        String description= tfDescription.getText() ;
        String datedebut = tfDatedebut.getValue().toString();
        String datefin = tfDatefin.getValue().toString();

       System.out.println("test hamza ");  
      
        
        EventService s = new EventService();
          Event e = new Event(title,description,datedebut,datefin);
        
     
        try {
            s.ajouter(e);
            
            
            
                try {
                    System.out.println("email dela3a");
                    sendVariableEmail("hamza.fray@esprit.tn","you have successfully created event","Congrats");
                } catch (Exception ex) {
                    Logger.getLogger(AjouterEventController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AjouterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
    
    
    public static void sendVariableEmail(String toEmail,String context,String title) throws Exception {
    // Set up mail server properties
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    // Create a mail session with authentication
    Authenticator auth = new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("athlontn@gmail.com", "vbmcqlujlyxnftax");
        }
    };
    Session session = Session.getInstance(props, auth);

    Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress( "athlontn@gmail.com" ) );
    
    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
    msg.setSubject(title);
    msg.setText(context);
    Transport.send(msg);
}
    
    
    
    
    

    @FXML
    private void redirectToLogin(ActionEvent event) {
    }
    
}
