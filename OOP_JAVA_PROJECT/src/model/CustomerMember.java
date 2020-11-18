/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author davidzhong
 */
public class CustomerMember extends Person{
    
    private int login_ID;
    private String password;
    private String mail;
    private int category_member;

    public CustomerMember(){}
    public CustomerMember(int loginID, String password, String mail, int category_member, String first_name, String last_name) {
        super(first_name, last_name);
        this.login_ID = loginID;
        this.password = password;
        this.mail=mail;
        this.category_member=category_member;
    }
    
    public void setLoginID(int m_id)
    {
    this.login_ID=m_id;
    }
    public int getLoginID()
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
    public String getMail()
    {
    return this.mail;
    }
    
    public void setCategoryMember(int m_categoryMember)
    {
    this.category_member=m_categoryMember;
    }
    public int getCategoryMember()
    {
    return this.category_member;
    }
    
    
    
  
    
    
}
