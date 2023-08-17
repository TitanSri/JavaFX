package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HashMapQuiz {
public static void main(String[] args) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		// add states and capitals to the map
		map.put("New South Wales", "Sydney");
		map.put("ACT", "Canberra");
		map.put("Western Australia", "Perth");
		map.put("Tasmania", "Hobart");
		map.put("South Australia", "Adelaide");
		map.put("Northern Territory", "Darwin");
		map.put("Queensland", "Brisbane");
		
		
		//get the key set for iteration
		ArrayList<String> states = new ArrayList<String>(map.keySet());
		
		int score = 0;
		Scanner input = new Scanner(System.in);
		
		for (int i=0; i < states.size(); i++ ) {
			System.out.println("What is the capital of " + states.get(i) + "?");
			
			// get the answer
			String answer = input.nextLine().trim();
			if (answer.equalsIgnoreCase(map.get(states.get(i)))) {
				System.out.println("Correct");
				score++;
			}
			else 
				System.out.println("That is incorrect. The capital of " + states.get(i) + " is " + map.get(states.get(i)));
		}

		input.close();
		System.out.println("\nYou scored " + score + " out of " + states.size());	
	}

}
