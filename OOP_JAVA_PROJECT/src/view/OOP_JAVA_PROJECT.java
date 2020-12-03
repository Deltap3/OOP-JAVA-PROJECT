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

/*
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
*/

/**
 *
 * @author maist
 */
public class OOP_JAVA_PROJECT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        try { 

            Connection dataBase = new Connection("project", "root", "projetJava2020");



        } catch (SQLException ex) {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        MainFrame frame= new MainFrame();
        Statistics stat = new Statistics("Most viewed film");
    }
}
