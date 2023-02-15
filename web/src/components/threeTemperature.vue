<template>
  <div id="threeTemperature" class="threeTemperature"></div>
</template>
<script lang="js">
import * as echarts from 'echarts';
import {defineComponent, onMounted, ref} from "vue";
import axios from "axios";
import {Tool} from "@/util/tool";
function dateToGMT(strDate){
  const dateStr = strDate.split(" ");
  const strGMT = dateStr[0] + " " + dateStr[1] + " " + dateStr[2] + " " + dateStr[5] + " " + dateStr[3] + " GMT+0800";
  const date = new Date(Date.parse(strGMT));
  return date;
}
export default defineComponent({
  name: 'threeTemperature',
  setup: function () {
    function handleQueryList(startTime, endTime) {
      return axios.get("/txt/listLastHourFortemperature", {
        params: {
          startTime: startTime,
          endTime: endTime,
          category: 3
        }
      })
    }
    function getData (value){
      let now = +new Date();
      const oneDay = 100;
      now = new Date(+now + oneDay);
      const valueName = now.getFullYear() + '/' + (now.getMonth() + 1) + '/' + now.getDate() +
          ' ' + (now.getHours() >= 10 ? now.getHours() : '0' + now.getHours()) + ':' +
          (now.getMinutes() >= 10 ? now.getMinutes() : '0' + now.getMinutes()) +
          ':' + (now.getSeconds() >= 10 ? now.getSeconds() : '0' + now.getSeconds());
      return {
        name: valueName,
        value: [
          valueName,
          Math.round(value * 100) / 100
        ]
      }
    }
    onMounted(async () => {
      let option;
      let websocket;
      let token;
      let temperature1 = [];
      let temperature2 = [];
      let temperature3 = [];
      option = {
        title: {
          text: '海底电缆实时温度值',
          textStyle: {
            color: '#ffffff',
            fontFamily: '宋体',
          },
          name: '海缆实时温度值',
          left: 'left',
        },
        toolbox: {
          show: true,
          feature: {
            dataView: {readOnly: false},
            magicType: {type: ['line', 'bar']},
            restore: {},
            saveAsImage: {}
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'line',
            animation: true,
            label: {
              backgroundColor: '#6a7985'
            },
            lineStyle: {
              color: '#00FF34'
            }
          },
          formatter: function (params) {

            return '时间：'+ params[0].name + '<br/>温度曲线1温度值 : ' + params[0].value[1]
                + '<br/>温度曲线2温度值 : ' + params[1].value[1]
                + '<br/>温度曲线3温度值 : ' + params[2].value[1];
          },
        },
        grid: {
          left: '50',
          right: '10',
          top: '50',
          bottom:'30'
        },
        xAxis: {
          type: 'category',
          splitLine: {
            show: false
          },
          axisLabel: {
          },
          triggerEvent: true
        },
        yAxis: {
          name: '温度值(℃)',
          nameLocation: 'center',
          nameGap: 30,
          type: 'value',
          boundaryGap: [0, '100%'],
          splitLine: {
            show: true,
            lineStyle: {
              color: ['#192148'],
              width: 1,
              type: 'solid'
            }
          },
          axisLine: {
            lineStyle: {
              color: '#fff',//左边线的颜色
              width: '1'//坐标线的宽度
            }
          },
          axisTick: {
            inside: true
          },
          axisLabel: {
            inside: false,
            formatter: '{value}\n'
          }
        },
        series: [{
          name: '温度曲线1',
          type: 'line',
          smooth: true,
          showSymbol: false,
          hoverAnimation: false,
          symbolSize: 20,
          itemStyle: {
            color: '#6A5ACD',
            normal: {
              lineStyle: {// 系列级个性化折线样式
                width: 1,
                type: 'solid',
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                  offset: 0,
                  color: '#0000FF'
                }, {
                  offset: 1,
                  color: '#0096FF'
                }]),//线条渐变色
              }
            },
          },//线条样式
          data: temperature1,
        }, {
          name: '温度曲线2',
          type: 'line',
          smooth: true,
          showSymbol: false,
          hoverAnimation: false,
          symbolSize: 20,
          itemStyle: {
            color: '#6A5ACD',
            normal: {
              lineStyle: {// 系列级个性化折线样式
                width: 1,
                type: 'solid',
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                  offset: 0,
                  color: '#0000FF'
                }, {
                  offset: 1,
                  color: '#0096FF'
                }]),//线条渐变色
              }
            },
          },//线条样式
          data: temperature2,
        },{
          name: '温度曲线3',
          type: 'line',
          smooth: true,
          showSymbol: false,
          hoverAnimation: false,
          symbolSize: 20,
          itemStyle: {
            color: '#6A5ACD',
            normal: {
              lineStyle: {// 系列级个性化折线样式
                width: 1,
                type: 'solid',
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                  offset: 0,
                  color: '#0000FF'
                }, {
                  offset: 1,
                  color: '#0096FF'
                }]),//线条渐变色
              }
            },
          },//线条样式
          data: temperature3,
        }]
      }
      const chartDom = document.getElementById('threeTemperature');
      let myChart = echarts.init(chartDom);
      myChart.setOption(option);
      const onOpen = () =>{
        console.log('WebSocket连接成功，状态码：',websocket.readyState)
      };
      const onMessage = function (msg){
        let data = JSON.parse(msg.data);
        if(temperature1.length % 20 === 0){
          for(let i = 0; i < 20; i++){
            temperature1.shift()
          }
        }
        if(temperature2.length % 20 === 0){
          for(let i = 0; i < 20; i++){
            temperature2.shift()
          }
        }
        if(temperature3.length % 20 === 0){
          for(let i = 0; i < 20; i++){
            temperature3.shift()
          }
        }
        temperature1.push(getData(data[0].value+2));
        temperature2.push(getData(data[1].value+1));
        temperature3.push(getData(data[2].value));
        myChart.setOption(option);
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
        //连接地址：ws://127.0.0.1:8080/ws/xxx
        websocket = new WebSocket(process.env.VUE_APP_WS_SERVER + '/ws/'+token);
        initWebSocket()
      }else{
        alert('当前浏览器 不支持')
      }
    });
    return {}
  },
})
</script>

<style scoped>
.threeTemperature{
  display: flex;
}
</style>