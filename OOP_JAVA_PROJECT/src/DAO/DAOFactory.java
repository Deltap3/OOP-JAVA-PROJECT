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
///Interface qui permet d'initialiser plusieurs DAO (ici EmployeeDAO, CustomerMemberDAO, ScreeningDAO et MovieDAO)
public class DAOFactory {
    
    protected static final Connection conn = Connections.getInstance();
    
    // Méthodes de récupération de l'implémentation de EmployeeDAO
    public static DAO getEmployeeDAO(){
        return new EmployeeDAO(conn);
    }
    
    /// Méthodes de récupération de l'implémentation du CustomerMemberDAO
    public static DAO getCustomerMemberDAO(){
        return new CustomerMemberDAO(conn);
    }
    
    /// Méthodes de récupération de l'implémentation de MovieDAO
    public static DAO getMovieDAO(){
        return new MovieDAO(conn);
    }
    
    /// Méthodes de récupération de l'implémentation de ScreeningDAO
    public static DAO getScreeningDAO(){
        return new ScreeningDAO(conn);
    }
    
    
}
