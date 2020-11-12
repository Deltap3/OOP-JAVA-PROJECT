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
    
    private String firstname;
    private String lastname;
    private String mail;

    public Person(String firstname, String lastname, String mail) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
    }
    public void setFirstname(String m_firstname)
    {
    this.firstname=m_firstname;
    }
    public String getFirstname()
    {
    return this.firstname;
    }
    
    public void setLastname(String m_lastname)
    {
    this.lastname=m_lastname;
    }
    public String getLastname()
    {
    return this.lastname;
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
