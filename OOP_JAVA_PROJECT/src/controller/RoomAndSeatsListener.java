/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author Juju
 * adjust the number of seats
 * to correspond to the room used
 */
public class RoomAndSeatsListener implements ActionListener{
    
    //attributes
    private JComboBox roomNumber;
    private JLabel numberSeats;

    //constructor
    public RoomAndSeatsListener(JComboBox roomNumber, JLabel numberSeats) {
        this.roomNumber = roomNumber;
        this.numberSeats = numberSeats;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        //get the choice (the only options are int so there is no need to check that)
       Integer selected=Integer.parseInt( (String) roomNumber.getSelectedItem());
       
       //adjust the number of seats
       switch (selected)
       {
           case 1:
               numberSeats.setText("100");
               break;
           case 2:
               numberSeats.setText("400");
               break;
           case 3:
               numberSeats.setText("250");
               break;
            default:
                break;
       }
        
        
    }
    
}
