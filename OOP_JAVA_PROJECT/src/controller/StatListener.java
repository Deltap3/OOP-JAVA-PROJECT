/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import model.CustomerGuest;
import view.MainFrame;

/**
 *
 * @author Juju
 */
public class StatListener implements ActionListener{
    MainFrame myFrame;
    JFrame statFrame;

    public StatListener(MainFrame myFrame, JFrame statFrame) {
        this.myFrame = myFrame;
        this.statFrame = statFrame;
    }
    

    public void actionPerformed(ActionEvent e)
    {
        statFrame.dispose();
        //myFrame.setSize(myFrame.getPanels().get(myInt).getSize());
        myFrame.setContentPane(myFrame.getPanels().get(22));
        myFrame.pack();
        myFrame.invalidate();
        myFrame.validate();
        myFrame.repaint();
    }
    
}
