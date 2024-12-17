#!/bin/bash
echo "publish------"

process_id=`ps -ef | grep modbusTcp.jar  | grep -v gred |awk '{print $2}'`
if [ $process_id ] ; then
sudo kill -9 $ process_id
fi

source /etc/profile
nohup java -jar -Dspring.profiles.active=prod ~/fbg/modbusTcp.jar > /dev/null 2>&1 &
nohup java -jar -Dspring.profiles.active=prod ~/pipeline/pipeline-monitor.jar > /dev/null 2>&1 &

echo "end publish"