<template>
  <div id="lineChartDisplayment" class="lineChartDisplayment"></div>
</template>

<script>
import {defineComponent, onMounted, toRefs, reactive} from "vue";
import * as echarts from "echarts";
import axios from "axios";

function handleQuery(){
  return axios.get("/fbg/minAndMaxFromLast24Hours")
}
function func(x) {
  x /= 10;
  return Math.sin(x) * Math.cos(x * 2 + 1) * Math.sin(x * 3 + 2) * 50;
}
function generateData() {
  let data = [];
  for (let i = -200; i <= 200; i += 0.1) {
    data.push([i, func(i)]);
  }
  return data;
}
export default defineComponent({
  name: 'line-chart-displayment',
  setup() {

    const state = reactive({
      option : {
        title: {
          text: '一个月内管道位移变化曲线图',
          textStyle:{
            color: '#ffffff',
            fontFamily: '宋体',
          }
        },
        animation: false,
        grid: {
          top: 40,
          left: 50,
          right: 40,
          bottom: 60
        },
        toolbox: {
          show: true,
          feature: {
            dataView: {readOnly: false},
            restore: {},
            saveAsImage: {}
          }
        },
        xAxis: {
          min:0,
          max:15,
          nameLocation:'middle',
          name: '管道长度(单位米)',
          nameGap:30,
          minorTick: {
            show: true
          },
          minorSplitLine: {
            show: true
          }
        },
        yAxis: {
          name: 'y',
          min: 0,
          max: 100,
          minorTick: {
            show: true
          },
          minorSplitLine: {
            show: true
          }
        },
        dataZoom: [
          {
            show: true,
            type: 'inside',
            filterMode: 'none',
            xAxisIndex: [0],
            startValue: -20,
            endValue: 20
          },
          {
            show: true,
            type: 'inside',
            filterMode: 'none',
            yAxisIndex: [0],
            startValue: -20,
            endValue: 20
          }
        ],
        series: [
          {
            type: 'line',
            showSymbol: false,
            clip: true,
            data: generateData(),
            markLine: {
              data: [
                {
                  type: 'max',
                  name: 'Max',
                  symbol:'circle',
                  label:{
                    show:true,
                    position : 'middle',
                    formatter: '{b}:   {c}'
                  }
                },
                {
                  type: 'average',
                  name: 'Avg',
                  symbol:'circle',
                  label:{
                    show:true,
                    position : 'middle',
                    formatter: '{b}:   {c}'
                  }
                },
              ]
            }
          }
        ]
      }
    })
    onMounted(async ()=>{
      const {data} = await handleQuery();
      const minArray = []
      const maxArray = []
      // data.content.forEach((item) => {
      //     maxArray.push(item.max)
      // })
      // data.content.forEach((item)=>{
      //    minArray.push(item.min)
      // })
      // state.option.series[0].data = maxArray
      // state.option.series[1].data = minArray
      const chart = echarts.init(document.getElementById('lineChartDisplayment'));
      window.onresize = chart.resize;
      chart.setOption(state.option);
    })
    return{
      ...toRefs(state)
    }
  },
})
</script>

<style scoped>
.lineChartDisplayment{
  display: flex;
}
</style>