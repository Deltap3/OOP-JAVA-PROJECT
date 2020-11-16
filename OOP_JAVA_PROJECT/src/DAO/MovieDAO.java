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
/**
 *
 * @author davidzhong
 */
public class MovieDAO extends DAO<Movie>{
    
    public MovieDAO(Connection conn)
    {
    super(conn);
    }
    
    
    public Movie add(Movie obj){
      try
      {
          String sql = ("insert into movies (title,genre,releaseDate,runTime,image)\n" +
                           "values ('"+obj.getTitle()+"','"+obj.getGenre()+"','"+obj.getReleaseDate()+"',"+obj.getRunTime()+",'"+obj.getImage()+"')");
          PreparedStatement stmt = connect.prepareStatement(sql); 
          
        stmt.executeUpdate();
        
      }
      catch (SQLException ex)
      {
         ex.printStackTrace();
      }
      
      return obj;
    }
  
  
  public void delete(Movie obj)
  {
  try{
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM movies WHERE id ="+ obj.getIdMovie());
        }catch(SQLException ex){
            ex.getMessage();
        }
  }
  
  public Movie find(int id) {
         Movie movie = new Movie();
        try{
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM COURS WHERE id = " + id);
            if(result.first()){
                movie = new Movie(id,
                        result.getString("title"),result.getString("genre"),
                        result.getString("director"),result.getString("releaseDate"),
                        result.getInt("runTime"),result.getString("image"));
               
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return movie;
    
}
}
