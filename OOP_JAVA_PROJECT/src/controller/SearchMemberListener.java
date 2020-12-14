/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CustomerMemberDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Connections;
import model.CustomerMember;
import view.MainFrame;
import view.OOP_JAVA_PROJECT;

/**
 *
 * @author Juju
 * search a member with its
 * first name, last name,
 * and mail address
 */
public class SearchMemberListener implements ActionListener{
    
    //attributes
        private int numPanel;
        private MainFrame myFrame;
        private JTextField firstNameField;
        private JTextField lastNameField;
        private JTextField mailField;

    //constructor
    public SearchMemberListener(int numPanel, MainFrame myFrame, JTextField firstNameField, JTextField lastNameField, JTextField mailField) {
        this.numPanel = numPanel;
        this.myFrame = myFrame;
        this.firstNameField = firstNameField;
        this.lastNameField = lastNameField;
        this.mailField = mailField;
    }

        public void actionPerformed(ActionEvent e) {
            try {
                //establish connection
                Connections co = new Connections("project", "root", "password");
                
                //get the data from the fields
                String fName= firstNameField.getText();
                String lName= lastNameField.getText();
                String mail= mailField.getText();
                
                //try to find the member
                CustomerMember member= new CustomerMember();
   
                 CustomerMemberDAO memberCo= new CustomerMemberDAO(co.getInstance());
                 member= memberCo.findFromNameEmail(fName, lName, mail);
                 
      
                if(member==null){
                    throw new IllegalArgumentException("did not find any corresponding member");
                }
                else
                { 
                    //set the member as selected member 
                    myFrame.setSelectedMember(member);
                    
                    //different actions depending on what panel is next
                    if(numPanel==19)
                    {
                        //build the info panel
                        //(this panel need to be actualised)
                        myFrame.buildMemberInfoPanel(numPanel, 10);
                    }
                    else if(numPanel==22)
                    {
                        //build the confiramtion panel
                        //for deleting a member
                        myFrame.buildPanel22();
                    }
                    
                    //go to the next panel
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
            


        }
    
}
