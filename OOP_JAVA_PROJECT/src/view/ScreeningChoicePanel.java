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
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
/**
 *
 * @author Juju
 */
public class ScreeningChoicePanel extends JPanel{
    
    public ScreeningChoicePanel(MainFrame frame, int nextPanel)
    {
        super();
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
            this.add(new JPanel(){
                
                public void paintComponent(Graphics g)
                {
                  super.paintComponent(g);
                  //drawing poster
                  g.drawImage(m.getImage(), 0, 0, this);
                  repaint();
                }
            });     
            
            JRadioButton choiceButton=new JRadioButton(s.getMovieName());
            
            choiceButton.addActionListener(new ChoiceActionListener(frame, nextPanel));
            group.add(choiceButton);
            this.add(choiceButton);
            
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
        
        public ChoiceActionListener(MainFrame frame, int numPanel)
        {
            this.frame=frame;
            this.numPanel=numPanel;
        }
        
        public void actionPerformed(ActionEvent e)
        {
            frame.setContentPane(frame.getPanels().get(numPanel));
            invalidate();
            validate();
        }
    }
    
   
}
