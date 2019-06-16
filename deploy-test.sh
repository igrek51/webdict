#/bin/bash
set -ex

USER=root
HOST=
SSH_PORT=2222

# Test
echo "Building WAR..."
mvn package -P test
echo "Copying WAR..."
scp -P $SSH_PORT target/webdict-test.war $USER@$HOST:/opt/tomcat8/webapps/webdict-test.war

echo "done"
