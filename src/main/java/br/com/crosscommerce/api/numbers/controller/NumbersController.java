package br.com.crosscommerce.api.numbers.controller;

import br.com.crosscommerce.api.numbers.service.NumbersService;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NumbersController {
  private NumbersService numbersService = new NumbersService();

  @RequestMapping(value = "/numbers", method = RequestMethod.GET)
  public List<Double> getParsedNumbers(){
    return this.numbersService.getNumbers();
  }

  @RequestMapping(value = "/numbers-ordered", method = RequestMethod.GET)
  public List<Double> getOrderedNumbers(){
    return this.numbersService.getOrderedNumbers();
  }
}
