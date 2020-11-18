/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Connections;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.SpringLayout;


/**
 *
 * @author Juju
 */
public class LoginPanel extends JPanel {

    private JTextField loginField;
    private JPasswordField pswField;

    public LoginPanel(MainFrame frame, String table, int prevPanelNum, int nextPanelNum) {
        
        super();
        this.setLayout(new SpringLayout());

        JLabel loginLabel = new JLabel("login: ");
        loginField = new JTextField();
        loginLabel.setLabelFor(loginField);

        JLabel passwordLabel = new JLabel("password: ");
        pswField = new JPasswordField();
        passwordLabel.setLabelFor(pswField);

        JButton backButton = new JButton("BACK");
        backButton.addActionListener(new BackActionListener(frame, prevPanelNum));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new OkActionListener(frame, table, nextPanelNum));
        
        this.add(loginLabel);
        this.add(loginField);
        this.add(passwordLabel);
        this.add(pswField);
        this.add(backButton);
        this.add(okButton);

        SpringUtilities.makeCompactGrid(this,
                3, 2, //rows, cols
                6, 6, //initX, initY
                6, 6); //xPad, yPad

    }

    public class BackActionListener implements ActionListener {

        private int numPanel;
        private MainFrame myFrame;

        public BackActionListener(MainFrame frame, int num) {
            numPanel = num;
            myFrame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            myFrame.setContentPane(myFrame.getPanels().get(numPanel));
            invalidate();
            validate();
        }
    }

    public class OkActionListener implements ActionListener {

        private int numPanel;
        private String table;
        private MainFrame myFrame;

        public OkActionListener(MainFrame frame, String tab, int num) {
            numPanel = num;
            table = tab;
            myFrame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                Connections co = new Connections("project", "root", "projetJava2020");
                String login= loginField.getText();
                String password= new String(pswField.getPassword());
                System.out.println("login: "+login);
                System.out.println("password: "+password);
                boolean correct=false;
                if(table.equals("members")){
                 correct= co.memberExist(login, password);
                }
                else if(table.equals("employee"))
                {
                    correct= co.employeeExist(login, password);
                }
                if (correct) {
                    myFrame.setContentPane(myFrame.getPanels().get(numPanel));
                    invalidate();
                    validate();
                } 
                else {
                    throw new IllegalArgumentException("login or password is incorrect");
                }
            } 
            catch (SQLException ex) {
                Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (ClassNotFoundException ex) {
                Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(IllegalArgumentException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);

            }
            
            loginField.setText("");
            pswField.setText("");

        }
    }
}
