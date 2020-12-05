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

//Classe qui gère toutes les scéances
public class Screening {
    //Une scéance est définie par
    //Le nom du film de la scéance
    private String movieName;
    //Sa date
    private String dateTime;
    //Son nombre de place disponibles
    private int number_seat;
    //Le nombre de tickets achetés
    private int tickets_boughts;
    //La réduction de la scéance
    private int discount;
    //Le numéro de la salle
    private int number_room;

    public Screening(){}
    
    //Son contructeur initialse toutes les variables à celles passées en paramètre
    public Screening(String movieName, String datetime, int number_seat, int tickets_boughts, int discount, int number_room) {
        this.movieName = movieName;
        this.dateTime = datetime;
        this.number_seat = number_seat;
        this.tickets_boughts = tickets_boughts;
        this.discount= discount;
        this.number_room=number_room;
    }
    
    //Setter du titre du film
    public void setMovieName(String m_id_movie)
    {
    this.movieName=m_id_movie;
    }
    
    //Getter du titre du film
    public String getMovieName()
    {
    return this.movieName;
    }
    
    //Setter de la date 
    public void setDateTime(String m_time)
    {
    this.dateTime=m_time;
    }
    
    //Getter de la date 
    public String getDateTime()
    {
    return this.dateTime;
    }
    
    //Setter du nombre de places disponibles 
    public void setNumberseat(int m_number_seat)
    {
    this.number_seat=m_number_seat;
    }
    
    //Getter du nombre de places disponibles 
    public int getNumberseat()
    {
    return this.number_seat;
    }
    
    //Setter du nombre de tickets achetés
    public void setTicketsBoughts(int m_tickets_boughts)
    {
    this.tickets_boughts=m_tickets_boughts;
    }
    
    //Getter du nombre de tickets achetés
    public int getTicketsBoughts()
    {
    return this.tickets_boughts;
    }
    
    //Setter de la réduction de la scéance
    public void setDiscount(int m_discount)
    {
    this.discount=m_discount;
    }
    
    //Getter de la réduction de la scéance
    public int getDiscount()
    {
    return this.discount;
    }
    
    //Setter du numéro de la salle
    public void setNumberRoom(int m_number_room)
    {
    this.number_room=m_number_room;
    }
    
    //Getter du numéro de la salle
    public int getNumberRoom()
    {
    return this.number_room;
    }
}
