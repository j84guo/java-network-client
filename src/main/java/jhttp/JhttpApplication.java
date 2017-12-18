/*
* main controller
*/

package jhttp;

public class JhttpApplication {

  public static void main(String[] args) {

    RequestOptions options = null;
    try{
      options = CommandLineParser.parse(args);
    }catch(RuntimeException e){
      if(e.getMessage() != null){
        System.out.println(e.getMessage());
      }else{
        System.out.println("Usage: java JhttpApplication [options...] <url>");
      }
      return;
    }

    Response response = null;
    try{
      HttpService service = new HttpService(options);
      response = service.getResponse();
    }catch(Exception e){
      System.out.println("Error connecting to host.\n" + e);
      return;
    }

    ResponseViewer viewer = new ResponseViewer(response);
    viewer.view();

  }
}
