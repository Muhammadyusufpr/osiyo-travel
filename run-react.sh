echo "remove old project"
pkill -f osiyo.jar
echo "success"
nohup java -jar server/osiyo.jar > server/osiyo.out &
echo "success"
exit


