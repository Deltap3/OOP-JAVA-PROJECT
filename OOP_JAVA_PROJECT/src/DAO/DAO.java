
package DAO;

import java.sql.Connection;

/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
//Interface that act like a link between java classes and database
public abstract class DAO<T>{
    protected Connection connect = null;
    
    public DAO(Connection con){
        this.connect = con;
    }
    //Add a element in the database
    public abstract T add(T obj);
    //Delete a element in the database
    public abstract void delete(T obj);
    //Find a element in the database
    public abstract T find(String id);
    
}
