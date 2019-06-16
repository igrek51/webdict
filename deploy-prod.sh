#/bin/bash
set -ex

USER=root
HOST=
SSH_PORT=2222

# Prod
echo "Building WAR..."
mvn package -P prod
echo "Copying WAR..."
scp -P $SSH_PORT target/webdict.war $USER@$HOST:/opt/tomcat8/webapps/webdict.war

echo "done"
