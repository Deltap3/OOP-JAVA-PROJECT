/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Map;
import javax.swing.BoxLayout;
/**
 *
 * @author Juju
 */
public class MainFrame extends JFrame{
    private ArrayList<JPanel> panelsList;
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 300;
    
    public MainFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
        setTitle("My movie theater");
        initComponents();
        setContentPane(panelsList.get(0));
        setVisible(true);
    }
    private void initComponents()
    {
        panelsList= new ArrayList<>();
        Map<String, Integer> initMap= new HashMap<>();
        
        //first panel
        initMap.put("Customer", 1);
        initMap.put("Employee", 8);
        ButtonMenuPanel panel0=new ButtonMenuPanel(this, "welcome\n are you a custommer or an employee?\n", initMap);
        panelsList.add(panel0);
        this.add(panel0);
        initMap.clear();
        
        //second panel
        initMap.put("Guest", 4);
        initMap.put("Member", 2);
        ButtonMenuPanel panel1=new ButtonMenuPanel(this, "are you a guest or a member?\n", initMap);
        panelsList.add(panel1);
        this.add(panel1);
        initMap.clear();
        
        
        //third panel
        LoginPanel panel2= new LoginPanel(this, "member", 1, 3);
        panelsList.add(panel2);
        this.add(panel2);
        
        /*
        //9th panel
        LoginPanel panel8= new LoginPanel(this, "employee", 0, 9);
        panelsList.add(panel8);
        this.add(panel8);
        */
        pack();
    }
    public ArrayList<JPanel> getPanels()
    {
        return panelsList;
    }
    public int getWidth()
    {
        return WINDOW_WIDTH;
    }
    public int getHeight()
    {
        return WINDOW_HEIGHT;
    }
    
}
