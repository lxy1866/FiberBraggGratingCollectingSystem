<template>
  <div id="shortTimeHis" class="shortTimeHis"></div>
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
  return axios.get("/historyData/shortTimeX")
}
function handleQueryY(){
  return axios.get("/historyData/shortTimeY")
}
export default defineComponent({
  name: 'shortTimeHis',
  setup() {
    let myChart: echarts.ECharts;
    
    onMounted(async () => {
      const chartDom = document.getElementById('shortTimeHis')!;
      myChart = echarts.init(chartDom);
      const option = {
        title: {
          text: '振动传感器短时能量变化',
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
          min:0,
          max:0.5,
          type: 'value',
          axisLabel: {
            formatter: '{value} dB'
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
                  x: '35%',
                  yAxis:0.25,
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
                  yAxis:0.45,
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
.shortTimeHis {
  width: 100%;
  height: 100%;
  min-height: 300px;
}
</style>