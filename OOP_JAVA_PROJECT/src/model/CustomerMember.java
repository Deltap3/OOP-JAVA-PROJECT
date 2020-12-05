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
public class CustomerMember extends Person{
    
    private String login_ID;
    private String password;
    private String mail;
    private int category_member;
    private double totalPaid;

    public CustomerMember(){}
    public CustomerMember(String loginID, String password, String mail, int category_member, String first_name, String last_name) {
        super(first_name, last_name);
        this.login_ID = loginID;
        this.password = password;
        this.mail=mail;
        this.category_member=category_member;
        this.totalPaid = 0.0;
    }

    public CustomerMember(String login_ID, String password, String mail, int category_member, double totalPaid, String first_name, String last_name) {
        super(first_name, last_name);
        this.login_ID = login_ID;
        this.password = password;
        this.mail = mail;
        this.category_member = category_member;
        this.totalPaid = totalPaid;
    }
    
    public void setLoginID(String m_id)
    {
    this.login_ID=m_id;
    }
    public String getLoginID()
    {
    return this.login_ID;
    }
    
    public void setPassword(String m_password)
    {
    this.password=m_password;
    }
    public String getPassword()
    {
    return this.password;
    }
    
    public void setMail(String m_mail)
    {
    this.mail=m_mail;
    }
    @Override
    public String getMail()
    {
    return this.mail;
    }
    
    public void setCategoryMember(int m_categoryMember)
    {
    this.category_member=m_categoryMember;
    }
    
    @Override
    public int getCategoryMember()
    {
    return this.category_member;
    }
    
    public double getTotalPaid(){
    return totalPaid;
    }
    
    @Override
    public boolean isMember()
    {
        return true;
    }
  
    public void addPrice(double price){
        totalPaid = totalPaid + price;
    }
    
}
