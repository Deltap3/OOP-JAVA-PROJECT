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

/**Classe qui gère les employés
 * elle hérite de la classe abstraite "Person"
 */
public class Employee extends Person{
    //Un employé possède un login et un mot de passe
    private String login_ID;
    private String password;

    public Employee(){}
   
    //Son contructeur initialse toutes les variables à celles passées en paramètre
    public Employee(String login_ID, String password, String first_name, String last_name) {
        //Appel du constructeur de "Person"
        super(first_name, last_name);
        this.login_ID = login_ID;
        this.password = password;
    }
    
    //Setter du login
    public void setLoginID(String m_id)
    {
    this.login_ID=m_id;
    }
    
    //Getter du login
    public String getLoginID()
    {
    return this.login_ID;
    }
    
    //Setter du mot de passe
    public void setPassword(String m_password)
    {
    this.password=m_password;
    }
    
    //Getter du mot de passe
    public String getPassword()
    {
    return this.password;
    }
    
    //Renvoie faux si on demande si l'objet est un membre
    @Override
    public boolean isMember()
    {
        return false;
    }
    
    
    
}
