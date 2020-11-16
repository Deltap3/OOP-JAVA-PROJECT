/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import model.Connections;
import java.sql.*;
/**
 *
 * @author davidzhong
 */
public class DAOFactory {
    
 
    protected static final Connection conn = Connections.getInstance();
    
    public static DAO getEmployeeDAO(){
        return new EmployeeDAO(conn);
    }
    
    public static DAO getCustomerMemberDAO(){
        return new CustomerMemberDAO(conn);
    }
    
    public static DAO getMovieDAO(){
        return new MovieDAO(conn);
    }
    
    public static DAO getScreeningDAO(){
        return new ScreeningDAO(conn);
    }
    
    
}
