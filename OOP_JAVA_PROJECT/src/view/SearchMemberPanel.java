/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AddMemberListener;
import controller.ChangePanelListener;
import controller.SearchMemberListener;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author Juju
 */
public class SearchMemberPanel extends JPanel{
    
    public SearchMemberPanel(MainFrame frame, int nextPanel)
    {
        super();
        this.setLayout(new SpringLayout());
        
        JLabel firstNameLabel = new JLabel("first name: ");
        JTextField firstNameField = new JTextField();
        Dimension dim= new Dimension(200,(int)firstNameField.getPreferredSize().getHeight());
        firstNameField.setPreferredSize(dim);
        firstNameField.setMaximumSize(dim);
        firstNameLabel.setLabelFor(firstNameField);
        
        JLabel lastNameLabel = new JLabel("last name: ");
        JTextField lastNameField = new JTextField();
        lastNameField.setPreferredSize(dim);
        lastNameField.setMaximumSize(dim);
        lastNameLabel.setLabelFor(lastNameField);
        
        JLabel mailLabel = new JLabel("mail adress: ");
        JTextField mailField = new JTextField();
        mailField.setPreferredSize(dim);
        mailField.setMaximumSize(dim);
        mailLabel.setLabelFor(mailField);
        
        JButton backButton = new JButton("BACK");
        backButton.addActionListener(new ChangePanelListener(frame, 17));
        JButton searchButton = new JButton("SEARCH");
        searchButton.addActionListener(new SearchMemberListener(nextPanel, frame, firstNameField, lastNameField, mailField));
        
        this.add(firstNameLabel);
        this.add(firstNameField);
        this.add(lastNameLabel);
        this.add(lastNameField);
        this.add(mailLabel);
        this.add(mailField);
        
        this.add(backButton);
        this.add(searchButton);
        
        this.setSize(new Dimension(800,1000));
        SpringUtilities.makeCompactGrid(this,
                4, 2, //rows, cols
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        
    }
}
