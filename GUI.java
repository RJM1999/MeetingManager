import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
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
		JButton runTests = new JButton("RUN TESTS");
		runTests.setFont(new Font("Arial", Font.PLAIN, 20));
		        
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
		p.add(runTests);
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
		searchMeetings.addActionListener(new ActionListener() { //UNDO
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null,
						"Please enter the number of employees you would like to compare");
				int employeeNum = Integer.parseInt(input);
				employeeNum--;
				int numOfDays = 0;
				int daysLeft = 0;
				Date startDate = null;
				Date endDate = null;
				//creating date formatter
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm");
				//create array 0 to 7 for time slots during the day
				//each part of array represent a different time slot (e.g 9-10, 10-11 etc)
				int [] timeSlotsArray = new int[7];
				//get number of employee diarys from input
				//System.out.println("Please enter the number of employee diarys to be compared:");
				//employeeNum = input.nextInt() - 1;
				//create array for employees
				Employee[] employeeArray = new Employee[employeeNum];
				//initialise first part of employee array
				employeeArray[0] = list.getHead();
				//add the rest of the employees to array
				for(int i = 1; i < employeeNum; i++)
				{
					employeeArray[i] = employeeArray[i-1].getNext();
				}

				//get start date
				int startDateDay = 0;
				int startDateMonth = 0;
				int startDateYear = 0;
				
				//get start date day
				String day = JOptionPane.showInputDialog(null,
						"Please enter the day of the start date of the search: ");
				startDateDay = Integer.parseInt(day);
				
				//get start date month
				String month = JOptionPane.showInputDialog(null,
						"Please enter the month of the start date of the search: ");
				startDateMonth = Integer.parseInt(month);
				
				//get start date year
				String year = JOptionPane.showInputDialog(null,
						"Please enter the year: ");
				startDateYear = Integer.parseInt(year);
				
				Calendar calendar = Calendar.getInstance();
				calendar.set(startDateYear, startDateMonth, startDateDay, 9, 0, 0);
				startDate = calendar.getTime();
				//get end date
				int endDateDay = 0;
				int endDateMonth = 0;
				int endDateYear = 0;
				//get end date day
				String endDay = JOptionPane.showInputDialog(null,
						"Please enter the day of the end date of the search: ");
				endDateDay = Integer.parseInt(endDay);
				
				//get end date month
				String endMonth = JOptionPane.showInputDialog(null,
						"Please enter the month of the end date of the search: ");
				endDateMonth = Integer.parseInt(endMonth);
				
				//get end date year
				String endYear = JOptionPane.showInputDialog(null,
						"Please enter the year of the end date of the search: ");
				endDateYear = Integer.parseInt(endYear);
				
				calendar.set(endDateYear, endDateMonth, endDateDay, 17, 0, 0);
				endDate = calendar.getTime();
				numOfDays = (endDateDay - startDateDay) + 1;
				int[] numOfDaysArray = new int[numOfDays];
				daysLeft = numOfDays;
				//set calendar to startDate
				calendar.setTime(startDate);
				//create currentDate for traversing
				Date currentDate = startDate;
				//look at each employee diary
				for(int j = 0; j < employeeArray.length; j++)
				{
				//look at each day within the diary
				//for(int x = 0; x < numOfDaysArray.length; x++)
				//{
				//look at each time slot within each employee diary
					for(int i = 0; i < timeSlotsArray.length; i++)
					{
						//if all days have been checked
						if(daysLeft == 0)
						{
							for(int k = 0; k < timeSlotsArray.length; k++)
							{
								if(timeSlotsArray[k] == 0)
								{
									System.out.println("line 299");
									//show time slots available
									System.out.println("Free space for meeting for all employees at: ");
									if(k == 0)
									{
										System.out.println((k+1) + ". 9am");
									}
									if(k == 1)
									{
										System.out.println((k+1) + ". 10am");
									}
									if(k == 2)
									{
										System.out.println((k+1) + ". 11am");
									}
									if(k == 3)
									{
										System.out.println((k+1) + ". 12pm");
									}
									if(k == 4)
									{
										System.out.println((k+1) + ". 1pm");
									}
									if(k == 5)
									{
										System.out.println((k+1) + ". 2pm");
									}
									if(k == 6)
									{
										System.out.println((k+1) + ". 3pm");
									}
									if(k == 7)
									{
										System.out.println((k+1) + ". 4pm");
									}
								}
							}
							//ask for users choice
							String slot = JOptionPane.showInputDialog(null,
									"During which slot would you like to add a meeting?");
							int userSlotChoice = Integer.parseInt(slot);
				
							//ask for description
							String description = JOptionPane.showInputDialog(null,
									"Please enter the description");
							//String description = Integer.parseInt(slot);
				
							//add meetings to the time slots
							switch (userSlotChoice)
							{
							case 1:
								for(int q = 0; q < employeeNum; q++)
								{
									calendar.setTime(startDate);
									Date dateToAdd = null;
									calendar.add(Calendar.HOUR_OF_DAY, 1);
									dateToAdd = calendar.getTime();
									employeeArray[q].getDiary().addMeeting(startDate,dateToAdd,description);
								}
								break;
							case 2:
								break;
							case 3:
								break;
							case 4:
								break;
							case 5:
								break;
							case 6:
								break;
							case 7:
								break;
							case 8:
								break;
							}
						}
						else
						{
							//if hours are 17 (5 o'clock), then set hours to 9 and take 1 from days left
							//this sets currentHours to the morning of the next day
							if(calendar.HOUR_OF_DAY == 17)
							{
								calendar.add(Calendar.HOUR_OF_DAY, -8);
								calendar.add(Calendar.DAY_OF_MONTH, 1);
								//take one away from the daysLeft
								daysLeft--;
							}
							//if there is a meeting at current time
							else if(employeeArray[j].getDiary().findInTreeSearchMethod(currentDate))
							{
								System.out.println("line 388");
								//add 1 to part of timeSlotsArray[i] that represents that hour
								timeSlotsArray[i]++;
								//add 1 to part of numOfDaysArray[x] that represents the day
								//numOfDaysArray[x]++;
								//add 1 hour to currentDate
								calendar.add(Calendar.HOUR_OF_DAY, 1);
								currentDate = calendar.getTime();
							}
						}
					}
				//}
				}
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
						found.editMeeting(2, originalNode.getStartTime(), null, originalNode.getDescription(), null);
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
		runTests.addActionListener(new ActionListener() { //EXIT 
			public void actionPerformed(ActionEvent e) {
				list.addTestEmployee(10,"Jack");
				list.addTestEmployee(9,"Jill");
				
				Employee found = list.find(10);
				found.addTestMeeting();
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
		System.out.println("Method called");
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

