/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


/**
 *
 * @author Hamza
 */
public class Comment {
    private int idcomment ;
    private String text ;
    
 
    public Comment( int idcomment , String text) {
        this.idcomment = idcomment;
        this.text = text;
       
        
        
    }

   
    @Override
    public String toString() {
        return "Comment{" + "idcomment=" + idcomment + ", text=" + text + '}';
    }

    
    public int getIdComment() {
        return idcomment;
    }

    public void setId(int idcomment) {
        this.idcomment = idcomment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

  

  
    
    
}
