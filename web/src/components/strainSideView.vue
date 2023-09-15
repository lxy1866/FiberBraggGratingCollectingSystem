<template>
  <div id="strainSideView" class="strainSideView"></div>
</template>

<script lang="js">
import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";
import {Tool} from "@/util/tool";

export default defineComponent({
  name: 'strainSideView',
  setup: function () {
    onMounted(async () => {
      let websocket;
      let token;
      let strain = [];
      var chartDom = document.getElementById('strainSideView');
      var myChart = echarts.init(chartDom);

      let data = {
        categoryData:[],
        valueData:[]
      } ;
      let option = {
        title: {
          text: '侧视图',
          textStyle: {
            fontSize: 30,
            color: '#ffffff',
            fontFamily: '宋体',
          },
        },
        visualMap:{
          textStyle: {
            color:'#ffffff'
          },
          top: 50,
          right: 10,
          pieces: [
            {
              gt: -0.002,
              lte: -0.0015,
              color: '#0000FF'
            },
            {
              gt: -0.0015,
              lte: -0.001,
              color: '#1E90FF'
            },
            {
              gt: -0.001,
              lte: -0.0005,
              color: '#00FFFF'
            },
            {
              gt: -0.0005,
              lte: 0,
              color: '#7FFFD4'
            },
            {
              gt: 0,
              lte: 0.0005,
              color: '#FFC0CB'
            },
            {
              gt: 0.0005,
              lte: 0.001,
              color: '#FA8072'
            },
            {
              gt: 0.001,
              lte: 0.0015,
              color: '#FF7F50'
            },
            {
              gt: 0.0015,
              lte: 0.002,
              color: '#FF4500'
            },
          ],
          outOfRange: {
            color: '#FF0000'
          }
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
        // dataZoom: [
        //   {
        //     type: 'inside'
        //   },
        //   {
        //     type: 'slider'
        //   }
        // ],
        xAxis: {
          show: false,
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
          min: function (value){
            return value.min-0.004
          },
          max: function (value){
            return value.max+0.004
          },
          show: false,
          splitArea: {
            show: false
          },
          scale:true,
        },
        series: [
          {
            type: 'line',
            itemStyle:{
              normal:{
                lineStyle:{
                  width:10
                }
              }
            },
            data: data.valueData,
            large: true
          }
        ]
      };

      function generateData(list) {
        const categoryData = [];
        const valueData = [];
        for (let i = 0; i < 20; i++) {
          categoryData.push(i)
        }
        for (let i = 0; i < list.length-1; i++) {
          const startValue=list[i];
          const endValue=list[i+1];
          const stepSize=(endValue-startValue)/9;
          for (let j = 0; j < 9; j++) {
            const interpolatedValue=startValue+(j*stepSize);
            valueData.push(interpolatedValue);
          }
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
        strain = JSON.parse(msg.data)
        // console.log(strain)
        data = generateData(strain);
        option.xAxis.data = data.categoryData;
        option.series[0].data = data.valueData;
        myChart.setOption(option)

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
        websocket = new WebSocket(process.env.VUE_APP_WS_SERVER + '/ws/' + token);
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