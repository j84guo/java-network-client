/******
Jhttp :
******/

Java implementation of a cURL-like (client url request library) network client.

Usage Examples :
./jhttpw http://www.google.ca
./jhttpw -o spark.jar http://central.maven.org/maven2/org/apache/spark/spark-core_2.11/2.2.1/spark-core_2.11-2.2.1.jar

Options :  
-m <method> specifies the HTTP verb (default is GET), e.g. -m POST
-o <filename> specifies the name of a to which the entity body will be saved, e.g. -o spark.jar
-d <urlencoded string> specifes url encoded data to add to the request body (defaults content type to application/x-www-form-urlencoded), e.g. -d name=value
-f <filename> specifies an existing file which will be transferred via multipart/form-data 
-h name=value specifies custom HTTP headers to add

Todo :

-java's URL class has issues with query string
-java's URL connection prevents some request headers from being printed
-format options


/*****************
Design Notes :
******************

1. General :
-compiled as an executable fat jar
-shell script to run application

2. Main Components :
-CliController.java (parse flags)
-HttpService.java (execute request)
-ResponseViewer.java (print output based on flags)

3. Helper Components :
-HttpVerb.java // enum containing http verbs
-QueryString.java // url encodes query string or post body

4. Flags :
-http verbs
  -> -m --method can be GET, POST, HEAD, OPTIONS
-verbose (host ip and headers)
  -> -v --verbose has no value

-request headers
  -> -h headerName=someValue
  -> (--accept) Accept: acceptable content MIME types, e.g. Accept: text/plain
  -> (--accept-charset) Accept-Charset: acceptable character set, e.g. Accept-Charset: utf-8
  -> (--accept-encoding) Accept-Encoding: acceptable encodings, e.g. Accept-Encoding: gzip, deflate
  -> (--accept-language) Accept-Language: acceptable languages, e.g. Accept-Language: en-US
  -> (--authorization) Authorization: credentials for HTTP authentication, e.g. authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==
  -> (--connection) Connection: control options for the current connection, e.g. Connection: keep-alive
  -> (--content-length) Content-Length: length of the request body in bytes
  -> (--content-type) Content-Type: MIME type of the body of the request (post)
  -> (--cookie) Cookie: HTTP cookie previously sent by the server with Set-Cookie, e.g. Cookie: $Version=1; Skin=new;
  -> (--date) Date: date of request, e.g. Date: Tue, 15 Nov 1994 08:12:31 GMT
  -> (--host) Host: domain name of the server and TCP port number on which the server is listening, e.g. Host: en.wikipedia.org:8080
  -> (--if-modified-since) If-Modified-Since: allows a 304 Not Modified to be returned if content is unchanged, e.g. If-Modified-Since: Sat, 29 Oct 1994 19:43:31 GMT
  -> (--user-agent) User-Agent: user agent string, e.g. User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:12.0) Gecko/20100101 Firefox/12.0

-request body
  -> -d --data stringToBeUrlEncoded
  -> -f --form sourceOfBinaryData
