
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
import model.Employee;
/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
public class EmployeeDAO extends DAO<Employee>{
    //This class is the link between our java code and our database
    public EmployeeDAO(Connection con)
    {
    super(con);
    }
    //Add the employee passed in parameter in the database
    public Employee add(Employee obj){
      try
      {
        //We try to add this employee
        String sql = ("insert into employee (firstName,lastName,login,passw)\n" +
                "values ('"+obj.getFirstName()+"','"+obj.getLastName()+
                "','"+obj.getLoginID()+"','"+obj.getPassword()+")");
        PreparedStatement stmt = connect.prepareStatement(sql); 
            //If the query suceed and the member is well added
          stmt.executeUpdate();
        
      }
      catch (SQLException ex)
      {
           //Else we display the error
         ex.printStackTrace();
      }
        //We return the objet we have just created
      return obj;
    }
  
    //We delete a employee from the database
  public void delete(Employee obj)
  {
  try{
      //We try to delete this employee
      this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM employee WHERE login = '"+ obj.getLoginID() + "'");
    }catch(SQLException ex){
        //If there's an error we display it
        ex.getMessage();
    }
  }
    /**
    * Find a employee in the database from a login
    * and retrun a employee object
    **/
  public Employee find(String login) {
         Employee emp = new Employee();
        try{
            //We try to find the employee with his login
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM employee WHERE login = '" + login + "'");
            //If the query suceed and there's at least one element in it
            if(result.first()){
                //We create a new employee
                emp = new Employee(result.getString("login"),
                        result.getString("passw"),
                        result.getString("firstName"),result.getString("lastName"));
               
            }
        }catch(SQLException ex){
            //If the query failed we display an error message
            ex.getMessage();
        }
        //We return the created member
        return emp;
    }
  
    /**
    * Find a member in the database from a login and password
    * and retrun a member object
    **/
    public Employee findFromLoginPassword(String login, String password)
    {
      Employee emp = new Employee();
        try{
            //We try to find this member 
            ResultSet result1 = this.connect.createStatement().executeQuery("SELECT * FROM members WHERE login = '" +login+"' AND passw = '" +password+"'");
            //If the query suceed and there's at least one element in it
            if(result1.first()){
                //We create a new member
                emp = new Employee(result1.getString("login"), result1.getString("passw"),
                        result1.getString("firstName"),result1.getString("lastName"));
               
            }
        }catch(SQLException ex){
            //If the query fails we display an error
            ex.getMessage();
        }
        //We return the created member
        return emp;
    }
    
    //Same method than the one that add a member in CustomerMemberDAO
    public CustomerMember addMember(CustomerMember obj){
      try
        {
        String sql=("insert into members (firstName,lastName,mail,login,passw,totalPaid,categoryMember)\n" +
                "values ('"+obj.getFirstName()+"','"+obj.getLastName()+"','"+obj.getMail()+
                "','"+obj.getLoginID()+"','"+obj.getPassword()+"','"+obj.getTotalPaid()+"','"+obj.getCategoryMember()+"')");


        PreparedStatement stmt = connect.prepareStatement(sql); 

            stmt.executeUpdate();
        } 
        catch (SQLException ex)
        {
           ex.printStackTrace();
        }
        return obj;
    }
    //Same method than the one that delete a member in CustomerMemberDAO
    public void deleteMember(CustomerMember obj){
      try{
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM members WHERE login = '"+ obj.getLoginID()+"'");
        }catch(SQLException ex){
            ex.getMessage();
        }
    }
    
    //Return a bolean whether the employee we research exists or not    
    public boolean employeeExist(String login, String password){
      try
      {
         //We try to find the employee in the database
         ResultSet resultSet = this.connect.createStatement().executeQuery("select * from employee\n" +
                           "where login = '" + login + "' and passw = '" + password + "'");
         
         if(resultSet.next())
            //If the query suceed and there's at least one element in it
            return true;
         else
             //Else we return false
             return false;
      }
      catch (SQLException ex)
      {
         //If there's an error we display it and return false
         ex.printStackTrace();
         return false;
      }
    }
}
    

  
  
    



