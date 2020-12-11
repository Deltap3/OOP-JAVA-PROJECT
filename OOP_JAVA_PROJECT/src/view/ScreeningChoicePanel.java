/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import model.*;
import DAO.*;
import controller.ChangePanelListener;
import controller.ScreeningChoiceListener;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
public class ScreeningChoicePanel extends JPanel{
    
    public ScreeningChoicePanel(MainFrame frame, int nextPanel)
    {
        super();
      
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.setPreferredSize(new Dimension(width/2, height/2));
        
        JPanel screeningPanel= new JPanel();
        screeningPanel.setSize(1200,1400);
        screeningPanel.setPreferredSize(new Dimension(1200,1400));
        screeningPanel.setLayout(new SpringLayout());

        ArrayList<Screening> sessions= new ArrayList<>();
        
        try{
            
        Connections con= new Connections("project", "root", "password");
        ScreeningDAO screnningCo= new ScreeningDAO(con.getInstance());
        sessions=screnningCo.getAllScreening();//will be replaced later
        
        MovieDAO movieCo= new MovieDAO(con.getInstance());
        
        for(Screening s: sessions)
        {
            
            
            final Movie m= movieCo.find(s.getMovieName());
            Image scaledImage = m.getImage().getScaledInstance(screeningPanel.getWidth()/6, screeningPanel.getHeight()/(int)(sessions.size()*1.2),Image.SCALE_SMOOTH);
            JButton btn= new JButton(new ImageIcon(scaledImage));
            btn.addActionListener(new ScreeningChoiceListener(frame, nextPanel,s));
            screeningPanel.add(btn);     
            screeningPanel.add(new JLabel(s.getMovieName()));
            screeningPanel.add(new JLabel(s.getDateTime()));
            
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
        
        JScrollPane scrollPanel= new JScrollPane(screeningPanel);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension((width/2), (height/3)));
        this.add(scrollPanel);
        
        
        JPanel panel2= new JPanel();
        JButton backButton=new JButton("Back");
        backButton.addActionListener(new ChangePanelListener(frame, 1));
        panel2.add(backButton);
        this.add(panel2);
                
    }
    
    
    
    
   
}
