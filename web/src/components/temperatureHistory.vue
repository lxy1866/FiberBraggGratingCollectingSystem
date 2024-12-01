<template>
  <div id="temperatureHistory" class="temperatureHistory"></div>
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
  return axios.get("/historyData/tempX")
}
function handleQueryY(){
  return axios.get("/historyData/tempY")
}
export default defineComponent({
  name: 'temperatureHistory',
  setup() {
    let myChart: echarts.ECharts;
    
    onMounted(async () => {
      const chartDom = document.getElementById('temperatureHistory')!;
      myChart = echarts.init(chartDom);
      const option = {
        title: {
          text: '海底管道温度变化',
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
          type: 'value',
          axisLabel: {
            formatter: '{value} °C'
          },
        },
        dataZoom: [
          {
            type: 'slider',
            show: true,
            start: 0,
            end: 100,
            filterMode: 'none'
          },
          {
            type: 'inside',
            show: true,
            filterMode: 'none'
          }
        ],
        series: [
          {
            data: [],
            type: 'line',
            smooth: true,
            name: '温度',
            symbolSize: 5,
            lineStyle: {
              width: 2
            },
            itemStyle: {
              color: '#409EFF'
            },
            areaStyle: {
              opacity: 0.2
            }
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

</style>