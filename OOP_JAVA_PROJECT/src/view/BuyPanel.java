/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import DAO.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author Juju
 */
public class BuyPanel extends JPanel{
    
    private JTextField nbTicketsField;
    public BuyPanel(MainFrame frame, Screening session)
    {
        super();
        setLayout(new SpringLayout());
        setSize(new Dimension(500,700));
        
        Movie chosenMovie = new Movie();
        
        
        try{   
            
        Connections con= new Connections("project", "root", "projetJava2020");
        MovieDAO movieCo= new MovieDAO(con.getInstance());
        chosenMovie= movieCo.find(session.getMovieName());
        }
        catch (SQLException | ClassNotFoundException ex) 
        {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(chosenMovie.getTitle());
        //1st row
        JLabel titleLabel= new JLabel(chosenMovie.getTitle());
        this.add(titleLabel);
        JLabel genrelabel= new JLabel(chosenMovie.getGenre());
        this.add(genrelabel);
        //2nd row
        JLabel startLabel= new JLabel(session.getDateTime());
        this.add(startLabel);
        JLabel runTimeLabel= new JLabel("run time: "+chosenMovie.getRunTime()+" min");
        this.add(runTimeLabel);
        //3rd row
        JLabel roomLabel= new JLabel("room ");
        this.add(roomLabel);
        JLabel discountLabel= new JLabel("");
        if(session.getDiscount()>0)
        {
            discountLabel.setText("member only extra "+session.getDiscount()+"% discount");
        }
        this.add(discountLabel);
        
        //4th row: number of tickets choice
        JLabel nbTicketsLabel=new JLabel("number of tickets: ");
        nbTicketsField= new JTextField();
        nbTicketsField.setMaximumSize(nbTicketsField.getPreferredSize());
        nbTicketsLabel.setLabelFor(nbTicketsField);
        this.add(nbTicketsLabel);
        this.add(nbTicketsField);
        
        //last row: buy/ go back
        JButton buyButton= new JButton("Buy");
        buyButton.addActionListener(new BuyActionListener(frame, session));
        this.add(buyButton);
        JButton backButton= new JButton("Back");
        backButton.addActionListener(new BackActionListener(frame));
        this.add(backButton);
        
        SpringUtilities.makeCompactGrid(this,
                5, 2, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
    }
    
    public class BackActionListener implements ActionListener {

        private MainFrame myFrame;

        public BackActionListener(MainFrame frame) {
            myFrame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            myFrame.setSize(myFrame.getPanels().get(4).getSize());
            myFrame.setContentPane(myFrame.getPanels().get(4));
            invalidate();
            validate();
        }
    }
    
    public class BuyActionListener implements ActionListener
    {
        private MainFrame myFrame;
        private Screening session;
        
        public BuyActionListener(MainFrame frame, Screening session)
        {
            myFrame=frame;
            this.session=session;
        }
        
        public void actionPerformed(ActionEvent e)
        {           
            try{
                
            String str= nbTicketsField.getText();
            int ticketsNumber=Integer.parseInt(str);
            
            if(session.getNumberseat()<(session.getTicketsBoughts()+ticketsNumber))
            {
               throw new IllegalArgumentException("you cannot buy "+ticketsNumber+" tickets for this screening session"); 
            }
            else{
                
            Order customOrder=new Order();  
            customOrder.setTicketsNumber(ticketsNumber);
            customOrder.setCustomer(myFrame.getUser());
            customOrder.setSession(session);
            myFrame.setCustomerOrder(customOrder);
            
            myFrame.setSize(myFrame.getPanels().get(6).getSize());
            myFrame.setContentPane(myFrame.getPanels().get(6));
            
            }
            
            }         
            catch(IllegalArgumentException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);

            }
            invalidate();
            validate();
            repaint();
        }
    }
}
