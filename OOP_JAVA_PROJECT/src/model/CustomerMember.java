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

/**This class manages the members
 * it extends "Person" class
 */
public class CustomerMember extends Person{
    //Member's login
    private String login_ID;
    //Its password
    private String password;
    //Its mail adress
    private String mail;
    //Its age
    private int category_member;
    //Its total paid from all its orders
    private double totalPaid;
    
    public CustomerMember(){}
    
    //Its constructor initialize all the atributes with the values passed in parameters and the total paid is 0
    public CustomerMember(String loginID, String password, String mail, int category_member, String first_name, String last_name) {
        //Calling of the "Person" constructor
        super(first_name, last_name);
        this.login_ID = loginID;
        this.password = password;
        this.mail=mail;
        this.category_member=category_member;
        this.totalPaid = 0.0;
    }
    
    //If total paid is passed in parameters we initialize it
    public CustomerMember(String login_ID, String password, String mail, int category_member, double totalPaid, String first_name, String last_name) {
        //Calling of the "Person" constructor
        super(first_name, last_name);
        this.login_ID = login_ID;
        this.password = password;
        this.mail = mail;
        this.category_member = category_member;
        this.totalPaid = totalPaid;
    }
    
    //Login setter
    public void setLoginID(String m_id)
    {
    this.login_ID=m_id;
    }
    
    //Login getter
    public String getLoginID()
    {
    return this.login_ID;
    }
    
    //Password setter
    public void setPassword(String m_password)
    {
    this.password=m_password;
    }
    
    //Password getter
    public String getPassword()
    {
    return this.password;
    }
    
    //Mail setter
    public void setMail(String m_mail)
    {
    this.mail=m_mail;
    }
    
    //Mail getter
    public String getMail()
    {
    return this.mail;
    }
    
    //Age setter
    public void setCategoryMember(int m_categoryMember)
    {
    this.category_member=m_categoryMember;
    }
    
    //Age getter
    public int getCategoryMember()
    {
    return this.category_member;
    }
    
    //Total paid getter
    public double getTotalPaid(){
    return totalPaid;
    }
    
    //Return true because it's a member
    @Override
    public boolean isMember()
    {
        return true;
    }
  
    //Add the price in parameter to the total price
    public void addPrice(double price){
        totalPaid = totalPaid + price;
    }
    
}
