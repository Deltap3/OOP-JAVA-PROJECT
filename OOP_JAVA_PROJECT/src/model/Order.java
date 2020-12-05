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

//Class that manages the orders
public class Order {
    
    //Unit price of each tickets
    private final double unitPrice;
    //The total number of tickets
    private int ticketsNumber;
    //A screening 
    private Screening session;
    //The person that who makes the order
    private Person customer;

    //The constructor initialize the unite price at 7.50€ and 0 tickets sold
    public Order() {
        unitPrice=7.50;
        ticketsNumber=0;
        customer=new CustomerGuest();
        session=new Screening();
    }

    //Compute the total price of the order with all the discounts
    public double computePrice()
    {
        //The total price is the number of tickets multiplied by the unit price of each tickets
        double totalPrice= unitPrice*ticketsNumber;
        //If the person who makes the order is a member
        if(customer.isMember())
        {
            //We apply the special discount
            //(the screening discount)
            int specialDiscount=session.getDiscount();
            totalPrice=totalPrice-(totalPrice*specialDiscount/100);
            
            //Then we apply the member discount
            int categoryDiscount=0;
            int age=customer.getCategoryMember();
            //We apply 20% discount for children
            if(age<13)
            {
               categoryDiscount=20; 
            }
            //And 15% discount for seniors
            else if(age>59)
            {
                categoryDiscount=15;
            }
            //We calculate the total price with discounts
            totalPrice=totalPrice-(totalPrice*categoryDiscount/100);
        }
        //We return the total price of the order
        return totalPrice;
    }
    
    //Unit price getter
    public double getUnitPrice() {
        return unitPrice;
    }

    //Tickets number getter
    public int getTicketsNumber() {
        return ticketsNumber;
    }

    //Tickets number setter
    public void setTicketsNumber(int ticketsNumber) {
        this.ticketsNumber = ticketsNumber;
    }

    //Screening getter
    public Screening getSession() {
        return session;
    }

    //Screening setter
    public void setSession(Screening session) {
        this.session = session;
    }

    //Customer who makes the order getter
    public Person getCustomer() {
        return customer;
    }

    //Customer who makes the order setter
    public void setCustomer(Person customer) {
        this.customer = customer;
    }
}
