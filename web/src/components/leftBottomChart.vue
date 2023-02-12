<template>
  <div id="realTemperature" class="realTemperature"></div>
</template>
<script type="text/javascript">
// 基于准备好的dom，初始化echarts实例
import * as echarts from "echarts";
import {defineComponent, onMounted, toRefs, reactive} from "vue";
import axios from "axios";
import {Tool} from "@/util/tool";
let TP_value=-1;
const kd = [];
const Gradient = [];
let leftColor = '';
let showValue = '';
let boxPosition = [65, 0];
let TP_txt = '';
// 刻度使用柱状图模拟，短设置1，长的设置3；构造一个数据
let i = 0, len = 135;
for(; i <= len; i++) {
  if(i < 10 || i > 130) {
    kd.push('')
  } else {
    if((i - 10) % 20 === 0) {
      kd.push('-3');
    } else if((i - 10) % 4 === 0) {
      kd.push('-1');
    } else {
      kd.push('');
    }
  }
}
//中间线的渐变色和文本内容
if(TP_value > 20) {
  TP_txt = '温度偏高';
  Gradient.push({
    offset: 0,
    color: '#403897'
  }, {
    offset: 0.5,
    color: '#288888'
  }, {
    offset: 1,
    color: '#988888'
  })
} else if(TP_value > -20) {
  TP_txt = '温度正常';
  Gradient.push({
    offset: 0,
    color: '#403897'
  }, {
    offset: 1,
    color: '#288888'
  })
} else {
  TP_txt = '温度偏低';
  Gradient.push({
    offset: 1,
    color: '#988888'
  })
}
if(TP_value > 62) {
  showValue = 62
} else {
  if(TP_value < -60) {
    showValue = -60
  } else {
    showValue = TP_value
  }
}
if(TP_value < -10) {
  boxPosition = [65, -120];
}
leftColor = Gradient[Gradient.length - 1].color;
export default defineComponent({
  name: 'temperature',
  // 因为柱状初始化为0，温度存在负值，所以加上负值60和空出距离10
  setup(){
    const state = reactive({
      option : {
        backgroundColor: 'transparent',
        title: {
          text: '海底电缆实时温度值',
          show: true,
          textStyle:{
            color: '#ffffff',
            fontFamily: '宋体',
          }
        },
        yAxis: [{
          show: false,
          data: [],
          min: 0,
          max: 135,
          axisLine: {
            show: false
          }
        }, {
          show: false,
          min: 0,
          max: 50,
        }, {
          type: 'category',
          data: ['', '', '', '', '', '', '', '', '', '', ''],
          position: 'left',
          offset: -80,
          axisLabel: {
            fontSize: 10,
            color: 'white'
          },
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
        }],
        xAxis: [{
          show: false,
          min: -10,
          max: 50,
          data: []
        }, {
          show: false,
          min: -10,
          max: 50,
          data: []
        }, {
          show: false,
          min: -10,
          max: 50,
          data: []
        }, {
          show: false,
          min: -5,
          max: 50,
        }],
        series: [{
          name: '条',
          type: 'bar',
          // 对应上面XAxis的第一个对)象配置
          xAxisIndex: 0,
          data: [{
            value: (showValue + 70),
            label: {
              normal: {
                show: true,
                position: boxPosition,
                width: 70,
                height: 40,
                formatter: '{back| ' + TP_value + ' }{unit|°C}\n{downTxt|' + TP_txt + '}',
                rich: {
                  back: {
                    align: 'center',
                    lineHeight: 30,
                    fontSize: 40,
                    fontFamily: 'digifacewide',
                    color: leftColor
                  },
                  unit: {
                    fontFamily: '微软雅黑',
                    fontSize: 15,
                    lineHeight: 50,
                    color: leftColor
                  },
                  downTxt: {
                    lineHeight: 50,
                    fontFamily: '宋体',
                    fontSize: 18,
                    align: 'center',
                    color: '#fff'
                  }
                }
              }
            }
          }],
          barWidth: 18,
          itemStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(0, 1, 0, 0, Gradient)
            }
          },
          z: 2
        }, {
          name: '白框',
          type: 'bar',
          xAxisIndex: 1,
          barGap: '-100%',
          data: [134],
          barWidth: 28,
          itemStyle: {
            normal: {
              color: '#0C2E6D',
              barBorderRadius: 50,
            }
          },
          z: 1
        }, {
          name: '外框',
          type: 'bar',
          xAxisIndex: 2,
          barGap: '-100%',
          data: [135],
          barWidth: 38,
          itemStyle: {
            normal: {
              color: '#4577BA',
              barBorderRadius: 50,
            }
          },
          z: 0
        }, {
          name: '圆',
          type: 'scatter',
          hoverAnimation: false,
          data: [0],
          xAxisIndex: 0,
          symbolSize: 48,
          itemStyle: {
            normal: {
              color: '#ffffff',
              opacity: 1,
            }
          },
          z: 2
        }, {
          name: '白圆',
          type: 'scatter',
          hoverAnimation: false,
          data: [0],
          xAxisIndex: 1,
          symbolSize: 60,
          itemStyle: {
            normal: {
              color: '#0C2E6D',
              opacity: 1,
            }
          },
          z: 1
        }, {
          name: '外圆',
          type: 'scatter',
          hoverAnimation: false,
          data: [0],
          xAxisIndex: 2,
          symbolSize: 70,
          itemStyle: {
            normal: {
              color: '#4577BA',
              opacity: 1,
            }
          },
          z: 0
        }, {
          name: '刻度',
          type: 'bar',
          yAxisIndex: 0,
          xAxisIndex: 3,
          label: {
            normal: {
              show: true,
              position: 'left',
              distance: 10,
              color: 'white',
              fontSize: 14,
              formatter: function(params) {
                if(params.dataIndex > 130 || params.dataIndex < 10) {
                  return '';
                } else {
                  if((params.dataIndex - 10) % 20 === 0) {
                    return params.dataIndex - 70;
                  } else {
                    return '';
                  }
                }
              }
            }
          },
          barGap: '-100%',
          data: kd,
          barWidth: 1,
          itemStyle: {
            normal: {
              color: 'white',
              barBorderRadius: 120,
            }
          },
          z: 0
        }]
      }
    })
    onMounted( ()=>{
      const myChart = echarts.init(document.getElementById('realTemperature'));
      let TP_value;
      //console.log(TP_value)
      const kd = [];
      const Gradient = [];
      let leftColor = '';
      let showValue = '';
      let boxPosition = [65, 0];
      let TP_txt = '';
      // 刻度使用柱状图模拟，短设置1，长的设置3；构造一个数据
      let i = 0, len = 135;
      for(; i <= len; i++) {
        if(i < 10 || i > 130) {
          kd.push('')
        } else {
          if((i - 10) % 20 === 0) {
            kd.push('-3');
          } else if((i - 10) % 4 === 0) {
            kd.push('-1');
          } else {
            kd.push('');
          }
        }
      }

      //myChart.setOption(state.option);

      let websocket;
      let token;
      const onOpen = () => {
        console.log('WebSocket连接成功，状态码：', websocket.readyState)
      };
      const onMessage = function (msg) {
        let data = JSON.parse(msg.data);
        state.option.series[0].data[0].value = TP_value =  data[0].value;
        //中间线的渐变色和文本内容
        if(TP_value > 20) {
          TP_txt = '';
          Gradient.push({
            offset: 0,
            color: '#403897'
          }, {
            offset: 0.5,
            color: '#288888'
          }, {
            offset: 1,
            color: '#988888'
          })
        } else if(TP_value > -20) {
          TP_txt = '';
          Gradient.push({
            offset: 0,
            color: '#403897'
          }, {
            offset: 0.5,
            color: '#288888'
          }, {
            offset: 1,
            color: '#988888'
          })
        } else {
          TP_txt = '';
          Gradient.push({
            offset: 1,
            color: '#403897'
          })
        }
        if(TP_value > 62) {
          showValue = 62
        } else {
          if(TP_value < -60) {
            showValue = -60
          } else {
            showValue = TP_value
          }
        }
        if(TP_value < -10) {
          boxPosition = [65, -120];
        }
        leftColor = Gradient[Gradient.length - 1].color;
        state.option.series[0].data[0].value = showValue + 70
        state.option.series[0].data[0].label.normal.position = boxPosition
        state.option.series[0].data[0].label.normal.formatter = '{back| ' + Math.round(TP_value)+ ' }{unit|°C}\n{downTxt|' + TP_txt + '}'
        state.option.series[0].data[0].label.normal.rich.back.color = leftColor
        state.option.series[0].itemStyle.normal.color = new echarts.graphic.LinearGradient(0, 1, 0, 0, Gradient)
        state.option.series[6].data = kd
        myChart.setOption(state.option);
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
    });
    return{
      ...toRefs(state),
    }
  }
})
</script>
<style scoped>
.realTemperature{
  display: flex;
}
</style>
