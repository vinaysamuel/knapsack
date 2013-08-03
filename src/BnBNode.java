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
    SimplexSolver simplexSoln;
    
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
    	simplexSoln = null;
    }
    
    public void solveSimplex(){
    	simplexSoln = new SimplexSolver(A,b,c);
    }
    
    public void createMatrix(Items itemList){
    	int count = 0;
    	for(int i = 0; i<path.length;i++){
    		if ((path[i] == -1) && (itemList.get(i).weight <= availableCapacity)){
    	  			count++;
    		}
    	}
    	b = new double [count+1];
    	A = new double [count+1][count];
    	c = new double [count];
    	
    	count = 0;
    	b[0] = availableCapacity;
    	for(int i = 0; i<path.length;i++){
    		if ((path[i] == -1) && (itemList.get(i).weight <= availableCapacity)){
    			A[0][count] = itemList.get(i).weight;
    			A[count+1][count] = 1;
    			b[count+1] = 1;
    			c[count] = -1*itemList.get(i).value;
    			count++;
    		}
    		else if ((path[i] == -1) && (itemList.get(i).weight > availableCapacity)){
    			path[i] = 0;
    		}
    	}
    }
    
}
