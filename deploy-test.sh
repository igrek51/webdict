#/bin/bash

# Test
echo "Building test WAR..."
mvn package -P test
echo "Copying test WAR..."
scp target/webdict-test.war igrek@35.187.18.31:/home/igrek/webdict-test.war
echo "Deploying to Tomcat..."
ssh -t igrek@35.187.18.31 "sudo cp /home/igrek/webdict-test.war /opt/tomcat8/webapps/webdict-test.war"

echo "done"
