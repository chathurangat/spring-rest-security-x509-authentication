# spring-rest-security-x509-authentication

using X.509 security mechanism for Securing Spring REST api with Spring Boot and Spring Security


### How to Run
 You can find these three directories.
#### 1. keystores   
Contains the already generated keyStores, trustStores and certificates for both client and server.

#### 2. spring-boot-security-x509-server-app 
Server application that contains the REST api. this application will support for the X.509 Authentication. This application will run in HTTPS. 
#### 3. spring-boot-security-x509-client-app
   Client application that calls the REST api offered by the server app by proving a valid certificate.
   
### Projects modules/directories  in detailed ....

####  1. keystores
This directory contains the generated keystores, truststores and certificates for both server application and client application. 
if you need you can use these generated artifacts for running this app. otherwise you can create your own set using following commands.

Go to any directory where you need to create these files and execute following commands. 
(alternatively you can change the path name of the files without going to any directory)
###### e.g:-   keytool -genkey -alias serverkey -keystore   <**pathToServerKeyStore**>   -keyalg RSA -storetype PKCS12  
 
##### 1) Generate server key and self signed server certificate
     keytool -genkey -alias serverkey -keystore serverkeystore.p12  -keyalg RSA -storetype PKCS12

##### 2) Generate client key and self signed client certificate 
     keytool -genkey -alias clientkey -keystore clientkeystore.p12 -keyalg RSA -storetype PKCS12

##### 3) Export the server certificate

    keytool -export -alias serverkey -file servercert.cer -keystore serverkeystore.p12

##### 4) Export the client certificate

    keytool -export -alias clientkey -file clientcert.cer -keystore clientkeystore.p12

##### 5) Import the server certificate into client truststore

    keytool -importcert -file servercert.cer -keystore clienttruststore.p12 -alias servercert

##### 6) Import the client certificate into server truststore

    keytool -importcert -file clientcert.cer -keystore servertruststore.p12 -alias clientcert


**Now you have created the client certificate, server certifcate and associate them with the relevant truststores and keystores. it is time to run client and server apps and see the output.**


#### 2. spring-boot-security-x509-server-app 

This is the server application which offer the REST service. Only the trusted client with the valid certificate can only acccess the server application.
check the server keystore and truststore paths and passwords are properly defined. Server app should be run on HTTPS protocol.

###### spring-boot-security-x509-server-app/src/main/resources/application.properties

```java
server.port = 8443
#keystore details
server.ssl.key-store=/Users/chathuranga/Projects/spring-boot-ssl-x509/keystores/serverkeystore.p12
server.ssl.key-store-password=1qaz2wsx

#truststore details - verifying client certificates
server.ssl.trust-store=/Users/chathuranga/Projects/spring-boot-ssl-x509/keystores/servertruststore.p12
server.ssl.trust-store-password=1qaz2wsx
server.ssl.client-auth=need
```

**build and run server app in the spring-boot embedded tomcat server**
Build App :- **mvn clean install**  
Run App:- **java -jar target/spring-boot-security-x509-server-app-0.0.1-SNAPSHOT.jar** 

You can be access through the following URL. but you cannot access it with your web browser as your web browser does not have the client certificate we have created for accessing the server app.
[http://localhost:8445/](http://localhost:8443/)


#### 3. spring-boot-security-x509-client-app 

This is the client application that we are accessing through the browser. Client application will invoke th REST api service offered by the server application and display the retrieved output. Since the server application requires X.509 authentication, client needs to verify the server certificate and needs to send its ceritifate for the server to authenticate client's identity. for these reasons client should have the access to the clientKeystore and clientTrustStore. So check whther both of those are properly defined in the application.

**RestClient.java**
```java
        System.setProperty("javax.net.ssl.keyStore", "/Users/chathuranga/Projects/spring-boot-ssl-x509/keystores/clientkeystore.p12");
        System.setProperty("javax.net.ssl.keyStorePassword", "3edc4rfv");
        System.setProperty("javax.net.ssl.trustStore", "/Users/chathuranga/Projects/spring-boot-ssl-x509/keystores/clienttruststore.p12");
        System.setProperty("javax.net.ssl.trustStorePassword", "3edc4rfv");
```


**Then build and run the application as follows.  if you need you can change the server port in application.properties**

Build App :- **mvn clean install**  
Run App:- **java -jar target/spring-boot-security-x509-server-app-0.0.1-SNAPSHOT.jar** 

[http://localhost:8445/](http://localhost:8445/)

Now You can load the above URL in your browser and see the output.
We have done with x.509 Authentication with Spring Boot and Spring Security.









