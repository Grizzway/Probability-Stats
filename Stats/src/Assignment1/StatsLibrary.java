package Assignment1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;

public class StatsLibrary {

	public double Mean(ArrayList<Integer> listOfNumbers)
	{
		int sum = 0;
		
		for(int singleNumber : listOfNumbers)
			sum = sum + singleNumber;
		
		return sum / (double)listOfNumbers.size();		
	}
	
	public double Median(ArrayList<Integer> listOfNumbers)
	{
		if(listOfNumbers.size()%2 != 0)
			return listOfNumbers.get(listOfNumbers.size() / 2);
		
		int MidL = listOfNumbers.get(listOfNumbers.size() / 2 - 1);
		int MidR = listOfNumbers.get(listOfNumbers.size() / 2 + 1);
		return (double) (MidL + MidR) / 2;
	}

	public Optional<Integer> ModeCalc(ArrayList<Integer> listOfNumbers)
	{
		Optional<Integer> maxVal = Optional.empty();
		int maxCount = 0;

		for(int i = 0; i < listOfNumbers.size(); i++){
			int count = 0;
			for(int j = 0; j < listOfNumbers.size(); j++){
				if(listOfNumbers.get(j).equals(listOfNumbers.get(i)))
					count++;
			}
			if(count > maxCount && count > 1){
				maxCount = count;
				maxVal = Optional.of(listOfNumbers.get(i));
			}
		}
		return maxVal;
	}

	public String Mode(ArrayList<Integer> listOfNumbers)
	{
		String response;
		Optional<Integer> result = ModeCalc(listOfNumbers);
		if(result.equals(Optional.empty()))
			response = "There is no mode.";
		else
			response = "The mode is " + result.get();
		return response;
	}
}
