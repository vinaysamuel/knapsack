import java.util.ArrayList;

/**
 * 
 */

/**
 * @author vinay
 *
 */
public class BnBNode {
    int[] path;
    int searchState;
    int val;
    int availableCapacity;
    int potentialVal;
    ArrayList <BnBNode> branches;
    
    
    BnBNode(int numItems)
    {
    	path = new int [numItems];
    	while (numItems > 0)
    	{
    		path[numItems] = -1;
    		numItems--;
    	}
    	branches = new ArrayList <BnBNode> (0);
    	searchState = -1;
    	val = 0; 
    }
    
}
