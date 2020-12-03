/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ChangePanelListener;
import controller.StatListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Juju
 */
public class StatPanel extends JPanel{
    
    public StatPanel(MainFrame frame, String statType)
    {
        super();
        JLabel label= new JLabel(statType);
        this.add(label);
        Statistics statFrame= new Statistics(statType);
        this.add(statFrame.getContentPane());
        JButton btn= new JButton("ok");
        btn.addActionListener(new StatListener(frame, statFrame));
        this.add(btn);
        
        statFrame.setVisible(true);
        
    }
    
}
