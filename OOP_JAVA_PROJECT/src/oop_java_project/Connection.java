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
    public void getAllFromTable(String query) throws SQLException {
        try
      {
         // Execute the query.
         ResultSet resultSet = stmt.executeQuery("select * from " + query);

         // Get the number of rows.
         resultSet.last();                 // Move to last row
         int numRows = resultSet.getRow(); // Get row number
         resultSet.first();                // Move to first row

         // Get a metadata object for the result set.
         ResultSetMetaData meta = resultSet.getMetaData();

         // Create an array of Strings for the column names.
         String [] colNames = new String[meta.getColumnCount()];

         // Store the column names in the colNames array.
         for (int i = 0; i < meta.getColumnCount(); i++)
         {
            // Get a column name.
            colNames[i] = meta.getColumnLabel(i+1);
            System.out.print(colNames[i] + "  ");
         }
          System.out.println("");
         // Create a 2D String array for the table data.
         String [][] tableData = new String[numRows][meta.getColumnCount()];

         // Store the columns in the tableData array.
         for (int row = 0; row < numRows; row++)
         {
            for (int col = 0; col < meta.getColumnCount(); col++)
            {
               tableData[row][col] = resultSet.getString(col + 1);
               System.out.print(tableData[row][col] + "  ");
            }
             System.out.println("");
            // Go to the next row in the ResultSet.
            resultSet.next();
         }
         
         // Close the statement and connection objects.
         stmt.close();
         conn.close();
      }
      catch (SQLException ex)
      {
         ex.printStackTrace();
      }
    }
}
