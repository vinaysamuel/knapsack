import java.util.ArrayList;

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
			while(node <0 )
			{
				if (thisNode.path[i] == -1)
					node = -1;
				else
					node = i;
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
		nextNode.availableCapacity -= (nextBranch&0x1)*items.get((nextBranch>>1)).weight;
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
	
	BnBNode traverseTree(BnBNode thisNode)
	{
		int nextBranch;
		nextBranch = chooseBranch(thisNode);
		while (nextBranch >=0)
		{
		   BnBNode returnNode = new BnBNode(items.size());
		   BnBNode nextNode = new BnBNode (items.size());
		   updateNode (thisNode, nextNode, nextBranch);
		// check constraints
		// check if node should be stored - if true push into branches
		}
		//return best node
	}
	
	void solve(Items itemList)
	{
		BnBNode firstNode;
		items = itemList;
		firstNode = getFirstNode();
		traverseTree (firstNode);
		
	}
}
