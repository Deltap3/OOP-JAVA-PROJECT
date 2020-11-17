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
import java.util.ArrayList;
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
          String sql = ("insert into screening (tim,numberSeat,ticketsBoughts,discount)\n" +
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
            ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM screening WHERE screeningID ="+ obj.getIdScreening());
        }catch(SQLException ex){
            ex.getMessage();
        }
  }
  
  public Screening find(int id) {
         Screening screening = new Screening();
        try{
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM screening WHERE screeningID = " + id);
            if(result.first()){
                screening = new Screening(id,
                        result.getInt("movieId"),result.getString("datetim"),
                        result.getInt("numberSeat"),result.getInt("ticketsBoughts"),
                        result.getInt("discount"));
               
            }
            
        
        }catch(SQLException ex){
            ex.getMessage();
        }
        return screening;
    
}//////////////Get Screening by DateTime////////////
    public Screening getScreeningByDateTime(String dateTime){
           Screening s = new Screening();
                   try{
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM screening WHERE datetim = '" + dateTime + "'");
            while(result.next()){
            
                s = new Screening(result.getInt("screeningID"),
                        result.getInt("movieId"),dateTime,
                        result.getInt("numberSeat"),result.getInt("ticketsBoughts"),
                        result.getInt("discount"));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return s;
    }

  ////////List of all the Screening
  public ArrayList<Screening> getAllScreening(){
        ArrayList<Screening> listScreening = new ArrayList<>();
        Screening s = new Screening();
        try{
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM screening");
            while(result.next()){
                s = new Screening(result.getInt("screeningID"),
                        result.getInt("movieId"),result.getString("datetim"),
                        result.getInt("numberSeat"),result.getInt("ticketsBoughts"),
                        result.getInt("discount"));
                       
                listScreening.add(s);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return listScreening;
    }
  
}

  
  

