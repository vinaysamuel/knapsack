/**
 * 
 */

/**
 * @author vinay
 *
 */
public class BnBNode {
    short [] path;
    int val;
    int availableCapacity;
    int potentialVal;
    int optimum;
    
    BnBNode(int numItems)
    {
    	path = new short [numItems];
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
