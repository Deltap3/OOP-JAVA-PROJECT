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

/**Classe qui gère les membres
 * elle hérite de la classe abstraite "Person"
 */
public class CustomerMember extends Person{
    //Le login du membre
    private String login_ID;
    //Son mot de passe
    private String password;
    //Son adresse mail
    private String mail;
    //Son âge
    private int category_member;
    //Le total qu'il à payé sur toutes ces commandes
    private double totalPaid;
    
    public CustomerMember(){}
    
    //Son contructeur initialse toutes les variables à celles passées en paramètre sauf le total payé qui est à 0
    public CustomerMember(String loginID, String password, String mail, int category_member, String first_name, String last_name) {
        //Appel du constructeur de "Person"
        super(first_name, last_name);
        this.login_ID = loginID;
        this.password = password;
        this.mail=mail;
        this.category_member=category_member;
        this.totalPaid = 0.0;
    }
    
    //Si on passe un total payé en paramètre on l'initialise
    public CustomerMember(String login_ID, String password, String mail, int category_member, double totalPaid, String first_name, String last_name) {
        //Appel du constructeur de "Person"
        super(first_name, last_name);
        this.login_ID = login_ID;
        this.password = password;
        this.mail = mail;
        this.category_member = category_member;
        this.totalPaid = totalPaid;
    }
    
    //Setter pour le login
    public void setLoginID(String m_id)
    {
    this.login_ID=m_id;
    }
    
    //Getter pour le login
    public String getLoginID()
    {
    return this.login_ID;
    }
    
    //Setter pour le mot de passe
    public void setPassword(String m_password)
    {
    this.password=m_password;
    }
    
    //Getter pour le mot de passe
    public String getPassword()
    {
    return this.password;
    }
    
    //Setter pour le mail
    public void setMail(String m_mail)
    {
    this.mail=m_mail;
    }
    
    //Getter pour le mail
    public String getMail()
    {
    return this.mail;
    }
    
    //Setter pour l'âge
    public void setCategoryMember(int m_categoryMember)
    {
    this.category_member=m_categoryMember;
    }
    
    //Getter pour l'âge
    public int getCategoryMember()
    {
    return this.category_member;
    }
    
    //Getter pour le total payé
    public double getTotalPaid(){
    return totalPaid;
    }
    
    //Renvoie vrai si on demande si l'objet est un membre
    @Override
    public boolean isMember()
    {
        return true;
    }
  
    //Ajoute un prix au prix total payé
    public void addPrice(double price){
        totalPaid = totalPaid + price;
    }
    
}
