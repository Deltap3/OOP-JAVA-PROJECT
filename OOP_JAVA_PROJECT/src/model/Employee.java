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
public class Employee extends Person{
    
    private String login_ID;
    private String password;

    public Employee(){}
   
    public Employee(String login_ID, String password, String first_name, String last_name) {
        super(first_name, last_name);
        this.login_ID = login_ID;
        this.password = password;
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
    
    
    
    
    
    
}
