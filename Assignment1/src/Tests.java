import java.util.ArrayList;
public class Tests {

	public static void main(String[] args) {
		Mean mean = new Mean();
		
		ArrayList<Integer> listOfNumbers = new ArrayList<>();
		for(int i = 0; i < 100; i++) {		
			listOfNumbers.add(i);
		}
		
		System.out.print(mean.computeMean(listOfNumbers));
	}
}
