package com.anon_grunt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class scraper {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  public static void main(String[] args) throws IOException, JSONException {
    String _file0 = "https://assets.generativedungeon.com/dungeons/";
    String _file1 = ".json";
    int domain0 = 1;
    int domain1 = 3000;
    try {
      File _file = new File("fileName.txt");
      _file.delete();
      _file.createNewFile();
    } catch(IOException e) {}
    try {
      FileWriter _writer = new FileWriter("fileName.txt");
      System.out.println("Writing to file, please wait...");
    

    
    
      _writer.write("[");
    for(int i=domain0; i<=domain1; i++) {

        JSONObject _json = readJsonFromUrl(_file0 + String.valueOf(i) + "/" + String.valueOf(i) + _file1);
        JSONArray array = _json.getJSONArray("attributes");
        _writer.write("{\"number\": \"" + String.valueOf(i) + "\",");
        for(int j=0; j<array.length(); j++) {
          _writer.write("\"" + array.getJSONObject(j).getString("trait_type") + "\": " + "\"" + array.getJSONObject(j).getString("value") + "\"");
          if(j<array.length()-1) _writer.write(",");
        }
        if(i<domain1) _writer.write("},");
        else _writer.write("}");
    }
    _writer.write("]");
    _writer.close();
    System.out.println("Finished writing to file.");
  } catch(IOException e) {}
  }
}

// paste the file text hear after finished
// https://www.convertjson.com/json-to-html-table.htm
