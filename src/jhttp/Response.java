package jhttp;

import java.net.HttpURLConnection;

import java.util.Map;
import java.util.List;

public class Response {
    public Map<String, List<String>> requestHeaderMap;
    public HttpURLConnection connection;
    public String outputFile;

    public Response(Map<String, List<String>> requestHeaderMap, HttpURLConnection connection, String outputFile){
      this.requestHeaderMap = requestHeaderMap;
      this.connection = connection;
      this.outputFile = outputFile;
    }
}
