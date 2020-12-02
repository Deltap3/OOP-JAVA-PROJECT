/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import view.MainFrame;

/**
 *
 * @author Juju
 */
public class ComboBoxListener implements ActionListener{
    
    private MainFrame frame;
    private int ticket;
    private JComboBox box;
    private String selected;

    public ComboBoxListener(MainFrame frame, int ticket, JComboBox box) {
        this.frame = frame;
        this.ticket = ticket;
        this.box = box;
    }

    
    public void actionPerformed(ActionEvent e)
    {
        selected=(String) box.getSelectedItem();
        
        if(selected.equals("regular"))
            frame.getCustomerOrder().getAgeDiscounts().set(ticket, 0);
        else if(selected.equals("children"))
            frame.getCustomerOrder().getAgeDiscounts().set(ticket, 20);
        else if(selected.equals("senior"))
            frame.getCustomerOrder().getAgeDiscounts().set(ticket, 15);
    }
    
}
