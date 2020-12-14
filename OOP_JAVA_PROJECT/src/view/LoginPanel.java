/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
package view;


import controller.ChangePanelListener;
import controller.LoginListener;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.SpringLayout;

/**
 *
 * @author Juju
 * panel to connect as a member (customer)
 * or as an employee
 * with a login and a password
 */
public class LoginPanel extends JPanel {

    private JTextField loginField;
    private JPasswordField pswField;

    /**
     *
     * @param frame the main frame
     * @param table the table used (employee or member)
     * @param prevPanelNum number of the previous panel
     * @param nextPanelNum number of the next panel
     */
    public LoginPanel(MainFrame frame, String table, int prevPanelNum, int nextPanelNum) {
        
        super();
        this.setLayout(new SpringLayout());
        
        //login 
        JLabel loginLabel = new JLabel("login: ");
        loginField = new JTextField();
        loginField.setPreferredSize(new Dimension(200,(int)loginField.getPreferredSize().getHeight()));
        loginField.setMaximumSize(loginField.getPreferredSize());
        loginLabel.setLabelFor(loginField);

        //password
        JLabel passwordLabel = new JLabel("password: ");
        pswField = new JPasswordField();
        pswField.setPreferredSize(new Dimension(200,(int)pswField.getPreferredSize().getHeight()));
        pswField.setMaximumSize(pswField.getPreferredSize());
        passwordLabel.setLabelFor(pswField);

        //navigation buttons
        JButton backButton = new JButton("BACK");
        backButton.addActionListener(new ChangePanelListener(frame, prevPanelNum));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new LoginListener(nextPanelNum, table, frame, loginField, pswField));
        
        this.add(loginLabel);
        this.add(loginField);
        this.add(passwordLabel);
        this.add(pswField);
        this.add(backButton);
        this.add(okButton);

        this.setBackground(Color.white);
        this.setSize(new Dimension(400,300));
        SpringUtilities.makeCompactGrid(this,
                3, 2, //rows, cols
                6,100, //initX, initY
                6, 6); //xPad, yPad

    }

    
    
}
