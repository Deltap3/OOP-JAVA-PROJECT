/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Juju
 */
public class Order {
    
    private final double unitPrice;
    private int ticketsNumber;
    private Screening session;
    private Person customer;

    public Order() {
        unitPrice=7.50;
    }

    
    public double getUnitPrice() {
        return unitPrice;
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
