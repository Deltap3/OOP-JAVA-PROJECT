
package view;


import controller.ChangePanelListener;
import controller.SearchMemberListener;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * panel to search a memeber
 * with his first name, last name
 * and mail adress
 */
public class SearchMemberPanel extends JPanel{
    
    /**
     * constructor
     * @param frame the main frame
     * @param nextPanel next panel number
     */
    public SearchMemberPanel(MainFrame frame, int nextPanel)
    {
        super();
        this.setLayout(new SpringLayout());
        this.setBackground(Color.white);
        
        //first name
        JLabel firstNameLabel = new JLabel("first name: ");
        JTextField firstNameField = new JTextField();
        Dimension dim= new Dimension(200,(int)firstNameField.getPreferredSize().getHeight());
        firstNameField.setPreferredSize(dim);
        firstNameField.setMaximumSize(dim);
        firstNameLabel.setLabelFor(firstNameField);
        
        //last name
        JLabel lastNameLabel = new JLabel("last name: ");
        JTextField lastNameField = new JTextField();
        lastNameField.setPreferredSize(dim);
        lastNameField.setMaximumSize(dim);
        lastNameLabel.setLabelFor(lastNameField);
        
        //mail adress
        JLabel mailLabel = new JLabel("mail adress: ");
        JTextField mailField = new JTextField();
        mailField.setPreferredSize(dim);
        mailField.setMaximumSize(dim);
        mailLabel.setLabelFor(mailField);
        
        //navigation buttons
        JButton backButton = new JButton("BACK");
        backButton.addActionListener(new ChangePanelListener(frame, 17));
        JButton searchButton = new JButton("SEARCH");
        searchButton.addActionListener(new SearchMemberListener(nextPanel, frame, firstNameField, lastNameField, mailField));
        
        //add components to the panel
        this.add(firstNameLabel);
        this.add(firstNameField);
        this.add(lastNameLabel);
        this.add(lastNameField);
        this.add(mailLabel);
        this.add(mailField);
        
        this.add(backButton);
        this.add(searchButton);
        
        this.setSize(new Dimension(500,700));
        
        SpringUtilities.makeCompactGrid(this,
                4, 2, //rows, cols
                6, 100, //initX, initY
                6, 6); //xPad, yPad
        
    }
}
