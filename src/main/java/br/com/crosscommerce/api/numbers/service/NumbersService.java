package br.com.crosscommerce.api.numbers.service;

import br.com.crosscommerce.api.numbers.model.ExtractorRequestNumbers;
import br.com.crosscommerce.api.numbers.util.MergeSort;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NumbersService {
  private ExtractorRequestNumbers extractorRequestNumbers;

  public NumbersService(){
    this.extractorRequestNumbers = new ExtractorRequestNumbers("http://challenge.dienekes.com.br/api/", "numbers");
  }

  public List<Double> getNumbers(){
    return this.extractorRequestNumbers.collectNumbers();
  }

  public List<Double> getOrderedNumbers(){
    List<Double> copyNumbers = this.extractorRequestNumbers.collectNumbers();
    MergeSort.mergeSort(copyNumbers);
    return copyNumbers;
  }

}
