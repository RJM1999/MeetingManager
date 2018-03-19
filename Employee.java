/**
 * 
 * @author Sarah Hartley
 *
 * @version v1.0
 *
 */
public class Employee {
	
	// fields to store the data being held in this list node (a student ID, name and diary)
    private String id;
    private String name;
    private Employee next;
    private Diary diary;

    /**
     * Default constructor. Initialise fields to default values
     */
    public Employee()
    {
        id = "";
        name = "";
        next = null;
        diary = null;
    }

    /**
     * Alternative constructor. Set fields to given values.
     *
     * @param id The id for the student
     * @param mark The student's mark
     */
    public Employee(String id, String name)
    {
        this.id = id;
        this.name = name;
        this.next = null;
        this.diary = null;
    }

    
    /**
     * Get the employee ID contained in this list node
     * 
     * @return The employee's ID as a String
     */
    public String getID()
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
    
    
    public void printMeetings() {
    	diary.printTree();
    }
    
    public void saveMeetings() {
    	diary.saveTree();
    }

    public void loadMeetings() {
    	diary.loadTree();
    }
}
