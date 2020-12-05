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
public class Order {
    
    private final double unitPrice;
    private int ticketsNumber;
    private Screening session;
    private Person customer;

    public Order() {
        unitPrice=7.50;
        ticketsNumber=0;
        customer=new CustomerGuest();
        session=new Screening();
    }

    public double computePrice()
    {
        double totalPrice= unitPrice*ticketsNumber;
        
        if(customer.isMember())
        {
            //first we apply the special discount
            //(discount that applies to the screening session)
            int specialDiscount=session.getDiscount();
            totalPrice=totalPrice-(totalPrice*specialDiscount/100);
            
            //then we apply the usual member discount
            int categoryDiscount=0;
            int age=customer.getCategoryMember();
            if(age<13)
            {
               categoryDiscount=20; 
            }
            else if(age>59)
            {
                categoryDiscount=15;
            }
            totalPrice=totalPrice-(totalPrice*categoryDiscount/100);
            
        }
        return totalPrice;
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
