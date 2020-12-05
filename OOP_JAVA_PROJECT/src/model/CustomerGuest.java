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

/**Classe qui gère les personnes effectuant des commandes mais qui ne sont pas membres
 * elle hérite de la classe abstraite "Person"
 */

public class CustomerGuest extends Person{
    
    public CustomerGuest()
    {}
    
    //Dans son constructeur on appelle celui de "Persone
    public CustomerGuest(String first_name, String last_name) {
        //Appel du constructeur de "Person"
        super(first_name, last_name);
    }
    
    //Renvoie faux si on demande si l'objet est un membre
    @Override
    public boolean isMember()
    {
        return false;
    }
}
