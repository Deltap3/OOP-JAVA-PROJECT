/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.CustomerGuest;
import view.MainFrame;

/**
 *
 * @author Juju
 * 
 * "basic" action listener that manages
 * the passage between a panel and another
 */
public class ChangePanelListener implements ActionListener{
    
    //attributes
    private MainFrame myFrame;
    private Integer myInt;
    
    //constructor
    public ChangePanelListener(MainFrame frame, Integer i)
    {
        myFrame=frame;
        myInt=i;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        //special actions may happen depending on
        //witch panel is next
        
        //when you are at the end of an order
        //(end of customer route)
        if(myInt==0 && e.getActionCommand().equals("Buy"))
        {
            //place the order
            myFrame.getCustomerOrder().placeOrder();
        }
        //if you go back from the screening selection
        //as a customer, you won't go back to the same
        //panel if you are a guest or a member
        if(myInt==1 && e.getActionCommand().equals("Back"))
        {
            if (myFrame.getUser().isMember())
                myInt=3;
        }
        //if you decide to buy tickets as a guest
        if(myInt==4 && e.getActionCommand().equals("Guest"))
        {
            //from this point to the end of the customer route, 
            //the user is a guest
            myFrame.setUser(new CustomerGuest());
        }
        
        //certain panels need to be updated
        //therfore we rebuild them with their own 
        //building method before we go to them
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
        

        //go to the next panel
        myFrame.makeContentPane(myFrame.getPanels().get(myInt));
        
        myFrame.invalidate();
        myFrame.validate();
        myFrame.repaint();
    }
    
}
