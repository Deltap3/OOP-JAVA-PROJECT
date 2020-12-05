/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.CustomerMember;
/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
public class CustomerMemberDAO extends DAO<CustomerMember>{
    //Cette classe fait le lien des membres entre notre base de données et notre code en java
    public CustomerMemberDAO(Connection conn)
    {
    super(conn);
    }
    //Ajoute le membre passé en paramètre dans la base de données
    public CustomerMember add(CustomerMember obj)
    {
        try
        {
            //On essaye d'ajouter un membre à la table membre
            String sql=("insert into members (firstName,lastName,mail,login,passw,totalPaid,categoryMember)\n" +
                "values ('"+obj.getFirstName()+"','"+obj.getLastName()+"','"+obj.getMail()+
                "','"+obj.getLoginID()+"','"+obj.getPassword()+"','"+obj.getTotalPaid()+"','"+obj.getCategoryMember()+"')");
            PreparedStatement stmt = connect.prepareStatement(sql); 
            //Si la requête fonctionne le membre est bien ajouté
            stmt.executeUpdate();
        } 
        catch (SQLException ex)
        {
           //Sinon on affiche une erreur
           ex.printStackTrace();
        }
        //On retourne le membre que l'on vient d'ajouter
        return obj;
    }
    
    //On supprime un membre de la base de données
    public void delete(CustomerMember obj)
    {
        try{
            //On essaye de supprimer le membre et on le supprime si aucune erreur n'arrive
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM members WHERE login = '"+ obj.getLoginID()+"'");
        }catch(SQLException ex){
            //Si il y a une erreur avec la requête on l'affiche
            ex.getMessage();
        }
    }
    
    /**
    * Permet de retrouver un membre dans la base de données
    * a partir d'un login et de récupérer un objet membre
    **/
    public CustomerMember find(String login)
    {
      CustomerMember member = new CustomerMember();
      try{
            //On essaye de trouver le membre avec son login
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM members WHERE login = '" + login+"'");
            //Si elle réussit et qu'il y a au moins un élément dans le résultat de la requête
            if(result.first()){
                //On construit un nouveau membre
                member = new CustomerMember(result.getString("login"), result.getString("passw"),
                        result.getString("mail"),result.getInt("categoryMember"),result.getDouble("totalPaid"),
                        result.getString("firstName"),result.getString("lastName"));
               
            }
        }catch(SQLException ex){
            //Si la requête n'a pas pu se réaliser on affiche une erreur
            ex.getMessage();
        }
        //On retourne le membre créé
        return member;
    }
    
    /**
    * Permet de retrouver un membre dans la base de données
    * a partir d'un login et d'un mot de passe
    * et de récupérer un objet membre
    **/
    public CustomerMember findFromLoginPassword(String login, String password)
    {
      CustomerMember member = new CustomerMember();
        try{
            //On essaye de trouver le membre avec son login et mot de passe
            ResultSet result1 = this.connect.createStatement().executeQuery("SELECT * FROM members WHERE login = '" +login+"' AND passw = '" +password+"'");
            //Si elle réussit et qu'il y a au moins un élément dans le résultat de la requête
            if(result1.first()){
                //On construit un nouveau membre
                member = new CustomerMember(result1.getString("login"),
                        result1.getString("passw"),result1.getString("mail"),result1.getInt("categoryMember"),result1.getDouble("totalPaid"),
                        result1.getString("firstName"),result1.getString("lastName"));
               
            }
        }catch(SQLException ex){
            //Si il y a une erreur avec la requête on l'affiche
            ex.getMessage();
        }
        //On retourne le membre créé
        return member;
    }
    
    /**
     * Permet d'obtenir le grade d'un membre en fonction 
     * du montant total de ses achats
     **/
    public String getGrade(CustomerMember member){
        String grade = "";
        double price = 0;
        try{
            //On essaye de trouver le total payé d'un membre avec son login
            ResultSet result1 = this.connect.createStatement().executeQuery("SELECT totalPaid FROM members WHERE login = '" + member.getLoginID()+"'");
            if(result1.first()){
                price = result1.getDouble("totalPaid");
            }
        }catch(SQLException ex){
            //Si il y a une erreur avec la requête on l'affiche
            ex.getMessage();
        }
        //Il fait parti du grade bronze si ses achats totaux vont de 0 à 50€
        if(price > 0 && price < 50)
            grade = "Bronze";
        //Il fait parti du grade argent si ses achats totaux vont de 50 à 100€
        else if(price > 50 && price < 100)
            grade = "Argent";
        //Il fait parti du grade or si ses achats totaux sont à plus 100€
        else
            grade = "Or";
        //On retourne le grade du membre
        return grade;
    }
    
    
    
}
