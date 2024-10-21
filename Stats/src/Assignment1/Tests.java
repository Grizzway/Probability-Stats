package Assignment1;

import java.util.*;

public class Tests {

	public static void main(String[] args) {
		StatsLibrary s_lib = new StatsLibrary();
		Random rnd = new Random();
		ArrayList<Integer> listOfNumbers = new ArrayList<>();
		for(int i = 0; i < 11; i++) {
			listOfNumbers.add(rnd.nextInt(10));
		}
		Collections.sort(listOfNumbers);
		System.out.println("Stats Library:");
		System.out.println(listOfNumbers);
		System.out.println(s_lib.Mean(listOfNumbers));
		System.out.println(s_lib.Median(listOfNumbers));
		System.out.println(s_lib.Mode(listOfNumbers));
		System.out.println(s_lib.StandardDev(listOfNumbers));
		System.out.println(s_lib.Factorial(50));
		System.out.println(s_lib.Permutations(4,4));
		System.out.println(s_lib.Combinations(2,5));



		System.out.println("Set Operations:");
		ArrayList<Integer> listA = new ArrayList<Integer>();
		Collections.addAll(listA, 1,2,3,7,8,9,10);
		ArrayList<Integer> listB = new ArrayList<Integer>();
		Collections.addAll(listB, 2,4,6,8,10);
		SetOperations setOp = new SetOperations();
		System.out.println(listA);
		System.out.println(listB);
		System.out.println(setOp.Union(listA, listB));
		System.out.println(setOp.Intersect(listA, listB));
		System.out.println(setOp.Complement(listA, listB));

	}
}
