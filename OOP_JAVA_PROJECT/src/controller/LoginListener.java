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
import model.Person;
import view.MainFrame;
import view.OOP_JAVA_PROJECT;

/**
 *
 * @author Juju
 */
public class LoginListener implements ActionListener{
    
     private int numPanel;
        private String table;
        private MainFrame myFrame;
        private JTextField loginField;
        private JPasswordField pswField;

        public LoginListener(int numPanel, String table, MainFrame myFrame, JTextField loginField, JPasswordField pswField) {
            this.numPanel = numPanel;
            this.table = table;
            this.myFrame = myFrame;
            this.loginField = loginField;
            this.pswField = pswField;
        }
        

        public void actionPerformed(ActionEvent e) {
            try {
                Connections co = new Connections("project", "root", "projetJava2020");
                System.out.println(co.getAllFromTable(""));
                String login= loginField.getText();
                String password= new String(pswField.getPassword());
                boolean correct=false;
                Person user = null;
                
                if(table.equals("members")){
                    
                 CustomerMemberDAO memberCo= new CustomerMemberDAO(co.getInstance());
                 user= memberCo.findFromLoginPassword(login, password);
                 correct= co.memberExist(login, password);
                 
                }
                else if(table.equals("employee"))
                {
                    EmployeeDAO employeeCo= new EmployeeDAO(co.getInstance());
                    user= employeeCo.findFromLoginPassword(login, password);
                    correct= co.employeeExist(login, password);
                }
                if (correct) {
                    myFrame.setUser(user);
                    myFrame.setSize(myFrame.getPanels().get(numPanel).getSize());
                    myFrame.setContentPane(myFrame.getPanels().get(numPanel));
                    myFrame.invalidate();
                    myFrame.validate();
                    myFrame.repaint();
                } 
                else {
                    throw new IllegalArgumentException("login or password is incorrect");
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
            
            loginField.setText("");
            pswField.setText("");

        }
    
}
