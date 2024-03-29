
package view;

import controller.ChangePanelListener;
import controller.TimeDiscountListener;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * create a discount that applies
 * to all screenings between 2 dateTimes
 */
public class TimeDiscountPanel extends JPanel{

    /**
     * constructor
     * @param frame the main frame
     */
    public TimeDiscountPanel(MainFrame frame) {
        
        super();
        this.setLayout(new SpringLayout());
        this.setBackground(Color.white);
        
        //dateTime selection
        //we use ours DateTimePanels
        JLabel label1= new JLabel("from: ");
        this.add(label1);
        DateTimePanel startPanel=new DateTimePanel();
        this.add(startPanel);
        
        JLabel label2= new JLabel("until: ");
        this.add(label2);
        DateTimePanel endPanel= new DateTimePanel();
        this.add(endPanel);
        
        
        //discount selection
        JPanel discountPanel= new JPanel();
        discountPanel.setBackground(Color.white);
        
        discountPanel.setLayout(new SpringLayout());
        JLabel label3= new JLabel("discount ");
        discountPanel.add(label3);
        
        JLabel label4= new JLabel("(member only)");
        discountPanel.add(label4);
        
        JTextField discountField= new JTextField();
        discountField.setText("0");
        discountField.setMaximumSize(discountField.getPreferredSize());
        discountPanel.add(discountField);

        JLabel label5= new JLabel(" % ");
        discountPanel.add(label5);
        
        SpringUtilities.makeCompactGrid(discountPanel,
                2, 2, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        
        this.add(discountPanel);
        
        //navigation buttons
        JPanel buttonPanel= new JPanel();
        buttonPanel.setBackground(Color.white);
        
        JButton backButton= new JButton("BACK");
        backButton.addActionListener(new ChangePanelListener(frame, 14));
        buttonPanel.add(backButton);
        JButton addButton= new JButton("APPLY DISCOUNT");
        addButton.addActionListener(new TimeDiscountListener(frame, startPanel, endPanel, discountField));
        buttonPanel.add(addButton);
        
        this.add(buttonPanel);
        this.setSize(new Dimension(500,800));
        SpringUtilities.makeCompactGrid(this,
                6, 1, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
    }
    
    
    
    
    
}
