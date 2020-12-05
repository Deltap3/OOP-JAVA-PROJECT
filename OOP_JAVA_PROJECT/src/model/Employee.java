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

/**This class manages the employees
 * it extends "Person" class
 */
public class Employee extends Person{
    //An employee have a login and a password
    private String login_ID;
    private String password;

    public Employee(){}
   
    //Its constructor initialize all the atributes with the values passed in parameters
    public Employee(String login_ID, String password, String first_name, String last_name) {
        //Calling of the "Person" constructor
        super(first_name, last_name);
        this.login_ID = login_ID;
        this.password = password;
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
    
    //Return false because a employee isn't a member
    @Override
    public boolean isMember()
    {
        return false;
    }
}
