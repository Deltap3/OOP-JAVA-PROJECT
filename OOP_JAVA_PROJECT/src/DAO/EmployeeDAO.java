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
import model.Employee;
/**
 *
 * @author davidzhong
 */
public class EmployeeDAO extends DAO<Employee>{
    
    public EmployeeDAO(Connection con)
    {
    super(con);
    }
    
    public Employee add(Employee obj){
      try
      {
          String sql = ("insert into employee (firstName,lastName,login,passw)\n" +
                  "values ('"+obj.getFirstName()+"','"+obj.getLastName()+
                  "','"+obj.getLoginID()+"','"+obj.getPassword()+")");
          PreparedStatement stmt = connect.prepareStatement(sql); 
          
        stmt.executeUpdate();
        
      }
      catch (SQLException ex)
      {
         ex.printStackTrace();
      }
      
      return obj;
    }
  
  
  public void delete(Employee obj)
  {
  try{
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM employee WHERE login = '"+ obj.getLoginID() + "'");
        }catch(SQLException ex){
            ex.getMessage();
        }
  }
  
  public Employee find(String login) {
         Employee emp = new Employee();
        try{
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM employee WHERE login = '" + login + "'");
            if(result.first()){
                emp = new Employee(result.getString("login"),
                        result.getString("passw"),
                        result.getString("firstName"),result.getString("lastName"));
               
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return emp;
    }
  public Employee findFromLoginPassword(String login, String password)
    {
      Employee emp = new Employee();
        try{
            ResultSet result1 = this.connect.createStatement().executeQuery("SELECT * FROM employee WHERE login = '" + login+"'");
            ResultSet result2 = this.connect.createStatement().executeQuery("SELECT * FROM employee WHERE passw = '" + password+"'");
            if(result1.first() && result2.first()){
                emp = new Employee(result1.getString("login"), result1.getString("passw"),
                        result1.getString("firstName"),result1.getString("lastName"));
               
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return emp;
    }
  
  
}
    

  
  
    



