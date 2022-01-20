package br.com.crosscommerce.api.numbers.service;

import br.com.crosscommerce.api.numbers.util.MergeSort;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NumberServiceTest {

  @Test
  public void getNumbersWithMock(){
    NumbersService numbersService = Mockito.mock(NumbersService.class);
    ArrayList numbers = new ArrayList<Double>();
    numbers.add(22.0);
    numbers.add(10.0);
    Mockito.when(numbersService.getNumbers()).thenReturn(numbers);
    Assert.assertEquals(22.0, numbersService.getNumbers().get(0), 0.001);
  }

  @Test
  public void getOrderedNumbersWithMock(){
    NumbersService numbersService = Mockito.mock(NumbersService.class);
    ArrayList numbers = new ArrayList<Double>();
    numbers.add(10.0);
    numbers.add(22.0);
    Mockito.when(numbersService.getOrderedNumbers()).thenReturn(numbers);
    List<Double> orderedNumbers = numbersService.getOrderedNumbers();

    ArrayList unorderedNumbers = new ArrayList<Double>();
    unorderedNumbers.add(22.0);
    unorderedNumbers.add(10.0);
    MergeSort.mergeSort(unorderedNumbers);
    Assert.assertEquals(orderedNumbers, unorderedNumbers);
  }

}
