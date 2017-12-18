Jhttp :
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
