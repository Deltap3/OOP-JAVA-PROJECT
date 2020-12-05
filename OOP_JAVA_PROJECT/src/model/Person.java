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

//Abstract class that has the common attributes to every person
public abstract class Person {
    //Each person got a first name and last name
    private String first_name;
    private String last_name;
    
    public Person(){}

    //Its constructor initialize all the atributes with the values passed in parameters
    public Person(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }
    
    //First name setter
    public void setFirstName(String m_first_name)
    {
    this.first_name=m_first_name;
    }
    
    //First name getter
    public String getFirstName()
    {
    return this.first_name;
    }
    
    //Last name setter
    public void setLastName(String m_last_name)
    {
    this.last_name=m_last_name;
    }
    
    //Last name getter
    public String getLastName()
    {
    return this.last_name;
    }
    
    //Abstract method to determine if a person is a member or not
    public abstract boolean isMember();

    //OverRided method to get the mail
    public String getMail() {
    return "";
    }

    //OverRided method to get the age
    public int getCategoryMember() {
        return 0;
    }
}
