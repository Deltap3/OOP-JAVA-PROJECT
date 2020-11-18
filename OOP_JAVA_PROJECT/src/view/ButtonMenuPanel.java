/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

/**
 *
 * @author Juju
 */
//this class creates a menu with a question
//and as many buttons as you want
//each button allows you to navigate between
//your panels in the programm
public class ButtonMenuPanel extends JPanel{
    
    public ButtonMenuPanel(MainFrame frame,String question, Map<String,Integer> buttonLink)
    {
        super();
        
        //making a vertical alligned layout
        this.setLayout(new SpringLayout());
        
        JLabel label=new JLabel(question);
        this.add(label);
        
        for(Map.Entry<String, Integer> e: buttonLink.entrySet())
        {
            JButton btn= new JButton(e.getKey());
            btn.addActionListener(new ChangePanelListener(frame, e.getValue()));
            this.add(btn);
        }
        
        SpringUtilities.makeCompactGrid(this,
                (buttonLink.size()+1), 1, //rows, cols
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        
    }
    
    private class ChangePanelListener implements ActionListener{
        MainFrame myFrame;
        Integer myInt;
        public ChangePanelListener(MainFrame frame, Integer i)
        {
            myFrame=frame;
            myInt=i;
        }
        public void actionPerformed(ActionEvent e)
        {
            
            myFrame.setContentPane(myFrame.getPanels().get(myInt));
            invalidate();
            validate();
        }
    }
}
