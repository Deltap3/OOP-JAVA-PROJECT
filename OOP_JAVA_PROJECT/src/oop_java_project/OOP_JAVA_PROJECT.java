/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_java_project;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            Connection dataBase = new Connection("project", "root", "password");
            dataBase.personExist("Pierre33", "Pierrot");
            dataBase.personExist("Pierre31", "Pierrot");
            dataBase.personExist("Pierre33", "Pierro");
        } catch (SQLException ex) {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
