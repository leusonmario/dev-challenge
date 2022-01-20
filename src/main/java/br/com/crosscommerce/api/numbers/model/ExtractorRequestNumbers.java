package br.com.crosscommerce.api.numbers.model;

import br.com.crosscommerce.api.numbers.util.RequestSupporter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ExtractorRequestNumbers {
  private String requestUrl;
  private String query;

  public ExtractorRequestNumbers(String requestUrl, String query){
    this.requestUrl = requestUrl;
    this.query = query;
  }

  private String readJSONInfo(URL url){
    String inline = "";
    Scanner scanner = null;
    try {
      scanner = new Scanner(url.openStream());
      while (scanner.hasNext()) {
        inline += scanner.nextLine();
      }
      scanner.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

    return inline;
  }

  private List<Double> parseNumbers(String request) {
    List<Double> temporaryNumbers = new ArrayList<>();
    try {
      HttpURLConnection connection = RequestSupporter.getConnection(request);
      String inline = readJSONInfo(connection.getURL());
      JSONParser parse = new JSONParser();
      JSONObject data_obj = (JSONObject) parse.parse(inline);
      JSONArray jsonArray = new JSONArray();

      jsonArray.add(data_obj.get("numbers"));
      temporaryNumbers = (List<Double>) jsonArray.get(0);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return temporaryNumbers;
  }

  private String adjustUrlForNumberPage(String url, String query, int pageNumber){
    return url + query + "?page=" + pageNumber;
  }

  public List<Double> collectNumbers() {
    List<Double> parsedNumbers = new ArrayList<>();
    List<Double> numbersOfOnePage;
    int pageNumber = 1;
    numbersOfOnePage = this.parseNumbers(adjustUrlForNumberPage(this.getRequestUrl(), this.getQuery(), pageNumber));

    while(numbersOfOnePage.size() > 0) {
      parsedNumbers.addAll(numbersOfOnePage);
      pageNumber++;
      numbersOfOnePage.clear();
      numbersOfOnePage = this.parseNumbers(
          adjustUrlForNumberPage(this.getRequestUrl(), this.getQuery(), pageNumber));
    }
    return parsedNumbers;
  }

  public String getRequestUrl(){
    return this.requestUrl;
  }

  public String getQuery(){
    return this.query;
  }

}
