/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AddMemberListener;
import controller.ChangePanelListener;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * create a new member
 * all necessary infos will be entered by the user
 */
public class CreateMemberPanel extends JPanel{
    
    /**
     * constructor
     * @param frame the main frame
     */
    public CreateMemberPanel(MainFrame frame)
    {
        super();
        
        this.setSize(new Dimension(400, 700));
        this.setPreferredSize(new Dimension(400, 700));
        this.setLayout(new SpringLayout());
        
        //first name
        JLabel firstNameLabel = new JLabel("first name: ");
        JTextField firstNameField = new JTextField();
        Dimension dim= new Dimension(200,(int)firstNameField.getPreferredSize().getHeight());
        firstNameField.setPreferredSize(dim);
        firstNameField.setMaximumSize(dim);
        firstNameLabel.setLabelFor(firstNameField);
        
        //last name
        JLabel lastNameLabel = new JLabel("last name: ");
        JTextField lastNameField = new JTextField();
        lastNameField.setPreferredSize(dim);
        lastNameField.setMaximumSize(dim);
        lastNameLabel.setLabelFor(lastNameField);
        
        //age
        JLabel ageLabel = new JLabel("age: ");
        JTextField ageField = new JTextField();
        ageField.setPreferredSize(dim);
        ageField.setMaximumSize(dim);
        ageLabel.setLabelFor(ageField);
        
        //mail adress
        JLabel mailLabel = new JLabel("mail adress: ");
        JTextField mailField = new JTextField();
        mailField.setPreferredSize(dim);
        mailField.setMaximumSize(dim);
        mailLabel.setLabelFor(mailField);
        
        //login
        JLabel loginLabel = new JLabel("login: ");
        JTextField loginField = new JTextField();
        loginField.setPreferredSize(dim);
        loginField.setMaximumSize(dim);
        loginLabel.setLabelFor(loginField);
        
        //password
        JLabel pswLabel = new JLabel("password: ");
        JTextField pswField = new JTextField();
        pswField.setPreferredSize(dim);
        pswField.setMaximumSize(dim);
        pswLabel.setLabelFor(pswField);
        
        //navigation buttons
        JButton backButton = new JButton("BACK");
        backButton.addActionListener(new ChangePanelListener(frame, 17));
        JButton addButton = new JButton("ADD");
        addButton.addActionListener(new AddMemberListener(frame, firstNameField, lastNameField, ageField, mailField, loginField, pswField));
        
        //add all the components
        this.add(firstNameLabel);
        this.add(firstNameField);
        this.add(lastNameLabel);
        this.add(lastNameField);
        this.add(ageLabel);
        this.add(ageField);
        this.add(mailLabel);
        this.add(mailField);
        this.add(loginLabel);
        this.add(loginField);
        this.add(pswLabel);
        this.add(pswField);
        
        this.add(backButton);
        this.add(addButton);
        
        //for the layout
        SpringUtilities.makeCompactGrid(this,//parent container
                7, 2, //rows, cols
                6, 100, //initX, initY
                6, 6); //xPad, yPad
        
        this.setBackground(Color.WHITE);
    }
}
