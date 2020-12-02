/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;
import DAO.*;
import controller.ChangePanelListener;
import controller.LoginListener;
import java.awt.Dimension;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.SpringLayout;


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
        this.setSize(new Dimension(700,600));
        JLabel loginLabel = new JLabel("login: ");
        loginField = new JTextField();
        loginField.setMaximumSize(loginField.getPreferredSize());
        loginLabel.setLabelFor(loginField);

        JLabel passwordLabel = new JLabel("password: ");
        pswField = new JPasswordField();
        pswField.setMaximumSize(pswField.getPreferredSize());
        passwordLabel.setLabelFor(pswField);

        JButton backButton = new JButton("BACK");
        backButton.addActionListener(new ChangePanelListener(frame, prevPanelNum));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new LoginListener(nextPanelNum, table, frame, loginField, pswField));
        
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

    
    
}
