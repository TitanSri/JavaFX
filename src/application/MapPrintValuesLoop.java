package application;

import java.util.ArrayList;
import java.util.TreeMap;

public class MapPrintValuesLoop {
	public static void main(String[] args) {
		TreeMap<Integer, String> map = new TreeMap<>();
		map.put(1, "First");
		map.put(2, "Second");
		map.put(3, "Third");
		map.put(4, "Fourth");
		map.put(5, "Fifth");
		
		// forEach loop to print out all contents of the map
		map.forEach((key, value) -> System.out.println(key + "/" + value + " "));
		
		// print just the values
		ArrayList<String> mapValues = new ArrayList<String>(map.values());
		
		// insert a new line between the printed entries above and below
		System.out.println();
		
		for (int i=0;i< mapValues.size();i++) 
			System.out.print(mapValues.get(i) + " ");

	}

}

