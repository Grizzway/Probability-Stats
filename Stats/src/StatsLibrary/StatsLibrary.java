package StatsLibrary;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class StatsLibrary {

	/*
	 * Section 1
	 */

	/**
	 * Calculates the mean (average) of an ArrayList of integers.
	 *
	 * @param listOfNumbers ArrayList of integers to calculate the mean.
	 * @return Mean of the list as a double.
	 */
	public double Mean(ArrayList<Integer> listOfNumbers){
		int sum = 0;
		
		for(int singleNumber : listOfNumbers)
			sum = sum + singleNumber;
		
		return sum / (double)listOfNumbers.size();		
	}

	/**
	 * Calculates the median value of an ArrayList of integers.
	 *
	 * @param listOfNumbers ArrayList of integers to find the median.
	 * @return Median of the list as a double.
	 */
	public double Median(ArrayList<Integer> listOfNumbers){
		Collections.sort(listOfNumbers);
		if(listOfNumbers.size()%2 != 0)
			return listOfNumbers.get(listOfNumbers.size() / 2);
		
		int MidL = listOfNumbers.get(listOfNumbers.size() / 2 - 1);
		int MidR = listOfNumbers.get(listOfNumbers.size() / 2 + 1);
		return (double) (MidL + MidR) / 2;
	}

	/**
	 * Calculates the mode (most frequently occurring value) of an ArrayList of integers.
	 *
	 * @param listOfNumbers ArrayList of integers to find the mode.
	 * @return Optional containing the mode if it exists, or an empty Optional if no mode is found.
	 */
	public Optional<Integer> ModeCalc(ArrayList<Integer> listOfNumbers){
		Optional<Integer> maxVal = Optional.empty();
		int maxCount = 0;

		for(int i = 0; i < listOfNumbers.size(); i++){
			int count = 0;
            for (Integer listOfNumber : listOfNumbers) {
                if (listOfNumber.equals(listOfNumbers.get(i)))
                    count++;
            }
			if(count > maxCount && count > 1){
				maxCount = count;
				maxVal = Optional.of(listOfNumbers.get(i));
			}
		}
		return maxVal;
	}

	/**
	 * Uses ModeCalc to display a message indicating if there is a mode.
	 *
	 * @param listOfNumbers ArrayList of integers to check for a mode.
	 * @return String indicating the mode or that no mode exists.
	 */
	public String Mode(ArrayList<Integer> listOfNumbers){
		String response;
		Optional<Integer> result = ModeCalc(listOfNumbers);
		if(result.equals(Optional.empty()))
			response = "There is no mode.";
		else
			response = "The mode is " + result.get();
		return response;
	}

	/**
	 * Calculates the variance of an ArrayList of integers.
	 *
	 * @param listOfNumbers ArrayList of integers to calculate the variance.
	 * @return Variance of the list as a double.
	 */
	public double Variance(ArrayList<Integer> listOfNumbers){
		double mean = Mean(listOfNumbers);
		double sum = 0.0;
		for(double num : listOfNumbers)
			sum += Math.pow(num - mean, 2);

		return sum/listOfNumbers.size();
	}

	/**
	 * Calculates the standard deviation of an ArrayList of integers.
	 *
	 * @param listOfNumbers ArrayList of integers to calculate the standard deviation.
	 * @return Standard deviation of the list as a double.
	 */
	public double StandardDev(ArrayList<Integer> listOfNumbers){
		return Math.sqrt(Variance(listOfNumbers));
	}

	/**
	 * Calculates the factorial of a given integer.
	 *
	 * @param num Integer for which to calculate the factorial.
	 * @return Factorial of the integer as a BigInteger.
	 */
	public BigInteger Factorial(int num){
		BigInteger result = BigInteger.valueOf(1);
		for(int i = 2; i <= num; i++)
			result = result.multiply(BigInteger.valueOf(i));

		return result;
	}

	/**
	 * Calculates the number of permutations, given the number of picks and the total number.
	 *
	 * @param pickAmount Number of elements to pick.
	 * @param totalAmount Total number of elements.
	 * @return Number of permutations as a BigInteger.
	 */
	public BigInteger Permutations(int pickAmount, int totalAmount){
		return Factorial(totalAmount).divide(Factorial(totalAmount - pickAmount));
	}

	/**
	 * Calculates the number of combinations, given the number of picks and the total number.
	 *
	 * @param pickAmount Number of elements to pick.
	 * @param totalAmount Total number of elements.
	 * @return Number of combinations as a BigInteger.
	 */
	public BigInteger Combinations(int pickAmount, int totalAmount) {
		return Permutations(pickAmount, totalAmount).divide(Factorial(pickAmount));
	}

	/**
	 * Checks if two events are independent based on their probabilities.
	 *
	 * @param probA Probability of event A occurring.
	 * @param probB Probability of event B occurring.
	 * @param intProbAB Intersection probability of events A and B (i.e., both events occurring).
	 * @return true if the events are independent, false otherwise.
	 */
	public boolean areIndependent(double probA, double probB, double intProbAB) {
		return Math.abs((probA * probB) - intProbAB) < 1e-4;
	}

	/**
	 * Checks if two events are dependent based on their probabilities.
	 *
	 * @param probA Probability of event A occurring.
	 * @param probB Probability of event B occurring.
	 * @param intProbAB Intersection probability of events A and B (i.e., both events occurring).
	 * @return true if the events are dependent, false otherwise.
	 */
	public boolean areDependent(double probA, double probB, double intProbAB) {
		return !areIndependent(probA, probB, intProbAB);
	}

	/**
	 * Calculates the intersection probability of two independent events.
	 *
	 * @param probA Probability of event A occurring.
	 * @param probB Probability of event B occurring.
	 * @return Probability that both events A and B occur (independent intersection).
	 */
	public double independentIntersection(double probA, double probB) {
		return probA * probB;
	}

	/**
	 * Calculates the intersection probability of two dependent events.
	 *
	 * @param probA Probability of event A occurring.
	 * @param conditionalProbB_given_A Conditional probability of event B occurring given that A occurs.
	 * @return Probability that both events A and B occur (dependent intersection).
	 */
	public double dependentIntersection(double probA, double conditionalProbB_given_A) {
		return probA * conditionalProbB_given_A;
	}

	/**
	 * Calculates the union probability of two mutually exclusive events.
	 *
	 * @param probA Probability of event A occurring.
	 * @param probB Probability of event B occurring.
	 * @return Probability that either event A or B occurs.
	 */
	public double exclusiveUnion(double probA, double probB) {
		return probA + probB;
	}

	/**
	 * Calculates the union probability of two non-mutually exclusive events.
	 *
	 * @param probA Probability of event A occurring.
	 * @param probB Probability of event B occurring.
	 * @param intersectionProb Intersection probability of events A and B (i.e., both events occurring).
	 * @return Probability that either event A or B occurs, accounting for overlap.
	 */
	public double nonExclusiveUnion(double probA, double probB, double intersectionProb) {
		return probA + probB - intersectionProb;
	}

	/**
	 * Calculates the expected value of a discrete random variable.
	 *
	 * @param listOfProbabilities ArrayList of probabilities for each outcome.
	 * @return Expected value as a double.
	 */
	public double DiscreteExpected(ArrayList<Double> listOfProbabilities){
		double expected = 0.0;

		for (int i = 0; i < listOfProbabilities.size(); i++){
			expected += (i +1) * listOfProbabilities.get(i);
		}
		return expected;
	}

	/**
	 * Calculates the variance of a discrete random variable.
	 *
	 * @param listOfProbabilities ArrayList of probabilities for each outcome.
	 * @return Variance as a double.
	 */
	public double DiscreteVariance(ArrayList<Double> listOfProbabilities){
		double expected = DiscreteExpected(listOfProbabilities);
		double variance = 0.0;

		for (int i = 0; i < listOfProbabilities.size(); i++){
			variance += listOfProbabilities.get(i) * Math.pow((i+1) - expected, 2);
		}
		return variance;
	}

	/**
	 * Calculates the standard deviation of a discrete random variable.
	 *
	 * @param listOfProbabilities ArrayList of probabilities for each outcome.
	 * @return Standard deviation as a double.
	 */
	public double DiscreteStandardDeviation(ArrayList<Double> listOfProbabilities){
		return Math.sqrt(DiscreteVariance(listOfProbabilities));
	}

	/**
	 * Calculates the probability mass function (PMF) of a binomial distribution.
	 *
	 * @param probability Probability of success in a single trial.
	 * @param totalTrials Total number of trials.
	 * @param trial Number of successful outcomes.
	 * @return Probability of exactly 'trial' successes as a double.
	 */
	public double BinomialDistributionPMF(double probability, int totalTrials, int trial){
		return Combinations(trial, totalTrials).intValue()
				* (Math.pow(probability, trial))
				* (Math.pow(1-probability, totalTrials-trial));
	}

	/**
	 * Calculates the expected value of a binomial distribution.
	 *
	 * @param totalTrials Total number of trials.
	 * @param probability Probability of success in a single trial.
	 * @return Expected value as a double.
	 */
	public double BinomialDistributionExpected(int totalTrials, double probability){
		return totalTrials*probability;
	}

	/**
	 * Calculates the variance of a binomial distribution.
	 *
	 * @param totalTrials Total number of trials.
	 * @param probability Probability of success in a single trial.
	 * @return Variance as a double.
	 */
	public double BinomialDistributionVariance(int totalTrials, double probability){
		return BinomialDistributionExpected(totalTrials,probability) * (1 - probability);
	}

	/**
	 * Calculates the standard deviation of a binomial distribution.
	 *
	 * @param totalTrials Total number of trials.
	 * @param probability Probability of success in a single trial.
	 * @return Standard deviation as a double.
	 */
	public double BinomialDistributionStandardDeviation(int totalTrials, double probability){
		return Math.sqrt(BinomialDistributionVariance(totalTrials,probability));
	}

	/**
	 * Calculates the probability mass function (PMF) of a geometric distribution.
	 *
	 * @param probability Probability of success in a single trial.
	 * @param totalTrials Number of trials needed to achieve the first success.
	 * @return Probability of first success occurring on the 'totalTrials' trial as a double.
	 */
	public double GeometricDistributionPMF(double probability, int totalTrials){
		return Math.pow(1-probability, totalTrials-1) * probability;
	}

	/**
	 * Calculates the expected value of a geometric distribution.
	 *
	 * @param totalTrials Total number of trials.
	 * @param probability Probability of success in a single trial.
	 * @return Expected value as a double.
	 */
	public double GeometricDistributionExpected(int totalTrials, double probability){
		return totalTrials/probability;
	}

	/**
	 * Calculates the variance of a geometric distribution.
	 *
	 * @param totalTrials Total number of trials.
	 * @param probability Probability of success in a single trial.
	 * @return Variance as a double.
	 */
	public double GeometricDistributionVariance(int totalTrials, double probability){
		return (1-probability) / Math.pow(probability,2);
	}

	/**
	 * Calculates the standard deviation of a geometric distribution.
	 *
	 * @param totalTrials Total number of trials.
	 * @param probability Probability of success in a single trial.
	 * @return Standard deviation as a double.
	 */
	public double GeometricDistributionStandardDeviation(int totalTrials, double probability){
		return Math.sqrt(GeometricDistributionVariance(totalTrials, probability));
	}

	/*
	* Section 2
	*/

	/**
	 * Calculates the probability for the Negative Binomial Distribution.
	 *
	 * @param k The number of trials.
	 * @param r The number of successes (r).
	 * @param p The probability of success (p).
	 * @return The probability p(y) as a double.
	 */
	public BigDecimal NegativeBinomialProbability(int r, int k, double p) {
		if (r < 0 || k < 0 || p < 0.0 || p > 1.0) {
			return BigDecimal.ZERO; // Invalid parameters
		}

		BigInteger combinations = Combinations(k, k + r - 1); // Binomial coefficient
		BigDecimal pSuccess = BigDecimal.valueOf(p).pow(r); // p^r
		BigDecimal pFailure = BigDecimal.valueOf(1 - p).pow(k); // (1-p)^k

		return new BigDecimal(combinations).multiply(pSuccess).multiply(pFailure);
	}


	/**
	 * Calculates the mean for the Negative Binomial Distribution.
	 *
	 * @param r The number of successes (r).
	 * @param p The probability of success (p).
	 * @return The mean μ.
	 */
	public double NegativeBinomialMean(int r, double p) {
		return r / p;
	}

	/**
	 * Calculates the standard deviation for the Negative Binomial Distribution.
	 *
	 * @param r The number of successes (r).
	 * @param p The probability of success (p).
	 * @return The standard deviation σ.
	 */
	public double NegativeBinomialStandardDeviation(int r, double p) {
		return Math.sqrt(r * (1 - p) / (p * p));
	}

	/**
	 * Calculates the probability for the Hypergeometric Distribution.
	 *
	 * @param N The total population size (N).
	 * @param K The number of successes in the population (K).
	 * @param n The sample size (n).
	 * @param k The number of successes observed (k).
	 * @return The probability p(k) as a BigDecimal.
	 */
	public BigDecimal HypergeometricProbability(int N, int K, int n, int k) {
		// Input validation
		if (k < 0 || k > K || k > n || K < 0 || N < 0 || n > N) {
			return BigDecimal.ZERO; // Invalid parameters
		}

		// Calculate combinations for the numerator and denominator
		BigInteger numerator = Combinations(k, K).multiply(Combinations(n - k, N - K)); // \binom{K}{k} * \binom{N-K}{n-k}
		BigInteger denominator = Combinations(n, N); // \binom{N}{n}

		// Return the probability as a BigDecimal with increased precision
        return new BigDecimal(numerator).divide(new BigDecimal(denominator), 6, RoundingMode.HALF_UP);
	}

	/**
	 * Calculates the mean for the Hypergeometric Distribution.
	 *
	 * @param r The number of successes in the population (r).
	 * @param n The sample size (n).
	 * @param N The total population size (N).
	 * @return The mean μ.
	 */
	public double HypergeometricMean(int r, int n, int N) {
		return (n * r) / (double) N;
	}

	/**
	 * Calculates the standard deviation for the Hypergeometric Distribution.
	 *
	 * @param r The number of successes in the population (r).
	 * @param n The sample size (n).
	 * @param N The total population size (N).
	 * @return The standard deviation σ.
	 */
	public double HypergeometricStandardDeviation(int r, int n, int N) {
		double numerator = n * (r / (double) N) * (1 - r / (double) N) * (N - n) / (double) (N - 1);
		return Math.sqrt(numerator);
	}

	/**
	 * Calculates the probability for the Poisson Distribution.
	 *
	 * @param y The number of occurrences (y).
	 * @param lambda The expected number of occurrences (λ).
	 * @return The probability p(y) as a double.
	 */
	public double PoissonProbability(int y, double lambda) {
		return (Math.pow(lambda, y) * Math.exp(-lambda)) / Factorial(y).doubleValue();
	}

	/**
	 * Calculates the probability for the Uniform Distribution.
	 *
	 * @param y The random variable value.
	 * @param theta1 The lower bound (Θ1).
	 * @param theta2 The upper bound (Θ2).
	 * @return The probability density f(y).
	 */
	public double uniformProbability(double y, double theta1, double theta2) {
		if (y >= theta1 && y <= theta2) {
			return 1 / (theta2 - theta1);
		}
		return 0;
	}


	/**
	 * Calculates the probability density for the Exponential Distribution.
	 *
	 * @param y The random variable value.
	 * @param beta The rate parameter (β).
	 * @return The probability density f(y).
	 */
	public double exponentialProbability(double y, double beta) {
		if (y >= 0) {
			return (1 / beta) * Math.exp(-y / beta);
		}
		return 0;
	}

}
