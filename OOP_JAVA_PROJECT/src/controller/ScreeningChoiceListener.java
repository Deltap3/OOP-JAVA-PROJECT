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
import view.RemoveScreeningPanel;

/**
 *Manages all the actions
 * whenever a screening session is selected
 * customer screening choice, 
 * employee create or remove a screening session
 */
public class ScreeningChoiceListener implements ActionListener{
    
        private MainFrame frame;
        private RemoveScreeningPanel sPanel;
        private int numPanel;
        private Screening session;
        private JLabel movieTitle;
        
        public ScreeningChoiceListener(MainFrame frame, int numPanel, Screening session)
        {
            this.frame=frame;
            this.numPanel=numPanel;
            this.session=session;
        }

        public ScreeningChoiceListener(RemoveScreeningPanel sPanel, Screening session) {
            this.sPanel = sPanel;
            this.session = session;
            numPanel=0;
        }
        
        public ScreeningChoiceListener(JLabel movieTitle) {

            this.movieTitle = movieTitle;
            numPanel=-1;
        }

        public void actionPerformed(ActionEvent e)
        {
            
            if(numPanel==5)
            {
                frame.setSession(session);
                frame.buildPanel5();
                frame.makeContentPane(frame.getPanels().get(numPanel));
                frame.pack();
                frame.centerFrame();
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }
            else if(numPanel==0)
            {
                sPanel.setSelectedSession(session);
            }
            else if(numPanel==-1)
            {
                movieTitle.setText(e.getActionCommand());
            }
        }
    }
