import java.util.Date;

/**
 * Class creating tree nodes (meetings)
 * 
 * @author Ross Maider
 *
 * @version v1.0
 */
public class Meeting
{
	//Define Fields
	Date startTime;
	Date endTime;
	String description;
	Meeting left;
	Meeting right;
	
	/**
	 *Constructor, no parameters
	 */
	Meeting()
	{
		
	}
	
	/**
	 * Constructor when adding a new node to the tree. 
	 * @param inStartTime Date the start date of the meeting to be added
	 * @param desc - String a description of the meeting to be added
	 */
	Meeting(Date inStartTime, String desc)
	{
		startTime = inStartTime;
		description = desc;
	}
	
	/**
	 * Constructor when the user loads in tree
	 * @param inStartTime - Date the start time of the meeting
	 * @param inEndTime - Date the end time of the meeting
	 * @param desc - String a description of the meeting to be added 
	 */
	Meeting(Date inStartTime, Date inEndTime, String desc)
	{
		startTime = inStartTime;
		endTime = inEndTime;
		description = desc;
	}
	
	/**
	 * Default constructor, with parameters. This would be the one normally used.
	 * @param inDateOfMeeting - Date the Date of the meeting
	 * @param inStartTime - Time start time of the meeting
	 * @param inEndTime - Time end time of the meeting
	 * @param inDescription - String a description of the meeting
	 * @param inLeft - Meeting a pointer for the left node of meeting
	 * @param inRight - Meeting a pointer for the right node of meeting
	 */
	Meeting(Date inEndTime, Date inStartTime,String inDescription, Meeting inLeft, Meeting inRight)
	{
		
		startTime = inStartTime;
		endTime = inEndTime;
		description = inDescription;
		left = inLeft;
		right = inRight;
	}
	
	public String getData()
	{
		String data = "The start date and time of the meeting is " + getStartTime() + " The end time and date of the meeting is " + getEndTime() + " Description: " + getDescription();
		return data;
	}
	
	public void editMeeting()
	{
		Diary edit = new Diary();
		String newDesc;
		String newStringDate;
		Date newDate = null;
		long newDur;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm"); //Date formatter
		Date endTime = new Date();
		
		Scanner s1 = new Scanner(System.in);
		System.out.println("Please enter the new start date for the meeting in the format dd-MM-yyyyTHH:mm");
		newStringDate = s1.nextLine();
		
		newDur = edit.calculateDuration();
		
		newDesc = edit.getDesc();
		
		try 
		{
			newDate = dateFormat.parse(newStringDate);
		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		endTime.setTime(newDur + newDate.getTime());
		
		setStartTime(newDate);
		setDescription(newDesc);
		setEndTime(endTime);
	}
	
	//****GETTERS AND SETTERS****

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the left
	 */
	public Meeting getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(Meeting left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public Meeting getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(Meeting right) {
		this.right = right;
	}
	
}
