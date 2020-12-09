/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author Juju
 */
public class DateTimePanel extends JPanel{
    
    JTextField yearField;
    JTextField monthField;
    JTextField dayField;
    JTextField hourField;
    JTextField minuteField;
    
    public DateTimePanel()
    {
        super();
        this.setLayout(new SpringLayout());
        
        //date
        JPanel datePanel= new JPanel();
        datePanel.setLayout(new SpringLayout());
        JLabel dateLabel= new JLabel("date: ");
        dateLabel.setLabelFor(datePanel);
        this.add(dateLabel);
        
        JLabel yearLabel= new JLabel("yyyy");
        yearField= new JTextField();
        yearField.setText("2020");
        yearField.setMaximumSize(yearField.getPreferredSize());
        
        JLabel monthLabel= new JLabel("mm");
        monthField= new JTextField();
        monthField.setText("12");
        monthField.setMaximumSize(monthField.getPreferredSize());
          
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
        
        this.add(datePanel);
        
        //time
        JPanel timePanel= new JPanel();
        timePanel.setLayout(new SpringLayout());
        JLabel timeLabel= new JLabel("time: ");
        timeLabel.setLabelFor(timePanel);
        this.add(timeLabel);
        
        hourField= new JTextField();
        hourField.setText("08");
        hourField.setMaximumSize(hourField.getPreferredSize());
        timePanel.add(hourField);
        
        minuteField= new JTextField();
        minuteField.setText("30");
        minuteField.setMaximumSize(minuteField.getPreferredSize());
        timePanel.add(minuteField);
        
        SpringUtilities.makeCompactGrid(timePanel,
                1, 2, //rows, cols 
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        
        this.add(timePanel);
        
        SpringUtilities.makeCompactGrid(this,
                4, 1, //rows, cols 
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
    
    public String getDateTime()
    {
        String dateTime=yearField.getText()+"-"+monthField.getText()+"-"+dayField.getText()
                    +" "+hourField.getText()+":"+minuteField.getText()+":00";
        
        return dateTime;
    }
}
