echo "Start.."
mvn clean package
echo "Upload to serve run.sh"
scp -r run-react.sh root@5.180.182.235:/root/run/


echo "Upload to server"
scp -r target/osiyo.jar root@5.180.182.235:/root/server
echo "Start old project"
ssh root@5.180.182.235 'bash run/run-react.sh'
