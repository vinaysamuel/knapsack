/**
 * 
 */

/**
 * @author vinay
 *
 */
public class BnBNode {
    int[] path;
    int val;
    int availableCapacity;
    int potentialVal;
    int optimum;
    
    BnBNode(int numItems)
    {
    	path = new int [numItems];
    	numItems--;
    	while (numItems >= 0)
    	{
    		path[numItems] = -1;
    		numItems--;
    	}
    	val = 0;
    	optimum = 0;

    }
    
}
