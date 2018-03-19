import java.util.Scanner;

/**
 * 
 * @author Sarah Hartley
 *
 * @version v1.0
 *
 */
public class EmployeeList {

    private Employee head; 
    
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
    public void addToList(String id, String name)
    {
    	Employee newNode;
    	newNode = new Employee(id, name);
    	newNode.setNext(head);
    	head = newNode;
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
    			System.out.println("ID: " + marker.getID());
    			System.out.println("Mark: " + marker.getName() + "\n");
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
    	System.out.println("Please enter the ID of the Employee you would like to search for");
    	Scanner s = new Scanner(System.in);
		String idToSearch = s.nextLine();
		Employee marker = null;
    	marker = head;
    	while (marker != null && foundNode == null){
    		if (marker.getID().equals(idToSearch)) {
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
    
    
    /**
     * method to delete an employee node from the list
     */
    public void delete() {
    	
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
    public Employee find(String id) {
    	Employee foundNode = null;
    	Employee marker = null;
    	marker = head;
    	while (marker != null && foundNode == null){
    		if (marker.getID().equals(id)) {
    			foundNode = marker;
    		}
    		else {
    			marker = marker.getNext();
    		}
    	}
    	return foundNode;
    }
}
