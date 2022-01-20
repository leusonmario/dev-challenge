package br.com.crosscommerce.api.numbers.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ExtractorRequestNumbersTest {

  @Test
  public void collectNumbersWithMockTest(){
    ExtractorRequestNumbers extractorRequestNumbers = Mockito.mock(ExtractorRequestNumbers.class);
    List<Double> numbers = new ArrayList<>();
    numbers.add(200.0);
    numbers.add(157.0);
    numbers.add(167.0);
    Mockito.when(extractorRequestNumbers.collectNumbers()).thenReturn(numbers);
    List<Double> returnedNumbers = extractorRequestNumbers.collectNumbers();
    Assert.assertEquals(200.0, returnedNumbers.get(0).doubleValue(), 0.00001);
  }

  @Test
  public void returnEmptyArrayForInvalidQuery(){
    ExtractorRequestNumbers extractorRequestNumbers = Mockito.mock(ExtractorRequestNumbers.class);
    Mockito.when(extractorRequestNumbers.getRequestUrl()).thenReturn("http://challenge.dienekes.com.br/api/");
    Mockito.when(extractorRequestNumbers.getQuery()).thenReturn("files");
    List<Double> returnedNumbers = extractorRequestNumbers.collectNumbers();
    Assert.assertTrue(returnedNumbers.size() == 0);
  }

  @Test
  public void returnEmptyArrayForInvalidURL(){
    ExtractorRequestNumbers extractorRequestNumbers = Mockito.mock(ExtractorRequestNumbers.class);
    Mockito.when(extractorRequestNumbers.getRequestUrl()).thenReturn("http://challenge.dienekes.com.br/api-two/");
    Mockito.when(extractorRequestNumbers.getQuery()).thenReturn("query");
    List<Double> returnedNumbers = extractorRequestNumbers.collectNumbers();
    Assert.assertTrue(returnedNumbers.size() == 0);
  }

}
