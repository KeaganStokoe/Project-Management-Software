/**
 * Main project file. 
 *
 * @author Keagan Stokoe
 * @version 1.00, 26 April 2021
 */

// Import required libraries.
import java.util.*;
import java.io.IOException;
import java.sql.SQLException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

	/**
	 * Main method. <br>
	 * Main program logic that calls relevant functions based on user input.
	 * 
	 * @throws SQLException, IOException, ParseException
	 *
	 * 
	 * @since version 1.00
	 */

	public static void main(String[] args) throws IOException, ParseException, SQLException {
		boolean runProgram = true;
		Scanner scanner = new Scanner(System.in); // Open scanner
		while (runProgram) {
			int action = displayMenu(scanner); // Display menu to user
			if (action == 1) { // Create new project
				Project newProject = initialiseNewProject();
				ProjectsFile.addProjectsToDatabase(newProject);
			} else if (action == 2) { // Edit existing project
				displayProjectSummary();
				int projectIndex = scanner.nextInt() - 1;
				int editAction = userEditOptions(scanner);
				editProjectDetails(scanner, editAction, projectIndex);
			} else if (action == 3) { // Find uncompleted projects
				ArrayList<Project> projectList = ProjectsFile.getProjects();
				checkUncompleted(projectList);
			} else if (action == 4) { // Find overdue projects
				ArrayList<Project> projectList = ProjectsFile.getProjects();
				checkOverdue(projectList);
			} else if (action == 5) { // Search projects
				ArrayList<Project> projectList = ProjectsFile.getProjects();
				System.out.println(
						"Select an action:\n\n" + "1. Search by project number.\n" + "2. Search by project name.");
				int searchOption = scanner.nextInt();
				scanner.nextLine();
				if (searchOption == 1) { // Search by number
					searchByNumber(scanner, projectList);
				} else if (searchOption == 2) { // Search by name
					searchByName(scanner, projectList);
				} else {
					System.out.println("Invalid selection. Please try again.");
				}

			} else if (action == 6) { // Exit program
				runProgram = false;
				System.out.println("Thank you for using the system.");
				System.exit(0);
			} else {
				System.out.println("Invalid action. Please try again. \n");
			}
		}

	}

	// ---------------------- METHODS -------------------------

	/**
	 * Display menu method. <br>
	 * The methods displays the menu to the user.
	 *
	 * @param scanner scanner object used to receive user input
	 * @param val2    the second value
	 * @return integer value used to indicate action required
	 * @see main method
	 * @since version 1.00
	 */

	private static int displayMenu(Scanner scanner) {

		System.out.print("\n Enter a number to complete an action:" + "\n");
		System.out.println("\n");
		System.out.print("1. Add a new project." + "\n");
		System.out.print("2. Update an existing project." + "\n");
		System.out.print("3. Show uncompleted projects." + "\n");
		System.out.print("4. Show overdue projects." + "\n");
		System.out.print("5. Search projects." + "\n");
		System.out.print("6. Exit. \n");
		int displayMenuaction = scanner.nextInt();
		scanner.nextLine(); // Consume newline left-over
		return displayMenuaction;
	}

	/**
	 * Initialise project method. <br>
	 * The method initialises the creation of a new project.
	 * 
	 * @return
	 * @return integer value used to indicate action required
	 * @see createArchitect, createContractor, createCustomer, createNewProject and
	 *      readFileLines
	 * @since version 1.00
	 */

	// This method initialises the creation of a new project by asking the user to
	// provide various inputs and calling the appropriate methods.
	private static Project initialiseNewProject() throws FileNotFoundException, IOException, ParseException {
		Scanner scanner = new Scanner(System.in); // Open scanner

		try {
			// Request architect details and create architect
			Person architect = createArchitect(scanner);

			// Request contractor details and create contractor
			Person contractor = createContractor(scanner);

			// Request customer details and create customer
			Person customer = createCustomer(scanner);

			// Request project details and create project.
			Project project = createNewProject(scanner, architect, contractor, customer);
			return project;

			// Add project to array list of projects
			// readFileLines();

		} catch (Exception e) {
			System.out.println("Unfortunately an error has occurred:" + e.getMessage());
			System.out.println("Please try again.");
			return null;
		}

	}

	/**
	 * Create new project method. <br>
	 * The method creates a new project object.
	 *
	 * @param scanner    scanner object used to receive user input
	 * @param architect  an object of person type. Each project has an architect.
	 * @param contractor an object of person type. Each project has an contractor.
	 * @param customer   an object of person type. Each project has an customer.
	 * @return New Project object
	 * @since version 1.00
	 */

	private static Project createNewProject(Scanner scanner, Person architect, Person contractor, Person customer)
			throws IOException, ParseException {

		System.out.print("Project number: ");
		String projectNumber = scanner.nextLine();
		System.out.print("Project name: ");
		String projectName = scanner.nextLine();
		System.out.print("Building type: ");
		String buildingType = scanner.nextLine();
		System.out.print("Address: ");
		String address = scanner.nextLine();
		System.out.print("ERF: ");
		int erfNumber = scanner.nextInt();
		scanner.nextLine(); // Consume newline left-over
		System.out.print("Total fee: ");
		float totalFee = scanner.nextFloat();
		System.out.print("Total paid: ");
		float totalPaid = scanner.nextFloat();
		scanner.nextLine(); // Consume newline left-over
		System.out.print("Deadline (dd/mm/yyyy): ");
		String deadlineDate = scanner.nextLine();

		// Create a new project using the Project constructor.
		try {
			Project project = new Project(projectNumber, projectName, buildingType, address, erfNumber, totalFee,
					totalPaid, deadlineDate, architect, contractor, customer, "no");
			System.out.println("New project successfully created. Details: " + "\n");
			// System.out.println(project.toString());
			project.description(); // Print project summary using description method defined in Project.java
			FileWriter fileWriter = new FileWriter("output.txt", true);
			Formatter formatter = new Formatter(fileWriter); // Create output.txt file to which results are written
			project.writeToFile(formatter);
			return project;

		} catch (Exception e) {
			System.out.println("An error seems to have occurred while creating your project - " + e.getMessage());
			System.out.println("Our apologies. Please try again.");
			return null;
		}
	}

	/**
	 * Create architect method. <br>
	 * Method that requests architect details and creates a new Architect Person
	 * object based on the input. The architect person object is then used as an
	 * input paramater in the Project constructor function.
	 *
	 * @param scanner scanner object used to receive user input.
	 * @return New Architect object
	 * @since version 1.00
	 * @see createNewProject method
	 */

	private static Person createArchitect(Scanner scanner) {
		System.out.print("Architect name: ");
		String architectName = scanner.nextLine();
		System.out.print("Architect phone number: ");
		String architectPhone = scanner.nextLine();
		System.out.print("Architect email address: ");
		String architectEmail = scanner.nextLine();
		System.out.print("Architect physical address: ");
		String architectAddress = scanner.nextLine();
		Person architect = new Person(architectName, architectPhone, architectEmail, architectAddress);
		return architect;

	}

	/**
	 * Create customer method. <br>
	 * Method that requests customer details and creates a new Customer Person
	 * object based on the input. The customer person object is then used as an
	 * input paramater in the Project constructor function.
	 *
	 * @param scanner scanner object used to receive user input.
	 * @return New Customer object
	 * @since version 1.00
	 * @see createNewProject method
	 */

	private static Person createCustomer(Scanner scanner) {
		System.out.print("Customer name: ");
		String customerName = scanner.nextLine();
		System.out.print("Customer phone number: ");
		String customerPhone = scanner.nextLine();
		System.out.print("Customer email address: ");
		String customerEmail = scanner.nextLine();
		System.out.print("Customer physical address: ");
		String customerAddress = scanner.nextLine();
		Person customer = new Person(customerName, customerPhone, customerEmail, customerAddress);
		return customer;

	}

	/**
	 * Create contractor method. <br>
	 * Method that requests contractor details and creates a new contractor Person
	 * object based on the input. The contractor person object is then used as an
	 * input paramater in the Project constructor function.
	 *
	 * @param scanner scanner object used to receive user input.
	 * @return New Contractor object
	 * @since version 1.00
	 * @see createNewProject method
	 */

	private static Person createContractor(Scanner scanner) {
		System.out.print("Contractor name: ");
		String contractorName = scanner.nextLine();
		System.out.print("Contractor phone number: ");
		String contractorPhone = scanner.nextLine();
		System.out.print("Contractor email address: ");
		String contractorEmail = scanner.nextLine();
		System.out.print("Contractor physical address: ");
		String contractorAddress = scanner.nextLine();
		Person contractor = new Person(contractorName, contractorPhone, contractorEmail, contractorAddress);
		return contractor;
	}

	/**
	 * Display project summary method. <br>
	 * The methods displays name and number of projects in the system.
	 *
	 * @since version 2.00
	 */

	private static void displayProjectSummary() {
		ArrayList<Project> projectList = ProjectsFile.getProjects();
		for (int i = 0; i < projectList.size(); i++) {
			System.out.println(projectList.get(i).shortProjectSummary());
		}
		System.out.println(
				"Please enter the project number of the project you wish to edit. Current project numbers range from 1 to "
						+ (projectList.size()) + ".");
	}

	/**
	 * Edit project method. <br>
	 * Method that provides user with edit options.
	 *
	 * @param scanner scanner object used to receive user input.
	 * @return integer used to determine edit action
	 * @since version 1.00
	 */

	private static int userEditOptions(Scanner scanner) {
		System.out.print("Enter a number to complete an action: " + "\n");
		System.out.print("1. Update project deadline" + "\n");
		System.out.print("2. Update fees paid" + "\n");
		System.out.print("3. Update contractor details " + "\n");
		System.out.print("4. Finalise project " + "\n");
		int action = scanner.nextInt();
		scanner.nextLine(); // Consume newline left-over
		return action;
	}

	/**
	 * Edit project details method. <br>
	 * Based on the action selected by the user, edit the project.
	 *
	 * @param scanner scanner object used to receive user input.
	 * @param action  integer representing the action to perform.
	 * @param index   integer representing the index of the project to be edited
	 * @throws SQLException
	 * @since version 1.00
	 * @see various update methods
	 */

	// Switch statement used for improved readability.
	private static void editProjectDetails(Scanner scanner, int action, int index)
			throws ParseException, FileNotFoundException, IOException, SQLException {
		// ArrayList<Project> projectList = ProjectsFile.getProjects(); // Get arrayList
		// of projects
		try {
			switch (action) {
			case 1:
				System.out.print("Please enter new deadline (dd/mm/yyyy): ");
				String updateDeadline = scanner.nextLine();
				ProjectsFile.updateDatabase("Deadline", index + 1, updateDeadline);
				break;
			case 2:
				System.out.print("Please enter fees paid to date: ");
				String updateAmountPaid = scanner.nextLine();
				ProjectsFile.updateDatabase("AmountPaid", index + 1, updateAmountPaid);
				break;
			case 3:
				System.out.print("Contractor phone number: ");
				String updatePhone = scanner.nextLine();
				System.out.print("Contractor email number: ");
				String updateEmail = scanner.nextLine();
				ProjectsFile.updateContractorDatabase(index, updatePhone, updateEmail);
				break;
			case 4:
				System.out.println("Please enter date of completion:");
				String completionDate = scanner.nextLine();
				ProjectsFile.finaliseProject(index + 1, "yes", completionDate);
				break;
			case 5:
				break;
			default:
				System.out.print("Invalid selection.");
			}
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("An error has occurred. Please try again");
		}

	}

	/**
	 * Display uncompleted projects method. <br>
	 * Display all projects that are yet to be completed.
	 * 
	 * @param projectList list of project objects on which specific project is
	 *                    accessed.
	 * @since version 1.00
	 * @see main method
	 */

	private static void checkUncompleted(ArrayList<Project> projectList) {
		System.out.println("The following projects are uncompleted: \n");
		for (Project element : projectList) {
			if ((element.isProjectCompleted().equals("no"))) {
				System.out.print(element + "\n");
			}
		}
	}

	/**
	 * Display uncompleted and overdue projects method. <br>
	 * Display all projects that are yet to be completed and are overdue.
	 * 
	 * @param projectList list of project objects on which specific project is
	 *                    accessed.
	 * @throws ParseException
	 * @since version 1.00
	 * @see main method
	 */
	private static void checkOverdue(ArrayList<Project> projectList) throws ParseException {
		int overdueProjects = 0;
		Date dateToday = new Date();
		System.out.println("The following projects are overdue: \n");
		for (Project element : projectList) {
			// The conditional below checks whether a project is uncompleted and whether the
			// due date has passed.
			// Dates are checked using the compareTo method. The deadline date is compared
			// to the current date. If the deadline has passed, a value of 1 is returned,
			// indicating that it is overdue.
			Date deadline = new SimpleDateFormat("dd/MM/yyyy").parse(element.getDeadlineDate());
			if ((element.isProjectCompleted().equals("no")) && ((deadline).compareTo(dateToday)) < 0) {
				System.out.print(element + "\n");
				overdueProjects += 1;
			}
		}
		if (overdueProjects <= 0) {
			System.out.println("No projects are overdue.");
		}
	}

	/**
	 * Search by project name method. <br>
	 * Allows user to search for project based on project name.
	 * 
	 * @since version 2.00
	 */

	private static void searchByName(Scanner scanner, ArrayList<Project> projectList) {
		System.out.println("Enter project name:");
		String projectName = scanner.nextLine().toLowerCase();
		for (Project element : projectList) {
			if (element.getName().toLowerCase().equals(projectName)) {
				System.out.println(element + "\n");
			}

		}
	}

	/**
	 * Search by project number method. <br>
	 * Allows user to search for project based on project number.
	 * 
	 * @since version 2.00
	 */
	private static void searchByNumber(Scanner scanner, ArrayList<Project> projectList) {
		System.out.println("Enter project number:");
		String projectNum = scanner.nextLine();
		for (Project element : projectList) {
			if (element.getNumber().equals(projectNum)) {
				System.out.println(element + "\n");
			}

		}
	}

}
