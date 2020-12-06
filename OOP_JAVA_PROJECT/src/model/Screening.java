/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */

//Class that manages all screenings
public class Screening {
    //The movie name of the screening
    private String movieName;
    //Its date
    private String dateTime;
    //Its total number of seats
    private int number_seat;
    //Its number of tickets boughts
    private int tickets_boughts;
    //It's discount
    private int discount;
    //Its room number
    private int number_room;

    public Screening(){}
    
    //Its constructor initialize all the atributes with the values passed in parameters
    public Screening(String movieName, String datetime, int number_seat, int tickets_boughts, int discount, int number_room) {
        this.movieName = movieName;
        this.dateTime = datetime;
        this.number_seat = number_seat;
        this.tickets_boughts = tickets_boughts;
        this.discount= discount;
        this.number_room=number_room;
    }
    
    //Movie name setter
    public void setMovieName(String m_id_movie)
    {
    this.movieName=m_id_movie;
    }
    
    //Movie name getter
    public String getMovieName()
    {
    return this.movieName;
    }
    
    //Date setter
    public void setDateTime(String m_time)
    {
    this.dateTime=m_time;
    }
    
    //Date getter
    public String getDateTime()
    {
    return this.dateTime;
    }
    
    //Number of seats setter 
    public void setNumberseat(int m_number_seat)
    {
    this.number_seat=m_number_seat;
    }
    
    //Number of seats getter 
    public int getNumberseat()
    {
    return this.number_seat;
    }
    
    //Tickets boughts setter 
    public void setTicketsBoughts(int m_tickets_boughts)
    {
    this.tickets_boughts=m_tickets_boughts;
    }
    
    //Tickets boughts getter 
    public int getTicketsBoughts()
    {
    return this.tickets_boughts;
    }
    
    //Discount setter 
    public void setDiscount(int m_discount)
    {
    this.discount=m_discount;
    }
    
    //Discount getter 
    public int getDiscount()
    {
    return this.discount;
    }
    
    //Room number setter 
    public void setNumberRoom(int m_number_room)
    {
    this.number_room=m_number_room;
    }
    
    //Room number getter
    public int getNumberRoom()
    {
    return this.number_room;
    }
}
