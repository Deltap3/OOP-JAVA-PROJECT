/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;
import DAO.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.SpringLayout;


/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
public class LoginPanel extends JPanel {

    private JTextField loginField;
    private JPasswordField pswField;

    public LoginPanel(MainFrame frame, String table, int prevPanelNum, int nextPanelNum) {
        
        super();
        this.setLayout(new SpringLayout());
        this.setSize(new Dimension(700,600));
        JLabel loginLabel = new JLabel("login: ");
        loginField = new JTextField();
        loginField.setMaximumSize(loginField.getPreferredSize());
        loginLabel.setLabelFor(loginField);

        JLabel passwordLabel = new JLabel("password: ");
        pswField = new JPasswordField();
        pswField.setMaximumSize(pswField.getPreferredSize());
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
            myFrame.setSize(myFrame.getPanels().get(numPanel).getSize());
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
                Connections co = new Connections("project", "root", "password");
                System.out.println(co.getAllFromTable(""));
                String login= loginField.getText();
                String password= new String(pswField.getPassword());
                boolean correct=false;
                Person user = null;
                
                if(table.equals("members")){
                    
                 CustomerMemberDAO memberCo= new CustomerMemberDAO(co.getInstance());
                 user= memberCo.findFromLoginPassword(login, password);
                 correct= co.memberExist(login, password);
                 
                }
                else if(table.equals("employee"))
                {
                    EmployeeDAO employeeCo= new EmployeeDAO(co.getInstance());
                    user= employeeCo.findFromLoginPassword(login, password);
                    correct= co.employeeExist(login, password);
                }
                if (correct) {
                    myFrame.setUser(user);
                    myFrame.setSize(myFrame.getPanels().get(numPanel).getSize());
                    myFrame.setContentPane(myFrame.getPanels().get(numPanel));
                    invalidate();
                    validate();
                    repaint();
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
