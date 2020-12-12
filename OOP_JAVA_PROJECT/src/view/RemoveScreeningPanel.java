/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.MovieDAO;
import DAO.ScreeningDAO;
import controller.ChangePanelListener;
import controller.RemoveScreeningListener;
import controller.ScreeningChoiceListener;
import java.awt.Dimension;
import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import model.Connections;
import model.Movie;
import model.Screening;

/**
 *
 * @author Juju
 */
public class RemoveScreeningPanel extends JPanel{

    private Screening selectedSession;
    
    public RemoveScreeningPanel(MainFrame frame) {
        
        super();
        this.setLayout(new SpringLayout());
        
        JPanel screeningPanel= new JPanel();
        screeningPanel.setLayout(new SpringLayout());
        selectedSession=null;
        ButtonGroup group= new ButtonGroup();
        ArrayList<Screening> sessions= new ArrayList<>();
        
        try{
            
        Connections con= new Connections("project", "root", "password");
        ScreeningDAO screnningCo= new ScreeningDAO(con.getInstance());
        sessions=screnningCo.getAllScreening();//will be replaced later
        
        
        
        for(Screening s: sessions)
        {
   
            JRadioButton btn= new JRadioButton(s.getDateTime());
            btn.addActionListener(new ScreeningChoiceListener(this,s));
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
        
        JPanel buttonPanel= new JPanel();
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

    public Screening getSelectedSession() {
        return selectedSession;
    }

    public void setSelectedSession(Screening selectedSession) {
        this.selectedSession = selectedSession;
    }
    
    
    
    
}
