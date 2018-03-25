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
    public void addMeeting() {
    	getDiary().addMeeting();
    }
    
    
    /**
     * method to call delete meeting in diary class
     */
    public void deleteMeeting() {
    	Date dateToDelete = inputDateOfMeeting();
    	getDiary().deleteNode(dateToDelete);
    }
    
    
    /**
     * method to call edit meeting in diary class
     */
    public void editMeeting() {
    	getDiary().edit();
    }
    
    
    /**
     * method to call add test meetings in diary class
     */
    public void addTestMeeting() {
    	getDiary().addTestMeetings("23-04-2018T09:00", 45,"Test 1");
    	getDiary().addTestMeetings("24-04-2018T09:00", 30,"Test 2");
    	getDiary().addTestMeetings("25-04-2018T09:00", 60,"Test 3");
    	getDiary().addTestMeetings("23-04-2018T12:00", 20,"Test 4");
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
