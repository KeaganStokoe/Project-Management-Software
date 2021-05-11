/**
 * The Architect class extends the Person class and contains the constructor
 * function to create a new Architect object of person type.
 *
 * @author Keagan Stokoe
 * @version 1.00, 26 April 2021
 */
public class Architect extends Person {

    /**
     * Architect constructor method. <br>
     * Constructor function that extends the Person constructor method.
     *
     * @param name            architect name
     * @param phoneNumber     architect phone number
     * @param emailAddress    architect email address
     * @param physicalAddress architect physical address
     * 
     * @see Person 
     * @since version 1.00
     */
    public Architect(String name, String phoneNumber, String emailAddress, String physicalAddress) {
        super(name, phoneNumber, emailAddress, physicalAddress);
    }

}
