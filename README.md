sudo dnf update -y
sudo dnf install -y java-21-amazon-corretto-devel
java -version   # Should show openjdk version 21


sudo dnf install -y git maven
mvn -version    # Should show Maven 3.8+

sudo wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.55/bin/apache-tomcat-10.1.55.tar.gz

# Install Java 21
sudo yum install -y java-21-amazon-corretto-devel

# Verify the installation
java -version


cd /opt

# Download Tomcat 10.1.55
sudo wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.55/bin/apache-tomcat-10.1.55.tar.gz

# Extract the archive
sudo tar -xzf apache-tomcat-10.1.55.tar.gz

# Rename the directory for easier access
sudo mv apache-tomcat-10.1.55 tomcat10

# Clean up the downloaded archive
sudo rm -f apache-tomcat-10.1.55.tar.gz

# Set the correct ownership
sudo chown -R ec2-user:ec2-user /opt/tomcat10


mvn clean package

sudo yum install -y java-21-amazon-corretto-devel

ls /usr/lib/jvm/ | grep java-21

sudo alternatives --config java

echo "export JAVA_HOME=/usr/lib/jvm/java-21-amazon-corretto.x86_64" >> ~/.bashrc
echo "export PATH=\$JAVA_HOME/bin:\$PATH" >> ~/.bashrc
source ~/.bashrc

sudo chown -R ec2-user:ec2-user /opt/tomcat10

mvn clean package

cp target/men-women-grocery.war /opt/tomcat10/webapps/

/opt/tomcat10/bin/startup.sh













cat /root/maven-tomcat-app/src/main/webapp/WEB-INF/web.xml


cat > /root/maven-tomcat-app/src/main/webapp/WEB-INF/web.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee 
         https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
         version="6.0">
    <display-name>Men & Women Grocery</display-name>
    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
</web-app>
EOF


cd /root/maven-tomcat-app
mvn clean package

/opt/tomcat10/bin/shutdown.sh
sleep 3
rm -rf /opt/tomcat10/webapps/men-women-grocery*
cp target/men-women-grocery.war /opt/tomcat10/webapps/
/opt/tomcat10/bin/startup.sh

tail -f /opt/tomcat10/logs/catalina.out























cd /root/maven-tomcat-app

# 1. Clean everything
mvn clean

# 2. Delete web.xml from source (already done, but verify)
rm -f src/main/webapp/WEB-INF/web.xml

# 3. Rebuild the WAR (no web.xml will be included)
mvn package

# 4. Verify the new WAR has NO web.xml
jar tf target/men-women-grocery.war | grep WEB-INF/web.xml
# This should return NOTHING (empty)

# 5. Stop Tomcat
/opt/tomcat10/bin/shutdown.sh
sleep 3

# 6. Remove old deployment
rm -rf /opt/tomcat10/webapps/men-women-grocery*

# 7. Copy the new WAR
cp target/men-women-grocery.war /opt/tomcat10/webapps/

# 8. Start Tomcat
/opt/tomcat10/bin/startup.sh

# 9. Watch logs (wait 10 seconds, then check)
tail -50 /opt/tomcat10/logs/catalina.out | grep -i "men-women"
