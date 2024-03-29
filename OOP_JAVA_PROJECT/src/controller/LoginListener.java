/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CustomerMemberDAO;
import DAO.EmployeeDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Connections;
import model.CustomerMember;
import model.Person;
import view.MainFrame;
import view.OOP_JAVA_PROJECT;

/**
 *
 * @author Juju
 * manage member and employee
 * connection (check login and password 
 * in the database)
 */
public class LoginListener implements ActionListener{
    
        //attributes
        private int numPanel;
        private String table;
        private MainFrame myFrame;
        private JTextField loginField;
        private JPasswordField pswField;

        //constructor
        public LoginListener(int numPanel, String table, MainFrame myFrame, JTextField loginField, JPasswordField pswField) {
            this.numPanel = numPanel;
            this.table = table;
            this.myFrame = myFrame;
            this.loginField = loginField;
            this.pswField = pswField;
        }
        

        public void actionPerformed(ActionEvent e) {
            try {
                //establish connection
                Connections co = new Connections("project", "root", "password");
                
               //get the login and password from the login panel
                String login= loginField.getText();
                String password= new String(pswField.getPassword());
                
                Person user = null;
                
                //check if correspond
                if(table.equals("members")){
                    
                 CustomerMemberDAO memberCo= new CustomerMemberDAO(co.getInstance());
                 user= memberCo.findFromLoginPassword(login, password);
                 myFrame.setSelectedMember((CustomerMember)user);
                 
                }
                else if(table.equals("employee"))
                {
                    EmployeeDAO employeeCo= new EmployeeDAO(co.getInstance());
                    user= employeeCo.findFromLoginPassword(login, password);
                    
                }
                
                 //if we couldn't find the user
                if(user==null){
                    throw new IllegalArgumentException("login or password is incorrect");
                }
                
                else
                {
                    //set the user and go to next panel
                    myFrame.setUser(user);
                    myFrame.makeContentPane(myFrame.getPanels().get(numPanel));

                    myFrame.invalidate();
                    myFrame.validate();
                    myFrame.repaint();
                
                }
            } 
            catch (SQLException ex) {
                Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (ClassNotFoundException ex) {
                Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(IllegalArgumentException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);

            }
            
            //clean the fields
            loginField.setText("");
            pswField.setText("");

        }
    
}
