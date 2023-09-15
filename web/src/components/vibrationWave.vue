<template>
  <div id="vibrationWave" class="vibrationWave"></div>
</template>
<script lang="js">
import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";
import {Tool} from "@/util/tool";

export default defineComponent({
  name: 'vibrationWave',
  setup: function () {
    onMounted(async () => {
      let websocket;
      let token;
      let vibration = [];
      var chartDom = document.getElementById('vibrationWave');
      var myChart = echarts.init(chartDom);

      let data = {
        categoryData:[],
        valueData:[]
      } ;
      let option = {
        title: {
          text: '振动信号（波长值）原始曲线图',
          textStyle: {
            color: '#ffffff',
            fontSize:20,
            fontFamily: '宋体',
          },
        },
        toolbox: {
          feature: {
            dataZoom: {
              yAxisIndex: false
            },
            saveAsImage: {
              pixelRatio: 2
            }
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: 90,
          bottom: 90
        },
        dataZoom: [
          {
            type: 'inside'
          },
          {
            type: 'slider'
          }
        ],
        xAxis: {
          data: data.categoryData,
          silent: false,
          splitLine: {
            show: false
          },
          splitArea: {
            show: false
          }
        },
        yAxis: {
          splitArea: {
            show: false
          },
          scale:true,
        },
        series: [
          {
            type: 'line',
            data: data.valueData,
            large: true
          }
        ]
      };
      function generateData(list) {

        let time = new Date();

        data.categoryData.push(echarts.format.formatTime('yyyy-MM-dd\nhh:mm:ss.SSS', time, false));
        console.log(data.categoryData);
        data.valueData.push(list);
        console.log(data.valueData);


      }
      const onOpen = () =>{
        console.log('WebSocket连接成功，状态码：',websocket.readyState)
      };
      const onMessage = function (msg){
        vibration = JSON.parse(msg.data)
        generateData(vibration);
        option.xAxis.data = data.categoryData;
        option.series[0].data = data.valueData;
        myChart.setOption(option)
        // console.log(vibration)
      };
      const onError = ()=>{
        console.log('WebSocket连接错误，状态码：', websocket.readyState)
      };
      const onClose = ()=>{
        console.log('WebSocket连接关闭，状态码：',websocket.readyState)
      };
      const initWebSocket = () =>{
        //连接成功
        websocket.onOpen = onOpen;
        // 收到消息的回调
        websocket.onmessage = onMessage;
        // 连接错误
        websocket.onerror = onError;
        // 连接关闭的回调
        websocket.onClose = onClose;
      }
      if('WebSocket' in window){

        token = Tool.uuid(10);
        console.log("******",token)
        //连接地址：ws://127.0.0.1:8080/vibrationWaveWs/xxx
        websocket = new WebSocket(process.env.VUE_APP_WS_SERVER + '/vibrationWaveWs/' + token);
        initWebSocket()
      }else{
        alert('当前浏览器 不支持')
      }
    })
  },
})
</script>

<style scoped>
.vibrationWave{
  display: flex;
}
</style>