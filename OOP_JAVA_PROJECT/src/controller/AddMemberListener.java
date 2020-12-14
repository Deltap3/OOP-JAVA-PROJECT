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
 * this class will manage the action
 * of creating a member and adding it to 
 * the database
 */
public class AddMemberListener implements ActionListener {

    //attributes
    private MainFrame myFrame;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField ageField;
    private JTextField mailField;
    private JTextField loginField;
    private JTextField pswField;

    //constructor
    public AddMemberListener(MainFrame myFrame, JTextField firstNameField, JTextField lastNameField, JTextField ageField, JTextField mailField, JTextField loginField, JTextField pswField) {
        this.myFrame = myFrame;
        this.firstNameField = firstNameField;
        this.lastNameField = lastNameField;
        this.ageField = ageField;
        this.mailField = mailField;
        this.loginField = loginField;
        this.pswField = pswField;
    }

    public void actionPerformed(ActionEvent e) {

        //get the data from the fields
        String login = loginField.getText(), psw = pswField.getText(), mail = mailField.getText();
        Integer age = Integer.parseInt(ageField.getText());
        String fName = firstNameField.getText(), lName = lastNameField.getText();
        
        try {
            //check if the fields are filled
            if (login.isEmpty() || psw.isEmpty() || mail.isEmpty() || ageField.getText().isEmpty() || fName.isEmpty() || lName.isEmpty()) {
                throw new IllegalArgumentException("please fill all the required fields");
            } else {
                //first we create a member from the data entered
                CustomerMember member = new CustomerMember(login, psw, mail, age, fName, lName);

                //then we add it to the database
                Connections co = new Connections("project", "root", "password");
                CustomerMemberDAO memberCo = new CustomerMemberDAO(co.getInstance());

                if (memberCo.find(login) != null) {
                    //different members cannot have the same login
                    throw new IllegalArgumentException("this login is already taken");
                } else {
                    //add the member to the database
                    memberCo.add(member);
                    //inform the user of success
                    JOptionPane.showConfirmDialog(null, "the new member has successfully been added to the database", "", JOptionPane.DEFAULT_OPTION);
                    
                    //finally we go to the next panel
                    myFrame.makeContentPane(myFrame.getPanels().get(10));

                    myFrame.invalidate();
                    myFrame.validate();
                    myFrame.repaint();
                }

            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);

        }

        //clean all the text fields
        firstNameField.setText("");
        lastNameField.setText("");
        ageField.setText("");
        mailField.setText("");
        loginField.setText("");
        pswField.setText("");

    }

}
