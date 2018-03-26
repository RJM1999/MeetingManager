import java.util.Date;

/**
 * 
 * Class for creating a new employee
 * 
 * @author Sarah Hartley
 *
 */
public class Employee {
	
	// fields to store the data being held in this list node (a student ID, name and diary)
    private int id;
    private String name;
    private Employee next;
    private Diary diary;

    /**
     * Default constructor. Initialise fields to default values
     */
    public Employee()
    {
        id = 0;
        name = "";
        next = null;
        setDiary(null);
    }

    /**
     * Alternative constructor. Set fields to given values.
     *
     * @param id The id for the student
     * @param mark The student's mark
     */
    public Employee(int id, String name)
    {
        this.id = id;
        this.name = name;
        this.next = null;
        this.setDiary(null);
    }

    
    /**
     * Get the employee ID contained in this list node
     * 
     * @return The employee's ID as an int
     */
    public int getID()
    {
        return id;
    }

    
    /**
     * Get the employee name contained in this list node
     * 
     * @return The employee's name
     */
    public String getName()
    {
        return name;
    }

    
    /**
     * Get the next node in the list after this one
     * 
     * @return A reference to the next node (or null if
     *         there is no next node)
     */
    public Employee getNext()
    {
    	return next;
    }
    

    /**
     * Set the next node in the list after this one
     * 
     * @param nextNode A reference to a ListNode object which 
     *             represents the next node in the list after
     *             this one.
     */
    public void setNext(Employee nextNode)
    {
    	next = nextNode;
    }
    
    
    /**
     * method to call print tree in diary class
     */
    public void printMeetings() {
    	getDiary().printTree();
    }
    
    
    /**
     * method to call save tree in diary class
     */
    public void saveMeetings() {
    	getDiary().saveTree();
    }

    
    /**
     * method to call load tree in diary class
     */
    public void loadMeetings() {
    	getDiary().loadTree();
    }
    
    
    /**
     * method to call add meeting in diary class
     */
    public void addMeeting(Date startDate, Date endDate, String desc) {
    	getDiary().addMeeting(startDate, endDate, desc);
    }
    
    
    /**
     * method to call delete meeting in diary class
     */
    public void deleteMeeting(Date dateToDelete) {
    	getDiary().deleteNode(dateToDelete);
    }
    
    
    /**
     * method to call edit meeting in diary class
     */
    public void editMeeting(int option, Date startDate, Date endTime, String desc, Date newDate) {
    	getDiary().edit(option, startDate, endTime, desc, newDate);
    }
    
    
    /**
     * Method to find the node in the tree
     * 
     * @param startDate the start date and time
     * @return the node 
     */
    public Meeting stack(Date startDate) {
    	return getDiary().findInTree(startDate);
    }
    
    /**
     * Method to find the node in the tree
     * 
     * @param startDate the start date and time
     * @return boolean
     */
    public boolean checkMeetingExists(Date startDate) {
    	return getDiary().findInTreeSearchMethod(startDate);
    }
    
    
    /**
     * method to call input date of meeting in diary class
     * 
     * @return the date of the meeting
     */
    public Date inputDateOfMeeting() {
    	return getDiary().inputDateOfMeeting();
    }

    
    /**
     * method to get the employees diary
     * 
     * @return diary the employees diary
     */
	public Diary getDiary() {
		return diary;
	}

	
	/**
	 * method to set the employees diary
	 * 
	 * @param diary the employees diary
	 */
	public void setDiary(Diary diary) {
		this.diary = diary;
	}
}
