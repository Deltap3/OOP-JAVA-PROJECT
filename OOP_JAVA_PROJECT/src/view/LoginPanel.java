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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.SpringLayout;


/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
public class LoginPanel extends JPanel {

    private JTextField loginField;
    private JPasswordField pswField;

    public LoginPanel(MainFrame frame, String table, int prevPanelNum, int nextPanelNum) {
        
        super();
        this.setLayout(new SpringLayout());
        
        JLabel loginLabel = new JLabel("login: ");
        loginField = new JTextField();
        loginField.setPreferredSize(new Dimension(200,(int)loginField.getPreferredSize().getHeight()));
        loginField.setMaximumSize(loginField.getPreferredSize());
        loginLabel.setLabelFor(loginField);

        JLabel passwordLabel = new JLabel("password: ");
        pswField = new JPasswordField();
        pswField.setPreferredSize(new Dimension(200,(int)pswField.getPreferredSize().getHeight()));
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

        this.setBackground(Color.white);
        this.setSize(new Dimension(400,300));
        SpringUtilities.makeCompactGrid(this,
                3, 2, //rows, cols
                6,100, //initX, initY
                6, 6); //xPad, yPad

    }

    
    
}
