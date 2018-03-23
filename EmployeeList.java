import javax.swing.JOptionPane;

/**
 * 
 * Class for creating linked list of employees
 * 
 * @author Sarah Hartley
 *
 * @version v1.0
 *
 */
public class EmployeeList {

    private Employee head; 
    int idToDelete;
    
    /**
     * Default constructor. Initialise fields to default values.
     */
    public EmployeeList()
    {
        head = null;
    }

    
    /**
     * Get the list node which is at the 'head' of the list
     * 
     * @return A reference to an Employee object which represents the node at the
     *         head of the list    
     */
    public Employee getHead()
    {
        return head;
    }

    
    /**
     * Set the 'head' of the list to the given node
     * 
     * @param  newHead A reference to a Employee object which will be
     *                 the head of the list. 
     */
    public void setHead(Employee newHead)
    {
        head = newHead;
    }
    

    /**
     * Add a new node to the start of the list which will contain
     * the data provided (a Employee ID and their name).
     * 
     * @param id The id of the Employee to be placed in this list node
     * @param name The Employee's name 
     */
    public void addToList(int id, String name)
    {
    	Employee newNode;
    	newNode = new Employee(id, name);
    	newNode.setNext(head);
    	newNode.setDiary(new Diary());
    	head = newNode;
    	System.out.println(name + " has been added.");
    }
    

    /**
     * Method to print all of the employees currently held in the list
     */
    public void printList() {
    	Employee marker = null;
    	marker = head;
    	if (marker == null) {
    		System.out.println("There are no employees in the list");
    	}
    	else {
    		while (marker != null){
    			System.out.println("ID: " + marker.getID() + "  Name: " + marker.getName());
    			marker = marker.getNext();
    		}
    	}
    }
    
    
    /**
     * Method to find an employee in the list
     */
    public void searchForEmployee() {
    	boolean found = false;
    	Employee foundNode = null;
    	try {
    		String input = JOptionPane.showInputDialog(null,
				"Please enter the ID of the Employee you would like to search for");
    		int idToSearch = Integer.parseInt(input);
    		Employee marker = null;
    		marker = head;
    		while (marker != null && foundNode == null){
    			if (marker.getID() == idToSearch) {
    				foundNode = marker;
    				found = true;
    			}
    			else {
    				marker = marker.getNext();
    			}
    		}
    		if (found == true) {
    			System.out.println("Employee name: " + marker.getName());
    		}
    		else {
    			System.out.println("Sorry, there is no Employee matching that ID");
    		}	
    	}
    	catch(Exception e) {
    		System.out.println("Sorry, there was an error with your input. Please make sure you are entering the id correctly");
    	}
    }
    
    
    /**
     * method to delete an employee node from the list
     */
    public void delete() {
    	boolean empty = isListEmpty();
    	if (empty == false) { //if there are employees in the list
    		try {
    			String input = JOptionPane.showInputDialog(null,
    					"Please enter the id of the employee you would like to delete");
    			idToDelete = Integer.parseInt(input);
    			Employee found = find(idToDelete); //checking employee exists in list
    			if (found == null) {
    				System.out.println("There is no employee matching that ID in the list");
    				return; //ends method
    			}
    		}
    		catch(Exception e){
    			System.out.println("Sorry, there was an error. Please make sure that you are inputting the id correctly.");
    		}
    		Employee nodeToDelete = null;
    		Employee previous = null;
    		Employee current = null;
    		current = head;
    		
    		while (current != null && nodeToDelete == null){
    			if (current.getID() == idToDelete) {
    				nodeToDelete = current;
    			}
    			else {
    				previous = current;
    				current = current.getNext();
    			}
    		}
    		System.out.println(nodeToDelete.getName() + " has been deleted");
    		if (previous == null) {
    			head = head.getNext();
    		}
    		else {
    			previous.setNext(nodeToDelete.getNext());
    		}	
    	}
    	else { //the list is empty
    		System.out.println("The list is empty");
    	}
    	
    }
  
    
    /**
     * Method to check if the list is empty
     * 
     * @return a boolean stating whether the list is empty or not
     */
    public boolean isListEmpty() {
    	if (head == null) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    
    /**
     * Method to find and return an employee in the list
     * 
     * @param id a string containing the ID of the employee
     * @return Employee A reference to an employee object
     */
    public Employee find(int id) {
    	Employee foundNode = null;
    	Employee marker = null;
    	marker = head;
    	while (marker != null && foundNode == null){
    		if (marker.getID() == id) {
    			foundNode = marker;
    		}
    		else {
    			marker = marker.getNext();
    		}
    	}
    	return foundNode;
    }
}
