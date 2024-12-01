#!/bin/bash
echo "publish------"

process_id=`ps -ef | grep modbusTcp.jar  | grep -v gred |awk '{print $2}'`
## ps -ef | grep modbusTcp.jar  | grep -v gred |awk '{print $2}'  用于获取进程号
## grep -v gred 用于过滤掉grep modbusTcp.jar的进程号，因为grep modbusTcp.jar也会被grep出来，所以需要过滤掉
if [ $process_id ] ; then
sudo kill -9 $ process_id
fi

source /etc/profile


nohup java -jar -Dspring.profiles.active=prod ~/fbg/modbusTcp.jar 2>&1 &
## > /dev/null  2>&1 & 是将标准错误输出重定向到标准输出，然后将标准输出重定向到/dev/null，这样就不会在终端输出信息了。
nohup java -jar -Dspring.prof> /dev/null iles.active=prod ~/memsFbg/modbusTcp.jar > /dev/null 2>&1 &
echo "end publish"

