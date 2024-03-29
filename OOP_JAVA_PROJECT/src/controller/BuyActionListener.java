/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Order;
import model.Screening;
import view.MainFrame;

/**
 *
 * @author Juju
 * 
 * creates the order when user
 * decides to by ticketsNumber tickets
 * for a secreening session
 */
public class BuyActionListener implements ActionListener{
    //attributes
    private MainFrame myFrame;
    private Screening session;
    private JTextField nbTicketsField;

    //constructor
    public BuyActionListener(MainFrame myFrame, Screening session, JTextField nbTextField) {
        this.myFrame = myFrame;
        this.session = session;
        this.nbTicketsField = nbTextField;
    }
    

    public void actionPerformed(ActionEvent e)
    {           
        try{
        //get the data
        String str= nbTicketsField.getText();
        int ticketsNumber=Integer.parseInt(str);

        //check if there are enought seats remaining
        if(session.getNumberseat()<(session.getTicketsBoughts()+ticketsNumber))
        {
           throw new IllegalArgumentException("you cannot buy "+ticketsNumber+" tickets for this screening session"); 
        }
        else{

        //create the Order
        Order customOrder=new Order(ticketsNumber,session,myFrame.getUser());  
        myFrame.setCustomerOrder(customOrder);

        //go to next panel
        myFrame.buildPanel8();
        myFrame.makeContentPane(myFrame.getPanels().get(8));

        }

        }         
        catch(IllegalArgumentException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);

        }
        
        myFrame.invalidate();
        myFrame.validate();
        myFrame.repaint();
    }
}
