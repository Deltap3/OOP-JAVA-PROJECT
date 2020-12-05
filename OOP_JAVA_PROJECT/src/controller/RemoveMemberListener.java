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
 */
public class RemoveMemberListener implements ActionListener{
    
        private MainFrame myFrame;
        private CustomerMember member;

    public RemoveMemberListener(MainFrame myFrame, CustomerMember member) {
        this.myFrame = myFrame;
        this.member = member;
    }

        public void actionPerformed(ActionEvent e) {
            
            
            try {
                //delete the member from the database
                Connections co = new Connections("project", "root", "projetJava2020");
                CustomerMemberDAO memberCo= new CustomerMemberDAO(co.getInstance());
                memberCo.delete(member);

                JOptionPane.showConfirmDialog(null, "the member has successfully been deleted from the database");
                
                // go to the next panel
                myFrame.setContentPane(myFrame.getPanels().get(10));
                myFrame.pack();
                myFrame.invalidate();
                myFrame.validate();
                myFrame.repaint();
                
                
            } 
            catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    
}
