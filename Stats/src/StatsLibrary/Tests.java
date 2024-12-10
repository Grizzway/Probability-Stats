package StatsLibrary;

import java.util.*;

public class Tests {

	public static void main(String[] args) {
		StatsLibrary s_lib = new StatsLibrary();
		Random rnd = new Random();
		ArrayList<Integer> listOfNumbers = new ArrayList<>();


		/*
		*  Section 1
		*/
//		//Generate a list of 15 random numbers from 0-10
//		for(int i = 0; i < 15; i++) {
//			listOfNumbers.add(rnd.nextInt(10));
//		}
//
//		Collections.sort(listOfNumbers);
//		System.out.println("--------Stats Library--------");
//		System.out.println("\nGiven this list of numbers: \n" + listOfNumbers);
//		System.out.println("\nThis is the Mean: " + s_lib.Mean(listOfNumbers));
//		System.out.println("This is the Median: " + s_lib.Median(listOfNumbers));
//		System.out.println(s_lib.Mode(listOfNumbers));
//		System.out.println("This is the Variance: " + s_lib.Variance(listOfNumbers));
//		System.out.println("This is the Standard Deviation: " + s_lib.StandardDev(listOfNumbers));
//		System.out.println("--------");
//		System.out.println("This is the factorial for the number 50: " + s_lib.Factorial(50));
//		System.out.println("This is how many permutations given 6, picking 4: " + s_lib.Permutations(4,6));
//		System.out.println("This is how many combinations given 5, picking 2: " + s_lib.Combinations(2,5));
//		System.out.println("--------");
//		System.out.println("This is the Binomial Distribution given p=0.8, total trials = 10 on the 7th trial: " + s_lib.BinomialDistributionPMF(0.8, 10,7));
//		System.out.println("This is the Expected for the Binomial Distribution for 5 trials with a probability of 0.5: " + s_lib.BinomialDistributionExpected(5,0.5));
//		System.out.println("This is the Variance for the Binomial Distribution for 5 trials with a probability of 0.5: " + s_lib.BinomialDistributionVariance(5,0.5));
//		System.out.println("This is the Standard Deviation for the Binomial Distribution for 5 trials with a probability of 0.5: " + s_lib.BinomialDistributionStandardDeviation(5,0.5));
//		System.out.println("--------");
//		System.out.println("This is the Expected for the Geometric Distribution for 1 trial with a probability of 0.5: " + s_lib.GeometricDistributionExpected(1,0.5));
//		System.out.println("This is the Variance for the Geometric Distribution for 1 trial with a probability of 0.5: " + s_lib.GeometricDistributionVariance(1,0.5));
//		System.out.println("This is the Standard Deviation for the Geometric Distribution for 1 trial with a probability of 0.5: " + s_lib.GeometricDistributionStandardDeviation(1,0.5));
//
//		System.out.println("\n-------------------------------------------------------------------------\n");
//
//		System.out.println("--------Set Operations--------");
//
//		//Superset A
//		ArrayList<Integer> listA = new ArrayList<>();
//		Collections.addAll(listA, 1,2,3,7,8,9,10);
//		//Subset B
//		ArrayList<Integer> listB = new ArrayList<>();
//		Collections.addAll(listB, 2,4,6,8,10);
//
//		SetOperations setOp = new SetOperations();
//
//		System.out.println("Given List A: \n" + listA);
//		System.out.println("And Given List B: \n" + listB);
//		System.out.println("\nThis is List A UNION List B: " + setOp.Union(listA, listB));
//		System.out.println("This is List A INTERSECT List B: " + setOp.Intersect(listA, listB));
//		System.out.println("Given Superset A, the COMPLEMENT of Subset B is: " + setOp.Complement(listA, listB));

		/*
		 *  Section 2
		 */

		System.out.println("Negative Binomial Probability: " + s_lib.NegativeBinomialProbability(5, 3, 0.4));
		System.out.println("Negative Binomial Probability: " + s_lib.NegativeBinomialProbability(5, 2, 0.3));
		System.out.println("Negative Binomial Mean: " + s_lib.NegativeBinomialMean(3, 0.5));
		System.out.println("Negative Binomial Standard Deviation: " + s_lib.NegativeBinomialStandardDeviation(3, 0.5));
		System.out.println("--------");
		System.out.println("Hypergeometric Probability: " + s_lib.HypergeometricProbability(2, 5, 3, 10));
		System.out.println("Hypergeometric Probability: " + s_lib.HypergeometricProbability(50, 10, 5, 2));
		System.out.println("Hypergeometric Mean: " + s_lib.HypergeometricMean(20, 10, 50));
		System.out.println("Hypergeometric Standard Deviation: " + s_lib.HypergeometricStandardDeviation(20, 10, 50));
		System.out.println("--------");
		System.out.println("Poisson Probability: " + s_lib.PoissonProbability(10, 5.0));
		System.out.println("--------");
		System.out.println("Uniform Probability: " + s_lib.uniformProbability(3.0, 1.0, 5.0));
		System.out.println("--------");
		System.out.println("Exponential Probability: " + s_lib.exponentialProbability(3.0, 2.0));

	}
}
