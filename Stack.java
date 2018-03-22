import java.util.Date;
/**
 * Stack class for undo feature
 * 
 * @author Arran Roy
 *
 * @version v1.0
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
     * Add a new node to the start of the list which will contain
     * the data provided (a number).
     * 
     */
    public void pushToStack(Date startTime, Date endTime, String duration, Meeting left, Meeting right)
    {
    	Meeting newMeeting = new Meeting();
    	
    	//setting values of the meeting
    	newMeeting.setNext(head);
    	newMeeting.setStartTime(startTime);
    	newMeeting.setEndTime(endTime);
    	newMeeting.setDuration(duration);
    	newMeeting.setLeft(left);
    	newMeeting.setRight(right);
    	
    	//make head point to the new nodes location
    	//thus making the new node the head
    	head = newMeeting;
    	
    	//print the number being pushed form stack
    	System.out.println("Pushed " + newMeeting);
    }
    
    /**
     * 
     * @return The return value 
     */
    public void popFromStack()
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
    	}
    }
    
    
}