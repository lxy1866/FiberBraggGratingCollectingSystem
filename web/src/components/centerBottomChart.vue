<template>
  <div id="centerBottomChart" class="centerBottomChart"></div>
</template>

<script lang="ts">
import {defineComponent, onMounted, toRefs, reactive} from "vue";
import * as echarts from "echarts";
import axios from "axios";
interface Xvalue {
  date: string
}
interface Yvalue {
  date: number
}
function handleQueryAvgX1(){
  return axios.get("/echarts/centerBottomX1")
}
function handleQueryAvgX2(){
  return axios.get("/echarts/centerBottomX2")
}
function handleQueryAvgY1(){
  return axios.get("/echarts/centerBottomY1")
}
function handleQueryAvgY2(){
  return axios.get("/echarts/centerBottomY2")
}
export default defineComponent({
  name: 'centerBottomChart',
  setup(){
    onMounted(async ()=>{
      const chartDom = document.getElementById('centerBottomChart')!;
      const myChart = echarts.init(chartDom);
      const option = {
        visualMap: [
          {
            show: false,
            type: 'continuous',
            seriesIndex: 0,
            min: 0,
            max: 400
          },
          {
            show: false,
            type: 'continuous',
            seriesIndex: 1,
            dimension: 0,
            min: 0,
            max: 400
          }
        ],
        title: [
          {
            text: '12%变形点1运行期间管道位移变化曲线图1',
            textStyle:{
              color: '#ffffff',
              fontFamily: '宋体',
            }
          },
          {
            top: '45%',
            text: '12%变形点2运行期间管道位移变化曲线图2',
            textStyle:{
              color: '#ffffff',
              fontFamily: '宋体',
            }
          }
        ],
        animation: false,
        // grid: {
        //   left: '2%',
        //   right: '2%',
        //   bottom: '10%',
        //   containLabel: true,
        //   show:'true',
        //   borderWidth:'0'
        // },
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
            magicType: { type: ['line', 'bar'] },
          }
        },
        xAxis: [
          {
            data: [],
          },
          {
            data: [],
            gridIndex: 1
          }
        ],
        yAxis: [
          {
            type: 'value',
            axisLabel: {
              formatter: '{value} mm'
            },
          },
          {
            type: 'value',
            axisLabel: {
              formatter: '{value} mm'
            },
            gridIndex: 1
          }
        ],
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
        grid: [
          {
            bottom: '60%'
          },
          {
            top: '55%'
          }
        ],
        series: [
          {
            data: [],
            type: 'line',
            smooth: true,
            markPoint: {
              data: [
                { type: 'max', name: 'Max' },
                { type: 'min', name: 'Min' }
              ]
            },
          },
          {
            data: [],
            type: 'line',
            smooth: true,
            xAxisIndex: 1,
            yAxisIndex: 1,
            markPoint: {
              data: [
                { type: 'max', name: 'Max' },
                { type: 'min', name: 'Min' }
              ]
            },
          }
        ]
      }
      await handleQueryAvgX1().then(res =>{
        option.xAxis[0].data = res.data.content;
        console.log(res.data.content);
      })
      await handleQueryAvgX2().then(res =>{
        option.xAxis[1].data = res.data.content;
        console.log(res.data.content);
      })
      await handleQueryAvgY1().then(res =>{
        option.series[0].data = res.data.content;
        console.log(res.data.content);
      })
      await handleQueryAvgY2().then(res =>{
        option.series[1].data = res.data.content;
        console.log(res.data.content);
      })
      myChart.setOption(option)
    })
  }
})
</script>
<style scoped>
.lineChartDisplayment{
  display: flex;
}
</style>