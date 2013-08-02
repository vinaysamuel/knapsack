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
    double potentialVal;
    int optimum;
    double[][] A;
    double[] b;
    double[] c;
    
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
    
    public void reduceMemory(){
    	A = null;
    	b = null;
    	c = null;
    }
    
    public void createMatrix(Items itemList){
    	int count = 0;
    	for(int i = 0; i<path.length;i++){
    		if (path[i] == -1){
    	  			count++;
    		}
    	}
    	b = new double [count+1];
    	A = new double [count+1][count];
    	c = new double [count];
    	
    	count = 0;
    	b[0] = availableCapacity;
    	for(int i = 0; i<path.length;i++){
    		if (path[i] == -1){
    			A[0][count] = itemList.get(i).weight;
    			A[count+1][count] = 1;
    			b[count+1] = 1;
    			c[count] = itemList.get(i).value;
    			count++;
    		}
    	}
    }
    
}
