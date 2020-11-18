/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;

/**
 *
 * @author davidzhong
 */
public abstract class DAO<T>{
    protected Connection connect = null;
    
    public DAO(Connection con){
        this.connect = con;
    }
     
  public abstract T add(T obj);
  
  public abstract void delete(T obj);
    
  public abstract T find(String id);
    
}
