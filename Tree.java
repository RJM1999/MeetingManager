import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Sarah Hartley
 */
public class Tree 
{
    private TreeNodes root; 
    int noOfItems;
    int indent;
    FileOutputStream outputStream = null;
	PrintWriter printWriter = null;
	
    
    /**
     * Default constructor. Initialise fields to default values.
     */
    public Tree()
    {
        root = null;
        noOfItems = 0;
    }

    
    /**
     * Get the list node which is at the 'root' of the list
     * 
     * @return A reference to a TreeNodes object which represents the node at the
     *         root of the list (or null if the list is empty, i.e. no 'root' has
     *         been set yet).    
     */
    public TreeNodes getRoot()
    {
        return root;
    }

    
    /**
     * Set the 'root' of the list to the given node
     * 
     * @param  newRoot A reference to a TreeNodes object which will be
     *                 the root of the list. 
     */
    public void setRoot(TreeNodes newRoot)
    {
        root = newRoot;
    }

    
    /**
     * Method to add a node to the tree
     * 
     * @param id an int containing the student ID
     * @param name a string containing the name of the student
     * @param mark an int containing the mark of the student
     * 
     * @return boolean a boolean stating whether adding a student was successful
     */
    public boolean addToTree(int id, String name, int mark)
    {
    	TreeNodes current = root;
    	TreeNodes previous = null;
    	TreeNodes newNode;
    	boolean found = false;
    	newNode = new TreeNodes(id, name, mark);
    	newNode.setRight(null);
    	newNode.setLeft(null);
    	if (current == null) {
    		root = newNode;
    	}
    	else {
    		found = checkNodeExists(id);
    		if (found == true) {
    			return false;
    		}
    		else {
    			while (current != null) {
    				previous = current;
    	    		if (id < current.getID()) {
    	    			current = current.getLeft();
    	    		}
    	    		else {
    	    			current = current.getRight();
    	    		}
    	    	}
    			if (id < previous.getID()) {
    				previous.setLeft(newNode);
    			}
    			else {
    				previous.setRight(newNode);
    			}
    		}
    	}
    	noOfItems++;
		return true;
    }


    /**
     * Method to search the tree to find a node
     * 
     * @param idToSearch the student ID to search for
     * @return foundNode The node that has been found
     */
    public TreeNodes findNode(int idToSearch) {
    	TreeNodes foundNode = root;
    	boolean found = false;
    	while (foundNode != null && found == false) {
    		if (idToSearch == foundNode.getID()) {
    			found = true;
    		}
    		else {
    			if (idToSearch < foundNode.getID()) {
    				foundNode = foundNode.getLeft();
    			}
    			else {
    				foundNode = foundNode.getRight();
    			}
    		}
    	}
    	if (found == true) {
    		return foundNode;
    	}
    	else {
    		return null;
    	}
    }
    
    /**
     * Method to check if a student already exists before adding them
     * 
     * @param id the ID to search for
     * @return found A boolean value stating whether the node was found or not
     */
    public boolean checkNodeExists(int id) {
    	TreeNodes upto = root;
    	boolean found = false;
    	while (upto != null && found == false) {
    		if (id == upto.getID()) {
    			found = true;
    		}
    		else {
    			if (id < upto.getID()) {
    				upto = upto.getLeft();
    			}
    			else {
    				upto = upto.getRight();
    			}
    		}
    	}
    	return found;
    }
    

    /**
     * Method to print out the tree
     *  
     *  @param printType a string containing the type of traversal to use when printing the tree
     */
    public void displayTree(String printType)
    {
        indent = 0; // needs to be added as an instance variable
        
        if ("preorder".equals(printType)) {
        	preOrderedTree(root);
        }
        else if ("postorder".equals(printType)) {
        	postOrderedTree(root);
        }
        else if ("inorder".equals(printType)) {
        	inOrderTree(root);
        }
   }

    /**
     * Method that uses recursion to print out the contents of the tree using preOrdered traversal
     *
     * @param node The root of the tree
     */
    public void preOrderedTree(TreeNodes node)
    {
      int i;

        if (node != null)
        {
           System.out.println(node.getSummaryData());
           preOrderedTree(node.getLeft());
           preOrderedTree(node.getRight());
         }
     }
    
    
    /**
     * Method that uses recursion to print out the contents of the tree using postOrder traversal
     * 
     * @param node The root of the tree
     */
    public void postOrderedTree(TreeNodes node)
    {
      int i;

        if (node != null)
        {
           postOrderedTree(node.getRight());
           System.out.println(node.getSummaryData());
           postOrderedTree(node.getLeft());
         }
     }
    
    
    /**
     * Method that uses recursion to print out the contents of the tree using inOrder traversal
     * 
     * @param node The root of the tree
     */
    public void inOrderTree(TreeNodes node)
    {
      int i;

        if (node != null)
        {
           indent += 6;
           
           inOrderTree(node.getRight());

           for (i=6; i<indent; i=i+6) {
           System.out.print ("\t");
           }
              
            System.out.println("      " + node.getSummaryData());
           
            inOrderTree(node.getLeft());
            
            indent -= 6;
         }
     }
    
    
    /**
     * Method to delete a node from the tree
     * 
     * @param idToDelete The ID of the student to delete
     * @return current The deleted node
     */
   public TreeNodes deleteNode(int idToDelete) { 
	   TreeNodes current = root;
	   TreeNodes previous = null;
	   //TreeNodes rightMost = null;
	   boolean found = false;
	   while (current != null && found == false) { //this loops until it finds the node to delete
		   if (idToDelete == current.getID()) {
			   found = true;
   			}
   			else {
   				previous = current;
   				if (idToDelete < current.getID()) {
   					previous = current;
   					current = current.getLeft();
   				}
   				else {
   					previous = current;
   					current = current.getRight();
   				}	
   			}
   		}

	  if (current.getLeft() == null && current.getRight() == null) { //NO CHILDREN
		   if (current == root) {
			   setRoot(null);
		   }
		   else if (previous.getLeft() == current){
			  previous.setLeft(null);
		   }
		   else {
			    previous.setRight(null);
		   }
		   System.out.println("You have deleted " + current.getName() + " from the database.");
	   }
	   
	   else if(current.getLeft() == null && current.getRight() != null) { //RIGHT CHILD ONLY
		   if (previous.getLeft() == current) { //if current is previous' left child
			   previous.setLeft(current.getRight());
		   }
		   else { //current is previous' right child
			   previous.setRight(current.getRight());
		   }
		   System.out.println("Removed " + current.getName() + " from database");
	   }
	   
	   else if(current.getLeft() != null && current.getRight() == null) { //LEFT CHILD ONLY
		   if (previous.getLeft() == current) { //if current is previous' left child
			   previous.setLeft(current.getLeft());
		   }
		   else { //current is previous' right child
			   previous.setRight(current.getLeft());
		   }
		   System.out.println("Removed " + current.getName() + " from database");
	   }
	   
	   else if(current.getLeft() != null && current.getRight() != null) { //BOTH CHILDREN
		   TreeNodes previousOfToDelete = previous;
		   previous = null;
		   TreeNodes nodeToDelete = current;
		   current = nodeToDelete.getLeft();
		   while(current.getRight() != null) {
			   previous = current;
			   current = current.getRight();
		   } 
		   
		   if (previousOfToDelete == null) {
			   setRoot(current);
		   }
		   else if (nodeToDelete.getID() < previousOfToDelete.getID()) { //nodeToDelete is left of parent
			   previousOfToDelete.setLeft(current);
		   }
		   else {
			   previousOfToDelete.setRight(current);
		   }

		   current.setRight(nodeToDelete.getRight());
		   System.out.println("Removed " + nodeToDelete.getName() + " from database");
	   }
	   
	   noOfItems--; //number of students in tree
	   return current;
   	}
   
   
   /**
    * Method to check how many students are being held in the database
    * 
    * @return noOfItems an int containing the number of items in the tree
    */
   public int getNoOfItems() {
	   return noOfItems;
   }
   
   
   /**
    * Method to save the tree
    */
   public void saveTree(){

	   try {
		   outputStream = new FileOutputStream("preorderTree.txt");
		   printWriter = new PrintWriter(outputStream);
		   outputTree(root);
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
   public void outputTree(TreeNodes node){
	   if (node != null)
	   {
		   printWriter.println(node.getID() + "," + node.getName() + "," + node.getMark());
		   System.out.println(node.getSummaryData());
		   outputTree(node.getLeft());
		   outputTree(node.getRight());
		}
    }
   
   
   /**
    * Method to load a previously saved tree from a text file
    */
   public void loadTree() {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try 
		{
			fileReader = new FileReader("preorderTree.txt");
			bufferedReader = new BufferedReader(fileReader);
			
			String nextLine = bufferedReader.readLine();
			
			while (nextLine != null) { //reading in the user grid
				String[] parts = nextLine.split(",");
				int id = Integer.parseInt(parts[0]);
				String name = parts[1];
				int mark = Integer.parseInt(parts[2]);
				addToTree(id, name, mark);
				nextLine = bufferedReader.readLine();
			}
		}
		catch (IOException e)
		{
			System.out.println("Error reading from file: " + e);
		}
	}
}