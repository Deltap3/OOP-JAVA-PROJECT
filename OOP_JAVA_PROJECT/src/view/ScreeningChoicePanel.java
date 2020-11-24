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
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Juju
 */
public class ScreeningChoicePanel extends JPanel{
    
    public ScreeningChoicePanel(MainFrame frame, int nextPanel)
    {
        super();
        setSize(new Dimension(1200,1400));
        setPreferredSize(new Dimension(1200,1400));
        setLayout(new SpringLayout());
        
        ButtonGroup group= new ButtonGroup();
        ArrayList<Screening> sessions= new ArrayList<>();
        
        try{
            
        Connections con= new Connections("project", "root", "projetJava2020");
        ScreeningDAO screnningCo= new ScreeningDAO(con.getInstance());
        sessions=screnningCo.getAllScreening();//will be replaced later
        
        MovieDAO movieCo= new MovieDAO(con.getInstance());
        
        for(Screening s: sessions)
        {
            
            
            final Movie m= movieCo.find(s.getMovieName());
            Image scaledImage = m.getImage().getScaledInstance(this.getWidth()/6,this.getHeight()/(sessions.size()*2),Image.SCALE_SMOOTH);
            JButton btn= new JButton(new ImageIcon(scaledImage));
            btn.addActionListener(new ChoiceActionListener(frame, nextPanel,s));
            this.add(btn);     
            this.add(new JLabel(s.getMovieName()));
            this.add(new JLabel(s.getDateTime()));
            
        }
        
        }
        catch (SQLException | ClassNotFoundException ex) 
        {
            Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        SpringUtilities.makeCompactGrid(this,
                sessions.size(), 3, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
    }
    
    
    public class ChoiceActionListener implements ActionListener{
        private MainFrame frame;
        private int numPanel;
        private Screening session;
        
        public ChoiceActionListener(MainFrame frame, int numPanel, Screening session)
        {
            this.frame=frame;
            this.numPanel=numPanel;
            this.session=session;
        }
        
        public void actionPerformed(ActionEvent e)
        {
            frame.setSession(session);
            if(numPanel==5)
            {
                frame.getPanels().set(numPanel, new BuyPanel(frame, session));
            }
            frame.setSize(frame.getPanels().get(numPanel).getSize());
            frame.setContentPane(frame.getPanels().get(numPanel));
            invalidate();
            validate();
            repaint();
        }
    }
    
   
}