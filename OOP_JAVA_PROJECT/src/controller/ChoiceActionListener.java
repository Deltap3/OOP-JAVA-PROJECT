/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
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
        private JLabel movieTitle;
        
        public ChoiceActionListener(MainFrame frame, int numPanel, Screening session)
        {
            this.frame=frame;
            this.numPanel=numPanel;
            this.session=session;
        }

        public ChoiceActionListener(JLabel movieTitle) {

            this.movieTitle = movieTitle;
            numPanel=-1;
        }

        public void actionPerformed(ActionEvent e)
        {
            
            if(numPanel==5)
            {
                frame.setSession(session);
                frame.buildPanel5();
                frame.setContentPane(frame.getPanels().get(numPanel));
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }
          
            else if(numPanel==-1)
            {
                movieTitle.setText(e.getActionCommand());
            }
        }
    }
