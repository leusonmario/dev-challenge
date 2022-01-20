package br.com.crosscommerce.api.numbers.util;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

  public static void mergeSort(List<Double> numbers){
    if (numbers.size() <= 1) {
      return ;
    }
    int midle = numbers.size()/2;
    List<Double> firstNumbers = new ArrayList<>(numbers.subList(0, midle));
    List<Double> lastNumbers = new ArrayList<>(numbers.subList(midle, numbers.size()));

    mergeSort(firstNumbers);
    mergeSort(lastNumbers);

    mergeArrays(numbers, firstNumbers, lastNumbers);
  }

  public static void mergeArrays(List<Double> finalList, List<Double> firstList, List<Double> secondList) {
    int indexFirstNumbers = 0;
    int indexLastNumbers = 0;
    int indexAllNumbers = 0;

    while (indexFirstNumbers < firstList.size() && indexLastNumbers < secondList.size()) {
      //is the number of the first list smaller than the number of the last list?
      if (firstList.get(indexFirstNumbers) < secondList.get(indexLastNumbers)) {
        finalList.set(indexAllNumbers++, firstList.get(indexFirstNumbers++));
      }
      else {
        finalList.set(indexAllNumbers++, secondList.get(indexLastNumbers++));
      }
    }
    //are there still numbers on the first list to be added on the final list?
    while (indexFirstNumbers < firstList.size()) {
      finalList.set(indexAllNumbers++, firstList.get(indexFirstNumbers++));
    }
    //are there still numbers on the second list to be added on the final list?
    while (indexLastNumbers < secondList.size()) {
      finalList.set(indexAllNumbers++, secondList.get(indexLastNumbers++));
    }
  }

}
