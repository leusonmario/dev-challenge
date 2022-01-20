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

  /***
   * For a given url, the method collects all associated information and
   * returns it as a string
   * @param url holds the target page
   * @return a string holding the information of the previous associated page
  */
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

  /***
   * For a given page request, the method parses and return a list of numbers (double)
   * If any problem occurred during this process, an empty list is returned.
   * @param request stands for the target page in request
   * @return a list of numbers
  */
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

  /***
   * This method extracts list of numbers based on a given url and query.
   * Each list associated with a page is locally saved and later returned
   * This process is interrupted when a list associated with a page has size zero.
   * @return a list of numbers (double)
   * */
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
