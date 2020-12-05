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
import model.Movie;
import model.Screening;

/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
public class ScreeningDAO extends DAO<Screening> {
    //This class is the link between our java code and our database
    public ScreeningDAO(Connection conn)
    {
        super(conn);
    }
    
    //Add the screening passed in parameters in the database
    public Screening add(Screening obj){
      try
      {
          //We try to add the screening
          String sql = ("insert into screening (tim,numberSeat,ticketsBoughts,discount,numberRoom)\n" +
                           "values ('"+obj.getDateTime()+"',"+obj.getNumberseat()+","+obj.getTicketsBoughts()+","+obj.getDiscount()+","+obj.getNumberRoom()+")");
          PreparedStatement stmt = connect.prepareStatement(sql); 
          //If the query suceed the screening is well added
          stmt.executeUpdate();
      
      }
      catch (SQLException ex)
      {
         //Else we display the error
         ex.printStackTrace();
      }
      //We return the created screening
      return obj;
    }
  
  //Dekete a screening from the database
  public void delete(Screening obj)
  {
    //We try to delete a screening
    try{
        this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM screening WHERE datetim = '"+ obj.getDateTime()+"'");
    }catch(SQLException ex){
        //If there's an error we display it
        ex.getMessage();
    }
  }
  /**
  * Find a screening in the database from its date and return a screening object
  **/
  public Screening find(String datetime) {
        Screening s = new Screening();
        try{
            //We try to find the screening from its date
            ResultSet result = this.connect.createStatement().executeQuery("SELECT  title, datetim,numberSeat,ticketsBoughts,discount,roomNumber FROM screening, movies\n" +
                "WHERE screening.movieId = movies.movieId AND datetim = '" + datetime + "'");
            //If the query suceed and there's at least one element in it
            if(result.next()){
                //We create the new screening
                s = new Screening(
                        result.getString("title"),result.getString("datetim"),
                        result.getInt("numberSeat"),result.getInt("ticketsBoughts"),
                        result.getInt("discount"),result.getInt("roomNumber"));
            }
        }catch(SQLException ex){
            //If there's an error we display it
            ex.printStackTrace();
        }
        //We return the created screening
        return s;
    }
  //Return all the screenings from the database
  public ArrayList<Screening> getAllScreening(){
        //We create an ArrayList of screenings
        ArrayList<Screening> listScreening = new ArrayList<>();
        Screening s = new Screening();
        try{
            //We try to find all the screenings
            ResultSet result = this.connect.createStatement().executeQuery("SELECT  title, datetim,numberSeat,ticketsBoughts,discount,roomNumber FROM screening, movies\n" +
                                                                            "WHERE screening.movieId = movies.movieId");
            //If the query suceed and for all the elements in the query
            while(result.next()){
                //We create a new screening
                s = new Screening(result.getString("title"),result.getString("datetim"),
                        result.getInt("numberSeat"),result.getInt("ticketsBoughts"),
                        result.getInt("discount"),result.getInt("roomNumber"));
                //We add it to the ArrayList    
                listScreening.add(s);
            }
        }catch(SQLException ex){
            //If there's an error we display it
            ex.printStackTrace();
        }
        //We return the screening ArrayList
        return listScreening;
    }
  
    /**
    * Update the number of tickets bought for a screening
    * and returns a boolean for whether the function to succeed or not
    **/ 
    public boolean addTickets (int tickets, String dateTime){
       try{
            //We try to update the number of tickets boughts
            this.connect.createStatement().executeUpdate("update screening \n" +
                                    "set ticketsBoughts = ticketsBoughts + "+tickets+"\n" +
                                    "where datetim = '"+dateTime+"'");
            //If the query succed we return true
            return true;
        }    
        catch(SQLException ex){
            //Else we display the error and return false
            ex.getMessage();
            return false;
        }
    }
    
    /**
    * Update the discount for a screening
    * and returns a boolean for whether the function to succeed or not
    **/ 
    public boolean setDiscount(Screening obj){
        try{
            //We try to update the discount of a screening
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE).executeUpdate("UPDATE screening SET discount = '"+obj.getDiscount()+"' WHERE datetim = '"+ obj.getDateTime()+"'");
            //If the query suceed we return true
            return true;
        }catch(SQLException ex){
            //Else we display the error and return false
            ex.getMessage();
            return false;
        }
    }
    
    //Sets the movie foreign key in the database
    public boolean setMovie(Movie mov){
        try{
            //We try to fid the movie key with its name
            ResultSet result = this.connect.createStatement().executeQuery("select movieId from movies where title = '"+mov.getTitle()+"'");
            //We try to update the screening with this id
            this.connect.createStatement().executeUpdate("update screening set movieId = '"+result.getString("movieId")+"' where movieId is null");
            //If the query succed we return true
            return true;
        }    
        catch(SQLException ex){
            //Else we display the error and return false
            ex.getMessage();
            return false;
        }
    }
} 

  
  

