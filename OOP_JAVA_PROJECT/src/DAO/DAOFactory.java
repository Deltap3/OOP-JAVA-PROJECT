/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import model.Connections;
import java.sql.*;
/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
//Interface that initialize several DAO (=EmployeeDAO, CustomerMemberDAO, ScreeningDAO and MovieDAO)
public class DAOFactory {
    
    protected static final Connection conn = Connections.getInstance();
    
    // Methods for recovering the EmployeeDAO implementation
    public static DAO getEmployeeDAO(){
        return new EmployeeDAO(conn);
    }
    
    // Methods for recovering the CustomerMemberDAO implementation
    public static DAO getCustomerMemberDAO(){
        return new CustomerMemberDAO(conn);
    }
    
    // Methods for recovering the MovieDAO implementation
    public static DAO getMovieDAO(){
        return new MovieDAO(conn);
    }
    
    // Methods for recovering the ScreeningDAO implementation
    public static DAO getScreeningDAO(){
        return new ScreeningDAO(conn);
    }
    
    
}
