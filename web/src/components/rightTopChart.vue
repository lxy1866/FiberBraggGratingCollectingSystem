<template>
  <div id="rightTopChart" class="rightTopChart"></div>
</template>
<script lang="js">
import * as echarts from 'echarts';
import {defineComponent, onMounted, ref} from "vue";
import axios from "axios";
function dateToGMT(strDate){
  const dateStr = strDate.split(" ");
  const strGMT = dateStr[0] + " " + dateStr[1] + " " + dateStr[2] + " " + dateStr[5] + " " + dateStr[3] + " GMT+0800";
  const date = new Date(Date.parse(strGMT));
  return date;
}
export default defineComponent({
  name: 'rightTopChart',
  setup: function () {
    function handleQueryList(startTime, endTime) {
      return axios.get("/txt/listLastHourForVibration", {
        params: {
          startTime: startTime,
          endTime: endTime,
          category: 3
        }
      })
    }

    onMounted(async () => {
      var fullYear = new Date(new Date().setFullYear(2022, 8, 2));
      // var frontOneHourTimeStamp = new Date(fullYear.setHours(0, 0, 0, 0)).getTime();
      // var currentHourTimeStamp = new Date(fullYear.setHours(0, 59, 59, 0)).getTime();
      var frontOneHourTimeStamp = new Date(new Date().setHours(new Date().getHours()-1, 0, 0, 0)).getTime();
      var currentHourTimeStamp = new Date(new Date().setHours(new Date().getHours()-1, 59, 59, 0)).getTime();
      let data = []
      data = await handleQueryList(frontOneHourTimeStamp, currentHourTimeStamp);

      data = data.data.content
      for (let i = 0; i < data.length; i++) {
        data[i].name = new Date(data[i].name).toLocaleString('zh')
        //data[i].name = new Date(data[i].name.replace("CST", 'GMT+0800')).toLocaleString()
        data[i][0] = data[i].name
      }

      let option;
      let websocket;
      let token;
      option = {
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true },
          }
        },
        title: {
          text: '海缆上一个小时振动值(Hz)',
          textStyle: {
            color: '#ffffff',
            fontFamily: '宋体',
          },
        },
        grid: {
          top: 40,
          bottom: 28,
          left: 80,
          right: 30
        },
        tooltip: {
          trigger: 'axis',
          formatter: function (params) {
            params = params[0];
            return (
                params.name +
                ' : ' +
                params.value[1]
            );
          },
          axisPointer: {
            animation: false
          }
        },
        xAxis: {
          type: 'time',
          splitLine: {
            show: false
          },
        },
        yAxis: {
          type: 'value',
          boundaryGap: [0, '100%'],
          splitLine: {
            show: false
          }
        },
        series: [
          {
            name: 'Fake Data',
            type: 'line',
            showSymbol: false,
            data: data
          }
        ],
      };
      const chartDom = document.getElementById('rightTopChart');
      let myChart = echarts.init(chartDom);
      // setInterval(function () {
      //   //console.log(data)
      //   for (let i = 0; i < 5; i++) {
      //     let shift = data.value.shift();
      //     data.value.push(shift);
      //   }
      //   option.series[0].data = data
      //   myChart.setOption(option)
      // }, 1000)

      option && myChart.setOption(option)
      // const onOpen = () =>{
      //   //console.log('WebSocket连接成功，状态码：',websocket.readyState)
      // };
      // const onMessage = function (msg:any){
      //   let data = JSON.parse(msg.data);
      //   option.series[0].data = data[0];
      //   myChart.setOption<echarts.EChartsOption>(option);
      // };
      // const onError = ()=>{
      //   //console.log('WebSocket连接错误，状态码：', websocket.readyState)
      // };
      // const onClose = ()=>{
      //   //console.log('WebSocket连接关闭，状态码：',websocket.readyState)
      // };
      // const initWebSocket = () =>{
      //   //连接成功
      //   websocket.onOpen = onOpen;
      //   // 收到消息的回调
      //   websocket.onmessage = onMessage;
      //   // 连接错误
      //   websocket.onerror = onError;
      //   // 连接关闭的回调
      //   websocket.onClose = onClose;
      // }
      // if('WebSocket' in window){
      //   token = Tool.uuid(10);
      //   //连接地址：ws://127.0.0.1:8080/ws/xxx
      //   websocket = new WebSocket(process.env.VUE_APP_WS_SERVER + '/ws/'+token);
      //   initWebSocket()
      // }else{
      //   alert('当前浏览器 不支持')
      // }
    });
    return {}
  },
})
</script>

<style scoped>
.rightTopChart{
  display: flex;
}
</style>