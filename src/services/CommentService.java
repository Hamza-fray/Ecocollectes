/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

/**
 *
 * @author khalil
 */

import entities.Comment;
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


public class CommentService {
    Connection connexion;
    Statement stm;
    private int randomCode;

    public int getRandomCode() {
        return randomCode;
    }
    
    public CommentService() {
        connexion = DataBase.getInstance().getConnection();
    }
       
    
    public List<Comment> afficher() throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String req = "select * from comment";
         PreparedStatement stm = connexion.prepareStatement(req);
        //ensemble de resultat
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            Comment c;
            c = new Comment(
                    rst.getInt("id"),
                    rst.getString("text")
                    

                   
            );
            comments.add(c);
        }
        return comments;

    }
    
        public void ajouter(Comment c) throws SQLException {

        String req = "INSERT INTO `comment` (`text`) "
                + "VALUES ( ?,) ";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, c.getText());
           
          

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
        
            public void delete(int id) throws SQLException {
        try {
            PreparedStatement pre = connexion.prepareStatement("Delete from comment where id=? ;");
            pre.setInt(1, id);
            if (pre.executeUpdate() != 0) {
                System.out.println("Comment Deleted");

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
           
           public Comment findCommentById(int id) throws SQLException {
               Comment c = null ;
               try {
             String req="select * from comment where id = ?" ;
            PreparedStatement pst = connexion.prepareStatement(req);
             pst.setInt(1, id); 
         
          
             ResultSet rst = pst.executeQuery();
              while (rst.next()) {
                c = new Comment(
                    rst.getInt("id") ,
                    
                   
                    rst.getString("text")
                   
                   
            );
              }
            
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
               return c ;
           }
             
           
             public void modifier(int id,Comment newecomment) {
        try {
            String req ="update comment set text=?,title=?";
            PreparedStatement pst = connexion.prepareStatement(req);
            pst.setString(1, newecomment.getText());
           
          
          
             pst.setInt(8, id);
            pst.executeUpdate();
            
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("succesfully updated");
                    alert.showAndWait(); 
           
            System.out.println("Commentaie est modifié");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                   Alert  alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("update error");
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
    }}
             
           
             
        
     
}
