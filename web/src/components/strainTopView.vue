<template>
  <div id="strainTopView" class="strainTopView"></div>
</template>

<script lang="js">
import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";
import {Tool} from "@/util/tool";
import "echarts-gl";

export default defineComponent({
  name: 'strainTopView',
  setup: function () {
    onMounted(async () => {
      let websocket;
      let token;
      let vibration = [];
      var chartDom = document.getElementById('strainTopView');
      var myChart = echarts.init(chartDom);
      let time = new Date();
      let data = {
        categoryData: [],
        valueData: []
      };
      let option = {
        title: {
          text: '应变分布俯视图',
          textStyle: {
            color: '#ffffff',
          },
        },

        tooltip: {},
        backgroundColor: "transparent",
        visualMap: {
          show: false,
          dimension: 2,
          min: -1,
          max: 1,
          inRange: {
            color: [
              '#313695',
              '#4575b4',
              '#74add1',
              '#abd9e9',
              '#e0f3f8',
              '#ffffbf',
              '#fee090',
              '#fdae61',
              '#f46d43',
              '#d73027',
              '#a50026'
            ]
          }
        },
        xAxis3D: {
          type: 'value'
        },
        yAxis3D: {
          type: 'value'
        },
        zAxis3D: {
          type: 'value',
        },
        grid3D: {
          viewControl: {
            rotateSensitivity: [0, 0],
            alpha:90,
            beta:180
          },
        },
        series: [
          {
            type: 'surface',
            wireframe: {
              // show: false
            },
            equation: {
              x: {
                step: 0.05
              },
              y: {
                step: 0.05
              },
              z: function (x, y) {
                if (Math.abs(x) < 0.1 && Math.abs(y) < 0.1) {
                  return '-';
                }
                return Math.sin(x * Math.PI) * Math.sin(y * Math.PI);
              }
            }
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

      const onOpen = () => {
        console.log('WebSocket连接成功，状态码：', websocket.readyState)
      };
      option && myChart.setOption(option)
      const onMessage = function (msg) {
        // vibration = JSON.parse(msg.data)
        // data = generateData(vibration);
        // option.xAxis.data = data.categoryData;
        // option.series[0].data = data.valueData;
        // myChart.setOption(option)
        // console.log(vibration)
      };
      const onError = () => {
        console.log('WebSocket连接错误，状态码：', websocket.readyState)
      };
      const onClose = () => {
        console.log('WebSocket连接关闭，状态码：', websocket.readyState)
      };
      const initWebSocket = () => {
        //连接成功
        websocket.onOpen = onOpen;
        // 收到消息的回调
        websocket.onmessage = onMessage;
        // 连接错误
        websocket.onerror = onError;
        // 连接关闭的回调
        websocket.onClose = onClose;
      }
      // if('WebSocket' in window){
      //
      //   token = Tool.uuid(10);
      //   console.log("******",token)
      //   //连接地址：ws://127.0.0.1:8080/vibrationWaveWs/xxx
      //   websocket = new WebSocket(process.env.VUE_APP_WS_SERVER + '/vibrationWaveWs/' + token);
      //   initWebSocket()
      // }else{
      //   alert('当前浏览器 不支持')
      // }
    })
  },
})
</script>

<style scoped>
.memsReshape {
//height: 50vh; //width: auto;
}
</style>