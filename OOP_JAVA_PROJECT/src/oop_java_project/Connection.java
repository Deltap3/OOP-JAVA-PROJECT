/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_java_project;

/**
 *
 * @author maist
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {
    private final java.sql.Connection conn;
    private final Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    
    public Connection(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException{
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");
        // url de connexion
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase+"?useSSL=false";
        //création d'une connexion JDBC à la base 
        conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
        // création d'un ordre SQL (statement)
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean loginIsCorrect(String table, String login, String password)
    {
        try
      {
         ResultSet resultSet = stmt.executeQuery("select * from "+table+"\n" +
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
    public boolean memberExist(String login, String password){
      try
      {
         ResultSet resultSet = stmt.executeQuery("select * from member\n" +
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
    public boolean employeeExist(String login, String password){
      try
      {
         ResultSet resultSet = stmt.executeQuery("select * from employee\n" +
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
    public boolean addMovie(String title, String genre, String releaseDate, int runTime, String image){
      try
      {
        stmt.executeUpdate("insert into movies (title,genre,releaseDate,runTime,image)\n" +
                           "values ('"+title+"','"+genre+"','2001-01-02',"+runTime+",'"+image+"')");
        return true;
      }
      catch (SQLException ex)
      {
         ex.printStackTrace();
         return false;
      }
    }
    public boolean addEmployee(String firstName, String lastName, String login, String password){
      try
      {
        stmt.executeUpdate("insert into employee (firstName,lastName,login,passw)\n" +
                           "values ('"+firstName+"','"+lastName+"','"+login+"','"+password+"')");
        return true;
      }
      catch (SQLException ex)
      {
         ex.printStackTrace();
         return false;
      }
    }
    public boolean addMember(String firstName, String lastName,String mail, String login, String password, int categoryMember){
      try
      {
        stmt.executeUpdate("insert into employee (firstName,lastName,mail,login,passw,categoryMember)\n" +
                           "values ('"+firstName+"','"+lastName+"','"+mail+"','"+login+"','"+password+"',"+categoryMember+")");
        return true;
      }
      catch (SQLException ex)
      {
         ex.printStackTrace();
         return false;
      }
    }
    public boolean addScreening(String datetime, int numberSeat, int ticketsBoughts, int discount){
      try
      {
        stmt.executeUpdate("insert into employee (tim,numberSeat,ticketsBoughts,discount)\n" +
                           "values ('"+datetime+"',"+numberSeat+","+ticketsBoughts+","+discount+")");
        return true;
      }
      catch (SQLException ex)
      {
         ex.printStackTrace();
         return false;
      }
    }
    public ArrayList<String> getScreeningFrom(String dateTime){
      try
      {
        ResultSet resultSet = stmt.executeQuery("select * from screening\n" +
                            "where datetim = '"+dateTime+"'");
        return getResult(resultSet);
      }
      catch (SQLException ex)
      {
         ex.printStackTrace();
         return null;
      }
    }
    public ArrayList<String> getMoviesFrom(String dateTime){
      try
      {
        ResultSet resultSet = stmt.executeQuery("select * from movies\n" +
                            "inner join screening on movies.movieId = screening.movieId"+
                            "where datetim = '"+dateTime+"'");
        return getResult(resultSet);
      }
      catch (SQLException ex)
      {
         ex.printStackTrace();
         return null;
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
