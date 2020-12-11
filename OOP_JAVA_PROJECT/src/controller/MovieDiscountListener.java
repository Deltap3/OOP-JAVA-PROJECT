/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ScreeningDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Connections;
import view.MainFrame;
import view.OOP_JAVA_PROJECT;

/**
 *
 * @author Juju
 */
public class MovieDiscountListener implements ActionListener {

    private MainFrame myFrame;
    private JLabel titleLabel;
    private JTextField discountField;

    public MovieDiscountListener(MainFrame myFrame, JLabel titleLabel, JTextField discountField) {
        this.myFrame = myFrame;
        this.titleLabel = titleLabel;
        this.discountField = discountField;
    }

    public void actionPerformed(ActionEvent e) {

        try {

            String movieTitle = titleLabel.getText();
            //we check if all the fields are filled /selections have been made
            if (movieTitle.isEmpty() || discountField.getText().isEmpty()) {
                throw new IllegalArgumentException("please fill all the required fields ");
            } else {

                Integer discount = Integer.parseInt(discountField.getText());

                // we establish the connection with the database
                Connections co = new Connections("project", "root", "password");
                ScreeningDAO screeningCo = new ScreeningDAO(co.getInstance());

                //we apply the discount on all screening sessions playing the movie
                screeningCo.setDiscountWithMovieTitle(movieTitle, discount);
                
                JOptionPane.showConfirmDialog(null, "the new discount of "+ discount 
                                            +"% has successfully been applied to all sessions playing"
                                            +movieTitle, "",JOptionPane.DEFAULT_OPTION);
                
                //finally we go to the next panel
                myFrame.makeContentPane(myFrame.getPanels().get(10));
                myFrame.pack();
                myFrame.centerFrame();
                myFrame.invalidate();
                myFrame.validate();
                myFrame.repaint();
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);

        }

    }

}
