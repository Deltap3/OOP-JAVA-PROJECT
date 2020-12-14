
package view;

import controller.ChangePanelListener;
import controller.RemoveMemberListener;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * panel to confirm the removal of
 * a member from the database
 */
public class RemoveMemberPanel extends JPanel{

    /**
     * constructor
     * @param frame the main frame
     */
    public RemoveMemberPanel(MainFrame frame) {
        
        super();
        this.setLayout(new SpringLayout());
        
        //informations on the member
        JPanel infoPanel= new JPanel();
        infoPanel.setBackground(Color.white);
        infoPanel.setLayout(new SpringLayout());
        
        JLabel label1= new JLabel("Do you want to remove this member?");
        infoPanel.add(label1);
        
        JLabel label2= new JLabel(""+frame.getSelectedMember().getFirstName()+" "+frame.getSelectedMember().getLastName());
        infoPanel.add(label2);
        
        JLabel label3= new JLabel(""+frame.getSelectedMember().getMail());
        infoPanel.add(label3);
        
        JLabel label4= new JLabel("total spent until now: "+frame.getSelectedMember().getTotalPaid());
        infoPanel.add(label4);
        
        SpringUtilities.makeCompactGrid(infoPanel,
                4, 1, //rows, cols
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        this.add(infoPanel);
        
        //navigation buttons
        JPanel buttonPanel= new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new SpringLayout());
        
        JButton yesButton= new JButton("YES");
        yesButton.addActionListener(new RemoveMemberListener(frame, frame.getSelectedMember()));
        buttonPanel.add(yesButton); 
        
        JButton noButton= new JButton("NO");
        noButton.addActionListener(new ChangePanelListener(frame, 21));
        buttonPanel.add(noButton);
        
        SpringUtilities.makeCompactGrid(buttonPanel,
                1, 2, //rows, cols
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        this.add(buttonPanel);
        
        this.setSize(new Dimension(400,600));
        
        this.setBackground(Color.white);
         SpringUtilities.makeCompactGrid(this,
                2, 1, //rows, cols
                6, 6, //initX, initY
                6, 6); //xPad, yPad
    }
    
    
    
}
