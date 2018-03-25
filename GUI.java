import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JTextArea;

/**
 * GUI Class
 * 
 * @author Sarah Hartley
 *
 */

public class GUI {

	
	public GUI() {
		
		gui();
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		new GUI();

	}

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
		JMenuItem undo = new JMenuItem("UNDO"); 
		meeting.add(undo);
		
		JMenuItem saveMeetings = new JMenuItem("Save meetings to a text file"); 
		file.add(saveMeetings);
		JMenuItem loadMeetings = new JMenuItem("Load meetings from a text file"); 
		file.add(loadMeetings);
		
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
				
					employeeFound.addMeeting();
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
				
					employeeFound.deleteMeeting();
				}
				catch(Exception e2) {
					System.out.println("Sorry, there was an error deleting the meeting. Please try again");
				}
			}
		});
		editMeeting.addActionListener(new ActionListener() { //EDIT MEETING
			public void actionPerformed(ActionEvent e) {
				try {
					String input = JOptionPane.showInputDialog(null,
						"Please enter the employee id");
					int id = Integer.parseInt(input);
					Employee employeeFound = list.find(id);
				
					employeeFound.editMeeting();
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
		undo.addActionListener(new ActionListener() { //UNDO
			public void actionPerformed(ActionEvent e) {
				Stack undo = new Stack();
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
				
				Employee found = list.find(10);
				found.addTestMeeting();
			}
		});
	}
	
}
