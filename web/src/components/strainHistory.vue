<template>
  <div id="strainHistory" class="strainHistory"></div>
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
  return axios.get("/historyData/strainX")
}
function handleQueryY(){
  return axios.get("/historyData/strainY")
}
export default defineComponent({
  name: 'strainHistory',
  setup() {
    let myChart: echarts.ECharts;
    
    const resize = () => {
      if (myChart) {
        myChart.resize();
      }
    }

    onMounted(async () => {
      const chartDom = document.getElementById('strainHistory')!;
      myChart = echarts.init(chartDom);
      const option = {
        title: {
          text: '应变量变化',
          textStyle: {
            color: '#ffffff',
            fontFamily: '宋体',
          }
        },
        // legend: {
        //   show: true,
        //   type: 'scroll',
        //   orient: 'horizontal',
        //   textStyle: {
        //     color: '#ffffff',
        //     fontFamily: '宋体',
        //   },
        // },
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
            formatter: '{value} '
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
            name: '传感器1',
            data: [],
            type: 'line',
            smooth: true,
          },
          {
            name: '传感器2',
            data: [],
            type: 'line',
            smooth: true,
          },
          {
            name: '传感器3',
            data: [],
            type: 'line',
            smooth: true,
          },
          {
            name: '传感器4',
            data: [],
            type: 'line',
            smooth: true,
          },
          {
            name: '传感器5',
            data: [],
            type: 'line',
            smooth: true,
          },
          {
            name: '传感器6',
            data: [],
            type: 'line',
            smooth: true,
          },
          {
            name: '传感器7',
            data: [],
            type: 'line',
            smooth: true,
          },
          {
            name: '传感器8',
            data: [],
            type: 'line',
            smooth: true,
          },
          {
            name: '传感器9',
            data: [],
            type: 'line',
            smooth: true,
          },
          {
            name: '传感器10',
            data: [],
            type: 'line',
            smooth: true,
          },
          {
            name: '传感器11',
            data: [],
            type: 'line',
            smooth: true,
          },
          {
            name: '传感器12',
            data: [],
            type: 'line',
            smooth: true,
          },
          {
            name: '传感器13',
            data: [],
            type: 'line',
            smooth: true,
          }
        ]
      }
      
      await handleQueryX().then(res => {
        option.xAxis.data = res.data.content;
      })
      await handleQueryY().then(res => {
        option.series[0].data = res.data.content["strain1"];
        option.series[1].data = res.data.content["strain2"];
        option.series[2].data = res.data.content["strain3"];
        option.series[3].data = res.data.content["strain4"];
        option.series[4].data = res.data.content["strain5"];
        option.series[5].data = res.data.content["strain6"];
        option.series[6].data = res.data.content["strain7"];
        option.series[7].data = res.data.content["strain8"];
        option.series[8].data = res.data.content["strain9"];
        option.series[9].data = res.data.content["strain10"];
        option.series[10].data = res.data.content["strain11"];
        option.series[11].data = res.data.content["strain12"];
        option.series[12].data = res.data.content["strain13"];
      })
      
      myChart.setOption(option);
      
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

    // 暴露 resize 方法
    return {
      resize
    }
  }
})
</script>

<style scoped>
.strainHistory {
  width: 100%;
  height: 100%;
  min-height: 300px;
}
</style>