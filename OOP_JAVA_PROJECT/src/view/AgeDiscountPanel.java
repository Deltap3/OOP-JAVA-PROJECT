/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ChangePanelListener;
import controller.ComboBoxListener;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 *
 * @author Juju
 * create a panel to select the
 * age category of each ticket purchased
 */
public class AgeDiscountPanel extends JPanel{
    
    public AgeDiscountPanel(MainFrame frame)
    {
        super(); 
        this.setLayout(new SpringLayout());
        
        int numberTickets= frame.getCustomerOrder().getTicketsNumber();
        
        this.setSize(new Dimension(500,numberTickets*150));
        
        //infos on top of the panel
        JLabel label1= new JLabel("please select the corresponding age range for each ticket: \n");
        this.add(label1);
        JLabel label2= new JLabel("children= under 15, regular= between 15 and 60, senior= over 60 \n");
        this.add(label2);
        JLabel label3= new JLabel("employees may ask for proof \n");
        this.add(label3);
        
        //create as many individual panels
        //as there are tickets purchased
        for(int i=0;i<numberTickets;i++)
        {
            JPanel contentPanel=buildIndividualPanel(frame, i);
            this.add(contentPanel);
        }
        //add the confirm button
        JButton okButton= new JButton("next");
        okButton.addActionListener(new ChangePanelListener(frame, 6));
        this.add(okButton);
        
        SpringUtilities.makeCompactGrid(this,
                numberTickets+4, 1, //rows, cols
                6,6, //initX, initY
                6, 6); //xPad, yPad
        
        this.setBackground(Color.white);
    }
    
    //build one panel to select the category of one ticket
    public JPanel buildIndividualPanel(MainFrame frame, int i)
    {
        
        JPanel contentPanel= new JPanel(new SpringLayout());
        JLabel panelLabel= new JLabel("ticket "+(i+1));
        contentPanel.add(panelLabel);
        String[] categoryStrings= {"children","regular","senior"};
        JComboBox categoryList = new JComboBox(categoryStrings);
        categoryList.setMaximumSize(categoryList.getPreferredSize());
        categoryList.setSelectedIndex(1);
        categoryList.addActionListener(new ComboBoxListener(frame, i, categoryList));
        contentPanel.add(categoryList);
        
        SpringUtilities.makeCompactGrid(contentPanel,
                2, 1, //rows, cols
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        contentPanel.setBackground(Color.white);
        return contentPanel;
    }
}
