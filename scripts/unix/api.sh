cd ../../api || exit 1

kill $(lsof -t -i:8080) &>/dev/null

mvn clean install
java -jar target/api-0.0.1-SNAPSHOT.jar