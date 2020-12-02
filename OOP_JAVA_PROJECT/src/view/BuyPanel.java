/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import model.*;
import DAO.*;
import controller.BuyActionListener;
import controller.ChangePanelListener;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
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
            
        Connections con= new Connections("project", "root", "password");
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
        JLabel roomLabel= new JLabel("room "+session.getNumberRoom());
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
        buyButton.addActionListener(new BuyActionListener(frame, session, nbTicketsField));
        this.add(buyButton);
        JButton backButton= new JButton("Back");
        backButton.addActionListener(new ChangePanelListener(frame, 4));
        
        this.add(backButton);
        
        SpringUtilities.makeCompactGrid(this,
                5, 2, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
    }
    
    
    
}
