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
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
public class ScreeningDAO extends DAO<Screening> {
    //Cette classe fait le lien des scéances entre notre base de données et notre code en java
    public ScreeningDAO(Connection conn)
    {
        super(conn);
    }
    
    //Ajoute la scéance passée en paramètre dans la base de données
    public Screening add(Screening obj){
      try
      {
          //On essaye d'ajouter une scéance à la table scéance
          String sql = ("insert into screening (tim,numberSeat,ticketsBoughts,discount,numberRoom)\n" +
                           "values ('"+obj.getDateTime()+"',"+obj.getNumberseat()+","+obj.getTicketsBoughts()+","+obj.getDiscount()+","+obj.getNumberRoom()+")");
          PreparedStatement stmt = connect.prepareStatement(sql); 
          //Si la requête fonctionne la scéance est bien ajouté
          stmt.executeUpdate();
      
      }
      catch (SQLException ex)
      {
         //Sinon on affiche une erreur
         ex.printStackTrace();
      }
      //On retourne la scéance que l'on vient d'ajouter
      return obj;
    }
  
  //On supprime une scéance de la base de données
  public void delete(Screening obj)
  {
    //On essaye de supprimer la scéance et on la supprime si aucune erreur n'arrive
    try{
        this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM screening WHERE datetim = '"+ obj.getDateTime()+"'");
    }catch(SQLException ex){
        //Si il y a une erreur avec la requête on l'affiche
        ex.getMessage();
    }
  }
  /**
  * Permet de retrouver une scéance dans la base de données
  * a partir de sa date et de récupérer un objet scéance
  **/
  public Screening find(String datetime) {
        Screening s = new Screening();
        try{
            //On essaye de trouver la scéance avec sa date
            ResultSet result = this.connect.createStatement().executeQuery("SELECT  title, datetim,numberSeat,ticketsBoughts,discount,roomNumber FROM screening, movies\n" +
                "WHERE screening.movieId = movies.movieId AND datetim = '" + datetime + "'");
            //Si elle réussit et qu'il y a au moins un élément dans le résultat de la requête
            if(result.next()){
                //On construit la nouvelle scéance
                s = new Screening(
                        result.getString("title"),result.getString("datetim"),
                        result.getInt("numberSeat"),result.getInt("ticketsBoughts"),
                        result.getInt("discount"),result.getInt("roomNumber"));
            }
        }catch(SQLException ex){
            //Si la requête n'a pas pu se réaliser on affiche une erreur
            ex.printStackTrace();
        }
        //On retourne la scéance créée
        return s;
    }
  //Permet de récupérer toutes les scéances de la base de donnée
  public ArrayList<Screening> getAllScreening(){
        //On créé une ArrayList de scéances pour stocker le résultat de la requête
        ArrayList<Screening> listScreening = new ArrayList<>();
        Screening s = new Screening();
        try{
            //On essaye de trouver toutes les scéances de la base de donnée
            ResultSet result = this.connect.createStatement().executeQuery("SELECT  title, datetim,numberSeat,ticketsBoughts,discount,roomNumber FROM screening, movies\n" +
                                                                            "WHERE screening.movieId = movies.movieId");
            //Si elle réussit et pour tous les éléments dans le résultat de la requête
            while(result.next()){
                //On construit la nouvelle scéance
                s = new Screening(result.getString("title"),result.getString("datetim"),
                        result.getInt("numberSeat"),result.getInt("ticketsBoughts"),
                        result.getInt("discount"),result.getInt("roomNumber"));
                //On l'ajoute à l'ArrayList de scéances       
                listScreening.add(s);
            }
        }catch(SQLException ex){
            //Si la requête n'a pas pu se réaliser on affiche une erreur
            ex.printStackTrace();
        }
        //On retourne l'ArrayList de scéances
        return listScreening;
    }
  
    /**
    * Permet d'actualiser le nombre de tickets achetés pour une scéance
    * et de renvoyer un booléen pour si la fonction à réussit ou pas
    **/ 
    public boolean addTickets (int tickets, String dateTime){
       try{
            //On essaye d'actualiser le nombre de tickets dans la base de données
            this.connect.createStatement().executeUpdate("update screening \n" +
                                    "set ticketsBoughts = ticketsBoughts + "+tickets+"\n" +
                                    "where datetim = '"+dateTime+"'");
            //Si elle réussit on retourne que la requête c'est bien déroulée
            return true;
        }    
        catch(SQLException ex){
            //Sinon on affiche l'erreur et on retourne que la requête ne s'est pas bien déroulée
            ex.getMessage();
            return false;
        }
    }
    
    /**
    * Permet d'actualiser la réduction  pour une scéance
    * et de renvoyer un booléen pour si la fonction à réussit ou pas
    **/ 
    public boolean setDiscount(Screening obj){
        try{
            //On essaye d'actualiser la réduction d'une scéance dans la base de données
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE).executeUpdate("UPDATE screening SET discount = '"+obj.getDiscount()+"' WHERE datetim = '"+ obj.getDateTime()+"'");
            //Si elle réussit on retourne que la requête c'est bien déroulée
            return true;
        }catch(SQLException ex){
            //Sinon on affiche l'erreur et on retourne que la requête ne s'est pas bien déroulée
            ex.getMessage();
            return false;
        }
    }
}

  
  

