/*
* information needed to conduct http request
*/

package jhttp;

import java.util.HashMap;

public class RequestOptions {

  String url;
  String httpMethod;
  String data;
  String fileName;
  String outputFile;
  HashMap<String, String> headers;

  // builder
  public RequestOptions() {
  }

  public RequestOptions setUrl(String url) {
    this.url=url;
    return this;
  }

  public RequestOptions setHttpMethod(String httpMethod) {
    this.httpMethod=httpMethod;
    return this;
  }

  public RequestOptions setHeaders(HashMap<String, String> headers) {
    this.headers=headers;
    return this;
  }

  public RequestOptions setData(String data) {
    this.data=data;
    return this;
  }

  public RequestOptions setFileName(String fileName) {
    this.fileName=fileName;
    return this;
  }

  public RequestOptions setOutputFile(String outputFile) {
    this.outputFile = outputFile;
    return this;
  }

  public String toString(){
    return url + "\n" + httpMethod + "\n" + headers + "\n" + data + "\n" + fileName;
  }

}
