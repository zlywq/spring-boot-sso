1. create Mysql DB: test
2. run query: grant all privileges on test.* to 'root'@'localhost' identified by '12345678';
3. mvn clean package
4. run com.test.login.LoginApplication
5. run com.test.resource.ResourceApplication
6. run com.test.web1.Web1Application
7. run com.test.web2.Web2Application
8. http://localhost
9. login username and passwork: user


��������б䶯�ο�note.txt�ļ�����������Ϊ׼������Ҳ�г�note.txt�ļ������ݣ�������֤��ʱͬ����




��git���Ǹ��ݡ�����ʵ��Spring Boot��(201611)�ĵ�����spring boot sso�����Ӵ���(https://github.com/chenfromsz/spring-boot-sso.git)�޸Ķ��ɡ�
��Ҫ�Ķ���֧������https���������У�������Щhttps���ڲ�ͬ��hostname�����У�����������Щ��ͬ��hostname��ָ��ͬһ������ip 127.0.0.1������win7�µ���ͨ����

��ظĶ�

�� /etc/hosts ���˼���
127.0.0.1 oauthlogin oauthres oauthweb1 oauthweb2 
127.0.0.1 mysqlt1

��������https���õ�֤�飬������������

java����ĸĶ����ٺ��١��������ز����������ļ��У��Ϳ��Բ���java�����ˡ�
����ע��webģ���������ҳ����ļ���Щ��ǰд����url��(ָhost name�Լ��˿ںű�д����)����Ҫ��Ӧ�Ķ���
���������ļ��ĸĶ����ص㣬�ο���ͬģ���µ� applicationVarhostHttps.yml �ļ���(�����и�applicationLocalHttps.yml�������https��localhost�����������������)

mysql����δ��ʵ���ԸĶ���



�й�ʹ�õ���֤�飬�ο���������
���ɲ����浽serverKeystore.jks�С��ļ���������ζ������server��ʹ�á�ע�����е�CN=someName����someName��Ҫ�ܹ��� /etc/hosts �������õ����ֶԵ��ϣ����������������bug������ġ�
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -genkeypair -alias alias1 -keyalg RSA -dname "CN=localhost,OU=zly,O=zly,L=City,S=State,C=US" -keypass 123456 -keystore serverKeystore.jks -storepass 123456 -validity 36500 -storetype JKS
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -genkeypair -alias aliasoauthlogin -keyalg RSA -dname "CN=oauthlogin,OU=zly,O=zly,L=City,S=State,C=US" -keypass 123456 -keystore serverKeystore.jks -storepass 123456 -validity 36500  -storetype JKS
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -genkeypair -alias aliasoauthres -keyalg RSA -dname "CN=oauthres,OU=zly,O=zly,L=City,S=State,C=US" -keypass 123456 -keystore serverKeystore.jks -storepass 123456 -validity 36500  -storetype JKS
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -genkeypair -alias aliasoauthweb1 -keyalg RSA -dname "CN=oauthweb1,OU=zly,O=zly,L=City,S=State,C=US" -keypass 123456 -keystore serverKeystore.jks -storepass 123456 -validity 36500  -storetype JKS
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -genkeypair -alias aliasoauthweb2 -keyalg RSA -dname "CN=oauthweb2,OU=zly,O=zly,L=City,S=State,C=US" -keypass 123456 -keystore serverKeystore.jks -storepass 123456 -validity 36500  -storetype JKS

������֤�鵼��
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -export -keystore serverKeystore.jks -alias alias1 -file alias1_localhost1.cer  -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -export -keystore serverKeystore.jks -alias aliasoauthlogin -file aliasoauthlogin.cer -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -export -keystore serverKeystore.jks -alias aliasoauthres -file aliasoauthres.cer  -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -export -keystore serverKeystore.jks -alias aliasoauthweb1 -file aliasoauthweb1.cer  -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -export -keystore serverKeystore.jks -alias aliasoauthweb2 -file aliasoauthweb2.cer -storepass 123456

�ٵ��뵽clientside_cacerts�С��ļ���������ζ������client��ʹ�á�
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -import -noprompt -v -trustcacerts -alias alias1 -file alias1_localhost1.cer -keypass 123456 -keystore clientside_cacerts -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -import -noprompt -v -trustcacerts -alias aliasoauthlogin -file aliasoauthlogin.cer -keypass 123456 -keystore clientside_cacerts -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -import -noprompt -v -trustcacerts -alias aliasoauthres -file aliasoauthres.cer -keypass 123456 -keystore clientside_cacerts -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -import -noprompt -v -trustcacerts -alias aliasoauthweb1 -file aliasoauthweb1.cer -keypass 123456 -keystore clientside_cacerts -storepass 123456
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -import -noprompt -v -trustcacerts -alias aliasoauthweb2 -file aliasoauthweb2.cer -keypass 123456 -keystore clientside_cacerts -storepass 123456

����Ϊjwt����֤׼��һ�㶫���������ʹ�ö�Ӧ������ʹ�õ�server֤�顣
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -export -keystore serverKeystore.jks -alias alias1 -file alias1_localhost1_rfc.cer  -storepass 123456 -rfc
"C:\Program Files\Java\jdk1.8.0_92\bin\keytool.exe" -export -keystore serverKeystore.jks -alias aliasoauthlogin -file aliasoauthlogin_rfc.cer -storepass 123456 -rfc
Ȼ����cygwin��ִ��
openssl x509 -inform pem -pubkey  < alias1_localhost1_rfc.cer > alias1_localhost1_rfc_cer_x509_pub
openssl x509 -inform pem -pubkey  < aliasoauthlogin_rfc.cer > aliasoauthlogin_rfc_cer_x509_pub
����xxx_pub�еĲ�������������resourceģ��� security.oauth.resource.jwt.keyValue ʱ�õ��š�Ŀǰ���֣�jwtĿǰû��Ҫ��hostnameƥ������⡣




��������У��ο����������4��cmd�������С�ע�������õ��� -Djavax.net.ssl.trustStore ����������������ɵ�֤�鱻���ε����⣬����ע����tomcat������trust-store: classpath:clientside_cacerts �ǲ��еģ������Թ��ˡ�
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

Ȼ�������������з�����ҳ
https://oauthlogin:8443
// https://oauthres:8083
https://oauthweb1:8081
https://oauthweb2:8082


