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
          String sql = ("insert into screening (tim,numberSeat,ticketsBoughts,discount,numberRoom)\n" +
                           "values ('"+obj.getDateTime()+"',"+obj.getNumberseat()+","+obj.getTicketsBoughts()+","+obj.getDiscount()+","+obj.getNumberRoom()+")");
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
            ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM screening WHERE datetim = '"+ obj.getDateTime()+"'");
        }catch(SQLException ex){
            ex.getMessage();
        }
  }
  
  public Screening find(String datetime) {
         Screening screening = new Screening();
        try{
            ResultSet result = this.connect.createStatement().executeQuery("SELECT  title, datetim,numberSeat,ticketsBoughts,discount,numberRoom FROM screening, movies\n" +
                "WHERE screening.movieId = movies.movieId AND datetim = '" + datetime + "'");
            if(result.first()){
                screening = new Screening(
                        result.getString("movieId"),result.getString("datetim"),
                        result.getInt("numberSeat"),result.getInt("ticketsBoughts"),
                        result.getInt("discount"),result.getInt("numberRoom"));
               
            }
            
        
        }catch(SQLException ex){
            ex.getMessage();
        }
        return screening;
    
}//////////////Get Screening by DateTime////////////
    public Screening getScreeningByDateTime(String dateTime){
           Screening s = new Screening();
                   try{
            ResultSet result = this.connect.createStatement().executeQuery("SELECT  title, datetim,numberSeat,ticketsBoughts,discount,numberRoom FROM screening, movies\n" +
                "WHERE screening.movieId = movies.movieId AND datetim = '" + dateTime + "'");
            while(result.next()){
            
                s = new Screening(
                        result.getString("title"),result.getString("datetim"),
                        result.getInt("numberSeat"),result.getInt("ticketsBoughts"),
                        result.getInt("discount"),result.getInt("numberRoom"));
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
            ResultSet result = this.connect.createStatement().executeQuery("SELECT  title, datetim,numberSeat,ticketsBoughts,discount,numberRoom FROM screening, movies\n" +
                                                                            "WHERE screening.movieId = movies.movieId");
            while(result.next()){
                s = new Screening(result.getString("title"),result.getString("datetim"),
                        result.getInt("numberSeat"),result.getInt("ticketsBoughts"),
                        result.getInt("discount"),result.getInt("numberRoom"));
                       
                listScreening.add(s);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return listScreening;
    }
  
}

  
  

