import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Diary class creating binary tree of meetings
 * 
 * @author Ross Maider
 * 
 */

public class Diary
{
	//Define Fields
	Meeting root;
	FileOutputStream outputStream = null;
	PrintWriter printWriter = null;
	
	/**
	 * Default constructor, no parameters.
	 */
	Diary()
	{
		root = null;
	}
	
	/**
	 * Constructor with parameters.
	 * @param inRoot - Meeting sets the root of the binary tree
	 */
	Diary(Meeting inRoot)
	{
		root = inRoot;
	}
	
	
	public void addMeeting()
	{
		Date startTime = inputDateOfMeeting();
		
		while(startTime == null)
		{
			startTime = inputDateOfMeeting();
		}
		Date endTime = new Date(startTime.getTime() + calculateDuration());
		
		String desc = getDesc();
		
		Meeting newNode = new Meeting(startTime, desc); //make new node
		
		newNode.setEndTime(endTime);
		
		if(findInTree(startTime) == null) //As long as the ID is not in the tree (duplicate data)
		{
			if(root == null)
			{
				root = newNode; //If the tree is empty set as the root if tree
			}
			else
			{
				Meeting current = root; //start at root
				Meeting parent; //prev
				
				while(true) //Until added
				{
					parent = current; //Set prev to current 
					
					if(startTime.before(current.getStartTime())) //If ID is smaller than current 
					{
						current = current.getLeft(); //go left
						
						if(current == null) //If its null
						{
							parent.setLeft(newNode); //Add to tree
							return; //Exit
						}
					}
					else //Otherwise 
					{
						current = current.getRight(); //Go right
						
						if(current == null) //If its null
						{
							parent.setRight(newNode); //Add 
							return; //exit
						}
					}
				}
			}
		}
		else //Otherwise 
		{
			System.out.println("Data was not added because it was already in the tree"); //Print message 
		}
	}
	
	
	public void addMeeting(Date startTime, Date endTime, String desc)
	{
		Meeting newNode = new Meeting(startTime, endTime, desc); //make new node
		
		if(findInTree(startTime) == null) //As long as the ID is not in the tree (duplicate data)
		{
			if(root == null)
			{
				root = newNode; //If the tree is empty set as the root if tree
			}
			else
			{
				Meeting current = root; //start at root
				Meeting parent; //prev
				
				while(true) //Until added
				{
					parent = current; //Set prev to current 
					
					if(startTime.before(current.getStartTime())) //If ID is smaller than current 
					{
						current = current.getLeft(); //go left
						
						if(current == null) //If its null
						{
							parent.setLeft(newNode); //Add to tree
							return; //Exit
						}
					}
					else //Otherwise 
					{
						current = current.getRight(); //Go right
						
						if(current == null) //If its null
						{
							parent.setRight(newNode); //Add 
							return; //exit
						}
					}
				}
			}
		}
		else //Otherwise 
		{
			System.out.println("Data was not added because it was already in the tree"); //Print message 
		}
	}
	
	
	/**
	 * This method adds to the meeting binary tree for testing purposes
	 * @param inStartTime - String date to be added 
	 * @param inEndTime -  String end date
	 * @param desc - String description
	 */
	public void addTestMeetings(String inStartTime,long dur, String desc)
	{
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm");
		Date startTime = null;
		try 
		{
			startTime = df.parse(inStartTime);
		} catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date endTime = new Date(startTime.getTime() + dur);
		
		Meeting newNode = new Meeting(startTime, endTime, desc); //make new node
		
		newNode.setEndTime(endTime);
		
		if(findInTree(startTime) == null) //As long as the ID is not in the tree (duplicate data)
		{
			if(root == null)
			{
				root = newNode; //If the tree is empty set as the root if tree
			}
			else
			{
				Meeting current = root; //start at root
				Meeting parent; //prev
				
				while(true) //Until added
				{
					parent = current; //Set prev to current 
					
					if(startTime.before(current.getStartTime())) //If ID is smaller than current 
					{
						current = current.getLeft(); //go left
						
						if(current == null) //If its null
						{
							parent.setLeft(newNode); //Add to tree
							return; //Exit
						}
					}
					else //Otherwise 
					{
						current = current.getRight(); //Go right
						
						if(current == null) //If its null
						{
							parent.setRight(newNode); //Add 
							return; //exit
						}
					}
				}
			}
		}
		else //Otherwise 
		{
			System.out.println("Data was not added because it was already in the tree"); //Print message 
		}
	}
	
	
	public void edit() {
		Date startDate = inputDateOfMeeting();
		Meeting meetingToEdit = findInTree(startDate);
		String input = JOptionPane.showInputDialog(null,
				"What would you like to edit? \n 1:date\n2: duration\n3: description");
		int option = Integer.parseInt(input);
		if (option == 1) {
			Date newDate = inputDateOfMeeting();
			meetingToEdit.setStartTime(newDate);
		}
		else if (option == 2) {
			Date endTime = new Date(startDate.getTime() + calculateDuration());
			meetingToEdit.setEndTime(endTime);
		}
		else if (option == 3) {
			String desc = getDesc();
			meetingToEdit.setDescription(desc);
		}
		System.out.println("Meeting modified");
	}
	
	
	/**
	 * This method gets the date for the meeting 
	 * @return Date The date of meeting in format dd-MM-yyyy
	 */
	public Date inputDateOfMeeting()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm"); //Date formatter
		Date meetingDate = null; //Returned variable 
		//String stringDate; //Input date
		
		//Get Date
		String stringDate = JOptionPane.showInputDialog(null,
				"Please input the date of the meeting and the start time of the meeting in the format dd-mm-yyyyThh:mm");

		
		//Try to format date
		try 
		{
			meetingDate = dateFormat.parse(stringDate); //format
			return meetingDate; //return it 
		}
		catch (ParseException e) //Catch errors
		{
			e.printStackTrace(); //print it
			System.out.println("This date is invlaid please try again");
			return null; //Return nothing 
		}
	}
	
	/**
	 * This method gets a suer to input the duration in minutes and converts to milliseconds. 
	 * @return millis Long variable for getting the meetings duration in milliseconds 
	 */
	public long calculateDuration()
	{
		//Get duration
		String input = JOptionPane.showInputDialog(null,
				"Please input the duration of the meeting in minutes");
		int duration = Integer.parseInt(input);

		long millis = duration * 60 * 1000; //convert to milliseconds 
		
		return millis; //return it
	}
	
	/**
-	 * This method calls the display tree
+	 * This method calls the display tree method
	 */
	public void printTree()
	{
		displayTree(root);
	}
	
	/**
-	 * 
+	 * This method prints out the tree on its side
	 * @param rootOfTree Meeting the root of the tree
	 */
	public void displayTree(Meeting rootOfTree)
	{
		int i;
	      int indent = 0;

	        if (rootOfTree != null) //If the root is not null
	        {
	           indent += 6; //Add  6
	           displayTree(rootOfTree.getLeft()); //Go to the right 
  
	            System.out.println("      " + rootOfTree.getData());

	            displayTree(rootOfTree.getRight());

	            indent -= 6;
	         }
	}
	
	/**
	 * This method is used to find a meeting within the binary tree.
	 * @param dateOfMeeting - Date field the date of the meeting
	 * @param startTime - Date field the start time of the meeting
	 * @return - Meeting a pointer to the meeting that it found
	 */
	public Meeting findInTree(Date startTime)
	{
		Meeting current; //Holding node
		
		current = root; //start at the root
		
		if(isTreeEmpty() == true) //Check if tree is empty
		{
			return null;
		}
		else
		{
			while(startTime.compareTo(current.getStartTime()) < 0 || startTime.compareTo(current.getStartTime()) > 0) //While its not the ID we are looking for
			{
				if(startTime.compareTo(current.getStartTime()) < 0)
				{
					current = current.getLeft(); //Go left
				}
				else
				{
					current = current.getRight(); //Go right
				}
				
				if(current == null)
				{
					return null; //not found
				}
			}
		}
		return current; //return the node
	}
	
	/**
	 * The method checks if the tree is empty and return a boolean
	 * @return empty Boolean True if the tree is empty
	 */
	public boolean isTreeEmpty()
	{
		boolean empty = false;
		
		if(root == null) //If root is null
		{
			empty = true; //then its empty
		}
		
		return empty;
	}
	
	/**
	 * this method deletes nodes, based on the id inputed by the user. can delete nodes with 0,1 or 2 children
	 * @param idToDelete the id of the node that is to be deleted
	 */
	public void deleteNode(Date dateToDelete) 
	{
		Meeting current = root;
		Meeting previous = null;
		Meeting next = null;
		
		
		current = root; //start at the root
		
		if(isTreeEmpty() == true) //Check if tree is empty
		{
			System.out.println("This tree is empty");
		}
		else
		{
			if(root.getStartTime().compareTo(dateToDelete) == 0 && root.getLeft() == null && root.getRight() == null)
			{
				root = null;
			}
			
			while(dateToDelete.compareTo(current.getStartTime()) != 0) //While its not the ID we are looking for
			{
				previous = current;
				
				if(dateToDelete.before(current.getStartTime()))
				{
					current = current.getLeft(); //Go left
				}
				else
				{
					current = current.getRight(); //Go right
				}
			
				if(current == null)
				{
					//not found
				}
			}
			
			if(current.getLeft() == null && current.getRight() == null) //If it is a leaf
			{
				if(previous.getLeft() == current) //If parents left points to current then
				{
					previous.setLeft(null); //set that left to null
				}
				else
				{
					previous.setRight(null); //Set parents right to null
				}
			}
			else if(current.getLeft() == null || current.getRight() == null) //if its a parent with one child
			{
				if(current.getLeft() == null) //if left points to null
				{
					next = current.getRight(); //Go right one
					
					if(previous.getLeft() == current) //If parents left points to current then
					{
						previous.setLeft(next); //set parents left to next node
					}
					else
					{
						previous.setRight(next); //Set parents right to next node
					}
				}
				else //right points to null
				{
					next = current.getLeft(); //go left
					
					if(previous.getLeft() == current) //If parents left points to current then
					{
						previous.setLeft(next); //set that left to next node
					}
					else
					{
						previous.setRight(next); //Set parents right to next node
					}
				}
			}
			else
			{
				   Meeting previousOfToDelete = previous;
				   previous = null;
				   Meeting nodeToDelete = current;
				   current = nodeToDelete.getLeft();
				   while(current.getRight() != null) {
					   previous = current;
					   current = current.getRight();
				   } 
				   
				   if (previousOfToDelete == null) {
					   setRoot(current);
				   }
				   else if (nodeToDelete.getStartTime().before(previousOfToDelete.getStartTime())) { //nodeToDelete is left of parent
					   previousOfToDelete.setLeft(current);
				   }
				   else {
					   previousOfToDelete.setRight(current);
				   }

				   current.setRight(nodeToDelete.getRight());
			}
				
		}
	}
	
	
	
	public String getDesc()
	{

		String desc = JOptionPane.showInputDialog(null,
				"Please input a descripiton of the meeting");

		return desc;
	}
	
	/**
	    * Method to save the tree
	    */
	   public void saveTree(){

		   try {
			   outputStream = new FileOutputStream("preorderTree.txt");
			   printWriter = new PrintWriter(outputStream);
			   outputTree(root, printWriter);
		   }
		   catch(Exception e){
			   System.out.println("Error " + e);
		   }
		   finally {
			   printWriter.close();
		   }
		}


	   /**
	    * Method to recursively output the tree to a text file
	    * 
	    * @param node the node to print
	    */
	   public void outputTree(Meeting node, PrintWriter printWriter){
		   if (node != null)
		   {
			   
			   printWriter.println(node.getStartTime() + "," + node.getEndTime() + "," + node.getDescription());
			   System.out.println(node.getData());
			   outputTree(node.getLeft(), printWriter);
			   outputTree(node.getRight(), printWriter);
			}
	    }
	   
	   
	   /**
	    * Method to load a previously saved tree from a text file 
	    */
	   public void loadTree() 
	   {
			FileReader fileReader = null;
			BufferedReader bufferedReader = null;
			DateFormat df =  new SimpleDateFormat("dd-MM-yyyy HH:mm");
			df =  new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
			try 
			{
				fileReader = new FileReader("preorderTree.txt");
				bufferedReader = new BufferedReader(fileReader);
				
				String nextLine = bufferedReader.readLine();
				
				while (nextLine != null) { //reading in the user grid
					String[] parts = nextLine.split(",");
					Date startTime = df.parse(parts[0]);
					Date endTime = df.parse(parts[1]);
					String desc = parts[2];
					addMeeting(startTime, endTime, desc);
					nextLine = bufferedReader.readLine();
				}
			}
			catch (IOException e)
			{
				System.out.println("Error reading from file: " + e);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	//GETTERS AND SETTERS

	/**
	 * @return the root
	 */
	public Meeting getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(Meeting root) {
		this.root = root;
	}
}
