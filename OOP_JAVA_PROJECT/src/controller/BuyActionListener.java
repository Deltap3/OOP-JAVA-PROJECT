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
 */
public class BuyActionListener implements ActionListener{
     private MainFrame myFrame;
    private Screening session;
    private JTextField nbTicketsField;

    public BuyActionListener(MainFrame myFrame, Screening session, JTextField nbTextField) {
        this.myFrame = myFrame;
        this.session = session;
        this.nbTicketsField = nbTextField;
    }
    

    public void actionPerformed(ActionEvent e)
    {           
        try{

        String str= nbTicketsField.getText();
        int ticketsNumber=Integer.parseInt(str);

        if(session.getNumberseat()<(session.getTicketsBoughts()+ticketsNumber))
        {
           throw new IllegalArgumentException("you cannot buy "+ticketsNumber+" tickets for this screening session"); 
        }
        else{

        Order customOrder=new Order(ticketsNumber,session,myFrame.getUser());  

        myFrame.setCustomerOrder(customOrder);

        myFrame.buildPanel8();
       // myFrame.setSize(myFrame.getPanels().get(8).getSize());
        myFrame.setContentPane(myFrame.getPanels().get(8));

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
