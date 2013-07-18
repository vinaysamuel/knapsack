/**
 * 
 */

/**
 * @author vinay
 *
 */
public class BranchNBondSolver {
	int optValue;
	int optVerified = 0;
	int [] itemsPicked;
	int maxCapacity;
	Items items;
	
	BranchNBondSolver(int capacity,int numItems)
	{
		maxCapacity = capacity;
		itemsPicked = new int [numItems];
	}
	
	int chooseBranch(BnBNode thisNode){
		int node;
		int i;
		
		node = -1;
		i = 0;
		if (thisNode.searchState < 0){
			while((node < 0 ) && (i<thisNode.path.length))
			{
				if (thisNode.path[i] == -1)
					node = i;
				else
					node = -1;
				i++;
			}
		}
		
		if (node == -1)
			return -1;
		
		node = node<<1;
		thisNode.searchState++;
		if (thisNode.searchState == 0)
			return node;
		else if (thisNode.searchState == 1)
			return (node|1);
		else if ((thisNode.searchState > 1)||(thisNode.searchState < 0))
			return -1;
		
		return thisNode.searchState; 
	}

	BnBNode getFirstNode()
	{
		BnBNode firstNode = new BnBNode (items.size());
		firstNode.potentialVal = 0;
		for (Item thisItem: items.itemList )
				firstNode.potentialVal += thisItem.value;
		firstNode.availableCapacity = maxCapacity;
		firstNode.val = 0;
		return firstNode;
	}
	
	void updateNode(BnBNode thisNode, BnBNode nextNode, int nextBranch)
	{
		int idx = 0;
		nextNode.path = thisNode.path;
		nextNode.path[nextBranch>>1] = nextBranch&0x1;
		nextNode.availableCapacity =thisNode.availableCapacity - (nextBranch&0x1)*items.get((nextBranch>>1)).weight;
		nextNode.potentialVal = 0;
		for (Item thisItem: items.itemList){
			if (nextNode.path[idx] == 1){
				   nextNode.potentialVal += thisItem.value; 
			}
			else if (nextNode.path[idx] < 0){
				if (thisItem.weight <= nextNode.availableCapacity){
				   nextNode.potentialVal += thisItem.value;
				}
				else
				{
				   nextNode.path[idx] = 0;
				}
		  }
		 }
		 nextNode.val = thisNode.val + (nextBranch&0x1) * items.get((nextBranch>>1)).value;
	}
	
	int constraintCheck(BnBNode parent, BnBNode node, int branch){
		if (node.availableCapacity<=0)
			return 0;
		if (parent.branch == null){
			return 1;
		}
		else if (parent.branch[~branch] == null){
			return 1;
		}
		else if ((parent.branch[~branch].potentialVal > node.potentialVal)||(parent.branch[~branch].val > node.potentialVal)){
			return 0;
		}
		else {
			return 1;
		}
			
	}
	
	BnBNode traverseTree(BnBNode thisNode)
	{
		int nextBranch;
		nextBranch = chooseBranch(thisNode);
		while (nextBranch >=0)
		{
		   BnBNode nextNode = new BnBNode (items.size());
		   updateNode (thisNode, nextNode, nextBranch);
		   if (constraintCheck(thisNode,nextNode,nextBranch) != 0) 
			   traverseTree(nextNode);
		   
		   thisNode.branch[nextBranch] = nextNode;
		   nextBranch = chooseBranch(thisNode);
		}
		if (thisNode.branch[0].availableCapacity < 0 ){
			thisNode = thisNode.branch[1];
		}
		else if (thisNode.branch[1].availableCapacity < 0 ){
			thisNode = thisNode.branch[0];
		} 
		else if (thisNode.branch[1].potentialVal > thisNode.branch[0].potentialVal){
			thisNode = thisNode.branch[1];
		}
		else{
			thisNode = thisNode.branch[0];
		}
			
		return thisNode;

	}
	
	void solve(Items itemList)
	{
		BnBNode firstNode;
		BnBNode optNode;
		items = itemList;
		firstNode = getFirstNode();
		optNode = traverseTree (firstNode);
		optValue = optNode.val;
		optVerified = 0;
		itemsPicked = optNode.path;
	}
}
