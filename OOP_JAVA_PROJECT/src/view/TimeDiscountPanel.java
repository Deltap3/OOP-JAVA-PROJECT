/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ChangePanelListener;
import controller.MovieDiscountListener;
import controller.TimeDiscountListener;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author Juju
 */
public class TimeDiscountPanel extends JPanel{

    public TimeDiscountPanel(MainFrame frame) {
        
        super();
        this.setLayout(new SpringLayout());
        
        //dateTime selection
        
        JLabel label1= new JLabel("from: ");
        this.add(label1);
        DateTimePanel startPanel=new DateTimePanel();
        this.add(startPanel);
        
        JLabel label2= new JLabel("until: ");
        this.add(label2);
        DateTimePanel endPanel= new DateTimePanel();
        this.add(endPanel);
        
        
        //discount selection
        JPanel discountPanel= new JPanel();
        discountPanel.setLayout(new SpringLayout());
        JLabel label3= new JLabel("discount ");
        discountPanel.add(label3);
        
        JLabel label4= new JLabel("(member only)");
        discountPanel.add(label4);
        
        JTextField discountField= new JTextField();
        discountField.setText("0");
        discountField.setMaximumSize(discountField.getPreferredSize());
        discountPanel.add(discountField);

        JLabel label5= new JLabel(" % ");
        discountPanel.add(label5);
        
        SpringUtilities.makeCompactGrid(discountPanel,
                2, 2, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        
        this.add(discountPanel);
        
        //navigation buttons
        JPanel buttonPanel= new JPanel();
        JButton backButton= new JButton("BACK");
        backButton.addActionListener(new ChangePanelListener(frame, 14));
        buttonPanel.add(backButton);
        JButton addButton= new JButton("APPLY DISCOUNT");
        addButton.addActionListener(new TimeDiscountListener(frame, startPanel, endPanel, discountField));
        buttonPanel.add(addButton);
        
        this.add(buttonPanel);
        this.setSize(new Dimension(500,800));
        SpringUtilities.makeCompactGrid(this,
                6, 1, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
    }
    
    
    
    
    
}
