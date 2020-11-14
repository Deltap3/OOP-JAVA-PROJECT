/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_java_project;

/**
 *
 * @author davidzhong
 */
public class Screening {
    
    Movie film;
    private String dateTime;
    private int number_seat;
    private int tickets_boughts;
    private int discount;

    public Screening(Movie film, String time, int number_seat, int tickets_boughts, int discount) {
        this.film = film;
        this.dateTime = time;
        this.number_seat = number_seat;
        this.tickets_boughts = tickets_boughts;
        this.discount= discount;
    }
    
    public void setTime(String m_time)
    {
    this.dateTime=m_time;
    }
    public String getTime()
    {
    return this.dateTime;
    }
    
    public void setNumberseat(int m_number_seat)
    {
    this.number_seat=m_number_seat;
    }
    public int getNumberseat()
    {
    return this.number_seat;
    }
    
    public void setTicketsboughts(int m_tickets_boughts)
    {
    this.tickets_boughts=m_tickets_boughts;
    }
    public int getTicketsboughts()
    {
    return this.tickets_boughts;
    }
    
    public void setDiscount(int m_discount)
    {
    this.discount=m_discount;
    }
    public int getDiscount()
    {
    return this.discount;
    }
    
    
}
