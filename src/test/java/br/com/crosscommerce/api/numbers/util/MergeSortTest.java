package br.com.crosscommerce.api.numbers.util;

import br.com.crosscommerce.api.numbers.model.ExtractorRequestNumbers;
import br.com.crosscommerce.api.numbers.util.MergeSort;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MergeSortTest {

  @Test
  public void checkFullOrderedArray(){
    ArrayList numbers = new ArrayList<Double>();
    numbers.add(22.0);
    numbers.add(10.0);
    numbers.add(15.0);
    numbers.add(100.0);
    numbers.add(4.0);

    MergeSort.mergeSort(numbers);
    Assert.assertEquals(4.0, numbers.get(0));
    Assert.assertEquals(100.0, numbers.get(4));
  }

  @Test
  public void checkMergeArrays(){
    ArrayList numbers = new ArrayList<Double>();
    numbers.add(22.0);
    numbers.add(10.0);

    ArrayList firstList = new ArrayList<Double>();
    firstList.add(22.0);
    ArrayList secondList = new ArrayList<Double>();
    secondList.add(10.0);

    MergeSort.mergeArrays(numbers, firstList, secondList);
    Assert.assertEquals(10.0, numbers.get(0));
    Assert.assertEquals(22.0, numbers.get(1));
  }

  @Test
  public void mergeOrderedNumbersWithMockTest(){
    ExtractorRequestNumbers extractorRequestNumbers = Mockito.mock(ExtractorRequestNumbers.class);
    List<Double> numbers = new ArrayList<>();
    numbers.add(200.0);
    numbers.add(157.0);
    numbers.add(167.0);
    Mockito.when(extractorRequestNumbers.collectNumbers()).thenReturn(numbers);
    List<Double> returnedNumbers = extractorRequestNumbers.collectNumbers();
    MergeSort.mergeSort(returnedNumbers);
    Assert.assertEquals(157.0, returnedNumbers.get(0).doubleValue(), 0.00001);
  }

}
