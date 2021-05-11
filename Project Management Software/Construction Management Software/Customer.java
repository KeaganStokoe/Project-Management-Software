/**
 * The Customer class extends the Person class and contains the constructor
 * function to create a new Customer object of person type.
 *
 * @author Keagan Stokoe
 * @version 1.00, 26 April 2021
 */
public class Customer extends Person {

    /**
     * Customer constructor method. <br>
     * Constructor function that extends the Person constructor method.
     *
     * @param name            customer name
     * @param phoneNumber     customer phone number
     * @param emailAddress    customer email address
     * @param physicalAddress customer physical address
     * 
     * @see Person 
     * @since version 1.00
     */
    public Customer(String name, String phoneNumber, String emailAddress, String physicalAddress) {
        super(name, phoneNumber, emailAddress, physicalAddress);
    }

}
