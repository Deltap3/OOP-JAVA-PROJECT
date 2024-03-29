/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
package view;
import model.*;
import DAO.*;
import controller.BuyActionListener;
import controller.ChangePanelListener;
import java.awt.Color;

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
 * panel that display the infos on the chosen
 * screening session and
 * let the user select how many tickets
 * he wants to buy
 */
public class BuyPanel extends JPanel{
    //attribut
    private JTextField nbTicketsField;

    /**
     * constructeur
     * @param frame the main frame
     * @param session selected session
     */
    public BuyPanel(MainFrame frame, Screening session)
    {
        super();
        //intermediate content panel
        JPanel contentPanel= new JPanel();
        
        contentPanel.setLayout(new SpringLayout());
        contentPanel.setSize(new Dimension(500,700));
        
        //informations on the screening session
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
        
        //1st row
        JLabel titleLabel= new JLabel(chosenMovie.getTitle());
        contentPanel.add(titleLabel);
        JLabel genrelabel= new JLabel(chosenMovie.getGenre());
        contentPanel.add(genrelabel);
        //2nd row
        JLabel startLabel= new JLabel(session.getDateTime());
        contentPanel.add(startLabel);
        JLabel runTimeLabel= new JLabel("run time: "+chosenMovie.getRunTime()+" min");
        contentPanel.add(runTimeLabel);
        //3rd row
        JLabel roomLabel= new JLabel("room "+session.getNumberRoom());
        contentPanel.add(roomLabel);
        JLabel discountLabel= new JLabel("");
        if(session.getDiscount()>0)
        {
            discountLabel.setText("member only extra "+session.getDiscount()+"% discount");
        }
        contentPanel.add(discountLabel);
        
        //4th row: number of tickets choice
        JLabel nbTicketsLabel=new JLabel("number of tickets: ");
        nbTicketsField= new JTextField();
        nbTicketsField.setMaximumSize(nbTicketsField.getPreferredSize());
        nbTicketsLabel.setLabelFor(nbTicketsField);
        contentPanel.add(nbTicketsLabel);
        contentPanel.add(nbTicketsField);
        
        //last row: buy/ go back
        JButton buyButton= new JButton("Buy");
        buyButton.addActionListener(new BuyActionListener(frame, session, nbTicketsField));
        contentPanel.add(buyButton);
        JButton backButton= new JButton("Back");
        backButton.addActionListener(new ChangePanelListener(frame, 4));
        
        contentPanel.add(backButton);
        
        //for the layout
        SpringUtilities.makeCompactGrid(contentPanel,
                5, 2, //rows, cols 
                6,6, //initX, initY
                6, 6); //xPad, yPad
        
        contentPanel.setBackground(Color.white);
        this.add(contentPanel);
        
        this.setSize(new Dimension(500,700));
        this.setPreferredSize(new Dimension(500,700));
        
        
    }
    
    
    
}
