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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import model.Connections;
import model.Movie;
import org.jfree.ui.DateChooserPanel;

/**
 *
 * @author Juju
 */
public class CreateScreeningPanel extends JPanel{

    public CreateScreeningPanel(MainFrame frame) {
        
        super();
      
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.setPreferredSize(new Dimension((width/2)+10, (height/2)+10));
        this.setSize(new Dimension((width/2)+10, (height/2)+10));
        
       
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
            btn.setBackground(Color.white);
            group.add(btn);
            titlePanel.add(btn);     
        
        }
        
        }
        catch (SQLException | ClassNotFoundException ex) 
        {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        }
        titlePanel.setBorder(new TitledBorder("movie "));
        titlePanel.setBackground(Color.white);
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
        
        roomPanel.setBorder(new TitledBorder("room choice"));
        roomPanel.setBackground(Color.white);
        this.add(roomPanel);
        
        //date and time
        DateTimePanel dateTimePanel= new DateTimePanel();
        this.add(dateTimePanel);
        
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
        
        discountPanel.setBorder(new TitledBorder("discount "));
        
        discountPanel.setBackground(Color.white);
        this.add(discountPanel);
        
        //navigation buttons
        JPanel buttonPanel= new JPanel();
        JButton backButton= new JButton("BACK");
        backButton.addActionListener(new ChangePanelListener(frame, 11));
        buttonPanel.add(backButton);
        JButton addButton= new JButton("ADD");
        addButton.addActionListener(new AddScreeningListener(frame, movieLabel, roomNumberList, numberOfSeatsLabel, dateTimePanel, discountField));
        buttonPanel.add(addButton);
        buttonPanel.setBackground(Color.white);
        this.add(buttonPanel);
        
        
        SpringUtilities.makeCompactGrid(this,
                5, 1, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        this.setBackground(Color.white);
    }
    
    
}
