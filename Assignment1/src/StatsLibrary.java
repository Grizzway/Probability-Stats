import java.util.ArrayList;

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
		return (MidL + MidR) / 2;
		
	}
}
