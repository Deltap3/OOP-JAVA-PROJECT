
package model;

import DAO.CustomerMemberDAO;
import DAO.ScreeningDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.OOP_JAVA_PROJECT;

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
    public void placeOrder()
    {
        boolean screeningUpdate=false, memberUpdate=false;
        try{
            Connections co= new Connections("project", "root", "password");
            
            //first we update the number of tickets broughts in our database
            ScreeningDAO screeningCo= new ScreeningDAO(co.getInstance());
            screeningUpdate=screeningCo.addTickets(ticketsNumber+session.getTicketsBoughts(), session.getDateTime(), session.getNumberRoom());
            
            if(screeningUpdate)
            System.out.println("screening update successfull");
            
            //then we update the total paid in member if the custommer is a memeber
            if(customer.isMember())
            {
                CustomerMember member= (CustomerMember) customer; 
                CustomerMemberDAO memberCo= new CustomerMemberDAO(co.getInstance());
                memberUpdate=memberCo.addToTotalPaid(computePrice()+member.getTotalPaid(), member.getLoginID());
               
                if(memberUpdate)
                System.out.println("member update successfull");
            }
        }
        catch (SQLException ex) {
                Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (ClassNotFoundException ex) {
                Logger.getLogger(OOP_JAVA_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
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
