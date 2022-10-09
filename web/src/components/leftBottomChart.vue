<template>
  <div id="leftBottomChart" class="leftBottomChart"></div>
</template>

<script lang="ts">
import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";
import axios from "axios";
interface Xvalue {
  date: string
}
interface Yvalue {
  date: number
}
function handleQueryAvgX(){
  return axios.get("/echarts/leftBottomX")
}
function handleQueryAvgY(){
  return axios.get("/echarts/leftBottomY")
}export default defineComponent({
  name: 'leftBottomChart',
  setup(){
    onMounted(async ()=>{
      const chartDom = document.getElementById('leftBottomChart')!;
      const myChart = echarts.init(chartDom);
      const option = {
        title: {
          text: '22%施工期间管道位移变化曲线图',
          textStyle:{
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
          show:'true',
          borderWidth:'0'
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
            magicType: { type: ['line', 'bar'] },
          }
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            formatter: '{value} mm'
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
                { type: 'max', name: 'Max' },
                { type: 'min', name: 'Min' }
              ]
            },
          }
        ]
      }
      await handleQueryAvgX().then(res =>{
        option.xAxis.data = res.data.content;
        console.log(res.data.content);
      })
      await handleQueryAvgY().then(res =>{
        option.series[0].data = res.data.content;
        console.log(res.data.content);
      })
      myChart.setOption(option)
    })
  }
})
</script>

<style scoped>
.leftTopChart {
  display: flex;
}
</style>