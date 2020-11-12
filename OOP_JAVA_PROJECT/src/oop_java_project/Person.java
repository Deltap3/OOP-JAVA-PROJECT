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
public class Person {
    
    private String first_name;
    private String last_name;
    private String mail;

    public Person(String first_name, String last_name, String mail) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.mail = mail;
    }
    public void setFirstName(String m_first_name)
    {
    this.first_name=m_first_name;
    }
    public String getFirstName()
    {
    return this.first_name;
    }
    
    public void setLastName(String m_last_name)
    {
    this.last_name=m_last_name;
    }
    public String getLastName()
    {
    return this.last_name;
    }
  
    public void setMail(String m_mail)
    {
    this.mail=m_mail;
    }
    public String getMail()
    {
    return this.mail;
    }
    
    
}
