import java.util.ArrayList;

/**
 * 
 */
/**
 * @author vinay
 *
 */
public class Items {
 	ArrayList <Item> itemList;
 	
 	Items()
 	{
 		itemList = new ArrayList<Item>();
 	}
 	
 	Item get(int i)
 	{
 		return itemList.get(i);
 	}
 	
 	int getValueForWeight(int weight)
 	{
 		int idx;
 		idx = getIndexForWeight(weight);
 		if (idx == -1)
 			return 0;
 		else
 			return itemList.get(idx).value;
 	}
 	
 	ArrayList<Integer> getWeightsBelow( int weight)
 	{
 		ArrayList<Integer> kList;
 		int size;
 		
 		size = itemList.size();
 		kList = new ArrayList<Integer>();
 		
 		while (size>0)
 		{
 			if (itemList.get(size-1).weight <= weight)
 				kList.add(itemList.get(size-1).weight);
 			size--;
 		}
 		return kList; 		
 		
 	}
 	
 	int getIndexForWeight(int weight)
 	{
 		int size;
 		
 		size = itemList.size();
 		
 		while (size>0)
 		{
 			if (itemList.get(size-1).weight <= weight)
 				return size-1;
 			size--;
 		}
 		return -1; 		
 	}
 	
 	int size()
 	{
 		return itemList.size();
 	}
 	
 	void addItem(int weight,int value)
 	{
 		Item thisItem;
 		thisItem = new Item(weight, value);
 		itemList.add(thisItem);
 	}
}
