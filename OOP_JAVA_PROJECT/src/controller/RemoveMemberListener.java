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
import model.Connections;
import model.CustomerMember;
import view.MainFrame;
import view.OOP_JAVA_PROJECT;

/**
 *
 * @author Juju
 * remove a member from the database
 */
public class RemoveMemberListener implements ActionListener{
    
    //attributes
        private MainFrame myFrame;
        private CustomerMember member;

    //constructor
    public RemoveMemberListener(MainFrame myFrame, CustomerMember member) {
        this.myFrame = myFrame;
        this.member = member;
    }

        public void actionPerformed(ActionEvent e) {
            
            
            try {
                //establish connection and
                //delete the member from the database
                Connections co = new Connections("project", "root", "password");
                CustomerMemberDAO memberCo= new CustomerMemberDAO(co.getInstance());
                memberCo.delete(member);

                //confirm success 
                JOptionPane.showConfirmDialog(null, "the member has successfully been deleted from the database",
                        "",JOptionPane.DEFAULT_OPTION);
                
                // go to the next panel
                myFrame.makeContentPane(myFrame.getPanels().get(10));

                myFrame.invalidate();
                myFrame.validate();
                myFrame.repaint();
                
                
            } 
            catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    
}
