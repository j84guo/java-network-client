package jhttp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;

import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;

public class FileUploader {

  private final String boundary;
  private OutputStreamWriter out;

  public FileUploader(OutputStreamWriter out){
    this.out = out;
    this.boundary = "===" + System.currentTimeMillis() + "===";
  }

  public void addFormField(String name, String value) throws Exception {
      out.write("--" + boundary + "\r\n");
      out.write("Content-Disposition: form-data; name=\"" + name + "\"" + "\r\n");
      out.write("Content-Type: text/plain; charset=" + "UTF-8" + "\r\n");
      out.write("\r\n");
      out.write(value + "\r\n");
      out.flush();
  }

  public void addFilePart(String fieldName, String fileName) throws Exception {
      File uploadFile = new File(fileName);
      out.write("--" + boundary + "\r\n");
      out.write("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"" + "\r\n");
      out.write("Content-Type: " + URLConnection.guessContentTypeFromName(fileName) + "\r\n");
      out.write("\r\n");
      out.flush();

      FileInputStream in = new FileInputStream(uploadFile);
      int c;
      while ((c = in.read()) != -1) {
          out.write(c);
      }
      out.write("\r\n");
      out.flush();
      in.close();
  }

}















// end
