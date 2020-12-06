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
 */
public class SearchMemberListener implements ActionListener{
    
        private int numPanel;
        private MainFrame myFrame;
        private JTextField firstNameField;
        private JTextField lastNameField;
        private JTextField mailField;

    public SearchMemberListener(int numPanel, MainFrame myFrame, JTextField firstNameField, JTextField lastNameField, JTextField mailField) {
        this.numPanel = numPanel;
        this.myFrame = myFrame;
        this.firstNameField = firstNameField;
        this.lastNameField = lastNameField;
        this.mailField = mailField;
    }

        public void actionPerformed(ActionEvent e) {
            try {
                Connections co = new Connections("project", "root", "password");
                String fName= firstNameField.getText();
                String lName= lastNameField.getText();
                String mail= mailField.getText();
                
                CustomerMember member= new CustomerMember();
   
                 CustomerMemberDAO memberCo= new CustomerMemberDAO(co.getInstance());
                 member= memberCo.findFromNameEmail(fName, lName, mail);
                 myFrame.setSelectedMember(member);
      
                if(member==null){
                    throw new IllegalArgumentException("did not find any corresponding member");
                }
                else
                { 
                    if(numPanel==19)
                    {
                        myFrame.buildMemberInfoPanel(numPanel, 10);
                    }
                    
                    myFrame.setContentPane(myFrame.getPanels().get(numPanel));
                    myFrame.pack();
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
            


        }
    
}
