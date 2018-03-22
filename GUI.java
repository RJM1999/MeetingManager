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
 * @version v1.0
 */

public class GUI {

	EmployeeList list = new EmployeeList();

	
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

		JTextArea calculate = new JTextArea(5,5);
		//calculate.setBounds(x,y,width,height); /*x,y,width,height are integer Values*/
		calculate.setBounds(50,50,250,200);
		calculate.setVisible(true);
		calculate.setLineWrap (true);
		
		EmployeeList test = new EmployeeList();
		
		JFrame f = new JFrame();
		f.setTitle("Meeting Manager");
		f.setVisible(true);
		f.setSize(600, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		
		JPanel p = new JPanel(); //creating panel
		JPanel background = new JPanel(); //creating panel
		background.setBackground(Color.white); //setting background colour to white
		JButton exit = new JButton("EXIT");
		        
		//Creating a menu bar
		JMenuBar mb = new JMenuBar();
		 
		JMenu employee = new JMenu("Employee");
		mb.add(employee);
		JMenu meeting = new JMenu("Meeting");
		mb.add(meeting);
		JMenu file = new JMenu("File");
		mb.add(file);
		
		//Creating drop down options
		JMenuItem addEmployee = new JMenuItem("Add an employee"); 
		employee.add(addEmployee);
		JMenuItem deleteEmployee = new JMenuItem("Delete an employee"); 
		employee.add(deleteEmployee);
		JMenuItem displayEmployees = new JMenuItem("Display a list of all the employees"); 
		employee.add(displayEmployees);
		
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
		f.add(background);
		f.add(p,BorderLayout.EAST);
		f.setJMenuBar(mb);
		f.add(calculate);
		
		System.setOut(new PrintStream(new Console(calculate)));
		
		//Action Listeners
		addEmployee.addActionListener(new ActionListener() { //ADD EMPLOYEE
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null,
						"Please enter the employee id");
				String name = JOptionPane.showInputDialog(null,
						"Please enter the employee name");
				int id = Integer.parseInt(input);
				test.addToList(id, name);
			}
		});
		deleteEmployee.addActionListener(new ActionListener() { //DELETE EMPLOYEE
			public void actionPerformed(ActionEvent e) {
				test.delete();
			}
		});
		displayEmployees.addActionListener(new ActionListener() { //DISPLAY EMPLOYEES
			public void actionPerformed(ActionEvent e) {
				test.printList();
			}
		});
		
		addMeeting.addActionListener(new ActionListener() { //ADD MEETING
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null,
						"Please enter the employee id");
				int id = Integer.parseInt(input);
				Employee employeeFound = test.find(id);
				
				employeeFound.addMeeting();
			}
		});
		deleteMeeting.addActionListener(new ActionListener() { //DELETE MEETING
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null,
						"Please enter the employee id");
				int id = Integer.parseInt(input);
				Employee employeeFound = test.find(id);
				
				employeeFound.deleteMeeting();
			}
		});
		editMeeting.addActionListener(new ActionListener() { //EDIT MEETING
			public void actionPerformed(ActionEvent e) {
				System.out.println("EDIT MEETING");
			}
		});
		printMeetings.addActionListener(new ActionListener() { //PRINT MEETINGS TO CONSOLE
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null,
						"Please enter the employee id");
				int id = Integer.parseInt(input);
				Employee found = test.find(id);
				found.printMeetings();
			}});
		undo.addActionListener(new ActionListener() { //UNDO
			public void actionPerformed(ActionEvent e) {
				System.out.println("UNDO");
			}
		});
		
		saveMeetings.addActionListener(new ActionListener() { //SAVE MEETINGS TO TEXT FILE
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null,
						"Please enter the employee id");
				int id = Integer.parseInt(input);
				Employee found = test.find(id);
				found.saveMeetings();
			}
		});
		loadMeetings.addActionListener(new ActionListener() { //LOAD MEETINGS
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null,
						"Please enter the employee id");
				int id = Integer.parseInt(input);
				Employee found = test.find(id);
				found.loadMeetings();
			}
		});
		
		exit.addActionListener(new ActionListener() { //EXIT 
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
}
