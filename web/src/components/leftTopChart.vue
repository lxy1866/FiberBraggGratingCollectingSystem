<template>
  <div id="leftTopChart" class="leftTopChart"></div>
</template>

<script lang="js">
import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";
import axios from "axios";
import {zhCN} from "naive-ui";
function dateToGMT(strDate){
  const dateStr = strDate.split(" ");
  const strGMT = dateStr[0] + " " + dateStr[1] + " " + dateStr[2] + " " + dateStr[5] + " " + dateStr[3] + " GMT+0800";
  const date = new Date(Date.parse(strGMT));
  return date;
}
export default defineComponent({
  name: 'leftTopChart',
  setup: function () {
    let updateFrequency = 2000
    const strainColors = {
      strain1 : '#3d2939',
      strain2 : '#000',
      strain3 : '#403897',
      strain4 :  '#f93',
      strain5 : '#bc002d',
      strain6 : '#924fa2',
      strain7 : '#044500',
      strain8 : '#09947d',
      strain9 : '#ef2b2d',
      strain10 : '#82043c',
      strain11 : '#442b1e',
      strain12 : '#330a17',
      strain13 : '#70247d',
    };
    function getFbgValueInfoForDistance() {
      return axios.get("/nr/strainDistance")
    }
    function handleQueryList(startTime, endTime) {
      return axios.get("/txt/listLastHour", {
        params: {
          startTime: startTime,
          endTime: endTime,
          category: 1
        }
      })
    }
    onMounted( async ()=>{
      const chartDom = document.getElementById('leftTopChart');
      const myChart = echarts.init(chartDom);
      let data = {};
      data = await getFbgValueInfoForDistance()
      const FbgValueInfo = data.data.content//数组 每一个元素是id, propertyName, min, max, distance, creatTime, category, channel
      let y = []
      for (let i = 0; i < FbgValueInfo.length; i++) {
        y.push(FbgValueInfo[i].distance)
      }
      // var fullYear = new Date(new Date().setFullYear(2022, 8, 2));
      // var frontOneHourTimeStamp = new Date(fullYear.setHours(0, 0, 0, 0)).getTime();
      //var currentHourTimeStamp = new Date(fullYear.setHours(0, 59, 59, 0)).getTime();
      var frontOneHourTimeStamp = new Date(new Date().setHours(new Date().getHours()-1, 0, 0, 0)).getTime();
      var currentHourTimeStamp = new Date(new Date().setHours(new Date().getHours()-1, 59, 59, 59)).getTime();
      let data2 = {}
      data2 = await handleQueryList(frontOneHourTimeStamp, currentHourTimeStamp)
      //二维数组
      const oneHourData = data2.data.content
      //console.log(oneHourData)
      let createTimeList = []
      let columnnLength = oneHourData.length
      for(let i = 0; i < oneHourData.length; i++){
        oneHourData[i][oneHourData[0].length - 1] = new Date(dateToGMT(oneHourData[i][oneHourData[0].length - 1])).toLocaleString('zh');
        //oneHourData[i][oneHourData[0].length - 1] = new Date(oneHourData[i][oneHourData[0].length - 1].replace("CST",'GMT+0800')).toLocaleString()
        createTimeList.push(oneHourData[i][oneHourData[0].length - 1])
      }
      //console.log(createTimeList)
      let startCreateTime = createTimeList[0];
      let option;
      let websocket;
      let token;

      // function distance(propertyName: string) {
      //   if (!propertyName) {
      //     return '';
      //   }
      //   return (
      //       FbgValueInfo.find(function (item: any) {
      //             return item.propertyName == propertyName
      //           }
      //       )).distance
      // }

      // function channel(propertyName: string) {
      //   if (!propertyName) {
      //     return '';
      //   }
      //   return (
      //       FbgValueInfo.find(function (item: any) {
      //             return item.propertyName == propertyName
      //           }
      //       )).channel
      // }
      option = {
        title: {
          text: '海缆上一个小时的应变值(με)',
          textStyle: {
            color: '#ffffff',
            fontFamily: '宋体',
          },
        },
        grid: {
          top: 40,
          bottom: 38,
          left: 60,
          right: 40
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
        xAxis: {
          max: 'dataMax',
          axisLabel: {
            show: true
          },
          axisLine: {
            lineStyle: {
              color: '#565c67'
            }

          },
        },
        dataset: {
          source: oneHourData.filter(function (d) {
            return d[d.length - 1] === startCreateTime;
          })
        },
        yAxis: {
          //name: '传感器名称',
          nameLocation: 'start',
          type: 'category',
          nameGap:  38,
          max: 0,
          inverse: false,
          axisLine: {
            lineStyle: {
              color: '#565c67',
              fontSize: 10
            }
          },
          nameTextStyle: {
            verticalAlign: 'bottom'
          },
          axisLabel: {
            show: true,
            fontSize: 14,
            inverse: true,
            formatter: function (value) {
              return value;
            },
            rich: {
              flag: {
                fontSize: 15,
                padding: 5
              }
            }
          },
          animationDuration: 300,
          animationDurationUpdate: 300
        },
        series: [
          {
            realtimeSort: false,
            seriesLayoutBy: 'column',
            type: 'bar',
            itemStyle: {
              //柱条的颜色
              color: function (param) {
                //console.log(param)
                //console.log(strainColors[param.data.propertyName]);
                return strainColors[param.value[0]]|| '#5470c6';
              }
            },
            encode: {
              x: 1,
              y: 0
            },
            label: {
              show: true,
              precision: 1,
              position: 'right',
              valueAnimation: true,
              fontFamily: 'monospace',
            },
          }
        ],
        // Disable init animation.
        animationDuration: 0,
        animationDurationUpdate: updateFrequency,
        animationEasing: 'linear', //初始动画的缓动效果
        animationEasingUpdate: 'linear', //数据更新动画的缓动效果
        graphic: {
          elements: [
            {
              type: 'text',
              right: 70,
              bottom: 0,
              style: {
                text: startCreateTime,
                font: 'bolder 17px monospace',
                fill: 'rgba(225, 225, 225, 0.25)'
              },
              z: 100
            }
          ]
        }
      };
      //option.yAxis.data = y
      option.yAxis.max = y.length - 1
      // for (let i = 0 ; i < columnnLength - 1; i++){
      //   option.series[0].encode.x.push(i);
      // }
      option.series[0].data = oneHourData
      for (let i = 0; i < createTimeList.length - 1; ++i) {
        (function (i) {
          setTimeout(function () {
            updateCreateTime(createTimeList[i * 13]);
          }, (i * updateFrequency));
        })(i);
      }
      function updateCreateTime(createTime) {
        let source = oneHourData.filter(function (d) {
          //console.log(d[d.length - 1])
          return d[d.length - 1] === createTime;
        });
        //console.log(source)
        option.series[0].data = source;
        option.graphic.elements[0].style.text = createTime;
        myChart.setOption(option);
      }
      // const onOpen = () => {
      //   //console.log('WebSocket连接成功，状态码：', websocket.readyState)
      // };
      // const onMessage = function (msg: any) {
      //   //console.log("WebSocket收到消息：",event.data);
      //   let data = JSON.parse(msg.data);
      //   //console.log("stain", data)
      //   option.series[0].data = data[1];
      //   myChart.setOption<echarts.EChartsOption>(option);
      // };
      // const onError = () => {
      //   //console.log('WebSocket连接错误，状态码：', websocket.readyState)
      // };
      // const onClose = () => {
      //   //console.log('WebSocket连接关闭，状态码：', websocket.readyState)
      // };
      // const initWebSocket = () => {
      //   //连接成功
      //   websocket.onOpen = onOpen;
      //   // 收到消息的回调
      //   websocket.onmessage = onMessage;
      //   // 连接错误
      //   websocket.onerror = onError;
      //   // 连接关闭的回调
      //   websocket.onClose = onClose;
      // }
      // if ('WebSocket' in window) {
      //   token = Tool.uuid(10);
      //   //连接地址：ws://127.0.0.1:8080/ws/xxx
      //   websocket = new WebSocket(process.env.VUE_APP_WS_SERVER + '/ws/' + token);
      //   initWebSocket()
      // } else {
      //   alert('当前浏览器 不支持')
      // }
    });
    return {
    }
  },
})
</script>

<style scoped>
.leftTopChart {
  display: flex;
}
</style>