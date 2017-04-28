1. create Mysql DB: test
2. run query: grant all privileges on test.* to 'root'@'localhost' identified by '12345678';
3. mvn clean package
4. run com.test.login.LoginApplication
5. run com.test.resource.ResourceApplication
6. run com.test.web1.Web1Application
7. run com.test.web2.Web2Application
8. http://localhost
9. login username and passwork: user


这里的所有变动参考note.txt文件，并且以其为准，下面也列出note.txt文件的内容，但不保证及时同步。




本git库是根据《深入实践Spring Boot》(201611)的第六章spring boot sso的例子代码(https://github.com/chenfromsz/spring-boot-sso.git)修改而成。
主要改动是支持其在https下正常运行，并且这些https是在不同的hostname下运行（但是是在这些不同的hostname都指向同一个本地ip 127.0.0.1），在win7下调试通过。

相关改动

对 /etc/hosts 加了几行
127.0.0.1 oauthlogin oauthres oauthweb1 oauthweb2 
127.0.0.1 mysqlt1

关于生成https所用的证书，见后面详述。

java代码的改动很少很少。待提出相关参数到属性文件中，就可以不改java代码了。
另外注意web模块里面的网页相关文件有些以前写死了url的(指host name以及端口号被写死的)，需要相应改动。
对于属性文件的改动是重点，参考不同模块下的 applicationVarhostHttps.yml 文件。(另外有个applicationLocalHttps.yml是试验过https在localhost下运行正常的情况。)

mysql配置未做实质性改动。



有关使用到的证书，参考如下命令
生成并都存到serverKeystore.jks中。文件的命名意味着其在server端使用。注意其中的CN=someName，这someName需要能够与 /etc/hosts 里面配置的名字对得上，这是由于遇到相关bug而解决的。
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -genkeypair -alias alias1 -keyalg RSA -dname "CN=localhost,OU=zly,O=zly,L=City,S=State,C=US" -keypass 123456 -keystore serverKeystore.jks -storepass 123456 -validity 36500 -storetype JKS
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -genkeypair -alias aliasoauthlogin -keyalg RSA -dname "CN=oauthlogin,OU=zly,O=zly,L=City,S=State,C=US" -keypass 123456 -keystore serverKeystore.jks -storepass 123456 -validity 36500  -storetype JKS
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -genkeypair -alias aliasoauthres -keyalg RSA -dname "CN=oauthres,OU=zly,O=zly,L=City,S=State,C=US" -keypass 123456 -keystore serverKeystore.jks -storepass 123456 -validity 36500  -storetype JKS
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -genkeypair -alias aliasoauthweb1 -keyalg RSA -dname "CN=oauthweb1,OU=zly,O=zly,L=City,S=State,C=US" -keypass 123456 -keystore serverKeystore.jks -storepass 123456 -validity 36500  -storetype JKS
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -genkeypair -alias aliasoauthweb2 -keyalg RSA -dname "CN=oauthweb2,OU=zly,O=zly,L=City,S=State,C=US" -keypass 123456 -keystore serverKeystore.jks -storepass 123456 -validity 36500  -storetype JKS

将各个证书导出
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -export -keystore serverKeystore.jks -alias alias1 -file alias1_localhost1.cer  -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -export -keystore serverKeystore.jks -alias aliasoauthlogin -file aliasoauthlogin.cer -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -export -keystore serverKeystore.jks -alias aliasoauthres -file aliasoauthres.cer  -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -export -keystore serverKeystore.jks -alias aliasoauthweb1 -file aliasoauthweb1.cer  -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -export -keystore serverKeystore.jks -alias aliasoauthweb2 -file aliasoauthweb2.cer -storepass 123456

再导入到clientside_cacerts中。文件的命名意味着其在client端使用。
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -import -noprompt -v -trustcacerts -alias alias1 -file alias1_localhost1.cer -keypass 123456 -keystore clientside_cacerts -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -import -noprompt -v -trustcacerts -alias aliasoauthlogin -file aliasoauthlogin.cer -keypass 123456 -keystore clientside_cacerts -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -import -noprompt -v -trustcacerts -alias aliasoauthres -file aliasoauthres.cer -keypass 123456 -keystore clientside_cacerts -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -import -noprompt -v -trustcacerts -alias aliasoauthweb1 -file aliasoauthweb1.cer -keypass 123456 -keystore clientside_cacerts -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -import -noprompt -v -trustcacerts -alias aliasoauthweb2 -file aliasoauthweb2.cer -keypass 123456 -keystore clientside_cacerts -storepass 123456

另外为jwt的认证准备一点东西。这里简化使用对应服务所使用的server证书。
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -export -keystore serverKeystore.jks -alias alias1 -file alias1_localhost1_rfc.cer  -storepass 123456 -rfc
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -export -keystore serverKeystore.jks -alias aliasoauthlogin -file aliasoauthlogin_rfc.cer -storepass 123456 -rfc
然后，在cygwin中执行
openssl x509 -inform pem -pubkey  < alias1_localhost1_rfc.cer > alias1_localhost1_rfc_cer_x509_pub
openssl x509 -inform pem -pubkey  < aliasoauthlogin_rfc.cer > aliasoauthlogin_rfc_cer_x509_pub
其中xxx_pub中的部分内容在配置resource模块的 security.oauth.resource.jwt.keyValue 时用得着。目前发现，jwt目前没有要求hostname匹配的问题。




最后是运行，参考如下命令，起4个cmd窗口运行。注意这里用到了 -Djavax.net.ssl.trustStore 参数，解决自行生成的证书被信任的问题，并且注意在tomcat中设置trust-store: classpath:clientside_cacerts 是不行的，这里试过了。
d:
cd D:\zly2\try\trySprBoot\trySso\trySsoInDockerVarhostHttps\login
java -jar login-1.0-SNAPSHOT.jar --spring.config.name=applicationVarhostHttps

d:
cd D:\zly2\try\trySprBoot\trySso\trySsoInDockerVarhostHttps\resource
java -Djavax.net.ssl.trustStore=D:\zly2\try\trySprBoot\trySso\trySsoInDockerLocalHttps\t\clientside_cacerts -jar resource-1.0-SNAPSHOT.jar --spring.config.name=applicationVarhostHttps

d:
cd D:\zly2\try\trySprBoot\trySso\trySsoInDockerVarhostHttps\web1
java -Djavax.net.ssl.trustStore=D:\zly2\try\trySprBoot\trySso\trySsoInDockerLocalHttps\t\clientside_cacerts -jar web1-1.0-SNAPSHOT.jar --spring.config.name=applicationVarhostHttps

d:
cd D:\zly2\try\trySprBoot\trySso\trySsoInDockerVarhostHttps\web2
java -Djavax.net.ssl.trustStore=D:\zly2\try\trySprBoot\trySso\trySsoInDockerLocalHttps\t\clientside_cacerts -jar web2-1.0-SNAPSHOT.jar --spring.config.name=applicationVarhostHttps

然后可以在浏览器中访问网页
https://oauthlogin:8443
// https://oauthres:8083
https://oauthweb1:8081
https://oauthweb2:8082


