/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.MovieDAO;
import controller.AddScreeningListener;
import controller.ChangePanelListener;
import controller.ScreeningChoiceListener;
import controller.RoomAndSeatsListener;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import model.Connections;
import model.Movie;

/**
 *
 * @author Juju
 */
public class CreateScreeningPanel extends JPanel{

    public CreateScreeningPanel(MainFrame frame) {
        
        super();
      
       // setSize(new Dimension(1200,1400));
      //  setPreferredSize(new Dimension(1200,1400));
        this.setLayout(new SpringLayout());
        
        //movie selection
        JPanel titlePanel= new JPanel(new GridLayout(0,3));
        JLabel movieLabel= new JLabel();
        ButtonGroup group= new ButtonGroup();
        ArrayList<Movie> movies= new ArrayList<>();
        try{
            
        Connections con= new Connections("project", "root", "password");
        MovieDAO movieCo= new MovieDAO(con.getInstance());
        movies=movieCo.getAllMovie();
        
        for(Movie m: movies)
        {
 
            JRadioButton btn= new JRadioButton(m.getTitle());
            btn.addActionListener(new ScreeningChoiceListener(movieLabel));
            
            group.add(btn);
            titlePanel.add(btn);     
        
        }
        
        }
        catch (SQLException | ClassNotFoundException ex) 
        {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.add(titlePanel);
        
        //room number and total seat number
        JPanel roomPanel= new JPanel();
        roomPanel.setLayout(new SpringLayout());
        JLabel roomLabel= new JLabel("room number: ");
        String[] roomNumberStrings= {"1","2","3"};
        JComboBox roomNumberList = new JComboBox(roomNumberStrings);
        roomNumberList.setMaximumSize(roomNumberList.getPreferredSize());
        roomNumberList.setSelectedIndex(0);
        roomLabel.setLabelFor(roomNumberList);
        //this label will be updated
        JLabel label0= new JLabel("number of seats: ");
        JLabel numberOfSeatsLabel= new JLabel("100");
        roomNumberList.addActionListener(new RoomAndSeatsListener(roomNumberList, numberOfSeatsLabel));
        
        roomPanel.add(roomLabel);
        roomPanel.add(roomNumberList);
        roomPanel.add(label0);
        roomPanel.add(numberOfSeatsLabel);
        
        SpringUtilities.makeCompactGrid(roomPanel,
                2, 2, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        
        this.add(roomPanel);
        
        //date
        JPanel datePanel= new JPanel();
        datePanel.setLayout(new SpringLayout());
        JLabel dateLabel= new JLabel("date: ");
        dateLabel.setLabelFor(datePanel);
        this.add(dateLabel);
        
        JLabel yearLabel= new JLabel("yyyy");
        JTextField yearField= new JTextField();
        yearField.setText("2020");
        yearField.setMaximumSize(yearField.getPreferredSize());
        
        JLabel monthLabel= new JLabel("mm");
        JTextField monthField= new JTextField();
        monthField.setText("12");
        monthField.setMaximumSize(monthField.getPreferredSize());
          
        JLabel dayLabel= new JLabel("dd"); 
        JTextField dayField= new JTextField();
        dayField.setText("15");
        dayField.setMaximumSize(dayField.getPreferredSize());
        
        
        datePanel.add(yearLabel);
        datePanel.add(monthLabel);
        datePanel.add(dayLabel);
        datePanel.add(yearField);
        datePanel.add(monthField);
        datePanel.add(dayField);
        
        SpringUtilities.makeCompactGrid(datePanel,
                2, 3, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        this.add(datePanel);
        
        //time
        JPanel timePanel= new JPanel();
        timePanel.setLayout(new SpringLayout());
        JLabel timeLabel= new JLabel("time: ");
        timeLabel.setLabelFor(timePanel);
        this.add(timeLabel);
        
        JTextField hourField= new JTextField();
        hourField.setText("08");
        hourField.setMaximumSize(hourField.getPreferredSize());
        timePanel.add(hourField);
        
        JTextField minuteField= new JTextField();
        minuteField.setText("30");
        minuteField.setMaximumSize(minuteField.getPreferredSize());
        timePanel.add(minuteField);
        
        SpringUtilities.makeCompactGrid(timePanel,
                1, 2, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        
        this.add(timePanel);
        
        //discount
        JPanel discountPanel= new JPanel();
        discountPanel.setLayout(new SpringLayout());
        JLabel label1= new JLabel("discount ");
        discountPanel.add(label1);
        
        JLabel label2= new JLabel("(member only)");
        discountPanel.add(label2);
        
        JTextField discountField= new JTextField();
        discountField.setText("0");
        discountField.setMaximumSize(discountField.getPreferredSize());
        discountPanel.add(discountField);

        JLabel label3= new JLabel(" % ");
        discountPanel.add(label3);
        
        SpringUtilities.makeCompactGrid(discountPanel,
                2, 2, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        
        this.add(discountPanel);
        
        //navigation buttons
        JPanel buttonPanel= new JPanel();
        JButton backButton= new JButton("BACK");
        backButton.addActionListener(new ChangePanelListener(frame, 11));
        buttonPanel.add(backButton);
        JButton addButton= new JButton("ADD");
        addButton.addActionListener(new AddScreeningListener(frame, movieLabel, roomNumberList, numberOfSeatsLabel, yearField, monthField, dayField, hourField, minuteField, discountField));
        buttonPanel.add(addButton);
        
        this.add(buttonPanel);
        
        SpringUtilities.makeCompactGrid(this,
                8, 1, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        
    }
    
    
}
