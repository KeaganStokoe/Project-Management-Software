/**
 * The Contractor class extends the Person class and contains the constructor
 * function to create a new Contractor object of person type.
 *
 * @author Keagan Stokoe
 * @version 1.00, 26 April 2021
 */
public class Contractor extends Person {

    /**
     * Contractor constructor method. <br>
     * Constructor function that extends the Person constructor method.
     *
     * @param name            contractor name
     * @param phoneNumber     contractor phone number
     * @param emailAddress    contractor email address
     * @param physicalAddress contractor physical address
     * 
     * @see Person
     * @since version 1.00
     */
    public Contractor(String name, String phoneNumber, String emailAddress, String physicalAddress) {
        super(name, phoneNumber, emailAddress, physicalAddress);
    }

}
