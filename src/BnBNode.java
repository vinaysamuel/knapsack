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
    ArrayList <BnBNode> branch;
    
    BnBNode(int numItems)
    {
    	path = new int [numItems];
    	numItems--;
    	while (numItems >= 0)
    	{
    		path[numItems] = -1;
    		numItems--;
    	}
    	searchState = -1;
    	val = 0; 
    	branch = new ArrayList <BnBNode> (0);

    }
    
}
