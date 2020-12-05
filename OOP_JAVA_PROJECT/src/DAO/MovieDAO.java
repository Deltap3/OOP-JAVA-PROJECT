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
import java.util.ArrayList;
/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
public class MovieDAO extends DAO<Movie>{
    //This class is the link between our java code and our database
    public MovieDAO(Connection conn)
    {
    super(conn);
    }

    //Add the movie passed in parameter in the database
    public Movie add(Movie obj){
      try
      {
          //We try to add this movie
          String sql = ("insert into movies (title,genre,releaseDate,runTime,image)\n" +
                           "values ('"+obj.getTitle()+"','"+obj.getGenre()+"','"+obj.getReleaseDate()+"',"+obj.getRunTime()+",'"+obj.getImage()+"')");
          PreparedStatement stmt = connect.prepareStatement(sql); 
            //If the query suceed and the movie is well added
          stmt.executeUpdate();
        
      }
      catch (SQLException ex)
      {
           //Else we display the error
         ex.printStackTrace();
      }
        //We return the objet we have just created
      return obj;
    }
  
    //We delete a movie from the database
  public void delete(Movie obj)
  {
    try{
        //We try to delete this movie
        this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM movies WHERE title = '"+ obj.getTitle()+"'");
    }catch(SQLException ex){
        //If there's an error we display it
        ex.getMessage();
    }
  }
    /**
    * Find a movie in the database from a movie name
    * and retrun a movie object
    **/
  public Movie find(String movieName) {
         Movie movie = new Movie();
        try{
            //We try to find the movie with his login
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM movies WHERE title = '" + movieName +"'");
            //If the query suceed and there's at least one element in it
            if(result.first()){
                //We create a new movie
                movie = new Movie(result.getString("title"),
                        result.getString("genre"),result.getString("releaseDate"),
                        result.getInt("runTime"),result.getString("image"));
               
            }
        }catch(SQLException ex){
            //If the query failed we display an error message
            ex.getMessage();
        }
        //We return the created movie
        return movie;
    }
  
    //Find a movie from its date
    public Movie getMovieByDateTime(String dateTime){
        Movie m = new Movie();
        try{
            //We try to find this movie from its date
            ResultSet result = this.connect.createStatement().executeQuery("select * from movies\n" +
                         "inner join screening on movies.movieId = screening.movieId"+
                         "where datetim = '"+dateTime+"'");
            //If the query suceed and there's at least one element in it
            while(result.next()){
                //We create a new movie
                m = new Movie(result.getString("title"),
                     result.getString("genre"),result.getString("releaseDate"),
                     result.getInt("runTime"),result.getString("image"));
            }
        }catch(SQLException ex){
            //Else we display an ereor
            ex.printStackTrace();
        }
        //We return the created movie
        return m;
    }

  //Return all the movies from the database
  public ArrayList<Movie> getAllMovie(){
        //We create an ArrayList of movies
        ArrayList<Movie> listMovie = new ArrayList<>();
        Movie m = new Movie();
        try{
            //We try to find all the movies
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM movies");
            //If the query suceed and for all the elements in the query
            while(result.next()){
                //We create a new movie
                m = new Movie(result.getString("title"),
                        result.getString("genre"),result.getString("releaseDate"),
                        result.getInt("runTime"),result.getString("image"));
                //We add it to the ArrayList    
                listMovie.add(m);
            }
        }catch(SQLException ex){
            //If there's an error we display it
            ex.printStackTrace();
        }
        //We return the movie ArrayList
        return listMovie;
    }
  
}
