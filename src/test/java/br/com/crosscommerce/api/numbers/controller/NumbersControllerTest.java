package br.com.crosscommerce.api.numbers.controller;

import br.com.crosscommerce.api.numbers.service.NumbersService;
import br.com.crosscommerce.api.numbers.util.MergeSort;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NumbersControllerTest {

  @Test
  public void getNumbersWithMock(){
    NumbersController numbersController = Mockito.mock(NumbersController.class);
    ArrayList numbers = new ArrayList<Double>();
    numbers.add(22.0);
    numbers.add(10.0);
    Mockito.when(numbersController.getParsedNumbers()).thenReturn(numbers);
    Assert.assertEquals(22.0, numbersController.getParsedNumbers().get(0), 0.001);
  }

  @Test
  public void getOrderedNumbersWithMock(){
    NumbersController numbersController = Mockito.mock(NumbersController.class);
    ArrayList numbers = new ArrayList<Double>();
    numbers.add(10.0);
    numbers.add(22.0);
    Mockito.when(numbersController.getOrderedNumbers()).thenReturn(numbers);
    Assert.assertEquals(numbers, numbersController.getOrderedNumbers());
  }

}
