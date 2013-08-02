
public class SimplexBnBSolver extends BranchNBondSolver {
	
	public SimplexBnBSolver(int capacity, int numItems) {
		super(capacity, numItems);
		// TODO Auto-generated constructor stub
	}
	
	public void updateNode (BnBNode thisNode, SimplexSolver temp){
		thisNode.potentialVal = temp.value();
		//loop over path for all !=-1 update the available capacity
		//		thisNode.availableCapacity =  		
		
	}

	void solve(Items itemList)
	{
		BnBNode thisNode;
		SimplexSolver temp;
		int optimum_reached = 0;
		thisNode = new BnBNode(itemList.size());
		thisNode.availableCapacity = maxCapacity;
		while (optimum_reached == 0){
			thisNode.createMatrix(itemList);
			temp = new SimplexSolver(thisNode.A, thisNode.b, thisNode.c);
			thisNode.reduceMemory();
		  // update node
		  // add to nodes
		  // remove parent node
		  // check optimum
		  //if (!optimum_reached){
		  //	choose next branch
		  //    
		}
	}

}
