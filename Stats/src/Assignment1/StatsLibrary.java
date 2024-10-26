package Assignment1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class StatsLibrary {

	public double Mean(ArrayList<Integer> listOfNumbers){
		int sum = 0;
		
		for(int singleNumber : listOfNumbers)
			sum = sum + singleNumber;
		
		return sum / (double)listOfNumbers.size();		
	}
	
	public double Median(ArrayList<Integer> listOfNumbers){
		Collections.sort(listOfNumbers);
		if(listOfNumbers.size()%2 != 0)
			return listOfNumbers.get(listOfNumbers.size() / 2);
		
		int MidL = listOfNumbers.get(listOfNumbers.size() / 2 - 1);
		int MidR = listOfNumbers.get(listOfNumbers.size() / 2 + 1);
		return (double) (MidL + MidR) / 2;
	}

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

	public String Mode(ArrayList<Integer> listOfNumbers){
		String response;
		Optional<Integer> result = ModeCalc(listOfNumbers);
		if(result.equals(Optional.empty()))
			response = "There is no mode.";
		else
			response = "The mode is " + result.get();
		return response;
	}

	public double Variance(ArrayList<Integer> listOfNumbers){
		double mean = Mean(listOfNumbers);
		double sum = 0.0;
		for(double num : listOfNumbers)
			sum += Math.pow(num - mean, 2);

		return sum/listOfNumbers.size();
	}

	public double StandardDev(ArrayList<Integer> listOfNumbers){
		return Math.sqrt(Variance(listOfNumbers));
	}

	public BigInteger Factorial(int num){
		BigInteger result = BigInteger.valueOf(1);
		for(int i = 2; i <= num; i++)
			result = result.multiply(BigInteger.valueOf(i));

		return result;
	}

	public BigInteger Permutations(int pickAmount, int totalAmount){
		return Factorial(totalAmount).divide(Factorial(totalAmount - pickAmount));
	}

	public BigInteger Combinations(int pickAmount, int totalAmount){
		return Permutations(pickAmount, totalAmount).divide(Factorial(pickAmount));
	}

	public boolean areIndependent(double probA, double probB, double intProbAB){
		return Math.abs((probA * probB) - intProbAB) < 1e-4;
	}

	public boolean areDependent(double probA, double probB, double intProbAB){
		return !areIndependent(probA,probB,intProbAB);
	}

	public double independentIntersection(double probA, double probB) {
		return probA * probB;
	}

	public double dependentIntersection(double probA, double conditionalProbB_given_A) {
		return probA * conditionalProbB_given_A;
	}

	public double exclusiveUnion(double probA, double probB) {
		return probA + probB;
	}

	public double nonExclusiveUnion(double probA, double probB, double intersectionProb) {
		return probA + probB - intersectionProb;
	}

	public double DiscreteExpected(ArrayList<Double> listOfProbabilities){
		double expected = 0.0;

		for (int i = 0; i < listOfProbabilities.size(); i++){
			expected += (i +1) * listOfProbabilities.get(i);
		}
		return expected;
	}

	public double DiscreteVariance(ArrayList<Double> listOfProbabilities){
		double expected = DiscreteExpected(listOfProbabilities);
		double variance = 0.0;

		for (int i = 0; i < listOfProbabilities.size(); i++){
			variance += listOfProbabilities.get(i) * Math.pow((i+1) - expected, 2);
		}
		return variance;
	}

	public double DiscreteStandardDeviation(ArrayList<Double> listOfProbabilities){
		return Math.sqrt(DiscreteVariance(listOfProbabilities));
	}

	public double BinomialDistributionPMF(double probability, int totalTrials, int trial){
		return Combinations(trial, totalTrials).intValue()
				* (Math.pow(probability, trial))
				* (Math.pow(1-probability, totalTrials-trial));
	}

	public double BinomialDistributionExpected(int totalTrials, double probability){
		return totalTrials*probability;
	}

	public double BinomialDistributionVariance(int totalTrials, double probability){
		return BinomialDistributionExpected(totalTrials,probability) * (1 - probability);
	}

	public double BinomialDistributionStandardDeviation(int totalTrials, double probability){
		return Math.sqrt(BinomialDistributionVariance(totalTrials,probability));
	}

	public double GeometricDistributionPMF(double probability, int totalTrials){
		return Math.pow(1-probability, totalTrials-1) * probability;
	}

	public double GeometricDistributionExpected(int totalTrials, double probability){
		return totalTrials/probability;
	}

	public double GeometricDistributionVariance(int totalTrials, double probability){
		return (1-probability) / Math.pow(probability,2);
	}

	public double GeometricDistributionStandardDeviation(int totalTrials, double probability){
		return Math.sqrt(GeometricDistributionVariance(totalTrials, probability));
	}
}
