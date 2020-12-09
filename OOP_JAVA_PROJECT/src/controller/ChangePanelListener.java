/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.CustomerGuest;
import model.CustomerMember;
import view.MainFrame;

/**
 *
 * @author Juju
 */
public class ChangePanelListener implements ActionListener{
    
    MainFrame myFrame;
    Integer myInt;
    
    public ChangePanelListener(MainFrame frame, Integer i)
    {
        myFrame=frame;
        myInt=i;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(myInt==0 && e.getActionCommand().equals("Buy"))
        {
            myFrame.getCustomerOrder().placeOrder();
        }
        
        if(myInt==4 && e.getActionCommand().equals("Guest"))
        {
            myFrame.setUser(new CustomerGuest());
        }
        else if(myInt==6)
        {
            myFrame.buildPanel6();
        }
        else if(myInt==7)
        {
            myFrame.buildMemberInfoPanel(7, 3);
        }
        else if(myInt==19)
        {
            myFrame.buildMemberInfoPanel(19, 10);
        }
        else if( myInt==24 || myInt==25 || myInt==26 || myInt==27)
        {
            myFrame.buildStatPanel(myInt, e.getActionCommand());
        }
        
        //myFrame.setSize(myFrame.getPanels().get(myInt).getSize());
        myFrame.setContentPane(myFrame.getPanels().get(myInt));
        myFrame.pack();
        myFrame.invalidate();
        myFrame.validate();
        myFrame.repaint();
    }
    
}
