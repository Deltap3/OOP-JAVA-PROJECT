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
public class ScreeningRoom {
    
    Movie film;
    private int time;
    private int number_seat;
    private int tickets_boughts;

    public ScreeningRoom(Movie film, int time, int number_seat, int tickets_boughts) {
        this.film = film;
        this.time = time;
        this.number_seat = number_seat;
        this.tickets_boughts = tickets_boughts;
    }
    
    public void setTime(int m_time)
    {
    this.time=m_time;
    }
    public int getTime()
    {
    return this.time;
    }
    
    public void setNumberseat(int m_numberseat)
    {
    this.number_seat=m_numberseat;
    }
    public int getNumberseat()
    {
    return this.number_seat;
    }
    
    public void setTicketsboughts(int m_ticketsboughts)
    {
    this.tickets_boughts=m_ticketsboughts;
    }
    public int getTicketsboughts()
    {
    return this.tickets_boughts;
    }
    
    
    
}
