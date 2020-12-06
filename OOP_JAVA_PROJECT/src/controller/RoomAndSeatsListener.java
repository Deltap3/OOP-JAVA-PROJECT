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
 */
public class RoomAndSeatsListener implements ActionListener{
    
    private JComboBox roomNumber;
    private JLabel numberSeats;

    public RoomAndSeatsListener(JComboBox roomNumber, JLabel numberSeats) {
        this.roomNumber = roomNumber;
        this.numberSeats = numberSeats;
    }
    
    public void actionPerformed(ActionEvent e)
    {
       Integer selected=Integer.parseInt( (String) roomNumber.getSelectedItem());
       
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
