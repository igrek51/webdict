#/bin/bash

# Prod
echo "Building prod WAR..."
mvn package -P prod
echo "Copying prod WAR..."
scp target/webdict.war igrek@35.187.18.31:/home/igrek/webdict.war
echo "Deploying to Tomcat..."
ssh -t igrek@35.187.18.31 "sudo cp /home/igrek/webdict.war /opt/tomcat8/webapps/webdict.war"

echo "done"
