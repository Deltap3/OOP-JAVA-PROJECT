
package view;

import controller.ChangePanelListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * this panel is an intermediate between
 * the "normal" view panels and 
 * a Statistics panel
 */
public class StatPanel extends JPanel{
    
    /**
     * constructor
     * @param frame the main frame
     * @param statType witch stat will be created
     */
    public StatPanel(MainFrame frame, String statType)
    {
        super();
        //dimension and background
        this.setBackground(Color.white);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.setPreferredSize(new Dimension(width/2, height/2));
        this.setSize(new Dimension(width/2, height/2));
        
        //add the panel and navigation button
        this.setLayout(new BorderLayout());
        JLabel label= new JLabel("");
        this.add(label, BorderLayout.NORTH);
        Statistics statContent= new Statistics(statType);
        this.add(statContent, BorderLayout.CENTER);
        JButton btn= new JButton("ok");
        btn.addActionListener(new ChangePanelListener(frame, 23));
        this.add(btn, BorderLayout.SOUTH);
        
        
    }
    
}
