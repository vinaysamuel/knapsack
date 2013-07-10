import java.util.ArrayList;

/**
 * 
 */

/**
 * @author vinay
 *
 */
public class DynamicProgramSolver {
	ArrayList<Items> optItemLists;
	int optValue;
	int optVerified = 0;
	int [] itemsPicked;
	int maxCapacity;
	
	DynamicProgramSolver(int capacity,int numItems)
	{
		optItemLists = new ArrayList<Items>(numItems);
		maxCapacity = capacity;
		itemsPicked = new int [numItems];
	}
	
	int [] list2Array(Items List)
	{
		int k;
		int storePrevValue;
		int [] Array = new int [maxCapacity];
		
		k =0;
		storePrevValue = 0;
		for (int i=0; i <List.size(); i++)
		{
			Array[k] = (List.get(i)).value;
			storePrevValue = (List.get(i)).value;
			k++;
			if ((i+1) < List.size()) {
				while ((k+1) < (List.get(i+1)).weight) {
					Array[k] = storePrevValue;
					k++;
				}
			}
		}
		for (;k<maxCapacity;k++) Array[k] = storePrevValue;
		return Array;
	}
	
	void createTable(Items itemList)
	{
		for (int itemNum=0; itemNum<itemList.size(); itemNum++) {
			Items prevItemOptList = new Items();
			Items thisItemOptList = new Items();
			Item thisItem;
			
			int lastMaxValue = -1;
			
			//Get the optList for this previous Item
			if (itemNum > 0) prevItemOptList = optItemLists.get(itemNum-1);
			thisItem = itemList.get(itemNum);
			
			//Find max value for this Item at every capacity
			for (int capacityRow = 1; capacityRow <= maxCapacity; capacityRow++ ){
				int maxValue;
				ArrayList <Integer> kList;

				maxValue = prevItemOptList.getValueForWeight(capacityRow);
				
				if (thisItem.value > prevItemOptList.getValueForWeight(capacityRow))
				{
					if (thisItem.weight<=capacityRow) maxValue = thisItem.value;
				}
				
				kList = prevItemOptList.getWeightsBelow(capacityRow);
				
				for (Integer k: kList)
				{
					if (((k + thisItem.weight) <= capacityRow) && ((prevItemOptList.getValueForWeight(k)+thisItem.value)>maxValue))
						maxValue = prevItemOptList.getValueForWeight(k)+thisItem.value;
				}
				//Push add highest value to the ArrayList
				//System.out.println("ItemNum "+itemNum+" value "+maxValue);
				if (maxValue!=lastMaxValue){
					lastMaxValue = maxValue;
					thisItemOptList.addItem(capacityRow,maxValue);
					optValue = maxValue;
				}
			}
			optItemLists.add(thisItemOptList);
		}
	}
	
	void solve(Items itemList)
	{
		int numItems;
		int idx;
		
		createTable(itemList);
		//Tracing items Picked
		numItems = itemsPicked.length;
		idx = maxCapacity-1; 

		for (int i = (numItems-1); i >0; i-- )
		{
			int val, lastVal;
			int[] tempArray1, tempArray2;

			if (idx<0)
			{
				itemsPicked[i] = 0;
				//if ((i-1) == 0) System.out.print(itemsPicked[0]);				
				continue;
			}

			tempArray1 = list2Array(optItemLists.get(i));
			tempArray2 = list2Array(optItemLists.get(i-1));
			val = tempArray1[idx];
			lastVal = tempArray2[idx];
			itemsPicked[i] = 0;
			if (val != lastVal){
				itemsPicked[i] = 1;
				idx = idx-itemList.get(i).weight;
			}
			
			if ((i-1) == 0) 
			{
				itemsPicked[0] = 0;
				if (idx>=0)
				{
					
					val = tempArray2[idx];
					if (val != 0) itemsPicked[0] = 1;
				}
					
			}
			
		}
	}
}
