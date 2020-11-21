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
 *
 * @author davidzhong
 */
public class CustomerMemberDAO extends DAO<CustomerMember>{
    
    public CustomerMemberDAO(Connection conn)
    {
    super(conn);
    }
    
    public CustomerMember add(CustomerMember obj)
    {
    try
    {
    String sql=("insert into members (firstName,lastName,login,passw,categoryMember)\n" +
                           "values ('"+obj.getFirstName()+"','"+obj.getLastName()+"','"+obj.getLoginID()+"',"
            + "               '"+obj.getPassword()+"',"+obj.getCategoryMember()+")");
    
    
    PreparedStatement stmt = connect.prepareStatement(sql); 
          
        stmt.executeUpdate();
    } 
    catch (SQLException ex)
    {
       ex.printStackTrace();
    }
      
      return obj;
    }
    
  
    public void delete(CustomerMember obj)
    {
         try{
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM members WHERE login = '"+ obj.getLoginID()+"'");
        }catch(SQLException ex){
            ex.getMessage();
        }
    }
    public CustomerMember find(String login)
    {
      CustomerMember member = new CustomerMember();
        try{
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM members WHERE login = '" + login+"'");
            if(result.first() && result.first()){
                member = new CustomerMember(result.getString("login"), result.getString("passw"),
                        result.getString("mail"),result.getInt("categoryMember"),
                        result.getString("firstName"),result.getString("lastName"));
               
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return member;
    }
    
    public CustomerMember findFromLoginPassword(String login, String password)
    {
      CustomerMember member = new CustomerMember();
        try{
            ResultSet result1 = this.connect.createStatement().executeQuery("SELECT * FROM members WHERE login = '" + login+"'");
            ResultSet result2 = this.connect.createStatement().executeQuery("SELECT * FROM members WHERE passw = '" + password+"'");
            if(result1.first() && result2.first()){
                member = new CustomerMember(result1.getString("login"),
                        result1.getString("passw"),result1.getString("mail"),result1.getInt("categoryMember"),
                        result1.getString("firstName"),result1.getString("lastName"));
               
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return member;
    }
}
