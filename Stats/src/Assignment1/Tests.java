package Assignment1;

import java.util.ArrayList;
import java.util.Random;

public class Tests {

	public static void main(String[] args) {
		StatsLibrary s_lib = new StatsLibrary();
		Random rnd = new Random();
		ArrayList<Integer> listOfNumbers = new ArrayList<>();
		for(int i = 0; i < 100; i++) {		
			listOfNumbers.add(rnd.nextInt(10));
		}

		System.out.println(listOfNumbers);
		System.out.println(s_lib.Mean(listOfNumbers));
		System.out.println(s_lib.Median(listOfNumbers));
		System.out.println(s_lib.Mode(listOfNumbers));
	}
}
