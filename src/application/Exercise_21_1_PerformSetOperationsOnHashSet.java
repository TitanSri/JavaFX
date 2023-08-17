package application;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Exercise_21_1_PerformSetOperationsOnHashSet {
	public static void main(String[] args) {
		// create hash set
		Set<String> set1 = new LinkedHashSet<>(Arrays.asList("Chemistry", "Mathematics", "Biology", "English"));
		Set<String> set2 = new LinkedHashSet<>(Arrays.asList("Biology", "English", "Geography", "Physics"));
		
		System.out.print("\nSet 1: ");
		for (String element : set1) {
			System.out.print(element + " ");
		}
		
		System.out.print("\nSet 2: ");
		for (String element : set2) {
			System.out.print(element + " ");
		}
		
		// union
		Set<String> set3 = new LinkedHashSet<>();
		set3.addAll(set1);
		set3.addAll(set2);
		
		System.out.print("\nSet 3 union both set: ");
		for (String element : set3) {
			System.out.print(element + " ");
		}		
		
		// difference
		System.out.print("\nSet 3 remove set 2: ");
		set3.removeAll(set2);
		for (String element : set3) {
			System.out.print(element + " ");
		}	
		
		// intersection 
		Set<String> intersection = new LinkedHashSet<>();
		for (String e: set2) {
			if (set1.contains(e))
				intersection.add(e);
		}
		System.out.println("\nIntersection of the two sets: " + intersection);
	}

}
