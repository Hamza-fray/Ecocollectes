/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

/**
 *
 * @author khalil
 */

import entities.Event;
import utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;
import javafx.scene.control.Alert;
import java.sql.Date;


public class EventService{
    Connection connexion;
    Statement stm;
    private int randomCode;

    public int getRandomCode() {
        return randomCode;
    }
    
    public EventService() {
        connexion = DataBase.getInstance().getConnection();
    }
       
    
    public List<Event> afficher() throws SQLException {
        List<Event> events = new ArrayList<>();
        String req = "select * from event";
         PreparedStatement stm = connexion.prepareStatement(req);
        //ensemble de resultat
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            Event e;
            e = new Event(
                    rst.getInt("idevent"),
                    rst.getString("title"),
                    rst.getString("description"),
                    rst.getString("datedebut"),
                    rst.getString("datefin")

                   
            );
            events.add(e);
        }
        return events;

    }
    
   
    public void ajouter(Event e) throws SQLException {

        String req = "INSERT INTO `event` (`description`, `title`, `datedebut`, `datefin`) "
                + "VALUES ( ?, ?, ?,  ?) ";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, e.getDescription());
            ps.setString(2, e.getTitle());
            ps.setString(3, e.getDatedebut() );
            ps.setString(4, e.getDatefin() );

         
          

            ps.executeUpdate();
            
             
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("succesfully added");
                    alert.showAndWait(); 
                    
        } catch (SQLException ex) {
              Alert  alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("addition error");
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
        }
    }
        
            public void delete(int idevent) throws SQLException {
        try {
            PreparedStatement pre = connexion.prepareStatement("Delete from event where idevent=? ;");
            pre.setInt(1, idevent);
            if (pre.executeUpdate() != 0) {
                System.out.println("event Deleted");

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
           
           public Event findEventById(int id) throws SQLException {
               Event e = null ;
               try {
             String req="select * from event where idevent = ?" ;
            PreparedStatement pst = connexion.prepareStatement(req);
             pst.setInt(1, id); 
         
          
             ResultSet rst = pst.executeQuery();
              while (rst.next()) {
                e = new Event(
                    rst.getInt("idevent") ,
                    
                    rst.getString("description"),
                    rst.getString("title"),
                    rst.getString("datedebut"),
                    rst.getString("datefin")
                    
                   
            );
              }
            
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
               return e ;
           }
                 public Event findEventByTitle(String title) throws SQLException {
               Event e = null ;
               try {
             String req="select * from event where title = ?" ;
            PreparedStatement pst = connexion.prepareStatement(req);
             pst.setString(1, title); 
         
          
             ResultSet rst = pst.executeQuery();
              while (rst.next()) {
                e = new Event(
                    rst.getInt("idevent") ,
                   
                    rst.getString("description"),
                    rst.getString("title"),
                    rst.getString("datedebut"),
                    rst.getString("datefin")
                    
                   
            );
              }
            
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
               return e ;
           }
           
             public void modifier(int id, Event newevent) {
        try {
            String req ="update event set description=?,title=?,datedebut=?,datefin=? where idevent=?";
            PreparedStatement pst = connexion.prepareStatement(req);
            pst.setString(1, newevent.getDescription());
            pst.setString(2, newevent.getTitle());
            pst.setString(3, newevent.getDatedebut() );
            pst.setString(4, newevent.getDatefin() );
            pst.setInt(5, id );
          
            pst.executeUpdate();
            
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("succesfully updated");
                    alert.showAndWait(); 
           
            System.out.println("Evennement est modifié");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                   Alert  alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("update error");
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
    }}
             
           
             
        
     
}
