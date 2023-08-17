package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class Exercise_21_2_Display_NonDuplicate_Names_Set {

	public static void main(String[] args) {
		File AtextFile = new File(args[0]);
		File BtextFile = new File(args[1]);
		
		TreeSet<String> set = new TreeSet<>();
		
		try (Scanner input = new Scanner(AtextFile);){
			while (input.hasNext()) {
				String[] words = input.nextLine().split("[ \n\t\r.,:;!?()-]");
				for (String word : words) {
					System.out.println(word);
					if (word.length() > 0) {
						set.add(word.toLowerCase());
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (Scanner input = new Scanner(BtextFile);){
			while (input.hasNext()) {
				String[] words = input.nextLine().split("[ \n\t\r.,:;!?()-]");
				for (String word : words) {
					System.out.println(word);
					if (word.length() > 0) {
						set.add(word.toLowerCase());
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String element : set) {
			System.out.print(element + " ");
		}	
	}

}
