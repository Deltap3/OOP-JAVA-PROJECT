/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Screening;
import view.BuyPanel;
import view.MainFrame;

/**
 *
 * @author Juju
 */
public class ChoiceActionListener implements ActionListener{
        private MainFrame frame;
        private int numPanel;
        private Screening session;
        
        public ChoiceActionListener(MainFrame frame, int numPanel, Screening session)
        {
            this.frame=frame;
            this.numPanel=numPanel;
            this.session=session;
        }
        
        public void actionPerformed(ActionEvent e)
        {
            frame.setSession(session);
            if(numPanel==5)
            {
                frame.buildPanel5();
            }
           // frame.setSize(frame.getPanels().get(numPanel).getSize());
            frame.setContentPane(frame.getPanels().get(numPanel));
            frame.invalidate();
            frame.validate();
            frame.repaint();
        }
    }
