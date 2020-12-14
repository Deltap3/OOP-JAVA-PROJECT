
package model;

/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */

/**This class manages the guest (persons that make an order but they aren't members)
 * it extends "Person" class
 */

public class CustomerGuest extends Person{
    
    public CustomerGuest()
    {}
    
    //It calls the constructor of "Person"
    public CustomerGuest(String first_name, String last_name) {
        super(first_name, last_name);
    }
    
    //Return false because a guest isn't a member
    @Override
    public boolean isMember()
    {
        return false;
    }
}
