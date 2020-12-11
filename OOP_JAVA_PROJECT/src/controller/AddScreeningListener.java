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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Connections;
import model.CustomerMember;
import model.Screening;
import view.DateTimePanel;
import view.MainFrame;
import view.OOP_JAVA_PROJECT;

/**
 *
 * @author Juju
 */
public class AddScreeningListener implements ActionListener {
    
        private MainFrame myFrame;
        private JLabel movieTitle;
        private JComboBox roomField;
        private JLabel seatLabel;
        private DateTimePanel dateTimePanel;
        private JTextField discountField;

    

    public AddScreeningListener(MainFrame myFrame, JLabel movieTitle, JComboBox roomField, JLabel seatLabel,DateTimePanel dateTimePanel, JTextField discountField) {
        this.myFrame = myFrame;
        this.movieTitle = movieTitle;
        this.roomField = roomField;
        this.seatLabel = seatLabel;
        this.dateTimePanel= dateTimePanel;
        this.discountField = discountField;
    }
    

        public void actionPerformed(ActionEvent e) {
            
            String dateTime=dateTimePanel.getDateTime();
         
            try {
                Integer roomNumber= Integer.parseInt((String) roomField.getSelectedItem());
                Integer numberOfSeats= Integer.parseInt(seatLabel.getText());
                Integer discount= Integer.parseInt(discountField.getText());
                
                if(movieTitle.getText().isEmpty())
                {
                    throw new IllegalArgumentException("please chose a movie");
                }
                
                else
                {
                //first we create a screening session from the data entered
                 Screening session= new Screening(movieTitle.getText(), dateTime, numberOfSeats, 0, discount, roomNumber);
                
                //then we establish the connection with the database
                Connections co = new Connections("project", "root", "password");
                ScreeningDAO screeningCo= new ScreeningDAO(co.getInstance());
                
                //we check if the room is free
                if(screeningCo.roomIsTaken(dateTime, roomNumber))
                {
                    JOptionPane.showMessageDialog(null, "this room is already taken at that time","",JOptionPane.ERROR_MESSAGE);
                }
                
                else{
                 //we add the session
                screeningCo.add(session);
                JOptionPane.showConfirmDialog(null, "the new screening session has successfully been added to the database",
                                                "",JOptionPane.DEFAULT_OPTION);
                
                //finally we go to the next panel
                myFrame.setContentPane(myFrame.getPanels().get(10));
                myFrame.pack();
                myFrame.centerFrame();
                myFrame.invalidate();
                myFrame.validate();
                myFrame.repaint();
                }
                }
                
            } 
            catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(IllegalArgumentException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);

            }

      
        }
}
