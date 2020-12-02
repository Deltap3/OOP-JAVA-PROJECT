/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.*;
import controller.ChangePanelListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import model.CustomerGuest;

/**
 *
 * @author Juju
 */
//this class creates a menu with a question
//and as many buttons as you want
//each button allows you to navigate between
//your panels in the programm
public class ButtonMenuPanel extends JPanel{
    
    public ButtonMenuPanel(MainFrame frame,ArrayList<String> infos, Map<String,Integer> buttonLink)
    {
        super();
        
        //making a vertical alligned layout
        this.setLayout(new SpringLayout());
        this.setSize(new Dimension(500,buttonLink.size()*200));
        
        for(String str: infos)
        {
            JLabel label=new JLabel(str);
            this.add(label);
        }
        
        
        for(Map.Entry<String, Integer> e: buttonLink.entrySet())
        {
            JButton btn= new JButton(e.getKey());
            btn.addActionListener(new ChangePanelListener(frame, e.getValue()));
            this.add(btn);
        }
        
        SpringUtilities.makeCompactGrid(this,
                (buttonLink.size()+infos.size()), 1, //rows, cols
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        
    }
    
    
}
