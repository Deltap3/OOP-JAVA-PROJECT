/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
public class Order {
    
    private final double unitPrice;
    private int ticketsNumber;
    private ArrayList<Integer> ageDiscounts;
    private Screening session;
    private Person customer;

    public Order() {
        
        unitPrice=7.50;
        ticketsNumber=0;
        customer=new CustomerGuest();
        session=new Screening();
        ageDiscounts= new ArrayList<>();
        
        for(int i=0;i<ticketsNumber;i++)
            ageDiscounts.add(0);
    }

    public Order(int ticketsNumber, Screening session, Person customer) {
        
        this.ticketsNumber = ticketsNumber;
        this.session = session;
        this.customer = customer;
        
        unitPrice=7.50;
        ageDiscounts= new ArrayList<>();
        
        for(int i=0;i<ticketsNumber;i++)
            ageDiscounts.add(0);
    }



    public double computePrice()
    {
        double totalPrice= 0.0;
        
        //first we apply the individual age discount on each ticket
        for(int disc :ageDiscounts)
        {
            double ticketPrice=unitPrice;
            ticketPrice-= ticketPrice*disc/100;
            totalPrice+=ticketPrice;
        }
        
        
        if(customer.isMember())
        {
            //then we apply the special discount
            //(discount that applies to the screening session)
            //this discount is only available if you are a member
            int specialDiscount=session.getDiscount();
            totalPrice-=totalPrice*specialDiscount/100;
          
        }
        
        return totalPrice;
    }
    public double getUnitPrice() {
        return unitPrice;
    }

    public ArrayList<Integer> getAgeDiscounts() {
        return ageDiscounts;
    }


    public int getTicketsNumber() {
        return ticketsNumber;
    }

    public void setTicketsNumber(int ticketsNumber) {
        this.ticketsNumber = ticketsNumber;
    }

    public Screening getSession() {
        return session;
    }

    public void setSession(Screening session) {
        this.session = session;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }
    
    
}
