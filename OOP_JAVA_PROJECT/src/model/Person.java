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

//Classe abstraite qui possède les attributs commun à toutes les personnes
public abstract class Person {
    //Chaque personne est caractérisé par un nom et un prénom
    private String first_name;
    private String last_name;
    
    public Person(){}

    //Son contructeur initialse toutes les variables à celles passées en paramètre
    public Person(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }
    
    //Setter du prénom
    public void setFirstName(String m_first_name)
    {
    this.first_name=m_first_name;
    }
    
    //Getter du prénom
    public String getFirstName()
    {
    return this.first_name;
    }
    
    //Setter du nom
    public void setLastName(String m_last_name)
    {
    this.last_name=m_last_name;
    }
    
    //Getter du prénom
    public String getLastName()
    {
    return this.last_name;
    }
    
    //Méthode abstraite qui sert à savoir si la personne est un membre ou pas
    public abstract boolean isMember();

    //Méthode overridé pour obtenir le mail
    public String getMail() {
    return "";
    }

    //Méthode overridé pour obtenir l'âge
    public int getCategoryMember() {
        return 0;
    }
}
