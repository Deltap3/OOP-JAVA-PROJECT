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
    //Cette classe fait le lien des films entre notre base de données et notre code en java
    public MovieDAO(Connection conn)
    {
    super(conn);
    }

    //Ajoute le film passé en paramètre dans la base de données
    public Movie add(Movie obj){
      try
      {
          //On essaye d'ajouter un film à la table film
          String sql = ("insert into movies (title,genre,releaseDate,runTime,image)\n" +
                           "values ('"+obj.getTitle()+"','"+obj.getGenre()+"','"+obj.getReleaseDate()+"',"+obj.getRunTime()+",'"+obj.getImage()+"')");
          PreparedStatement stmt = connect.prepareStatement(sql); 
          //Si la requête fonctionne le film est bien ajouté
          stmt.executeUpdate();
        
      }
      catch (SQLException ex)
      {
         //Sinon on affiche une erreur
         ex.printStackTrace();
      }
      //On retourne le film que l'on vient d'ajouter
      return obj;
    }
  
  //On supprime un film de la base de données
  public void delete(Movie obj)
  {
    try{
        //On essaye de supprimer le film et on le supprime si aucune erreur n'arrive
        this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM movies WHERE title = '"+ obj.getTitle()+"'");
    }catch(SQLException ex){
        //Si il y a une erreur avec la requête on l'affiche
        ex.getMessage();
    }
  }
  /**
  * Permet de retrouver un gilm dans la base de données
  * a partir de son nom et de récupérer un objet film
  **/
  public Movie find(String movieName) {
         Movie movie = new Movie();
        try{
            //On essaye de trouver le film avec son nom
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM movies WHERE title = '" + movieName +"'");
            //Si elle réussit et qu'il y a au moins un élément dans le résultat de la requête
            if(result.first()){
                //On construit un nouveau film
                movie = new Movie(result.getString("title"),
                        result.getString("genre"),result.getString("releaseDate"),
                        result.getInt("runTime"),result.getString("image"));
               
            }
        }catch(SQLException ex){
            //Si la requête n'a pas pu se réaliser on affiche une erreur
            ex.getMessage();
        }
        //On retourne le film créé
        return movie;
    }
    //Permet de trouver un film en fonction de la date d'une de ses scéances
    public Movie getMovieByDateTime(String dateTime){
        Movie m = new Movie();
        try{
            //On essaye de trouver le film correspondant à la scéance que l'on cherche
            //grâce à sa date
            ResultSet result = this.connect.createStatement().executeQuery("select * from movies\n" +
                         "inner join screening on movies.movieId = screening.movieId"+
                         "where datetim = '"+dateTime+"'");
            //Si elle réussit et pour tous les éléments dans le résultat de la requête
            while(result.next()){
                //On construit le nouveau film
                m = new Movie(result.getString("title"),
                     result.getString("genre"),result.getString("releaseDate"),
                     result.getInt("runTime"),result.getString("image"));
            }
        }catch(SQLException ex){
            //Si la requête n'a pas pu se réaliser on affiche une erreur
            ex.printStackTrace();
        }
        //On retourne le film créé
        return m;
    }

  //Permet de récupérer tous les films de la base de donnée
  public ArrayList<Movie> getAllMovie(){
        //On créé une ArrayList de films pour stocker le résultat de la requête
        ArrayList<Movie> listMovie = new ArrayList<>();
        Movie m = new Movie();
        try{
            //On essaye de trouver touts les films de la base de donnée
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM movies");
            //Si elle réussit et pour tous les éléments dans le résultat de la requête
            while(result.next()){
                //On construit le nouveau film
                m = new Movie(result.getString("title"),
                        result.getString("genre"),result.getString("releaseDate"),
                        result.getInt("runTime"),result.getString("image"));
                //On l'ajoute à l'ArrayList de films      
                listMovie.add(m);
            }
        }catch(SQLException ex){
            //Si la requête n'a pas pu se réaliser on affiche une erreur
            ex.printStackTrace();
        }
        //On retourne l'ArrayList de films
        return listMovie;
    }
  
}
