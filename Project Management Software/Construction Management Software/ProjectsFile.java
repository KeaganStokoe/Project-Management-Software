// Imports
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Keagan Stokoe
 * @since version 2.00
 *
 */
public class ProjectsFile {

	// Methods

	/**
	 * Connects to the Database
	 * 
	 * @return Statement Object of the Database
	 */
	public static Statement getDatabaseStatement() {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms?useSSL=false&allowPublicKeyRetrieval=true", "otheruser",
					"swordfish");
			Statement stmnt = conn.createStatement();
			return stmnt;
		} catch (SQLException e) {
			System.out.println("Could not establish connection to the database.");
			return null;
		}
	}

	/**
	 * Reads Projects from Database into an ArrayList
	 * 
	 * @return ArrayList of Project objects
	 */
	public static ArrayList<Project> getProjects() {
		// Create ArrayList of projects
		ArrayList<Project> projects = new ArrayList<Project>();

		try {
			Statement stmnt = getDatabaseStatement();
			// String used to select all fields needed from tables in Database
			String select = "select * from Projects" + " inner join Contractor on Projects.ContractorID = Contractor.ID"
					+ " inner join Customer on Projects.CustomerID = Customer.ID"
					+ " inner join Architect on Projects.ArchitectID = Architect.ID";

			ResultSet results = stmnt.executeQuery(select);

			while (results.next()) {
				// Creating Project Object

				String projectNumber = results.getString("ProjectNumber");
				String name = results.getString("Name");
				String buildingType = results.getString("BuildingType");
				String address = results.getString("BuildingAddress");
				int erfNumber = results.getInt("ErfNumber");
				Float totalFee = results.getFloat("TotalFee");
				Float totalPaid = results.getFloat("AmountPaid");
				String deadlineDate = results.getString("Deadline");
				String projectCompleted = results.getString("FINALISED");

				// Creating and Assigning Project People
				Person architect = new Architect(results.getString("Architect.NAME"),
						results.getString("Architect.PhoneNumber"), results.getString("Architect.EmailAddress"),
						results.getString("Architect.ADDRESS"));

				Person contractor = new Contractor(results.getString("contractor.NAME"),
						results.getString("contractor.PhoneNumber"), results.getString("contractor.EmailAddress"),
						results.getString("contractor.ADDRESS"));

				Person customer = new Customer(results.getString("customer.NAME"),
						results.getString("customer.PhoneNumber"), results.getString("customer.EmailAddress"),
						results.getString("customer.ADDRESS"));

				Project project = new Project(projectNumber, name, buildingType, address, erfNumber, totalFee,
						totalPaid, deadlineDate, architect, contractor, customer, projectCompleted);

				// Adding Project record to Project ArrayList
				projects.add(project);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Returns Project Object ArrayList
		return projects;
	}

	/** Adds Project Object ArrayList to Database */
	public static void addProjectsToDatabase(Project project) {
		Statement statement = getDatabaseStatement();
		int count = 0;

		try {
			ResultSet num = statement.executeQuery("select ProjectNumber from Projects");

			while (num.next()) {
				count++;
			}

			// String used to insert new Projects fields into the Database
			String insertProjectString = "insert into Projects values ('" + project.getName() + "', '"
					+ project.getNumber() + "', '" + project.getBuildingType() + "', '" + project.getAddress() + "', '"
					+ project.getErfNumber() + "', '" + project.getDeadlineDate() + "', " + project.getTotalFee() + ", "
					+ project.getTotalPaid() + ", " + (count + 1) + ", " + (count + 1) + ", " + (count + 1) + ", '"
					+ project.isProjectCompleted() + "', NULL)";

			statement.executeUpdate(insertProjectString);

			String insertContractorString = "insert into Contractor values (" + (count + 1) + ", '"
					+ project.contractor.getName() + "', '" + project.contractor.getPhoneNumber() + "', '"
					+ project.contractor.getEmailAddress() + "', '" + project.contractor.getPhysicalAddress() + "')";

			statement.executeUpdate(insertContractorString);

			String insertCustomerString = "insert into Customer values (" + (count + 1) + ", '"
					+ project.customer.getName() + "', '" + project.customer.getPhoneNumber() + "', '"
					+ project.customer.getEmailAddress() + "', '" + project.customer.getPhysicalAddress() + "')";

			statement.executeUpdate(insertCustomerString);

			String insertArchitectString = "insert into Architect values (" + (count + 1) + ", '"
					+ project.architect.getName() + "', '" + project.architect.getPhoneNumber() + "', '"
					+ project.architect.getEmailAddress() + "', '" + project.architect.getPhysicalAddress() + "')";

			statement.executeUpdate(insertArchitectString);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates a Projects Record in the Database
	 * 
	 * @param field   Which field to update for that projects record
	 * @param project Which Project's record to update
	 */
	public static void updateDatabase(String field, int project, String update) {
		Statement statement = getDatabaseStatement();

		// Strings for varies Update queries
		String updateDeadline = "update Projects set DEADLINE = '" + update + "' where ProjectNumber = " + project;
		String updateAmountPaid = "update Projects set AmountPaid = " + update + " where ProjectNumber = " + project;
		String successMessage = "Update successful.";

		try {
			// If statements to decide which update to make
			if (field.equals("Deadline")) {
				statement.executeUpdate(updateDeadline);
				System.out.println(successMessage);
			}

			if (field.equals("AmountPaid")) {
				statement.executeUpdate(updateAmountPaid);
				System.out.println(successMessage);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Unfortunately an arror has occured. Please try again.");
		}
	}
	
	/**
	 * Update contractor details method. <br>
	 * Update details of contrcator working on the project.
	 * 
	 * @param index       integer representing the index of the project to be edited
	 * @param updatePhone String value for SQL query to run update of phone number
	 * @param updateEmail String value for SQL query to run update of email address
	 * @since version 2.00
	 * @see editProjectDetails methods
	 */

	public static void updateContractorDatabase(int project, String updatePhone, String updateEmail)
			throws SQLException {
		Statement statement = getDatabaseStatement();

		String updateContractor = "update Contractor set PhoneNumber = '" + updatePhone + "', EmailAddress = '"
				+ updateEmail + "' where ID = " + project;
		try {
			statement.executeUpdate(updateContractor);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Unfortunately an arror has occured. Please try again.");

		}

	}

	/**
	 * Finalise method. <br>
	 * Generate invoice and mark project as completed.
	 *
	 * @param index       integer representing the index of the project to be edited
	 * @param updateStatus SQL query to run update
	 * @param completionDate date project is completed
	 * @since version 2.00
	 * @see editProjectDetails methods
	 */
	public static void finaliseProject(int project, String updateStatus, String completionDate)
			throws SQLException, IOException {
		Statement statement = getDatabaseStatement();
		String updateFinalised = "update Projects set FINALISED = '" + updateStatus + "', CompletionDate = '"
				+ completionDate + "' where ProjectNumber = " + project;
		ArrayList<Project> projectList = getProjects();
		Project currentProject = projectList.get(project - 1);
		float totalOutstanding = currentProject.getTotalFee() - currentProject.getTotalPaid();

		FileWriter fileWriter = new FileWriter("completedProjects.txt", true);
		Formatter formatter = new Formatter(fileWriter); // Create output.txt file to which results are written

		try {
			statement.executeUpdate(updateFinalised);
			if (totalOutstanding > 0) {
				System.out.println("Project Invoice:\n========================\n\nProject Name:"
						+ currentProject.getName() + "\nCustomer name:" + currentProject.customer.getName()
						+ "\nCustomer phone number:" + currentProject.customer.getPhoneNumber() + "\nCustomer email:"
						+ currentProject.customer.getEmailAddress() + "\nTotal outstanding: R" + totalOutstanding
						+ "\n\nThank you for using Poise Construction Management.");
			} else {
				System.out.println("Fee paid in full. Thank you for using Poise Construction Management.");
			}

			// Write completed project to text file called completedProjects.txt
			currentProject.writeToFile(formatter);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Unfortunately an arror has occured. Please try again.");

		}

	}

}
