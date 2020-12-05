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

import java.sql.*;

//Cette classe gère toute la partie connexion à la base de donnée
public class Connections {
    //La connexion à la base de données
    private static Connection conn;
    //Sert à envoyer une requête à la base de données
    private Statement stmt;
    //Permet d'avoir le résultat de cette requête
    private ResultSet rset;
    //Permet d'avoir des données précises du résultat de la requête
    private ResultSetMetaData rsetMeta;
    //Stocke  l'URL de notre base de données
    private static String urlDatabase;
    //Stocke  le login de notre base de données
    private static String loginDatabase;
    //Stocke  le mot de passe de notre base de données
    private static String passwordDatabase;
    
    //Ici on se connecte à la base de donnée et on définis son URL, login et mot de passe
    public Connections(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        this.urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase+"?useSSL=false";
        this.loginDatabase=loginDatabase;
        this.passwordDatabase=passwordDatabase;
        getInstance();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }
    
    //Permet de renvoyer directement la connexion de la base de données
     public static Connection getInstance(){
       //Si il n'y a pas encore de connexion
       if(conn == null){
         try{
             //On essaye de se connecter à la base de données
             conn = DriverManager.getConnection(urlDatabase,loginDatabase,passwordDatabase);
             //Si on réussit on en informe l'utilisateur
             System.out.println("Connexion BDD réussie");
         }catch (SQLException ex){
             //Si la connexion rate on en informe l'utilisateur et on affiche l'erreur
             ex.getMessage();
             System.out.println("Connexion BDD ratée");
         }
       }
       //Si il existe déjà une connexion on la renvoie
       return conn;
   }
}
