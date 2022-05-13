#!/bin/bash
echo "publish------"

process_id=`ps -ef | grep modbusTcp.jar  | grep -v gred |awk '{print $2}'`
if [ $process_id ] ; then
sudo kill -9 $ process_id
fi

source /etc/profile
nohup java -jar -Dspring.profiles.active=prod ~/fbg/modbusTcp.jar > /dev/null 2>&1 &
nohup java -jar -Dspring.profiles.active=prod ~/pipeline/pipelineMonitor.jar > /dev/null 2>&1 &
echo "end publish"


#登录阿里云镜像仓
docker login --username=晓丹1866 registry.cn-shanghai.aliyuncs.com --password=2012T20Tyear
#构建整个项目，或者单独构建common项目,避免依赖未被构建上去
mvn install -Dmaven.test.skip=true dockerfile:build
docker tag pipeline-monitor/pipeline-monitor:latest registry.cn-shanghai.aliyuncs.com/xucunyin-springboot-kaluna/pipeline-danly-top:v1.1
docker push registry.cn-shanghai.aliyuncs.com/xucunyin-springboot-kaluna/pipeline-danly-top:v1.1
echo "pipeline构建推送成功"