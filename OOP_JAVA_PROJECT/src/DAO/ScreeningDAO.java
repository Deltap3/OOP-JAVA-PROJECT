/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Movie;
import model.Screening;

/**
 *
 * @author davidzhong
 */
public class ScreeningDAO extends DAO<Screening> {
    
    public ScreeningDAO(Connection conn)
    {
    super(conn);
    }
    
    
    public Screening add(Screening obj){
      try
      {
          String sql = ("insert into employee (tim,numberSeat,ticketsBoughts,discount)\n" +
                           "values ('"+obj.getDateTime()+"',"+obj.getNumberseat()+","+obj.getTicketsBoughts()+","+obj.getDiscount()+")");
          PreparedStatement stmt = connect.prepareStatement(sql); 
          
        stmt.executeUpdate();
      
      }
      catch (SQLException ex)
      {
         ex.printStackTrace();
      }
      
      return obj;
    }
  
  
  public void delete(Screening obj)
  {
  try{
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM movies WHERE id ="+ obj.getIdScreening());
        }catch(SQLException ex){
            ex.getMessage();
        }
  }
  
  public Screening find(int id) {
         Screening screening = new Screening();
        try{
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM COURS WHERE id = " + id);
            if(result.first()){
                screening = new Screening(id,
                        result.getInt("movieID"),result.getString("datetim"),
                        result.getInt("numberSeat"),result.getInt("ticketsBoughts"),
                        result.getInt("discount"));
               
            }
            
        
        }catch(SQLException ex){
            ex.getMessage();
        }
        return screening;
    
}
}
