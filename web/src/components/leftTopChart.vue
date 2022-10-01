<template>
  <div id="leftTopChart" class="leftTopChart"></div>
</template>

<script lang="ts">
import * as echarts from 'echarts';
import store from "@/store";
import {Tool} from "@/util/tool";
import {defineComponent, onMounted, computed} from "vue";
import axios from "axios";

const user = computed(() => store.state.user)
export default defineComponent({
  name: 'leftTopChart',
  setup() {
    const strainColors: Record<string, string> = {
      0: '#3d2939',
      1: '#000',
      2: '#403897',
      3: '#f93',
      4: '#bc002d',
      5: '#924fa2',
      6: '#044500',
      7: '#09947d',
      8: '#ef2b2d',
      9: '#82043c',
      10: '#442b1e',
      11: '#330a17',
      12: '#70247d',
      13: '#b22234',
      14: '#ef7878',
      15: '#559069',
      16: '#a45060',
      17: '#670044',
      18: '#565965',
      19: '#340569',
      20: '#586042',
      21: '#619405',
      22: '#945006',
      23: '#586473',
      24: '#124995',
      25: '#759594',
      27: '#c56600'
    };
    function getFbgValueInfoForDistance() {
      return axios.get("/nr/strainDistance")
    }
    onMounted(async () => {
      const {data} = await getFbgValueInfoForDistance();

      //console.log(data.content)
      const FbgValueInfo = data.content//数组 每一个元素是id, propertyName, min, max, distance, creatTime, category, channel
      let y = []
      for (let i = 0; i < FbgValueInfo.length; i++) {
        y.push(FbgValueInfo[i].propertyName)
        //console.log(y)
      }
      const chartDom = document.getElementById('leftTopChart')!;
      const myChart = echarts.init(chartDom);
      let option: any;
      let websocket: any;
      let token: any;

      function distance(propertyName: string) {
        if (!propertyName) {
          return '';
        }
        return (
            FbgValueInfo.find(function (item: any) {
                  return item.propertyName == propertyName
                }
            )).distance
      }
      function channel(propertyName: string) {
        if (!propertyName) {
          return '';
        }
        return (
            FbgValueInfo.find(function (item: any) {
                  return item.propertyName == propertyName
                }
            )).channel
      }

      option = {
        title: {
          text: '海缆实时应变值(με)',
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
            show: true, formatter: function (n: number) {
              return Math.round(n) + '';
            },
          },
          axisLine: {
            lineStyle: {
              color: '#565c67'
            }
          },
        },
        yAxis: {
          name: '初始位置',
          nameLocation: 'start',
          type: 'category',
          max: 19,
          inverse: false,
          data: ['val6', 'val7', 'val8', 'val9', 'val10'],
          axisLine: {
            lineStyle: {
              color: '#565c67',
              fontSize: 10
            }
          },
          axisLabel: {
            show: true,
            fontSize: 14,
            formatter: function (value: any) {
              return '{distance|' + distance(value) + '米}';
            },
            rich: {
              distance: {
                fontSize: 15,
                padding: 4
              }
            },
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
              color: function (param:any) {
                //console.log(param)
                return strainColors[param.data.channel];
              }
            },
            label: {
              show: true,
              precision: 1,
              position: 'right',
              valueAnimation: true,
              fontFamily: 'monospace',
            },
            data: [100, 200, 300, 400, 500]
          }
        ],
        // Disable init animation.
        animationDuration: 0,
        animationDurationUpdate: 2000,
        animationEasing: 'linear', //初始动画的缓动效果
        animationEasingUpdate: 'linear', //数据更新动画的缓动效果
      };
      option.yAxis.data = y
      myChart.setOption<echarts.EChartsOption>(option);
      const onOpen = () => {
        //console.log('WebSocket连接成功，状态码：', websocket.readyState)
      };
      const onMessage = function (msg: any) {
        //console.log("WebSocket收到消息：",event.data);
        let data = JSON.parse(msg.data);
        //console.log("stain", data)
        option.series[0].data = data[1];
        myChart.setOption<echarts.EChartsOption>(option);
      };
      const onError = () => {
        //console.log('WebSocket连接错误，状态码：', websocket.readyState)
      };
      const onClose = () => {
        //console.log('WebSocket连接关闭，状态码：', websocket.readyState)
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
      if ('WebSocket' in window) {
        token = Tool.uuid(10);
        //连接地址：ws://127.0.0.1:8080/ws/xxx
        websocket = new WebSocket(process.env.VUE_APP_WS_SERVER + '/ws/' + token);
        initWebSocket()
      } else {
        alert('当前浏览器 不支持')
      }
    });
    return {
      user,
    }
  },
})
</script>

<style scoped>
.leftTopChart {
  display: flex;
}
</style>