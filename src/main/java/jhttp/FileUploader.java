package jhttp;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;

import java.net.URLConnection;

public class FileUploader {

  private final String boundary;
  private OutputStreamWriter out;

  public FileUploader(){
      this.boundary = makeBoundary();
  }

  public FileUploader setOut(OutputStreamWriter out){
      this.out = out;
      return this;
  }

  public String getBoundary(){
      return this.boundary;
  }

  public FileUploader(OutputStreamWriter out){
    this.out = out;
    this.boundary = makeBoundary();
  }

  private String makeBoundary(){
      return "===" + System.currentTimeMillis() + "===";
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

  public void endParts() throws Exception{
      out.write("--" + boundary + "--\r\n");
  }
}















// end
