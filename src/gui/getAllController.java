/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.EventService;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class getAllController implements Initializable {

    @FXML
    private AnchorPane registerPane;
    @FXML
    private TableView<Event> tvHamza;
    ObservableList<Event> evtList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> coltitre;
    @FXML
    private TableColumn<?, ?> coldesc;
    @FXML
    private TableColumn<?, ?> coldebut;
    @FXML
    private TableColumn<?, ?> colfin;
    @FXML
    private TextField searchField;
    private TableView<Event> tableView;
    private ObservableList<Event> dataList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ObservableList<Event> list = getEList();
                coltitre.setCellValueFactory(new PropertyValueFactory<>("title"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        coldebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        colfin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
                tvHamza.setItems(list);
                
  
    }    
    
    
    public ObservableList<Event> getEList() {

        evtList.clear();
        
        EventService es= new EventService();
         
        
        try {
            evtList.addAll( es.afficher() );
        } catch (SQLException ex) {
            Logger.getLogger(getAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return evtList;
        
    }
    
      
       


    @FXML
        public void refresh() {
        ObservableList<Event> list = getEList();
        coltitre.setCellValueFactory(new PropertyValueFactory<>("title"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        coldebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        colfin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        tvHamza.setItems(list);
    }
    
    
    @FXML
  private void Suppr() {
        Event usr = (Event) tvHamza.getSelectionModel().getSelectedItem();
        if( usr!=null ) {
        //
                try {
                        EventService es= new EventService();
                        es.delete(usr.getIdEvent() );
            System.out.println("event est supprimée");
            
                } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        //
        refresh();
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("event :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("event supprimé");
                alert.showAndWait();
    }
        else{
            System.out.println("event not selected ");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("event :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a event from the table ");
                alert.showAndWait();
        }
    }
        
      
        
    @FXML
          private void modifier() {
              
              
                      Event rico = (Event) tvHamza.getSelectionModel().getSelectedItem();
        if( rico!=null ) {
        //
        
                      
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/modifierEvent.fxml"));
                // Get the root node of the new FXML file
                Parent root77;
                          try {
                              root77 = loader.load();
                                              // Get the controller of the new FXML file
                ModifierEventController controllerB = loader.getController();
                // Get the User variable to pass to ControllerB
                // Call the method in ControllerB to pass the User variable
                controllerB.receive(rico);
                // Set the scene for the new FXML file
                Scene scene77 = new Scene(root77);
                Stage newStage77 = new Stage();
                newStage77.setScene(scene77);
                newStage77.show();
   
                              
                              
                          } catch (IOException ex) {
                              Logger.getLogger(getAllController.class.getName()).log(Level.SEVERE, null, ex);
                          }
                          

        
        
        
        
    }
        else{
            System.out.println("event not selected ");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("event :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a event from the table ");
                alert.showAndWait();
        }
              
              
              
          }
        
        
        
            
    @FXML
        public void ajo() {
            
                          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ajouterEvent.fxml"));
                // Get the root node of the new FXML file
                Parent root66;
                          try {
                              root66 = loader.load();
                AjouterEventController controllerB = loader.getController();
  
                Scene scene66 = new Scene(root66);
                Stage newStage66 = new Stage();
                newStage66.setScene(scene66);
                newStage66.show();
                   } catch (IOException ex) {
                              Logger.getLogger(getAllController.class.getName()).log(Level.SEVERE, null, ex);
                          }
            
    }
    
          
          
          
  /*  
    public ObservableList<Event> getEList() {

        evtList.clear();
        
        EventService es= new EventService();
         
        
        try {
            evtList.addAll( es.afficher() );
        } catch (SQLException ex) {
            Logger.getLogger(getAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return evtList;
        
    }
*/
   public void searchEvent() {
        FilteredList<Event> filteredData = new FilteredList<>(dataList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Event -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (Event.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
               
                }
                return false;
            });
        });
        // wrap the filtered list in a SortedList
        SortedList<Event> sortedData = new SortedList<>(filteredData);
        // bind the SortedList comparator to the TableView comparator
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        // add sorted (and filtered) data to the table
        tableView.setItems(sortedData);
    }
        
}
