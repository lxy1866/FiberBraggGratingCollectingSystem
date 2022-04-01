<template>
  <div id="lineChartTemperature" class="lineChartTemperature"></div>
</template>

<script>
import {defineComponent, onMounted, toRefs, reactive} from "vue";
import * as echarts from "echarts";
import axios from "axios";

function handleQuery(){
  return axios.get("/fbg/minAndMaxFromLast24Hours")
}
export default defineComponent({
  name: 'm_line-chart-temperature',
  setup() {
    const state = reactive({
      option : {
        title: {
          text: '过去24小时温度最大最小值',
          textStyle:{
            color: 'white',
            fontFamily: '宋体',
            fontSize: 10
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
              color: 'white',  //更改坐标轴文字颜色
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
              color: 'white',  //更改坐标轴文字颜色
              fontSize : 10     //更改坐标轴文字大小
            }
          }
        },
        legend: {
          orient:"vertical",
          backgroundColor: "transparent",
          inactiveColor: "white",
          textStyle:{
            color: "blue",
            fontSize: 10
          },
          left: '40%'
        },
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
      const chart = echarts.init(document.getElementById('lineChartTemperature'));
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
.lineChartTemperature{
  display: flex;
}
</style>