package Assignment1;

import java.util.ArrayList;
import java.util.Collections;

public class SetOperations {

    public ArrayList<Integer> Union(ArrayList<Integer> listOfNumbersA, ArrayList<Integer> listOfNumbersB){
        ArrayList<Integer> unionList = new ArrayList<>(listOfNumbersA);
        for(Integer number : listOfNumbersB){
            if(!unionList.contains(number))
                unionList.add(number);
        }
        Collections.sort(unionList);
        return  unionList;
    }

    public ArrayList<Integer> Intersect(ArrayList<Integer> listOfNumbersA, ArrayList<Integer> listOfNumbersB){
        ArrayList<Integer> intersectList = new ArrayList<>();
        for(Integer number : listOfNumbersA){
            if(listOfNumbersB.contains(number))
                intersectList.add(number);
        }
        Collections.sort(intersectList);
        return intersectList;
    }

    public ArrayList<Integer> Complement(ArrayList<Integer> listOfNumbers, ArrayList<Integer> subset){
        ArrayList<Integer> complementList = new ArrayList<>();
        for(Integer number : listOfNumbers){
            if(!subset.contains(number))
                complementList.add(number);
        }
        Collections.sort(complementList);
        return complementList;
    }
}
