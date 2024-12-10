package StatsLibrary;

import java.util.ArrayList;
import java.util.Collections;

public class SetOperations {


    /**
     * Calculates the union of two given ArrayLists of Integers.
     *
     * @param listOfNumbersA The first list of integers.
     * @param listOfNumbersB The second list of integers.
     * @return A sorted ArrayList containing the union of both lists, with duplicates removed.
     */
    public ArrayList<Integer> Union(ArrayList<Integer> listOfNumbersA, ArrayList<Integer> listOfNumbersB){
        ArrayList<Integer> unionList = new ArrayList<>(listOfNumbersA);
        for(Integer number : listOfNumbersB){
            if(!unionList.contains(number))
                unionList.add(number);
        }
        Collections.sort(unionList);
        return  unionList;
    }

    /**
     * Calculates the intersection of two given ArrayLists of Integers.
     *
     * @param listOfNumbersA The first list of integers.
     * @param listOfNumbersB The second list of integers.
     * @return A sorted ArrayList containing the intersection of both lists.
     */
    public ArrayList<Integer> Intersect(ArrayList<Integer> listOfNumbersA, ArrayList<Integer> listOfNumbersB){
        ArrayList<Integer> intersectList = new ArrayList<>();
        for(Integer number : listOfNumbersA){
            if(listOfNumbersB.contains(number))
                intersectList.add(number);
        }
        Collections.sort(intersectList);
        return intersectList;
    }

    /**
     * Calculates the complement of a subset based on a given superset of integers.
     *
     * @param listOfNumbers The superset of integers.
     * @param subset The subset of integers.
     * @return A sorted ArrayList containing elements in the superset that are not in the subset.
     */
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
