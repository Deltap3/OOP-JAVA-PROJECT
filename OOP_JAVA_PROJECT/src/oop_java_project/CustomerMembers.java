/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_java_project;

/**
 *
 * @author davidzhong
 */
public class CustomerMembers extends Person{
    
    private int login_ID;
    private String password;
    private int category_member;

    public CustomerMembers(int loginID, String password,int category_member, String first_name, String last_name, String mail) {
        super(first_name, last_name, mail);
        this.login_ID = loginID;
        this.password = password;
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
    
    public float discount_in_percent()
    {
    float discount=0;
    if(category_member==1)///10% children
    {
    discount=10;
    return discount;
    }
    if (category_member==2)///25% senior
    {
    discount=25;
    return discount;
    }
    else///20% other member so regularmember
    {
    discount=20;
    return discount;
    }
        
    }  
    
  
    
    
}
