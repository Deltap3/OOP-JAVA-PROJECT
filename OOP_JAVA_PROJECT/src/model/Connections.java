/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author maist
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connections {
    
    private static Connection conn;
    private Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    private static String urlDatabase;
    private static String loginDatabase;
    private static String passwordDatabase;
    
    public Connections(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException{
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");
        // url de connexion
        this.urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase+"?useSSL=false";
        this.loginDatabase=loginDatabase;
        this.passwordDatabase=passwordDatabase;
        
        // création d'un ordre SQL (statement)
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }
    
     public static Connection getInstance(){
       if(conn == null){
           try{
               conn = DriverManager.getConnection(urlDatabase,loginDatabase,passwordDatabase);
               System.out.println("Connexion BDD réussie");
           }catch (SQLException ex){
               ex.getMessage();
               System.out.println("Connexion BDD ratée");
           }
           
       }
       return conn;
   }
 
    
    private ArrayList<String> getResult(ResultSet resultSet){
        try {
            ArrayList<String> result = new ArrayList<String>();
            int numRows = resultSet.getRow();
            ResultSetMetaData meta = resultSet.getMetaData();
            for (int row = 0; row < numRows; row++)
            {
                for (int col = 0; col < meta.getColumnCount(); col++)
                    result.add(resultSet.getString(col + 1));
                resultSet.next();
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(Connections.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ArrayList<String> getAllFromTable(String query) throws SQLException {
      try
      {
         ResultSet resultSet = stmt.executeQuery("select * from " + query);
         return getResult(resultSet);
      }
      catch (SQLException ex)
      {
         ex.printStackTrace();
         return null;
      }
    }
    public boolean personExist(String login, String password){
      try
      {
         ResultSet resultSet = stmt.executeQuery("select * from person\n" +
                           "where login = '" + login + "' and passw = '" + password + "'");
         if(resultSet.next())
            return true;
         else
             return false;
      }
      catch (SQLException ex)
      {
         ex.printStackTrace();
          System.out.println("non");
         return false;
      }
    }
    
    
  
    public boolean addDiscount(String start, String end, int discount){
      try
      {
        ResultSet resultSet = stmt.executeQuery("update screening\n" +
                                                "set discount ='"+discount+"'\n" +
                                                "where datetim <'"+end+"' and datetim >'"+start+"'");
         return true;
      }
      catch (SQLException ex)
      {
         ex.printStackTrace();
         return false;
      }
    }
    
    
    public ArrayList<String> findClient(String firstName, String lastName, String mail){
        try
        {
          ResultSet resultSet = stmt.executeQuery("select * from members\n" +
                            "where firstName = '"+firstName+"' and lastName = '"+lastName+"' and mail = '"+mail+"'");
          return getResult(resultSet);
        }
        catch (SQLException ex)
        {
           ex.printStackTrace();
           return null;
        }
    }
    public ArrayList<String> getMoviesWihtoutScreenings(String dateTime){
      try
      {
        ResultSet resultSet = stmt.executeQuery("select * from movies\n" +
                        "inner join screening on movies.movieId = screening.movieId\n" +
                        "where screening.movieId is null");
        return getResult(resultSet);
      }
      catch (SQLException ex)
      {
         ex.printStackTrace();
         return null;
      }
    }
}
