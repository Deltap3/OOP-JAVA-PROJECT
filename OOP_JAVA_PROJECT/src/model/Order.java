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

//Classe qui permet de gérer les commandes
public class Order {
    
    //Une commande est définie par
    //Le prix unitaire des tickets
    private final double unitPrice;
    //Le nombre de tickets
    private int ticketsNumber;
    //Une scéance
    private Screening session;
    //La personne qui réalise la commande
    private Person customer;

    //Le constructeur initialise le nombre de tickets à 0 et le prix unitaire à 7.50€
    public Order() {
        unitPrice=7.50;
        ticketsNumber=0;
        customer=new CustomerGuest();
        session=new Screening();
    }

    //Sert à calculer le prix d'une commande et ensuite le renvoie à l'appelant
    public double computePrice()
    {
        //Le prix total de la commande est le prix unitaire d'un ticket
        //multiplié par le nombre total de tickets
        double totalPrice= unitPrice*ticketsNumber;
        //Si la pesonne qui réalise la commannde est un membre
        if(customer.isMember())
        {
            //On applique d'abors la réduction spéciale
            //(la réduction des scéances en elle-même)
            int specialDiscount=session.getDiscount();
            totalPrice=totalPrice-(totalPrice*specialDiscount/100);
            
            //On applique ensuite la réduction lié à l'âge de la personne
            int categoryDiscount=0;
            int age=customer.getCategoryMember();
            //On applique 20% de réduction pour les enfants
            if(age<13)
            {
               categoryDiscount=20; 
            }
            //Et 12% pour les séniors
            else if(age>59)
            {
                categoryDiscount=15;
            }
            //On calcule ensuite le prix avec les réductions
            totalPrice=totalPrice-(totalPrice*categoryDiscount/100);
        }
        //On retourne le prix total de la commande
        return totalPrice;
    }
    
    //Getter du prix unitaire d'un tickets
    public double getUnitPrice() {
        return unitPrice;
    }

    //Getter du nombre de tickets
    public int getTicketsNumber() {
        return ticketsNumber;
    }

    //Setter du nombre de rickets
    public void setTicketsNumber(int ticketsNumber) {
        this.ticketsNumber = ticketsNumber;
    }

    //Getter de la scéance de la commande
    public Screening getSession() {
        return session;
    }

    //Setter de la scéance de la commande
    public void setSession(Screening session) {
        this.session = session;
    }

    //Getter de la personne réalisant la commande
    public Person getCustomer() {
        return customer;
    }

    //Setter de la personne réalisant la commande
    public void setCustomer(Person customer) {
        this.customer = customer;
    }
}
