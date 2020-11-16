/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import oop_java_project.Connection;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.SpringLayout;
import oop_java_project.OOP_JAVA_PROJECT;

/**
 *
 * @author Juju
 */
public class LoginPanel extends JPanel {

    private JTextField loginField;
    private JPasswordField pswField;

    public LoginPanel(MainFrame frame, String table, int prevPanelNum, int nextPanelNum) {
        
        super();
        this.setLayout(new SpringLayout());

        JLabel loginLabel = new JLabel("login: ");
        loginField = new JTextField();
        loginLabel.setLabelFor(loginField);

        JLabel passwordLabel = new JLabel("password: ");
        pswField = new JPasswordField();
        passwordLabel.setLabelFor(pswField);

        JButton backButton = new JButton("BACK");
        backButton.addActionListener(new BackActionListener(frame, prevPanelNum));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new OkActionListener(frame, table, nextPanelNum));
        
        this.add(loginLabel);
        this.add(loginField);
        this.add(passwordLabel);
        this.add(pswField);
        this.add(backButton);
        this.add(okButton);

        SpringUtilities.makeCompactGrid(this,
                3, 2, //rows, cols
                6, 6, //initX, initY
                6, 6); //xPad, yPad

    }

    public class BackActionListener implements ActionListener {

        private int numPanel;
        private MainFrame myFrame;

        public BackActionListener(MainFrame frame, int num) {
            numPanel = num;
            myFrame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            myFrame.setContentPane(myFrame.getPanels().get(numPanel));
            invalidate();
            validate();
        }
    }

    public class OkActionListener implements ActionListener {

        private int numPanel;
        private String table;
        private MainFrame myFrame;

        public OkActionListener(MainFrame frame, String tab, int num) {
            numPanel = num;
            table = tab;
            myFrame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                Connection co = new Connection("project", "root", "projetJava2020");
                if (co.loginIsCorrect(table, loginField.getText(), new String(pswField.getPassword()))) {
                    myFrame.setContentPane(myFrame.getPanels().get(numPanel));
                    invalidate();
                    validate();
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

        }
    }
}
