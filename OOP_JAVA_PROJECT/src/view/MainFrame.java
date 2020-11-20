/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Map;

/**
 *
 * @author Juju
 */
public class MainFrame extends JFrame{
    
    private ArrayList<JPanel> panelsList;
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 300;
    private Person user;
    private Screening session;
    private Order customerOrder;
    
    public MainFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
        setTitle("My movie theater");
        initComponents();
        setContentPane(panelsList.get(0));
        setVisible(true);
    }
    private void initComponents()
    {
        session= new Screening();
        panelsList= new ArrayList<>();
        Map<String, Integer> initMap= new LinkedHashMap<>();
        //map based on insertion order
        
        //panel 0
        initMap.put("Customer", 1);
        initMap.put("Employee", 8);
        ButtonMenuPanel panel0=new ButtonMenuPanel(this, "welcome\n are you a custommer or an employee?\n", initMap);
        panelsList.add(panel0);
        this.add(panel0);
        initMap.clear();
        
        //panel 1
        initMap.put("Guest", 4);
        initMap.put("Member", 2);
        initMap.put("back", 0);
        ButtonMenuPanel panel1=new ButtonMenuPanel(this, "are you a guest or a member?\n", initMap);
        panelsList.add(panel1);
        this.add(panel1);
        initMap.clear();
        
        
        //panel 2
        LoginPanel panel2= new LoginPanel(this, "members", 1, 3);
        panelsList.add(panel2);
        this.add(panel2);
        
        //panel 3
        initMap.put("buy tickets", 4);
        initMap.put("display info", 7);
        initMap.put("back", 2);
        ButtonMenuPanel panel3=new ButtonMenuPanel(this, "what do you want to do?\n", initMap);
        panelsList.add(panel3);
        this.add(panel3);
        initMap.clear();
        
        //panel 4
        ScreeningChoicePanel panel4= new ScreeningChoicePanel(this, 5);
        panelsList.add(panel4);
        this.add(panel4);
        
        //panel 5
        BuyPanel panel5= new BuyPanel(this, session);
        panelsList.add(panel5);
        this.add(panel5);
        
        /*
        //panel 8
        LoginPanel panel8= new LoginPanel(this, "employee", 0, 9);
        panelsList.add(panel8);
        this.add(panel8);
        
        //panel 9
        initMap.put("manage screenings", 10);
        initMap.put("manage discounts", 13);
        initMap.put("manage member customers",16);
        initMap.put("show stats",21);
        initMap.put("back", 8);
        ButtonMenuPanel panel9=new ButtonMenuPanel(this, "what do you want to do?\n", initMap);
        panelsList.add(panel9);
        this.add(panel9);
        initMap.clear();
        
        //panel 10
        initMap.put("add screening session", 11);
        initMap.put("Remove screening session", 12);
        initMap.put("back", 9);
        ButtonMenuPanel panel10=new ButtonMenuPanel(this, "what do you want to do?\n", initMap);
        panelsList.add(panel10);
        this.add(panel10);
        initMap.clear();
        
        //panel 13
        initMap.put("add screening session", 11);
        initMap.put("Remove screening session", 12);
        initMap.put("back", 9);
        ButtonMenuPanel panel13=new ButtonMenuPanel(this, "discount type: \n", initMap);
        panelsList.add(panel13);
        this.add(panel13);
        initMap.clear();
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

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public Order getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(Order customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Screening getSession() {
        return session;
    }

    public void setSession(Screening session) {
        this.session = session;
    }
    
}
