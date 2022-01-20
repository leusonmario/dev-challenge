package br.com.crosscommerce.api.numbers.util;

import br.com.crosscommerce.api.numbers.util.RequestSupporter;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class RequestSupporterTest {

  @Test
  public void requestForValidUrlTest(){
    Assert.assertTrue(RequestSupporter.isHttpConnectionAvailable("http://challenge.dienekes.com.br/api/numbers?page=1"));
  }

  @Test
  public void requestForInvalidUrlTest(){
    Assert.assertFalse(RequestSupporter.isHttpConnectionAvailable("http://challenge.dienekes.com.br/api/old-numbers"));
  }



}
