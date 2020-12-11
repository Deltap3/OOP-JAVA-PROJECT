/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CustomerMemberDAO;
import DAO.ScreeningDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Connections;
import model.CustomerMember;
import model.Screening;
import view.MainFrame;
import view.OOP_JAVA_PROJECT;
import view.RemoveScreeningPanel;

/**
 *
 * @author Juju
 */
public class RemoveScreeningListener implements ActionListener{
        private MainFrame myFrame;
        private RemoveScreeningPanel sPanel;

    public RemoveScreeningListener(MainFrame myFrame, RemoveScreeningPanel sPanel) {
        this.myFrame = myFrame;
        this.sPanel = sPanel;
    }

        

        public void actionPerformed(ActionEvent e) {
            
            
            try {
                //delete the member from the database
                Connections co = new Connections("project", "root", "password");
                ScreeningDAO screeningCo= new ScreeningDAO(co.getInstance());
                screeningCo.delete(sPanel.getSelectedSession());

                JOptionPane.showConfirmDialog(null, "the screening session has "
                        + "successfully been deleted from the database", "",JOptionPane.DEFAULT_OPTION);
                
                // go to the next panel
                myFrame.setContentPane(myFrame.getPanels().get(10));
                myFrame.pack();
                myFrame.centerFrame();
                myFrame.invalidate();
                myFrame.validate();
                myFrame.repaint();
                
                
            } 
            catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
}
