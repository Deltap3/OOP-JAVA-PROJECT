
package model;

/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */

import java.sql.*;

//This class manage the connection to the database
public class Connections {
    //Connection to the databade
    private static Connection conn;
    //Alows to send a query to the database
    private Statement stmt;
    //Alows to get the result of the query
    private ResultSet rset;
    //Alows to have meta data about the query
    private ResultSetMetaData rsetMeta;
    //Stocks our database URL
    private static String urlDatabase;
    //Stocks our database login
    private static String loginDatabase;
    //Stocks our database password
    private static String passwordDatabase;
    
    //We connect to the databade from its name, login and password
    public Connections(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        this.urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase+"?useSSL=false";
        this.loginDatabase=loginDatabase;
        this.passwordDatabase=passwordDatabase;
        getInstance();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }
    
    //Return the instance of the database
     public static Connection getInstance(){
       //If there's no connection
       if(conn == null){
         try{
             //We try to connect to the database
             conn = DriverManager.getConnection(urlDatabase,loginDatabase,passwordDatabase);
             //If it suceed we display it to the user
             System.out.println("Database connection suceed");
         }catch (SQLException ex){
             //If it failed we display it to the user
             ex.getMessage();
             System.out.println("Database connection failed");
         }
       }
       //If there's already a connection we return it
       return conn;
   }
}
