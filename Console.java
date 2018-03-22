import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
* Class to output the data written in the console to a text area in the GUI
* 
* @author Sarah Hartley
* 
*/
public class Console extends OutputStream {

	ArrayList <Byte> text = new ArrayList <Byte> (); //storing the data written in stream in an array

	private JTextArea output; //the text area to write to

	
	/**
	* Creates a console context.
	* 
	* @param output the text area to write to 
	*/
	public Console(JTextArea output) {
		this.output = output;
	}
	

	/**
	* Called when data has been written to the console.
	*/
	private void outputToConsole() {
		int lines = 0;
		for (int i = 0; i < text.size(); i++) {
			byte b = text.get(i);

		    if (b == 10) {
		        lines++;
		    }

		    if (lines >= 250) {
		    	text = (ArrayList<Byte>) text.subList(i, text.size());
		    }
		 }

		 StringBuilder sb = new StringBuilder();

		 for (byte b : text) {
		      sb.append((char) b);
		 }

		 output.setText(sb.toString()); //setting output to text that has been built
	}

	
	/**
	 * Method to output the text in the console to the text area
	 */
	@Override
	public void write(int i) throws IOException {

		// Append the piece of data to our array of data.
		text.add((byte) i);

		// Indicate that data has just been written.
		outputToConsole();
	}
}