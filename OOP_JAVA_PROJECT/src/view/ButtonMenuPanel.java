
package view;


import controller.ChangePanelListener;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;


/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */

/*
* this class creates a menu
* with informations on the top
* in JLabels
* and as many buttons as you want
* each button allows you to navigate between
* your panels in the programm
 */
public class ButtonMenuPanel extends JPanel{
    
    /**
     *
     * @param frame the main frame
     * @param infos an ArrayList of all the information
     * we will display as JLabels
     * @param buttonLink a map: 
     * String: button text, 
     * Integer: number of the panel where this button leads
     */
    public ButtonMenuPanel(MainFrame frame,ArrayList<String> infos, Map<String,Integer> buttonLink)
    {
        super();
        
        JPanel contentPanel= new JPanel();
       
        contentPanel.setLayout(new SpringLayout());
        
        
        for(String str: infos)
        {
            //skip a line ("\n" is not taken in account in JLabels)
            //before discplaying the content
            JLabel label=new JLabel("<html><br/>"+str+"</html>");
            contentPanel.add(label);
        }
        
        
        for(Map.Entry<String, Integer> e: buttonLink.entrySet())
        {
            JButton btn= new JButton(e.getKey());
            btn.addActionListener(new ChangePanelListener(frame, e.getValue()));
            contentPanel.add(btn);
        }
        contentPanel.setBackground(Color.white);
        contentPanel.setSize(new Dimension(400,((buttonLink.size()+infos.size())*20)+200));
        contentPanel.setPreferredSize(new Dimension(400,((buttonLink.size()+infos.size())*20)+200));
        
        int inY=contentPanel.getHeight()/6;
        SpringUtilities.makeCompactGrid(contentPanel,
                (buttonLink.size()+infos.size()), 1, //rows, cols
                20,inY, //initX, initY
                10, 10); //xPad, yPad
        
        this.add(contentPanel);
        this.setBackground(Color.white);
        this.setSize(new Dimension(410,contentPanel.getHeight()));
        this.setPreferredSize(new Dimension(410,contentPanel.getHeight()));
    }
    
    
}
