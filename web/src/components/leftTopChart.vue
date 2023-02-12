<template>
  <div id="leftTopChart" class="leftTopChart"></div>
</template>

<script lang="js">
import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";
import {Tool} from "@/util/tool";

export default defineComponent({
  name: 'leftTopChart',
  setup: function () {
    Date.prototype.format = function (fmt) {
      var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
      };
      if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
      }
      for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
      }
      return fmt;
    }
    const strainColors = {
      strain1: '#3d2939',
      strain2: '#000',
      strain3: '#403897',
      strain4: '#f93',
      strain5: '#bc002d',
      strain6: '#924fa2',
      strain7: '#044500',
      strain8: '#09947d',
      strain9: '#ef2b2d',
      strain10: '#82043c',
      strain11: '#442b1e',
      strain12: '#330a17',
      strain13: '#70247d',
    };
    onMounted(() => {
      var chartDom = document.getElementById('leftTopChart');
      var myChart = echarts.init(chartDom);
      var option;
      const strain = [];
      option = {
        title: {
          text: '海底电缆实时应变值(με)',
          textStyle: {
            color: '#ffffff',
            fontFamily: '宋体',
          },
        },
        xAxis: {
          max: 'dataMax'
        },
        yAxis: {
          type: 'category',
          data: ['strain1', 'strain2', 'strain3', 'strain4', 'strain5', 'strain6', 'strain7', 'strain8', 'strain9', 'strain10', 'strain11', 'strain12', 'strain13'],
          inverse: true,
          animationDuration: 300,
          animationDurationUpdate: 300,
        },
        grid: {
          top: 40,
          bottom: 40,
          left: 120,
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
        series: [
          {
            name: 'X',
            type: 'bar',
            data: strain,
            label: {
              show: true,
              position: 'right',
              valueAnimation: true
            }
          }
        ],
        animationDuration: 0,
        animationDurationUpdate: 3000,
        animationEasing: 'linear',
        animationEasingUpdate: 'linear',
        visualMap: {
          orient: 'vertical',
          left: 'left',
          bottom: 0,
          min: -100,
          max: 100,
          text: ['High Score', 'Low Score'],
          textStyle: {
            color: 'white'
          },
          // Map the score column to color
          dimension: 0,
          inRange: {
            color: ['#403897', '#288888', '#988888']
          },
          padding: 5,
          textGap: 20
        },
        graphic: {
          elements: [
            {
              type: 'text',
              right: 70,
              bottom: 0,
              style: {
                text: "",
                font: 'bolder 17px monospace',
                fill: 'rgba(225, 225, 225, 255)'
              },
              z: 100
            }
          ]
        }
      };
      let websocket;
      let token;
      const onOpen = () => {
        console.log('WebSocket连接成功，状态码：', websocket.readyState)
      };
      const onMessage = function (msg) {
        let data = JSON.parse(msg.data);
        for (let i = 4; i < 17; i++) {
          //四舍五入取两位小数
          strain.push(Math.round(data[i].value * 100) / 100)
        }
        option.series[0].data = strain;
        option.graphic.elements[0].style.text = new Date().format("yyyy-MM-dd hh:mm:ss");
        myChart.setOption(option);
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

      if ('WebSocket' in window) {
        token = Tool.uuid(10);
        //连接地址：ws://127.0.0.1:8080/ws/xxx
        websocket = new WebSocket(process.env.VUE_APP_WS_SERVER + '/ws/' + token);
        initWebSocket()
      } else {
        alert('当前浏览器 不支持')
      }
    })
  }
})
</script>

<style scoped>
.leftTopChart {
  display: flex;
}
</style>