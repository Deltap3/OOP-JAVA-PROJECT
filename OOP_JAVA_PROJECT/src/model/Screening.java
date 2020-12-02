/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author davidzhong
 */
public class Screening {
    
    private String movieName;
    private String dateTime;
    private int number_seat;
    private int tickets_boughts;
    private int discount;
    private int number_room;

    public Screening(){}
    public Screening(String movieName, String datetime, int number_seat, int tickets_boughts, int discount, int number_room) {
        this.movieName = movieName;
        this.dateTime = datetime;
        this.number_seat = number_seat;
        this.tickets_boughts = tickets_boughts;
        this.discount= discount;
        this.number_room=number_room;
    }
    
    public void setMovieName(String m_id_movie)
    {
    this.movieName=m_id_movie;
    }
    public String getMovieName()
    {
    return this.movieName;
    }
    
    public void setDateTime(String m_time)
    {
    this.dateTime=m_time;
    }
    public String getDateTime()
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
    
    public void setTicketsBoughts(int m_tickets_boughts)
    {
    this.tickets_boughts=m_tickets_boughts;
    }
    public int getTicketsBoughts()
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
    
     public void setNumberRoom(int m_number_room)
    {
    this.number_room=m_number_room;
    }
    public int getNumberRoom()
    {
    return this.number_room;
    }
    
    
}
