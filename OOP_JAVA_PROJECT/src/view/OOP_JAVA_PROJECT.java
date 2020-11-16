/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Connections;
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
            
            Connections dataBase = new Connections("project", "root", "password");
            DAO dao=new MovieDAO(dataBase);
            //dataBase.getScreeningFrom("2000-03-23 23:30:30");
        } catch (SQLException ex) {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
