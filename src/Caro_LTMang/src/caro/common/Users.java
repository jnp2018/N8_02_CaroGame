package caro.common;

import java.io.Serializable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nhan Nguyen
 */
public class Users implements Serializable{
    private int Id;
    private String username;
   

    public Users(int Id, String username) {
        this.Id = Id;
        this.username = username;
     
    }


    public Users(String username, String password) {
        this.username = username;
    }
  
    
    public Users()
    {
    
    }
    
  
    
    public int getId()
    {
        return Id;
    }
    public String getUsername()
    {
        return username;
    }
    
   
    public void setId(int id)
    {
        this.Id = id;
    }
    public void setUsername(String username)
    {
         this.username = username;
    }
   

}
