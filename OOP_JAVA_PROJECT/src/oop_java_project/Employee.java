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
public class Employee extends Person{
    
    private int loginID;
    private String password;

    public Employee(int loginID, String password, String firstname, String lastname, String mail) {
        super(firstname, lastname, mail);
        this.loginID = loginID;
        this.password = password;
    }
    
    public void setLoginID(int m_id)
    {
    this.loginID=m_id;
    }
    public int getLoginID()
    {
    return this.loginID;
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
