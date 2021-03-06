import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * GUI Class
 * 
 * @author Sarah Hartley
 *
 */

public class GUI {

	int lastActionTaken = 0;
	int idOfLastAction = 0;
	Diary diary = new Diary();
	Stack undo = new Stack();
	
	/**
	 * Constructor
	 */
	public GUI() {
		
		gui();
		
	}
	
	
	/**
	 * @param args normal main arguments
	 */
	public static void main(String[] args) {
		
		new GUI();

	}

	/**
	 * Method to create and run the GUI (menu)
	 */
	public void gui() {

		JTextArea console = new JTextArea();
		console.setBounds(50,50,250,200);
		console.setVisible(true);
		console.setLineWrap (true);
		console.setFont(new Font("Arial", Font.PLAIN, 18));
		
		EmployeeList list = new EmployeeList();
		
		JFrame f = new JFrame();
		f.setTitle("Meeting Manager");
		f.setVisible(true);
		f.setSize(800, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		
		JPanel p = new JPanel(); //creating panel
		JPanel background = new JPanel(); //creating panel
		background.setBackground(Color.white); //setting background colour to white
		JButton exit = new JButton("EXIT");
		exit.setFont(new Font("Arial", Font.PLAIN, 20));
		        
		//Creating a menu bar
		JMenuBar mb = new JMenuBar();
		 
		JMenu employee = new JMenu("Employee");
		employee.setFont(new Font("Arial", Font.PLAIN, 20));
		mb.add(employee);
		JMenu meeting = new JMenu("Meeting");
		meeting.setFont(new Font("Arial", Font.PLAIN, 20));
		mb.add(meeting);
		JMenu file = new JMenu("File");
		file.setFont(new Font("Arial", Font.PLAIN, 20));
		mb.add(file);
		
		//Creating drop down options
		JMenuItem addEmployee = new JMenuItem("Add an employee"); 
		employee.add(addEmployee);
		JMenuItem deleteEmployee = new JMenuItem("Delete an employee"); 
		employee.add(deleteEmployee);
		JMenuItem displayEmployees = new JMenuItem("Display a list of all the employees"); 
		employee.add(displayEmployees);
		JMenuItem searchEmployee = new JMenuItem("Search for an employee");
		employee.add(searchEmployee);
		
		JMenuItem addMeeting = new JMenuItem("Add a meeting"); 
		meeting.add(addMeeting);
		JMenuItem deleteMeeting = new JMenuItem("Delete a meeting"); 
		meeting.add(deleteMeeting);
		JMenuItem editMeeting = new JMenuItem("Edit a meeting"); 
		meeting.add(editMeeting);
		JMenuItem printMeetings = new JMenuItem("Print meetings"); 
		meeting.add(printMeetings);
		JMenuItem searchMeetings = new JMenuItem("Search for meeting availabilities"); 
		meeting.add(searchMeetings);
		JMenuItem undoOption = new JMenuItem("UNDO"); 
		meeting.add(undoOption);
		
		JMenuItem saveMeetings = new JMenuItem("Save meetings to a text file"); 
		file.add(saveMeetings);
		JMenuItem loadMeetings = new JMenuItem("Load meetings from a text file"); 
		file.add(loadMeetings);
		JMenuItem printStack = new JMenuItem("Print Stack"); 
		file.add(printStack);
		
		//Displaying the menu
		p.add(exit);
		f.add(background);
		f.add(p,BorderLayout.EAST);
		f.setJMenuBar(mb);
		f.add(console);
		
		System.setOut(new PrintStream(new Console(console)));
		
		//Action Listeners
		addEmployee.addActionListener(new ActionListener() { //ADD EMPLOYEE
			public void actionPerformed(ActionEvent e) {
				try {
					String input = JOptionPane.showInputDialog(null,
						"Please enter the employee id");
					String name = JOptionPane.showInputDialog(null,
						"Please enter the employee name");
					int id = Integer.parseInt(input);
					list.addToList(id, name);
				}
				catch(Exception e2) {
					System.out.println("Sorry, there was an error adding to the list. Please try again");
				}
			}
		});
		deleteEmployee.addActionListener(new ActionListener() { //DELETE EMPLOYEE
			public void actionPerformed(ActionEvent e) {
				list.delete();
			}
		});
		displayEmployees.addActionListener(new ActionListener() { //DISPLAY EMPLOYEES
			public void actionPerformed(ActionEvent e) {
				list.printList();
			}
		});
		searchEmployee.addActionListener(new ActionListener() { //SEARCH FOR EMPLOYEE
			public void actionPerformed(ActionEvent e) {
				list.searchForEmployee();
			}
		});
		
		addMeeting.addActionListener(new ActionListener() { //ADD MEETING
			public void actionPerformed(ActionEvent e) {
				try {
					String input = JOptionPane.showInputDialog(null,
						"Please enter the employee id");
					int id = Integer.parseInt(input);
					Employee employeeFound = list.find(id);
				
					Date startDate = diary.inputDateOfMeeting();
					Date endDate = new Date(startDate.getTime() + diary.calculateDuration());
					String desc = diary.getDesc();
					
					idOfLastAction = id;
					lastActionTaken = 1;
					employeeFound.addMeeting(startDate, endDate, desc);
					handleStack(employeeFound, startDate);
					System.out.println("Stack handled");

				}
				catch(Exception e2) {
					System.out.println("Sorry, there was an error adding the meeting. Please try again");
				}
			}
		});
		deleteMeeting.addActionListener(new ActionListener() { //DELETE MEETING
			public void actionPerformed(ActionEvent e) {
				try {
					String input = JOptionPane.showInputDialog(null,
						"Please enter the employee id");
					int id = Integer.parseInt(input);
					Employee employeeFound = list.find(id);
					Date dateToDelete = diary.inputDateOfMeeting();
					
					idOfLastAction = id;
					lastActionTaken = 2;
					
					handleStack(employeeFound, dateToDelete);
					employeeFound.deleteMeeting(dateToDelete);
				}
				catch(Exception e2) {
					System.out.println("Sorry, there was an error deleting the meeting. Please try again");
				}
			}
		});
		editMeeting.addActionListener(new ActionListener() { //EDIT MEETING
			public void actionPerformed(ActionEvent e) {
				try {
					
					String inputId = JOptionPane.showInputDialog(null,
						"Please enter the employee id");
					int id = Integer.parseInt(inputId);
					Employee employeeFound = list.find(id);
					Date startDate = diary.inputDateOfMeeting();
					
					idOfLastAction = id;
					lastActionTaken = 3;
					
					handleStack(employeeFound, startDate);
					
					String input = JOptionPane.showInputDialog(null,
							"What would you like to edit? \n 1:date\n2: duration\n3: description");
					int option = Integer.parseInt(input);
					
					if (option == 1) {
						Date newDate = diary.inputDateOfMeeting();
						pushUpdatedMeeting(startDate, employeeFound, null, null, newDate);
						employeeFound.editMeeting(option, startDate, null, null, newDate);
					}
					else if (option == 2) {
						Date endTime = new Date(startDate.getTime() + diary.calculateDuration());
						pushUpdatedMeeting(startDate, employeeFound, endTime, null, null);
						employeeFound.editMeeting(option, startDate, endTime, null, null);
					}
					else if (option == 3) {
						String desc = diary.getDesc();
						pushUpdatedMeeting(startDate, employeeFound, null, desc, null);
						employeeFound.editMeeting(option, startDate, null, desc, null);
					}
				}
				catch(Exception e2) {
					System.out.println("Sorry, there was an error editing the meeting. Please try again");
				}
			}
		});
		printMeetings.addActionListener(new ActionListener() { //PRINT MEETINGS TO CONSOLE
			public void actionPerformed(ActionEvent e) {
				try {
					String input = JOptionPane.showInputDialog(null,
						"Please enter the employee id");
					int id = Integer.parseInt(input);
					Employee found = list.find(id);
					found.printMeetings();
				}
				catch(Exception e2) {
					System.out.println("Sorry, there was an error printing the meetings. Please try again");
				}
			}});
		searchMeetings.addActionListener(new ActionListener() { //SEARCH FOR AVAILABILITIES
			public void actionPerformed(ActionEvent e) {
				long start = System.nanoTime();
				//creating date formatter
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm");
				String[] employeeArray;
				String employeeNames = JOptionPane.showInputDialog(null,
						"Please enter the names of employees you would like to compare, separated by commas");
				employeeArray = employeeNames.split(","); //Creating array of names 
				
				int[] employee1Schedule = new int[9];
				int[] employee2Schedule = new int[9];
				int[] employee3Schedule = new int[9];
				int[] employee4Schedule = new int[9];
				int[] employee5Schedule = new int[9];
				
				for (int i=0; i<8; i++) { //initialising employee schedules
					employee1Schedule[i] = 0;
					employee2Schedule[i] = 0;
					employee3Schedule[i] = 0;
					employee4Schedule[i] = 0;
					employee5Schedule[i] = 0;
				}
				
				Date[] testTime = new Date[8];
				for(int l = 0; l<8; l++) {
					
					Date meetingDate;
					String stringDate;
					//SimpleDateFormat datesFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm"); //Date formatter
					for (int i = 9; i<17; i++) {
						if (i==9) {
							stringDate = "23-03-2018T0" + i + ":00";
						}
						else {
							stringDate = "23-03-2018T" + i + ":00";
						}
						
						try 
						{
							meetingDate = dateFormat.parse(stringDate); //format
							testTime[i-9] = meetingDate; //Adding each hour of day to testTime array
						}
						catch (ParseException e2) //Catch errors
						{
							e2.printStackTrace(); //print it
						}
					}
				}
				for (int i = 0; i<employeeArray.length; i++) {
					
					Employee found = new Employee();
					boolean unavailable;
					found = list.findEmployee(employeeArray[i]);

					for (int j = 0; j<testTime.length; j++) {
						unavailable = found.checkMeetingExists(testTime[j]);
						if (unavailable == true) { //unavailable
							if (i==0) {
								employee1Schedule[j]= 1;
							}
							else if (i==1){
								employee2Schedule[j]= 1;
							}
							else if (i==2){
								employee3Schedule[j]= 1;
							}
							else if (i==3){
								employee4Schedule[j]= 1;
							}
							else if (i==4){
								employee5Schedule[j]= 1;
							}
						}
						else { //free
							if (i==0) {
								employee1Schedule[j] = 0;
							}
							else if (i==1){
								employee2Schedule[j]= 0;
							}
							else if (i==2){
								employee3Schedule[j]= 0;
							}
							else if (i==3){
								employee4Schedule[j]= 0;
							}
							else if (i==4){
								employee5Schedule[j]= 0;
							}
						}
					}
				}
			
				System.out.println("The available times for meetings are : ");
				if (employee1Schedule[0] == 0 && employee2Schedule[0] == 0 && employee3Schedule[0] == 0 && employee4Schedule[0] == 0 && employee5Schedule[0] == 0) 
				{
					System.out.println(" 09:00 ");
				}
				if (employee1Schedule[1] == 0 && employee2Schedule[1] == 0 && employee3Schedule[1] == 0 && employee4Schedule[1] == 0 && employee5Schedule[1] == 0) 
				{
					System.out.println(" 10:00 ");
				}
				if (employee1Schedule[2] == 0 && employee2Schedule[2] == 0 && employee3Schedule[2] == 0 && employee4Schedule[2] == 0 && employee5Schedule[2] == 0) 
				{
					System.out.println(" 11:00 ");
				}
				if (employee1Schedule[3] == 0 && employee2Schedule[3] == 0 && employee3Schedule[3] == 0 && employee4Schedule[3] == 0 && employee5Schedule[3] == 0) 
				{
					System.out.println(" 12:00 ");
				}
				if (employee1Schedule[4] == 0 && employee2Schedule[4] == 0 && employee3Schedule[4] == 0 && employee4Schedule[4] == 0 && employee5Schedule[4] == 0) 
				{
					System.out.println(" 13:00 ");
				}
				if (employee1Schedule[5] == 0 && employee2Schedule[5] == 0 && employee3Schedule[5] == 0 && employee4Schedule[5] == 0 && employee5Schedule[5] == 0) 
				{
					System.out.println(" 14:00 ");
				}
				if (employee1Schedule[6] == 0 && employee2Schedule[6] == 0 && employee3Schedule[6] == 0 && employee4Schedule[6] == 0 && employee5Schedule[6] == 0) 
				{
					System.out.println(" 15:00 ");
				}
				if (employee1Schedule[7] == 0 && employee2Schedule[7] == 0 && employee3Schedule[7] == 0 && employee4Schedule[7] == 0 && employee5Schedule[7] == 0) 
				{
					System.out.println(" 16:00 ");
				}
				
				String input = JOptionPane.showInputDialog(null,
						"What time would you like to add the meeting? Please enter the time in hh:00 format");
	
				Date startDate;
				String stringDate = "23-03-2018T" + input; 
				try 
				{
					startDate = dateFormat.parse(stringDate); //format
					Date endDate = new Date(startDate.getTime() + diary.calculateDuration());
					String desc = diary.getDesc();
					Employee employeeFound;
					for (int i=0; i<employeeArray.length; i++) {
						employeeFound = list.findEmployee(employeeArray[i]);
						employeeFound.addMeeting(startDate, endDate, desc);
					}
				}
				catch (ParseException e2) //Catch errors
				{
					e2.printStackTrace(); //print it
				}
				long endTime = System.nanoTime();
				long duration =  endTime - start;
				System.out.println("Total time take in " + duration);
			}
			

		});
		undoOption.addActionListener(new ActionListener() { //UNDO
			public void actionPerformed(ActionEvent e) {
				//ACTIONS 1:ADD 2:DELETE 3:EDIT
				if (lastActionTaken == 1) {
					Meeting node = undo.popFromStack();
					Employee found = list.find(idOfLastAction);
					Date startTime = node.getStartTime();
					found.deleteMeeting(startTime);
				}
				else if (lastActionTaken == 2) {
					Meeting node = undo.popFromStack();
					Employee found = list.find(idOfLastAction);
					Date startTime = node.getStartTime();
					Date endTime = node.getEndTime();
					String description = node.getDescription();
					found.addMeeting(startTime, endTime, description);
				}
				else if (lastActionTaken == 3) {
					Meeting changedNode = undo.popFromStack();
					Meeting originalNode = undo.popFromStack();
					Employee found = list.find(idOfLastAction);
					if (changedNode.getStartTime() != originalNode.getStartTime()) {
						found.editMeeting(1, changedNode.getStartTime(), null, null, originalNode.getStartTime());
					}
					else if(changedNode.getEndTime() != originalNode.getEndTime()) {
						found.editMeeting(2, originalNode.getStartTime(), originalNode.getEndTime(), null, null);
					}
					else if(changedNode.getDescription() != originalNode.getDescription()) {
						found.editMeeting(3, originalNode.getStartTime(), null, originalNode.getDescription(), null);
					}
					else {
						System.out.println("Error");
					}
				}
			}
		});
		
		saveMeetings.addActionListener(new ActionListener() { //SAVE MEETINGS TO TEXT FILE
			public void actionPerformed(ActionEvent e) {
				try {
					String input = JOptionPane.showInputDialog(null,
						"Please enter the employee id");
					int id = Integer.parseInt(input);
					Employee found = list.find(id);
					found.saveMeetings();
				}
				catch(Exception e2) {
					System.out.println("Sorry, there was an error saving the meetings. Please try again");
				}
			}
		});
		loadMeetings.addActionListener(new ActionListener() { //LOAD MEETINGS
			public void actionPerformed(ActionEvent e) {
				try {
					String input = JOptionPane.showInputDialog(null,
						"Please enter the employee id");
					int id = Integer.parseInt(input);
					Employee found = list.find(id);
					found.loadMeetings();
				}
				catch(Exception e2) {
					System.out.println("Sorry, there was an error loading the meetings. Please try again");
				}
			}
		});
		
		exit.addActionListener(new ActionListener() { //EXIT 
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		printStack.addActionListener(new ActionListener() { //EXIT 
			public void actionPerformed(ActionEvent e) {
				undo.printStack();
			}
		});
	}
	
	/**
	 * Method to add a meeting to the stack for the undo method
	 * 
	 * @param employeeFound the employee whos diary the meeting is in
	 * @param startDate the start date and time of the meeting
	 */
	public void handleStack(Employee employeeFound, Date startDate) {
			Meeting meeting = employeeFound.stack(startDate);
			Date endTime = meeting.getEndTime();
			String description = meeting.getDescription();
			Meeting left = meeting.getLeft();
			Meeting right = meeting.getRight();
			
			undo.pushToStack(startDate, endTime, description, left, right);
	}
	
	/**
	 * Method to push the updated method to the stack
	 * 
	 * @param startDate the start time and date of the meeting
	 * @param found the employee who's diary the meeting is in
	 * @param endTime the end time and date of the meeting
	 * @param description a description of the meeting
	 * @param newStartDate the new start date and time 
	 */
	public void pushUpdatedMeeting(Date startDate, Employee found, Date endTime, String description, Date newStartDate) {
		
		Meeting meetingToEdit = found.stack(startDate);
		if (endTime == null) {
			endTime = meetingToEdit.getEndTime();
		}
		if (description == null) {
			description = meetingToEdit.getDescription();
		}
		Meeting left = meetingToEdit.getLeft();
		Meeting right = meetingToEdit.getRight();
		if (newStartDate != null) { //if the user changed the start date
			undo.pushToStack(newStartDate, endTime, description, left, right);
		}
		else {
			undo.pushToStack(startDate, endTime, description, left, right);
		}
	}
}
