package br.com.crosscommerce.api.numbers.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RequestSupporter {

  public static boolean isHttpConnectionAvailable(String requestUrl){
    HttpURLConnection conn = getConnection(requestUrl);
    try {
      if (conn.getResponseCode() == 200)
        return true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static HttpURLConnection getConnection(String requestUrl){
    HttpURLConnection conn = null;
    try {
      URL url = new URL(requestUrl);
      conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.connect();
    }catch (ProtocolException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return conn;
  }

}
