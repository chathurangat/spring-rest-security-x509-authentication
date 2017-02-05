# spring-rest-security-x509-authentication

using X.509 security mechanism for Securing Spring REST api with Spring Boot and Spring Security


### How to Run
 You can find these three directories.
#### 1. keystores   
Contains the already geenrated keyStores, trustStrores and certificates for both client and server.

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
###### e.g:-   keytool -genkey -alias serverkey -keystore  <<pathToServerKeyStore>>  -keyalg RSA -storetype PKCS12  
<br/>
 
##### 1) Generate server key and self signed server certificate
     keytool -genkey -alias serverkey -keystore **serverkeystore.p12**  -keyalg RSA -storetype PKCS12

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


###### Now you have created client certificate, server certifcate and associate them with the relevant truststores and keystores. 
###### Now it is time to run client and server apps and see the output.


