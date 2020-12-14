/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import DAO.ScreeningDAO;
import controller.ChangePanelListener;
import controller.RemoveScreeningListener;
import controller.ScreeningChoiceListener;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import model.Connections;
import model.Screening;

/**
 * panel that removes a screening session
 * from the database
 */
public class RemoveScreeningPanel extends JPanel{

    //attribute
    private Screening selectedSession;
    
    /**
     * constructor
     * @param frame the main frame
     */
    public RemoveScreeningPanel(MainFrame frame) {
        
        super();
        this.setLayout(new SpringLayout());
        this.setBackground(Color.white);
        
        //panel with all the sessions
        JPanel screeningPanel= new JPanel();
        screeningPanel.setBackground(Color.white);
        
        screeningPanel.setLayout(new SpringLayout());
        selectedSession=null;
        ButtonGroup group= new ButtonGroup();
        ArrayList<Screening> sessions= new ArrayList<>();
        
        try{
         //connection with the database   
        Connections con= new Connections("project", "root", "password");
        ScreeningDAO screnningCo= new ScreeningDAO(con.getInstance());
        sessions=screnningCo.getAllScreening();//will be replaced later
        
        
        //for each session
        for(Screening s: sessions)
        {
   
            JRadioButton btn= new JRadioButton(s.getDateTime());
            btn.addActionListener(new ScreeningChoiceListener(this,s));
            btn.setBackground(Color.white);
            group.add(btn);
            screeningPanel.add(btn);     
            screeningPanel.add(new JLabel("room "+s.getNumberRoom()));
            screeningPanel.add(new JLabel(s.getMovieName()));
            
        }
        
        }
        catch (SQLException | ClassNotFoundException ex) 
        {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        SpringUtilities.makeCompactGrid(screeningPanel,
                sessions.size(), 3, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        
        this.add(screeningPanel);
        
        //navigation buttons
        JPanel buttonPanel= new JPanel();
        buttonPanel.setBackground(Color.white);
        JButton backButton= new JButton("BACK");
        backButton.addActionListener(new ChangePanelListener(frame, 11));
        buttonPanel.add(backButton);
        
        JButton deleteButton= new JButton("DELETE");
        deleteButton.addActionListener(new RemoveScreeningListener(frame, this));
        buttonPanel.add(deleteButton);
        
        this.add(buttonPanel);
        this.setSize(new Dimension(500,700));
        
        SpringUtilities.makeCompactGrid(this,
                2, 1, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
    }

    //getters and setters
    public Screening getSelectedSession() {
        return selectedSession;
    }
    public void setSelectedSession(Screening selectedSession) {
        this.selectedSession = selectedSession;
    }
    
    
    
    
}
