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
        panelsList.add(new ButtonMenuPanel(this, "welcome\n are you a custommer or an employee?\n", initMap));
        initMap.clear();
        
        //second panel
        initMap.put("Guest", 2);
        initMap.put("Member", 5);
        panelsList.add(new ButtonMenuPanel(this, "are you a guest or a member?\n", initMap));
        initMap.clear();
        
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
