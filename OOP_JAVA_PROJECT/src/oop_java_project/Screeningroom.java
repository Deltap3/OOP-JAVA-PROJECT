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
public class Screeningroom {
    
    Movie film;
    private int time;
    private int numberseat;
    private int ticketsboughts;

    public Screeningroom(Movie film, int time, int numberseat, int ticketsboughts) {
        this.film = film;
        this.time = time;
        this.numberseat = numberseat;
        this.ticketsboughts = ticketsboughts;
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
    this.numberseat=m_numberseat;
    }
    public int getNumberseat()
    {
    return this.numberseat;
    }
    
    public void setTicketsboughts(int m_ticketsboughts)
    {
    this.ticketsboughts=m_ticketsboughts;
    }
    public int getTicketsboughts()
    {
    return this.ticketsboughts;
    }
    
    
    
}
