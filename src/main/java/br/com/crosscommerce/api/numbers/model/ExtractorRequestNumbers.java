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

  private List<Double> parseNumbers(String inline){
    List<Double> temporaryNumbers = new ArrayList<>();
    try {
      JSONParser parse = new JSONParser();
      JSONObject data_obj = (JSONObject) parse.parse(inline);
      JSONArray jsonArray = new JSONArray();

      jsonArray.add(data_obj.get("numbers"));
      temporaryNumbers = (List<Double>) jsonArray.get(0);
      if (temporaryNumbers.size() < 1)
        throw new RuntimeException("No valid numbers were available");
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return temporaryNumbers;
  }

  private String adjustUrlForNumberPage(String url, String query, int pageNumber){
    return url + query + "?page=" + pageNumber;
  }

  public List<Double> collectNumbers(){
    List<Double> parsedNumbers = new ArrayList<>();
    int pageNumber = 1;
    String request = adjustUrlForNumberPage(this.requestUrl, this.query, pageNumber);
    while(RequestSupporter.isHttpConnectionAvailable(request)){
      request = adjustUrlForNumberPage(this.requestUrl, this.query, pageNumber);
      HttpURLConnection connection = RequestSupporter.getConnection(request);
      String jsonInfo = readJSONInfo(connection.getURL());
      parsedNumbers.addAll(parseNumbers(jsonInfo));
      pageNumber++;
    }
    return parsedNumbers;
  }

}
