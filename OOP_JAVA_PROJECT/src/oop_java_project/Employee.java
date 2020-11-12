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
    
    private int login_ID;
    private String password;

    public Employee(int login_ID, String password, String first_name, String last_name, String mail) {
        super(first_name, last_name, mail);
        this.login_ID = login_ID;
        this.password = password;
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
    
    
    
    
    
    
}
