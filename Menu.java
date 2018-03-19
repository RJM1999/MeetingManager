import java.util.Scanner;

public class Menu 
{
	/**
	 * main method that start the program
	 * @param args main method parameter
	 */
	public static void main(String args[])
	{
		Menu menu = new Menu();
		menu.processUserChoices();
	}
	
	/**
	 * method used to display the menu to the user
	 */
	public void displayMenu() 
	{
		System.out.println("\nPlease select one of the following options:");
		System.out.println("1. Add Employee");
		System.out.println("2. Delete Employee");
		System.out.println("3. Edit Employee");
		System.out.println("4. Search Employee");
		System.out.println("5. Add Meeting");
		System.out.println("6. Edit Meeting");
		System.out.println("7. Delete Meeting");
		System.out.println("8. Undo Previous Action");
		System.out.println("0. Exit");
	}
	
	public void processUserChoices()
	{
		String temp = "";
		Scanner input = new Scanner(System.in);
		//Tree tester = new Tree();

		while (!temp.equals("0"))
		{
			displayMenu();
			temp = input.nextLine();
			
			switch (temp)
			{
				//add employee
				case "1":
					
					System.out.println("Employee added.");
					break;
				//delete employee
				case "2":
					
					System.out.println("Employee deleted.");
					break;
				//edit employee
				case "3":
					
					System.out.println("Employee edited.");
					break;
				//search employee
				case "4":
					
					System.out.println("Employee searched.");
					break;
				//add meeting
				case "5":
					
					System.out.println("Meeting added.");
					break;
				//delete meeting
				case "6":
					
					System.out.println("Meeting deleted.");
					break;
				//edit meeting
				case "7":
					
					System.out.println("Meeting edited.");
					break;
				//undo previous action
				case "8":
					
					System.out.println("Action undone.");
					break;
				//exit
				case "0":
					System.out.println("Goodbye.");
					break;
				default:
					System.out.println("Error. Invalid choice.");
					break;
			}
		}
	}
	
	
}
