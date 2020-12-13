/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.MovieDAO;
import controller.AddScreeningListener;
import controller.ChangePanelListener;
import controller.MovieDiscountListener;
import controller.ScreeningChoiceListener;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import model.Connections;
import model.Movie;

/**
 *
 * @author Juju
 */
public class MovieDiscountPanel extends JPanel{
    
    public MovieDiscountPanel(MainFrame frame)
    {
        super();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.setPreferredSize(new Dimension(width/2, height/2));
        this.setSize(new Dimension(width/2, height/2));
        
        this.setLayout(new SpringLayout());
        
        //movie selection
        JPanel titlePanel= new JPanel(new GridLayout(0,3));
        titlePanel.setBorder(new TitledBorder("movie: "));
        titlePanel.setBackground(Color.white);
        //this label will stay hidden
        //it is there to store the title of the
        //selected movie
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
        this.add(titlePanel);
        
        //discount selection
        JPanel discountPanel= new JPanel();
        discountPanel.setLayout(new SpringLayout());
        discountPanel.setBackground(Color.white);
        discountPanel.setBorder(new TitledBorder("new discount "));
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
        buttonPanel.setBackground(Color.white);
        JButton backButton= new JButton("BACK");
        backButton.addActionListener(new ChangePanelListener(frame, 14));
        buttonPanel.add(backButton);
        JButton addButton= new JButton("APPLY DISCOUNT");
        addButton.addActionListener(new MovieDiscountListener(frame, movieLabel, discountField));
        buttonPanel.add(addButton);
        
        this.add(buttonPanel);
        
        SpringUtilities.makeCompactGrid(this,
                3, 1, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        this.setBackground(Color.white);
    }
}
