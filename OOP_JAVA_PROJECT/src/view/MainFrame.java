/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.CustomerMemberDAO;
import java.awt.BorderLayout;
import model.*;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Map;

import javax.swing.SpringLayout;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import org.jfree.ui.RefineryUtilities;


/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
public class MainFrame extends JFrame{
    
    private ArrayList<JPanel> panelsList;
    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 1400;
    private Person user;
    private CustomerMember selectedMember;
    private Screening session;
    private Order customerOrder;
    
    public MainFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
        setTitle("My movie theater");
        initComponents();
        setSize(panelsList.get(0).getSize());
        setContentPane(panelsList.get(0));
        setVisible(true);
    }
    private void initComponents()
    {
        this.setLayout(new BorderLayout());
        
        customerOrder= new Order();
        user= new CustomerGuest();
        session= new Screening();
        panelsList= new ArrayList<>();
        Map<String, Integer> initMap= new LinkedHashMap<>();
        ArrayList<String> initInfos= new ArrayList<>();
        //map based on insertion order
        
        for(int i=0;i<28;i++)
        {
            panelsList.add(new JPanel());
        }
        //panel 0: home screen / side choice
        initMap.put("Customer", 1);
        initMap.put("Employee", 9);
        initInfos.add("welcome\n are you a custommer or an employee?\n");
        ButtonMenuPanel panel0=new ButtonMenuPanel(this, initInfos, initMap);
        panelsList.set(0,panel0);
        this.add(panel0);
        initMap.clear();
        initInfos.clear();
        
        //customer side
        //panel 1: guest or member choice
        initMap.put("Guest", 4);
        initMap.put("Member", 2);
        initMap.put("back", 0);
        initInfos.add("are you a guest or a member?\n");
        ButtonMenuPanel panel1=new ButtonMenuPanel(this, initInfos, initMap);
        panelsList.set(1,panel1);
        this.add(panel1);
        initMap.clear();
        initInfos.clear();
        
        //panel 2: login as member
        LoginPanel panel2= new LoginPanel(this, "members", 1, 3);
        panelsList.set(2,panel2);
        this.add(panel2);
        
        //panel 3: member options screen
        initMap.put("buy tickets", 4);
        initMap.put("display info", 7);
        initMap.put("back", 2);
        initInfos.add("what do you want to do?\n");
        ButtonMenuPanel panel3=new ButtonMenuPanel(this, initInfos, initMap);
        panelsList.set(3,panel3);
        this.add(panel3);
        initMap.clear();
        initInfos.clear();
        
        //panel 4: screening session choice
        ScreeningChoicePanel panel4= new ScreeningChoicePanel(this, 5);
        panelsList.set(4,panel4);
        this.add(panel4);
        
        //the panels 5 to 8 need to be actualised
        //therfore they have their own build method

        //panel 5: film infos and number of tickets selection
        //panel 8: age discount selection by ticket
        //panel 6: buy receipe and last confirmation 
        //panel 7: member info panel

        //employee side
        //panel 9: login as employee
        LoginPanel panel9= new LoginPanel(this, "employee", 0, 10);
        panelsList.set(9,panel9);
        this.add(panel9);
        
        //panel 10: main employee options menu
        initMap.put("manage screenings", 11);
        initMap.put("manage discounts", 14);
        initMap.put("manage members customers",17);
        initMap.put("show stats",23);
        initMap.put("back", 9);
        initInfos.add("general management screen\n");
        ButtonMenuPanel panel10=new ButtonMenuPanel(this, initInfos, initMap);
        panelsList.set(10,panel10);
        this.add(panel10);
        initMap.clear();
        initInfos.clear();
        
        //panel 11: screening management menu
        initMap.put("add screening session", 12);
        initMap.put("Remove screening session", 13);
        initMap.put("back", 10);
        initInfos.add("screening management\n");
        ButtonMenuPanel panel11=new ButtonMenuPanel(this, initInfos, initMap);
        panelsList.set(11,panel11);
        this.add(panel11);
        initMap.clear();
        initInfos.clear();
        
        //panel 12: create a new screening session
        CreateScreeningPanel panel12= new CreateScreeningPanel(this);
        panelsList.set(12, panel12);
        this.add(panel12);
        
        //panel13: remove an existing screening session
        RemoveScreeningPanel panel13= new RemoveScreeningPanel(this);
        panelsList.set(13, panel13);
        this.add(panel13);
        
        //panel 14: discount management menu
        initMap.put("by movie played", 15);
        initMap.put("by screening schedule", 16);
        initMap.put("back", 10);
        initInfos.add("choose the discount type\n");
        ButtonMenuPanel panel14=new ButtonMenuPanel(this, initInfos, initMap);
        panelsList.set(14,panel14);
        this.add(panel14);
        initMap.clear();
        initInfos.clear();
        
        //panel 15: discount by movie played
        MovieDiscountPanel panel15= new MovieDiscountPanel(this);
        panelsList.set(15,panel15);
        this.add(panel15);
        
        //panel 16: discount between 2 dates

        //panel 17: member management menu
        initMap.put("see a member's info", 18);
        initMap.put("Remove a member", 21);
        initMap.put("add a new member", 20);
        initMap.put("back", 10);
        initInfos.add("member management\n");
        ButtonMenuPanel panel17=new ButtonMenuPanel(this, initInfos, initMap);
        panelsList.set(17,panel17);
        this.add(panel17);
        initMap.clear();
        initInfos.clear();
        
        //panel 18: search for a member (to show its infos)
        SearchMemberPanel panel18= new SearchMemberPanel(this, 19);
        panelsList.set(18, panel18);
        this.add(panel18);
 
        //panel 19 : show this member's infos
        //need to be actualised
        
        //panel 20 : create a new member
        CreateMemberPanel panel20= new CreateMemberPanel(this);
        panelsList.set(20, panel20);
        this.add(panel20);
        
        //panel 21 : search for a member (to delete it)
        SearchMemberPanel panel21= new SearchMemberPanel(this, 22);
        panelsList.set(21, panel21);
        this.add(panel21);
        
        //panel 22 : delete said member 
        //need to be updated so it has its own build method
        
        //panel 23 : stats menu
        initMap.put("Most viewed film", 24);
        initMap.put("Percentage tickets per seats", 25);
        initMap.put("Most viewed Genre", 26);
        initMap.put("Discount per Screenings", 27);
        initMap.put("back", 10);
        initInfos.add("stats list\n");
        ButtonMenuPanel panel23=new ButtonMenuPanel(this, initInfos, initMap);
        panelsList.set(23,panel23);
        this.add(panel23);
        initMap.clear();
        initInfos.clear();
        
        //the stat panels (panels 23 to 26)
        //have their own build method
        //since they need to be updated
        
        pack();
    }
    public void buildPanel5()
    {
        BuyPanel panel5= new BuyPanel(this, session);
        panelsList.set(5,panel5);
        this.add(panel5);
    }
    public void buildPanel6()
    {
        Map<String, Integer> initMap= new LinkedHashMap<>();
        ArrayList<String> initInfos= new ArrayList<>();
        initMap.put("Back", 5);
        initMap.put("Buy", 0);
        initInfos.add(session.getMovieName());
        initInfos.add("room "+session.getNumberRoom());
        initInfos.add(""+customerOrder.getTicketsNumber()+" tickets");
        initInfos.add("total price: "+customerOrder.computePrice());
        JPanel panel6= new ButtonMenuPanel(this, initInfos, initMap);
        panelsList.set(6, panel6);
        this.add(panel6);
    }
    public void buildPanel8()
    {
        AgeDiscountPanel panel8= new AgeDiscountPanel(this);
        panelsList.set(8, panel8);
        this.add(panel8);
        
    }
    public void buildPanel22()
    {
        RemoveMemberPanel panel22= new RemoveMemberPanel(this);
        panelsList.set(22, panel22);
        this.add(panel22);
    }
    public void buildMemberInfoPanel(int currentPanel, int nextPanel)
    {
        Map<String, Integer> initMap= new LinkedHashMap<>();
        ArrayList<String> initInfos= new ArrayList<>();
        
        initMap.put("ok", nextPanel);
        
        initInfos.add("member infos: ");
        initInfos.add(selectedMember.getFirstName()+" "+selectedMember.getLastName());
        initInfos.add(selectedMember.getMail());
        initInfos.add("age: "+selectedMember.getCategoryMember());
        
        double totalPaid= selectedMember.getTotalPaid();
        initInfos.add("total paid: "+ totalPaid);
        
        if(totalPaid < 50)
        {
            initInfos.add("bronze member");
        }
        else if(totalPaid <100)
        {
            initInfos.add("silver member");
        }
        else
        {
            initInfos.add("gold member");
        }
        
        ButtonMenuPanel panel= new ButtonMenuPanel(this, initInfos, initMap);
        panelsList.set(currentPanel,panel);
        this.add(panel);
    }
   
    
    public void buildStatPanel(int num, String type)
    {
        StatPanel panel= new StatPanel(this, type);
        panelsList.set(num, panel);
        this.add(panel);
    }

    public CustomerMember getSelectedMember() {
        return selectedMember;
    }

    public void setSelectedMember(CustomerMember selectedMember) {
        this.selectedMember = selectedMember;
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
