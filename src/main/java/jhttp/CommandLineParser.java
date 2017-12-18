/*
* parses arguments from command line
*/

package jhttp;

import java.util.HashMap;

public class CommandLineParser {

  private static String url;
  private static String httpMethod;
  private static HashMap<String, String> headers = new HashMap<String, String>();
  private static String data;
  private static String fileName;
  private static String outputFile;

  public static RequestOptions parse(String[] args) {
    if(args.length == 0){
      throw new RuntimeException();
    }

    url = args[args.length-1];

    for(int i=0; i<args.length-1; i++) {
      if(isKey(args[i])) {
          String key = args[i];
          String value = getValueIfExists(i+1, args);

          if(key.equals(JhttpOptionTypes.HTTP_METHOD)) {
            httpMethod = value;
          } else if(key.equals(JhttpOptionTypes.HEADER)) {
            putHttpKeyValueIfValid(value);
          } else if(key.equals(JhttpOptionTypes.DATA)) {
            data = value;
          } else if(key.equals(JhttpOptionTypes.FILE_NAME)) {
            fileName = value;
          } else if(key.equals(JhttpOptionTypes.OUTPUT_FILE)) {
            outputFile = value;
          }
      }
    }

    if(data != null && fileName != null){
      throw new RuntimeException("Cannot specify two different HTTP bodies. Use one of -d and -f.");
    } else if (data != null || fileName != null) {
      httpMethod = httpMethod == null ? "POST" : httpMethod;
    }

    RequestOptions options = new RequestOptions()
      .setUrl(url)
      .setHttpMethod(httpMethod)
      .setHeaders(headers)
      .setData(data)
      .setFileName(fileName)
      .setOutputFile(outputFile);

    return options;
  }

  private static void putHttpKeyValueIfValid(String pair){
    if(pair == null || pair.indexOf("=") == -1){
      return;
    }

    String headerName = pair.substring(0, pair.indexOf("="));
    String headerValue = pair.substring(pair.indexOf("=") + 1);

    headers.put(headerName, headerValue);
  }

  private static boolean isKey(String key) {
    return key.startsWith("-");
  }

  private static String getValueIfExists(int valueIndex, String[] args) {
    if(valueIndex >= args.length-1 || isKey(args[valueIndex])) {
      return null;
    } else {
      return args[valueIndex];
    }
  }

}
