/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Juju
 * panel used to select a date and time
 */
public class DateTimePanel extends JPanel{
    //attributes
    private JTextField yearField;
    private JTextField monthField;
    private JTextField dayField;
    private JTextField hourField;
    private JTextField minuteField;
    
    /**
     * no necessary parameter
     */
    public DateTimePanel()
    {
        super();
        this.setLayout(new SpringLayout());
        
        //date
        JPanel datePanel= new JPanel();
        
        TitledBorder dateBorder= BorderFactory.createTitledBorder("date");
        datePanel.setBorder(dateBorder);
        
        datePanel.setLayout(new SpringLayout());
        //year
        JLabel yearLabel= new JLabel("yyyy");
        yearField= new JTextField();
        yearField.setText("2020");
        yearField.setMaximumSize(yearField.getPreferredSize());
        //month
        JLabel monthLabel= new JLabel("mm");
        monthField= new JTextField();
        monthField.setText("12");
        monthField.setMaximumSize(monthField.getPreferredSize());
        //day  
        JLabel dayLabel= new JLabel("dd"); 
        dayField= new JTextField();
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
        datePanel.setBackground(Color.white);
        
        this.add(datePanel);
        
        //time
        JPanel timePanel= new JPanel();
        timePanel.setLayout(new SpringLayout());
        
        TitledBorder timeBorder= BorderFactory.createTitledBorder("time");
        timePanel.setBorder(timeBorder);
        timePanel.setBackground(Color.white);

        //hour
        hourField= new JTextField();
        hourField.setText("08");
        hourField.setMaximumSize(hourField.getPreferredSize());
        timePanel.add(hourField);
        //minute
        minuteField= new JTextField();
        minuteField.setText("30");
        minuteField.setMaximumSize(minuteField.getPreferredSize());
        timePanel.add(minuteField);
        
        SpringUtilities.makeCompactGrid(timePanel,
                1, 2, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        
        this.add(timePanel);
        this.setBackground(Color.white);
        
        SpringUtilities.makeCompactGrid(this,
                2, 1, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
    }

    public JTextField getYearField() {
        return yearField;
    }

    public JTextField getMonthField() {
        return monthField;
    }

    public JTextField getDayField() {
        return dayField;
    }

    public JTextField getHourField() {
        return hourField;
    }

    public JTextField getMinuteField() {
        return minuteField;
    }
    
    /**
     *
     * @return dateTime: the current dateTime
     * entered in the textFields
     * it is already formated 
     */
    public String getDateTime()
    {
        String dateTime=yearField.getText()+"-"+monthField.getText()+"-"+dayField.getText()
                    +" "+hourField.getText()+":"+minuteField.getText()+":00";
        
        return dateTime;
    }
}
