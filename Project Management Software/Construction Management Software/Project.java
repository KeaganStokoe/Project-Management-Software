
/**
 * The Project class contains logic for creating and managing project objects used in the Main project file.
 *
 * @author Keagan Stokoe
 * @version 1.00, 26 April 2021
 */

import java.lang.Math;
import java.util.Formatter;

public class Project {

    // Attributes of every project
    String number;
    String name;
    String buildingType;
    String address;
    int erfNumber;
    float totalFee;
    float totalPaid;
    String deadlineDate;
    Person architect;
    Person contractor;
    Person customer;
    String projectCompleted;

    /**
     * Project constructor method. <br>
     * The method used to generate new project objects.
     *
     * @param number           project number
     * @param name             project name
     * @param buildingType     the building type of the project e.g. house,
     *                         apartment
     * @param address          building address
     * @param erfNumber        property ERF number
     * @param totalFee         total cost of the project
     * @param totalPaid        amount paid up until this point
     * @param deadlineDate     expected date of completion
     * @param architect        Person object containing details of project architect
     * @param contractor       Person object containing details of project
     *                         contractor
     * @param customer         Person object containing details of project customer
     * @param projectCompleted boolean indicating whether project is completed
     * @since version 1.00
     */

    public Project(String number, String name, String buildingType, String address, int erfNumber, float totalFee,
            float totalPaid, String deadlineDate, Person architect, Person contractor, Person customer,
            String projectCompleted) {

        this.setNumber(number);
        this.setBuildingType(buildingType);
        this.setCustomer(customer);
        this.setName(name);
        this.setAddress(address);
        this.setErfNumber(erfNumber);
        this.setTotalFee(totalFee);
        this.setTotalPaid(totalPaid);
        this.setDeadlineDate(deadlineDate);
        this.setArchitect(architect);
        this.setContractor(contractor);
        this.setProjectCompleted(projectCompleted);
    }

    /**
     *
     * Get project number. <br>
     * The method gets the project number of a specific project.
     *
     * @return project number
     * @since version 1.00
     */
    public String getNumber() {
        return number;
    }

    /**
     *
     * Set project number method. <br>
     * The method sets the project number for a specific project.
     *
     * @since version 1.00
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     *
     * Get project name. <br>
     * The method gets the project name of a specific project.
     *
     * @return project name
     * @since version 1.00
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Set project name method. <br>
     * The method sets the name for a specific project. If no project name is
     * provided, the project is named according to the type of building and the
     * surname of the customer.
     *
     * @since version 1.00
     */
    public void setName(String name) {
        if (name.length() >= 1) {
            this.name = name;
        } else {
            String[] names = (customer.getName()).split(" "); // Split name to get first name and surname
            if (names.length > 1) {
                this.name = getBuildingType() + " " + names[1]; // If a surname is provided, use it.
            } else {
                this.name = getBuildingType() + " " + names[0]; // If no surname is provided, use the first name.
            }

        }
    }

    /**
     *
     * Get building type. <br>
     * The method gets the building type of a specific project.
     *
     * @return building type
     * @since version 1.00
     */
    public String getBuildingType() {
        return buildingType;
    }

    /**
     *
     * Set building type method. <br>
     * The method sets the building type for a specific project.
     *
     * @since version 1.00
     */
    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    /**
     *
     * Get building address. <br>
     * The method gets the address of a specific project.
     *
     * @return building address
     * @since version 1.00
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * Set address method. <br>
     * The method sets the address for a specific project.
     *
     * @since version 1.00
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * Get ERF number. <br>
     * The method gets the erf number of a specific project.
     *
     * @return erf number
     * @since version 1.00
     */
    public int getErfNumber() {
        return erfNumber;
    }

    /**
     *
     * Set ERF number method. <br>
     * The method sets ERF number for a specific project.
     *
     * @since version 1.00
     */
    public void setErfNumber(int erfNumber) {
        this.erfNumber = erfNumber;
    }

    /**
     *
     * Get project fee <br>
     * The method gets the total fee of a specific project.
     *
     * @return project fee
     * @since version 1.00
     */
    public float getTotalFee() {
        return totalFee;
    }

    /**
     *
     * Set total fees due method. <br>
     * The method sets total fees payable for a specific project.
     *
     * @since version 1.00
     */
    public void setTotalFee(float totalFee) {
        this.totalFee = totalFee;
    }

    /**
     *
     * Get total paid to date. <br>
     * The method gets the total already paid of a specific project.
     *
     * @return total already paid
     * @since version 1.00
     */
    public float getTotalPaid() {
        return totalPaid;
    }

    /**
     *
     * Set total paid method. <br>
     * The method sets total paid for a specific project.
     *
     * @since version 1.00
     */
    public void setTotalPaid(float totalPaid) {
        this.totalPaid = totalPaid;
    }

    /**
     *
     * Get project deadline. <br>
     * The method gets the deadline date of a specific project.
     *
     * @return deadline date
     * @since version 1.00
     */
    public String getDeadlineDate() {
        return deadlineDate;
    }

    /**
     *
     * Set project deadline. <br>
     * The method sets the deadline date of a specific project.
     *
     * @since version 1.00
     */
    public void setDeadlineDate(String newDeadline) {
        this.deadlineDate = newDeadline;
    }

    /**
     *
     * Get architect. <br>
     * The method gets the architect of a specific project.
     *
     * @return project architect
     * @since version 1.00
     */
    public Person getArchitect() {
        return architect;
    }

    /**
     *
     * Set project architect. <br>
     * The method sets the architect of a specific project.
     *
     * @since version 1.00
     */
    public void setArchitect(Person architect) {
        this.architect = architect;
    }

    /**
     *
     * Get contractor. <br>
     * The method gets the contractor of a specific project.
     *
     * @return project contractor
     * @since version 1.00
     */
    public Person getContractor() {
        return contractor;
    }

    /**
     *
     * Set project contractor. <br>
     * The method sets the contractor of a specific project.
     *
     * @since version 1.00
     */
    public void setContractor(Person contractor) {
        this.contractor = contractor;
    }

    /**
     *
     * Get customer. <br>
     * The method gets the customer of a specific project.
     *
     * @return project customer
     * @since version 1.00
     */
    public Person getCustomer() {
        return customer;
    }

    /**
     *
     * Set project customer. <br>
     * The method sets the customer of a specific project.
     *
     * @since version 1.00
     */
    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    /**
     *
     * Get project completion status. <br>
     * The method gets the completion status of a specific project.
     *
     * @return boolean completion status
     * @since version 1.00
     */
    public String isProjectCompleted() {
        return projectCompleted;
    }

    /**
     *
     * Set project completion status. <br>
     * The method sets the completion status of a specific project.
     *
     * @since version 1.00
     */
    public void setProjectCompleted(String projectCompleted2) {
        this.projectCompleted = projectCompleted2;
    }

    /**
     *
     * Project description method. <br>
     * Print summary description of the project using the getters and setters.
     * Display in an easy to read format.
     *
     * @return project details
     * @since version 1.00
     */
    public void description() {
        System.out.println("=======================================");
        System.out.println("\n");
        System.out.println("Project details:");
        System.out.println("\n");
        System.out.println("Project number: " + getNumber());
        System.out.println("Project name: " + getName());
        System.out.println("Building type: " + getBuildingType());
        System.out.println("Building address: " + getAddress());
        System.out.println("ERF number: " + getErfNumber());
        System.out.println("Total fee: " + getTotalFee());
        System.out.println("Total paid: " + getTotalPaid());
        System.out.println("Deadline: " + getDeadlineDate());
        System.out.println("=======================================");
        System.out.println("\n");
        System.out.println("Architect details: ");
        System.out.println("\n");
        personDetails(architect);
        System.out.println("=======================================");
        System.out.println("\n");
        System.out.println("Contractor details: ");
        System.out.println("\n");
        personDetails(contractor);
        System.out.println("=======================================");
        System.out.println("\n");
        System.out.println("Customer details: ");
        System.out.println("\n");
        personDetails(customer);
        System.out.println("=======================================");
        System.out.println("\n");

    }

    /**
     *
     * Person description method. <br>
     * Print summary description of a person object using the getters and setters.
     * Display in an easy to read format based on the person passed as an argument.
     *
     * @return person details
     * @since version 1.00
     */

    public void personDetails(Person person) {
        System.out.println("Name: " + person.getName());
        System.out.println("Phone number: " + person.getPhoneNumber());
        System.out.println("Email address: " + person.getEmailAddress());
        System.out.println("Physical address: " + person.getPhysicalAddress());
    }

    /**
     *
     * Generate invoice method. <br>
     * Generate an invoice that displays customer details and amount outstanding.
     *
     * @return string invoice
     * @since version 1.00
     */

    public void generateInvoice(Person person) {
        float amountOutstanding = getTotalFee() - getTotalPaid();
        if (amountOutstanding == 0) {
            System.out.print("The customer has paid in full. No invoice necessary.");
        } else if (amountOutstanding < 0) {
            System.out.println("The customer has overpaid. Amount owed to customer: " + Math.abs(amountOutstanding));
        } else {
            System.out.println("Customer Invoice");
            System.out.println();
            System.out.println("Name: " + person.getName());
            System.out.println("Phone number: " + person.getPhoneNumber());
            System.out.println("Email address: " + person.getEmailAddress());
            System.out.println("Physical address: " + person.getPhysicalAddress());
            System.out.println("Amount outstanding: " + amountOutstanding);
        }

    }

    /**
     *
     * Write project to file method. <br>
     * Function to write contents to output.txt file based on value of operand.
     *
     * @param formatter to write to text file
     * @return string invoice
     * @since version 1.00
     */

    public void writeToFile(Formatter formatter) {
        try {
            String fileString = getNumber() + "; " + getName() + "; " + getBuildingType() + "; " + getAddress() + "; "
                    + getErfNumber() + "; " + getTotalFee() + "; " + getTotalPaid() + "; " + getDeadlineDate() + "; "
                    + architect.getName() + "; " + architect.getPhoneNumber() + "; " + architect.getEmailAddress()
                    + "; " + architect.getPhysicalAddress() + "; " + contractor.getName() + "; "
                    + contractor.getPhoneNumber() + "; " + contractor.getEmailAddress() + "; "
                    + contractor.getPhysicalAddress() + "; " + customer.getName() + "; " + customer.getPhoneNumber()
                    + "; " + customer.getEmailAddress() + "; " + customer.getPhysicalAddress() + "; "
                    + isProjectCompleted();
            formatter.format("%s", fileString);
            formatter.format("\n");
        } finally {
            formatter.close();
        }

    }

    @Override
    /**
     *
     * Write to string method. <br>
     * Function to write project details as string.
     *
     * @return string summary
     * @since version 1.00
     */
    public String toString() {
        return "Project summary: \n================ \nName: " + name + "\n" + "Number: " + number + "\n"
                + "Building type: " + buildingType + "\n" + "Deadline: " + deadlineDate + "\n" + "ERF number: "
                + erfNumber + "\n" + "Total fees: " + totalFee + "\n" + "Total paid: " + totalPaid + "\n"
                + "Architect name: " + architect.getName() + "\n" + "Architect email:  " + architect.getEmailAddress()
                + "\n" + "Architect phone:" + architect.getPhoneNumber() + "\n"  + "Architect address:  "
                + architect.getPhysicalAddress() + "\n" + "Contractor name: " + contractor.getName() + "\n"
                + "Contractor email:  " + contractor.getEmailAddress() + "\n" + "Contractor phone:"
                + contractor.getPhoneNumber() + "\n" + "Contractor address:  " + contractor.getPhysicalAddress() + "\n"
                + "Customer name: " + customer.getName() + "\n" + "Customer email:  " + customer.getEmailAddress()
                + "\n" + "Customer phone:" + customer.getPhoneNumber() + "\n" + "Customer address:  "
                + customer.getPhysicalAddress() + "\n" + "Project completed: " + projectCompleted + "\n"
                + "----------------------------------------------" + "\n";
    }
    
    public String shortProjectSummary() {
		return "Project summary: \n================ \nName: " + name + "\n" + "Number: " + number + "\n";
    }

}
