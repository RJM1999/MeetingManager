import java.util.Date;

/**
 * Stack class for undo feature
 * 
 * @author Arran Roy
 */

public class Stack 
{
	// used to hold a reference to an instance of a Meeting object which
	// will be the first item in the list, i.e. at the 'head' of the list
	private Meeting head; 

	
    /**
     * Default constructor. Initialise fields to default values.
     */
    public Stack()
    {
        // set the list to be empty, i.e. not referring to anything valid yet
        head = null;
    }
    
    
    /**
     * Get the Meeting which is at the 'head' of the list
     * 
     * @return A reference to a Meeting object which represents the node at the
     *         head of the list (or null if the list is empty, i.e. no 'head' has
     *         been set yet).    
     */
    public Meeting getHead()
    {
        return head;
    }
    
    
    /**
     * Set the 'head' of the list to the given node
     * 
     * @param  newHead A reference to a Meeting object which will be
     *                 the head of the list. 
     *                 
     * NOTE: if a list already exists, the existing listing will be 
     * lost since the head is being assigned to something new.
     */
    public void setHead(Meeting newHead)
    {
        head = newHead;
    }
    

    /**
     * this method saves the information of the meeting to the stack
     * 
     * @param startTime the starting date of the meeting being added
     * @param endTime the end date of the meeting being added
     * @param description the description of the meeting being added
     * @param left the left child node of the meeting being added
     * @param right the right child node of the meeting being added
     */
    public void pushToStack(Date startTime, Date endTime, String description, Meeting left, Meeting right)
    {
    	Meeting newMeeting = new Meeting();
    	
    	//setting values of the meeting
    	newMeeting.setNext(head);
    	newMeeting.setStartTime(startTime);
    	newMeeting.setEndTime(endTime);
    	newMeeting.setDescription(description);
    	newMeeting.setLeft(left);
    	newMeeting.setRight(right);
    	
    	//make head point to the new nodes location
    	//thus making the new node the head
    	head = newMeeting;
    	
    }
    
    
    /**
     * method that pops the head of the stack
     * @return The return value that is the meeting being popped
     */
    public Meeting popFromStack()
    {
    	//Declare a marker/variable called ‘meetingToDelete’ which will point to a Meeting
    	Meeting meetingToDelete;
    	
    	
    	//Before deleting a node, check if the stack is empty
    	if (head == null)
    	{
    		//If it is empty, return null
    		System.out.println("Nothing to pop from stack.");
    	}
    	//Else
    	else
    	{
    		//make nodeToDelete point to head
    		meetingToDelete = head;
    		
    		//make head point to head’s next
    		head = meetingToDelete.getNext();
    		
    		//print the number being popped form stack
    		System.out.println("Popped " + meetingToDelete);
    		return meetingToDelete;
    	}
		return null;
    }
    
    
    /**
	 * Prints out the number in the current node
	 */
	public void printStack()
	{
		// Create a ListNode variable called ‘currentNode’
		// Set marker to be the same as the Head
		Meeting currentNode = head;
	   
	    // While marker is not null
		while (currentNode != null)
		{
			// print the information at marker
			System.out.println(currentNode.getStartTime());
			System.out.println(currentNode.getEndTime());
			System.out.println(currentNode.getDescription());

			// move marker to the next ListNode
			currentNode = currentNode.getNext();
	    // End while
		}
	}
}