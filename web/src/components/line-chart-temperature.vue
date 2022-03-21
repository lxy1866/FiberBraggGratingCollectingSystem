<template>
  <div id="lineChartTemperature"></div>
</template>

<script>
import {defineComponent, onMounted} from "vue";

import * as echarts from "echarts";

let option = {
  title: {
    text: '过去24小时的温度最大最小值',
    textStyle:{
      color: '#ffffff',
      fontFamily: '宋体',
    }
  },
  tooltip: {
    trigger: 'axis'
  },
  toolbox: {
    show: true,
    feature: {
      dataView: { readOnly: false },
      magicType: { type: ['line', 'bar'] },
      restore: {},
      saveAsImage: {}
    }
  },
  xAxis: {
    type: 'category',
    boundaryGap: false,
    data:  [-1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -14, -15, -16, -17, -18, -19, -20, -21, -22, -23, -24],
    axisLabel: {
      show: true,
      textStyle: {
        color: '#ffffff',  //更改坐标轴文字颜色
        fontSize : 12      //更改坐标轴文字大小
      }
    },
  },
  yAxis: {
    type: 'value',
    boundaryGap: [0, '100%'],
    axisLabel: {
      formatter: '{value} °C',
      show: true,
      textStyle: {
        color: '#ffffff',  //更改坐标轴文字颜色
        fontSize : 12      //更改坐标轴文字大小
      }
    }
  },
  legend:[
    {
      orient:"vertical",
      backgroundColor: "transparent",
      inactiveColor: "white",
      textStyle:{
        color: "blue"
      }

    }
  ],
  series: [
    {
      name: 'Highest',
      type: 'line',
      data: [10, 11, 13, 11, 12, 12, 9, 8, 10, 5, 6, 8, 9, 12,15,18,19,10,16,44,22,12,45,6],
      markPoint: {
        data: [
          { type: 'max', name: 'Max' },
          { type: 'min', name: 'Min' }
        ]
      },
      markLine: {
        data: [{ type: 'average', name: 'Avg' }]
      }
    },
    {
      name: 'Lowest',
      type: 'line',
      data: [-10, -11, -13, -11, -12, -12, -9, -8, -10, -5, -6, -8, -9, -12,-15,-18,-19,-10,-16,-44,-7, -24, -6,-10],
      markPoint: {
        data: [
          { type: 'max', name: 'Max' },
          { type: 'min', name: 'Min' }
        ]
      },
      markLine: {
        data: [
          { type: 'average', name: 'Avg' },
          [
            {
              symbol: 'none',
              x: '90%',
              yAxis: 'max'
            },
            {
              symbol: 'circle',
              label: {
                position: 'start',
                formatter: 'Max'
              },
              type: 'max',
              name: '最高点'
            }
          ]
        ]
      }
    }
  ]
};

export default defineComponent({
  name: 'line-chart-temperature',
  setup() {
    onMounted(()=>{
      const chart = echarts.init(document.getElementById('lineChartTemperature'));
      chart.setOption(option);
    })
    return{
    }
  },
})
</script>

<style scoped>
</style>