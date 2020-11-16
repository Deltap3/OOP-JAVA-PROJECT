/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import DAO.*;

/**
 *
 * @author maist
 */
public class OOP_JAVA_PROJECT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try { 
            
            
            
            Connections usedataBase = new Connections("project", "root", "password");
            Connection dataBase=usedataBase.getInstance();
            ScreeningDAO dao=new ScreeningDAO(dataBase);
            dao.getScreeningByDateTime("2000-03-23 23:30:30");
            
        } catch (SQLException ex) {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
