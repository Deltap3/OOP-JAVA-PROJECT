/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.*;
import controller.ChangePanelListener;
import java.awt.Dimension;
import java.awt.Toolkit;
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
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
//this class creates a menu with a question
//and as many buttons as you want
//each button allows you to navigate between
//your panels in the programm
public class ButtonMenuPanel extends JPanel{
    
    public ButtonMenuPanel(MainFrame frame,ArrayList<String> infos, Map<String,Integer> buttonLink)
    {
        super();
        
        JPanel contentPanel= new JPanel();
        
        //making a vertical alligned layout
        contentPanel.setLayout(new SpringLayout());
        
        
        for(String str: infos)
        {
            JLabel label=new JLabel("<html><br/>"+str+"</html>");
            contentPanel.add(label);
        }
        
        
        for(Map.Entry<String, Integer> e: buttonLink.entrySet())
        {
            JButton btn= new JButton(e.getKey());
            btn.addActionListener(new ChangePanelListener(frame, e.getValue()));
            contentPanel.add(btn);
        }
        
        contentPanel.setSize(new Dimension(400,((buttonLink.size()+infos.size())*20)+200));
        contentPanel.setPreferredSize(new Dimension(400,((buttonLink.size()+infos.size())*20)+200));
        
        int inY=contentPanel.getHeight()/6;
        SpringUtilities.makeCompactGrid(contentPanel,
                (buttonLink.size()+infos.size()), 1, //rows, cols
                20,inY, //initX, initY
                10, 10); //xPad, yPad
        
        this.add(contentPanel);
        
        this.setSize(new Dimension(410,contentPanel.getHeight()+10));
        this.setPreferredSize(new Dimension(410,contentPanel.getHeight()+10));
    }
    
    
}
