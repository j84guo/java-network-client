package jhttp;

import java.net.HttpURLConnection;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Map;
import java.util.List;

public class ResponseViewer {

  private HttpURLConnection connection;
  private Map<String, List<String>> requestHeaderMap;
  private String outputFile;

  public ResponseViewer(Response response){
    this.connection = response.connection;
    this.requestHeaderMap = response.requestHeaderMap;
    this.outputFile = response.outputFile;
  }

  public void view(){
    if(outputFile != null){
      printRequest();
      printResponseLineAndHeaders();
      saveFile(outputFile);
    }else{
      printRequestAndResponse();
    }
  }

  private void saveFile(String outputFile){
		try(InputStream in = connection.getInputStream();
        FileOutputStream out = new FileOutputStream(outputFile);
        BufferedOutputStream bufferedOut = new BufferedOutputStream(out);){
			int c;
			while((c = in.read()) != -1){
				bufferedOut.write(c);
			}
		}catch(Exception e){
      System.out.println("Error saving file.\n" + e);
    }
  }

  private void printRequestAndResponse(){
    printRequest();
    printResponse();
  }

  // todo : view restricted headers
  private void printRequest(){
    String path = connection.getURL().getFile();
    if(path.equals("")) path = "/";
    System.out.println("> " + connection.getRequestMethod() + " " + path + " HTTP/1.1");
    for (Map.Entry<String, List<String>> entry : requestHeaderMap.entrySet()) {
      for(String value : entry.getValue()){
        System.out.println("> " + entry.getKey() + ": " + value);
      }
    }
    System.out.println(">");
  }

  private void printResponse(){
    printResponseLineAndHeaders();
    printResponseBody();
  }

  private void printResponseLineAndHeaders(){
    System.out.println("< " + connection.getHeaderField(0));
    for (int i = 1;; i++) {
        String header = connection.getHeaderField(i);
        if (header == null) break;
        System.out.println("< " + connection.getHeaderFieldKey(i) + ": " + header);
    }
    System.out.println("<");
  }

  private void printResponseBody(){
    try(InputStream raw = connection.getInputStream()){
  		printFromStream(raw);
    }catch(Exception e){
      printFromStream(connection.getErrorStream());
    }
  }

  private void printFromStream(InputStream raw){
    try(BufferedInputStream buffer = new BufferedInputStream(raw)){
      InputStreamReader reader = new InputStreamReader(buffer);
      int c;
      while((c = reader.read()) != -1){ // note that this assumes the local encoding, in this case UTF-8
        System.out.print((char) c);
      }
    }catch(Exception e){
      System.out.println(e);
    }
  }
}












// end
