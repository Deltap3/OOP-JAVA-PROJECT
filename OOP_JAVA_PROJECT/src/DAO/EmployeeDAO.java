
package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.CustomerMember;
import model.Employee;
/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
public class EmployeeDAO extends DAO<Employee>{
    //Cette classe fait le lien des employés entre notre base de données et notre code en java
    public EmployeeDAO(Connection con)
    {
    super(con);
    }
    //Ajoute l'employé passé en paramètre dans la base de données
    public Employee add(Employee obj){
      try
      {
        //On essaye d'ajouter un employé à la table membre
        String sql = ("insert into employee (firstName,lastName,login,passw)\n" +
                "values ('"+obj.getFirstName()+"','"+obj.getLastName()+
                "','"+obj.getLoginID()+"','"+obj.getPassword()+")");
        PreparedStatement stmt = connect.prepareStatement(sql); 
        //Si la requête fonctionne le membre est bien ajouté
          stmt.executeUpdate();
        
      }
      catch (SQLException ex)
      {
         //Sinon on affiche une erreur
         ex.printStackTrace();
      }
      //On retourne l'employé que l'on vient d'ajouter
      return obj;
    }
  
  //On supprime un employé de la base de données
  public void delete(Employee obj)
  {
  try{
      //On essaye de supprimer l'employé et on le supprime si aucune erreur n'arrive
      this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM employee WHERE login = '"+ obj.getLoginID() + "'");
    }catch(SQLException ex){
        //Si il y a une erreur avec la requête on l'affiche
        ex.getMessage();
    }
  }
  /**
   * Permet de retrouver un employé dans la base de données
   * a partir d'un login et de récupérer un objet employé
   **/
  public Employee find(String login) {
         Employee emp = new Employee();
        try{
            //On essaye de trouver l'employé avec son login
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM employee WHERE login = '" + login + "'");
            //Si elle réussit et qu'il y a au moins un élément dans le résultat de la requête
            if(result.first()){
                //On construit un nouveau employé
                emp = new Employee(result.getString("login"),
                        result.getString("passw"),
                        result.getString("firstName"),result.getString("lastName"));
               
            }
        }catch(SQLException ex){
            //Si la requête n'a pas pu se réaliser on affiche une erreur
            ex.getMessage();
        }
        //On retourne l'employé créé
        return emp;
    }
    /**
    * Permet de retrouver un employé dans la base de données
    * a partir d'un login et d'un mot de passe
    * et de récupérer un objet employé
    **/
    public Employee findFromLoginPassword(String login, String password)
    {
      Employee emp = new Employee();
        try{
            //On essaye de trouver l'employé avec son login et mot de passe
            ResultSet result1 = this.connect.createStatement().executeQuery("SELECT * FROM members WHERE login = '" +login+"' AND passw = '" +password+"'");
            //Si elle réussit et qu'il y a au moins un élément dans le résultat de la requête
            if(result1.first()){
                //On construit un nouveau employé
                emp = new Employee(result1.getString("login"), result1.getString("passw"),
                        result1.getString("firstName"),result1.getString("lastName"));
               
            }
        }catch(SQLException ex){
            //Si il y a une erreur avec la requête on l'affiche
            ex.getMessage();
        }
        //On retourne l'employé créé
        return emp;
    }
    
}
    

  
  
    



