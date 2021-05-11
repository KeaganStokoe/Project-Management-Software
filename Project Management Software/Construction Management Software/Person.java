/**
 * The Person class contains logic for creating and managing person objects used
 * in the Main project file and Person file.
 *
 * @author Keagan Stokoe
 * @version 1.00, 26 April 2021
 */

public class Person {

    // Attributes that each person has
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String physicalAddress;

    /**
     * Person constructor method. <br>
     * Constructor function. Used to create person objects in the Main.java file,
     * e.g. an architect of the type Person.
     *
     * @param name            person name
     * @param phoneNumber     person phone number
     * @param emailAddress    person email address
     * @param physicalAddress person physical address
     * 
     * @see Main
     * @since version 1.00
     */
    public Person(String name, String phoneNumber, String emailAddress, String physicalAddress) {
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
        this.setEmailAddress(emailAddress);
        this.setPhysicalAddress(physicalAddress);
    }

    /**
     *
     * Get person phone name. <br>
     * The method gets the name of a specific person.
     *
     * @return person name
     * @since version 1.00
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Set person name method. <br>
     * The method sets the person phone number of a specific person.
     *
     * @since version 1.00
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * Get person phone number. <br>
     * The method gets the person phone number of a specific person.
     *
     * @return person phone number
     * @since version 1.00
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * Set person phone number. <br>
     * The method sets the person phone number of a specific person.
     *
     * @since version 1.00
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * Get person email address. <br>
     * The method gets the person email address of a specific person.
     *
     * @return person email address
     * @since version 1.00
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     *
     * Set person email address. <br>
     * The method sets the person email address of a specific person.
     *
     * @since version 1.00
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     *
     * Get person physical address. <br>
     * The method gets the person physical address of a specific person.
     *
     * @return person physical address
     * @since version 1.00
     */
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    /**
     *
     * Set person physical address. <br>
     * The method sets the person physical address of a specific person.
     *
     * @since version 1.00
     */
    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }
}
