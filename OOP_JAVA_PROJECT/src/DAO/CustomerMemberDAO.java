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
    //This class is the link between our java code and our database
    public CustomerMemberDAO(Connection conn)
    {
    super(conn);
    }
    //Add the member passed in parameter in the database
    public CustomerMember add(CustomerMember obj)
    {
        try
        {
            //We try to add this member
            String sql=("insert into members (firstName,lastName,mail,login,passw,totalPaid,categoryMember)\n" +
                "values ('"+obj.getFirstName()+"','"+obj.getLastName()+"','"+obj.getMail()+
                "','"+obj.getLoginID()+"','"+obj.getPassword()+"','"+obj.getTotalPaid()+"','"+obj.getCategoryMember()+"')");
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
    
    //We delete a member from the database
    public void delete(CustomerMember obj)
    {
        try{
            //We try to delete this member
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM members WHERE login = '"+ obj.getLoginID()+"'");
        }catch(SQLException ex){
            //If there's an error we display it
            ex.getMessage();
        }
    }
    
    /**
    * Find a member in the database from a login
    * and retrun a member object
    **/
    public CustomerMember find(String login)
    {
      CustomerMember member = new CustomerMember();
      try{
            //We try to find the member with his login
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM members WHERE login = '" + login+"'");
            //If the query suceed and there's at least one element in it
            if(result.first()){
                //We create a new member
                member = new CustomerMember(result.getString("login"), result.getString("passw"),
                        result.getString("mail"),result.getInt("categoryMember"),result.getDouble("totalPaid"),
                        result.getString("firstName"),result.getString("lastName"));
               
            }
        }catch(SQLException ex){
            //If the query failed we display an error message
            ex.getMessage();
        }
        //We return the created member
        return member;
    }
    
    /**
    * Find a member in the database from a login and password
    * and retrun a member object
    **/
    public CustomerMember findFromLoginPassword(String login, String password)
    {
      CustomerMember member = new CustomerMember();
        try{
            //We try to find this member 
            ResultSet result1 = this.connect.createStatement().executeQuery("SELECT * FROM members WHERE login = '" +login+"' AND passw = '" +password+"'");
            //If the query suceed and there's at least one element in it
            if(result1.first()){
                //We create a new member
                member = new CustomerMember(result1.getString("login"),
                        result1.getString("passw"),result1.getString("mail"),result1.getInt("categoryMember"),result1.getDouble("totalPaid"),
                        result1.getString("firstName"),result1.getString("lastName"));
               
            }
        }catch(SQLException ex){
            //If the query fails we display an error
            ex.getMessage();
        }
        //We return the created member
        return member;
    }
    
    /**
     * Return the grade of a member based on the total price he paid
     * for all the screenings
     **/
    public String getGrade(CustomerMember member){
        String grade = "";
        double price = 0;
        try{
            //We try to find the tatal paid with the login of a member
            ResultSet result1 = this.connect.createStatement().executeQuery("SELECT totalPaid FROM members WHERE login = '" + member.getLoginID()+"'");
            if(result1.first()){
                price = result1.getDouble("totalPaid");
            }
        }catch(SQLException ex){
            //If there's an error we display it
            ex.getMessage();
        }
        //If he has paid less than 50€ he's bronze grade
        if(price > 0 && price < 50)
            grade = "Bronze";
        //If he has paid less between 50€ and 100€ he's silver grade
        else if(price > 50 && price < 100)
            grade = "Silver";
        //If he has paid more than 100€ he's gold grade
        else
            grade = "Gold";
        //We return the member's grade
        return grade;
    }
    
    //Return true of the member exists and false if not
    public boolean memberExist(String login, String password){
      try
      {
         //We try to find the member in the database
         ResultSet resultSet = this.connect.createStatement().executeQuery("select * from members\n" +
                           "where login = '" + login + "' and passw = '" + password + "'");
         if(resultSet.next())
             //If the query suceed and there's at least one element in it we return true
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
