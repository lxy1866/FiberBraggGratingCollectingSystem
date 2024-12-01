<template>
  <div id="vibrationHis" class="vibrationHis"></div>
</template>

<script lang="ts">
import * as echarts from 'echarts';
import {defineComponent, onMounted, onUnmounted} from "vue";
import axios from "axios";
interface Xvalue {
  date: string
}
interface Yvalue {
  date: number
}
function handleQueryX(){
  return axios.get("/historyData/vibrationX")
}
function handleQueryY(){
  return axios.get("/historyData/vibrationY")
}
export default defineComponent({
  name: "vibrationHis",
  setup() {
    let myChart: echarts.ECharts;
    onMounted(async () => {
      const chartDom = document.getElementById('vibrationHis')!;
      const myChart = echarts.init(chartDom);
      const option = {
        title: {
          text: '振动信号随时间变化',
          textStyle: {
            color: '#ffffff',
            fontFamily: '宋体',
          }
        },
        animation: false,
        grid: {
          left: '2%',
          right: '2%',
          bottom: '10%',
          containLabel: true,
          show: 'true',
          borderWidth: '0'
        },
        tooltip: {
          trigger: 'axis',
          backgroundColor: 'rgba(32, 33, 36,.7)',
          borderColor: 'rgba(32, 33, 36,0.20)',
          borderWidth: 1,
          textStyle: {
            color: '#fff',
            fontSize: '12'
          },
          axisPointer: {
            type: 'cross'
          }
        },
        toolbox: {
          show: true,
          feature: {
            dataView: {readOnly: false},
            restore: {},
            saveAsImage: {},
            magicType: {type: ['line', 'bar']},
          }
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          max: 1549.52,
          min: 1549.48,
          type: 'value',
          axisLabel: {
            formatter: '{value} nm'
          },
        },
        dataZoom: [
          {
            show: true,
            type: 'inside',
            filterMode: 'none',
          },
          {
            show: true,
            type: 'inside',
            filterMode: 'none',
          }
        ],
        series: [
          {
            data: [],
            type: 'line',
            smooth: true,
            markPoint: {
              data: [
                {
                  x: '40%',
                  yAxis:1549.505,
                  symbol: "roundRect",
                  symbolSize: 60,
                  value: '台风\n\n轩岚诺',
                  animation: true,
                  label: {
                    show: true,
                    color: '#000'
                  },
                  itemStyle: { color:'#0f0' }
                },
                {
                  x: '90%',
                  yAxis:1549.516,
                  symbol: "roundRect",
                  symbolSize: 60,
                  value: '台风\n\n梅花',
                  animation: true,
                  label: {
                    show: true,
                    color: '#000'
                  },
                  itemStyle: { color:'#0f0' }
                },
              ]
            },
          }
        ]
      }
      await handleQueryX().then(res => {
        option.xAxis.data = res.data.content;
      })
      await handleQueryY().then(res => {
        option.series[0].data = res.data.content;
      })
      myChart.setOption(option)
      window.addEventListener('resize', () => {
        myChart.resize();
      });
    });
    onUnmounted(() => {
      if (myChart) {
        myChart.dispose();
      }
      window.removeEventListener('resize', () => {
        myChart.resize();
      });
    });
    
  }
})
</script>

<style scoped>
.vibrationHis {
  width: 100%;
  height: 100%;
  min-height: 300px;
}
</style>