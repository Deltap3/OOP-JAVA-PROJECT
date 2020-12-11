/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ChangePanelListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.setPreferredSize(new Dimension(width/2, height/2));
        this.setSize(new Dimension(width/2, height/2));
        
        this.setLayout(new BorderLayout());
        JLabel label= new JLabel(statType);
        this.add(label, BorderLayout.NORTH);
        Statistics statFrame= new Statistics(statType);
        this.add(statFrame, BorderLayout.CENTER);
        JButton btn= new JButton("ok");
        btn.addActionListener(new ChangePanelListener(frame, 23));
        this.add(btn, BorderLayout.SOUTH);
        
        statFrame.setVisible(true);
        
    }
    
}
