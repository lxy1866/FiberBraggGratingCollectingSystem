<template>
  <div id="leftTopChart" class="leftTopChart"></div>
</template>

<script lang="ts">
import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";
import axios from "axios";
import {message} from "ant-design-vue";
interface Xvalue {
  date: string
}
interface Yvalue {
  date: number
}
function handleQueryAvgX(){
  return axios.get("/echarts/leftTopX")
}
function handleQueryAvgY(){
  return axios.get("/echarts/leftTopY")
}
export default defineComponent({
  name: 'leftTopChart',
  setup(){
    onMounted(async ()=>{
      const chartDom = document.getElementById('leftTopChart')!;
      const myChart = echarts.init(chartDom);
      const option = {
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [],
            type: 'line',
            smooth: true
          }
        ]
      }
      handleQueryAvgX().then(res =>{
        option.xAxis.data = res.data.content;
        //console.log(res.data.content);
      })
      handleQueryAvgY().then(res =>{
        option.series[0].data = res.data.content;
        //console.log(res.data.content);
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