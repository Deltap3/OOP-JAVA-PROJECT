/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;

/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
//Interface qui permet de créer le lien entre les classes Java et la base de données
public abstract class DAO<T>{
    protected Connection connect = null;
    
    public DAO(Connection con){
        this.connect = con;
    }
    //Permet d'ajouter dans la base de données un élément de la classe souhaité
    public abstract T add(T obj);
    //Permet de supprimer de la base de données un élément de la classe souhaité
    public abstract void delete(T obj);
    //Permet de trouver dans la base de données un élément de la classe souhaité
    public abstract T find(String id);
    
}
