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
            ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM members WHERE id ="+ obj.getLoginID());
        }catch(SQLException ex){
            ex.getMessage();
        }
    }
    public CustomerMember find(int id)
    {
      CustomerMember member = new CustomerMember();
        try{
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM members WHERE login = " + id);
            if(result.first() && result.first()){
                member = new CustomerMember(id,
                        result.getString("passw"),result.getString("categoryMember"),
                        result.getString("firstName"),result.getString("lastName"));
               
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return member;
    }
    
    public CustomerMember findFromLoginPassword(int id, String password)
    {
      CustomerMember member = new CustomerMember();
        try{
            ResultSet result1 = this.connect.createStatement().executeQuery("SELECT * FROM members WHERE login = " + id);
            ResultSet result2 = this.connect.createStatement().executeQuery("SELECT * FROM members WHERE passw = " + password);
            if(result1.first() && result2.first()){
                member = new CustomerMember(id,
                        password,result1.getString("categoryMember"),
                        result1.getString("firstName"),result1.getString("lastName"));
               
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return member;
    }
    
    
    
    
}
