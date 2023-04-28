/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.util.*;
import services.EventService;

/**
 *
 * @author Hamza
 */
public class Event{

   
    private int idevent ;
    private String description,title ;
    private String datedebut,datefin;
    
   

    public Event( int idevent , String description, String title , String datedebut, String datefin) {
        this.idevent = idevent;
        this.description = description;
        this.title = title;
        this.datedebut = datedebut;
        this.datefin = datefin;
        
        
    }
    
      public Event(String description, String title) {
       
        this.description = description;
        this.title = title;
      
        
        
    }
 public Event( String description, String title , String datedebut, String datefin) {
  
        this.description = description;
        this.title = title;
        this.datedebut = datedebut;
        this.datefin = datefin;
        
        
    }



   
    @Override
    public String toString() {
        return "Event{" + "id=" + idevent + ", description=" + description + ", title=" + title + ", datedebut=" + datedebut + ", datefin=" + datefin +'}';
    }

    public Event(int idevent, String title) {
        this.idevent = idevent;
        this.title = title;
    }

    public int getIdEvent() {
        return idevent;
    }

    public void setIdEvent(int idevent) {
        this.idevent = idevent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

   
    
    
}
