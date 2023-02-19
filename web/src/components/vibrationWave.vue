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
      let time = new Date();
      let data = {
        categoryData:[],
        valueData:[]
      } ;
      let option = {
        title: {
          text: '海底电缆振动波长值',
          textStyle: {
            color: '#ffffff',
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
          min:1540,
          max:1560
        },
        series: [
          {
            type: 'bar',
            data: data.valueData,
            large: true
          }
        ]
      };
      time -= 5000
      function generateData(list) {
        const categoryData = [];
        const valueData = [];
        for (let i = 0; i < 500; i++) {
          categoryData.push(
              echarts.format.formatTime('yyyy-MM-dd\nhh:mm:ss', time, false)
          );
          valueData.push(list[i]);
          time += 10
        }
        return {
          categoryData: categoryData,
          valueData: valueData
        };
      }
      const onOpen = () =>{
        console.log('WebSocket连接成功，状态码：',websocket.readyState)
      };
      const onMessage = function (msg){
        vibration = JSON.parse(msg.data)
        data = generateData(vibration);
        option.xAxis.data = data.categoryData;
        option.series[0].data = data.valueData;

        myChart.setOption(option)
        console.log(vibration)
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