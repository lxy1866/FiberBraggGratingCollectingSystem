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
        backgroundColor: '#0f1c2d',
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
            left: 'center',
            top: '3%',
            textStyle:{
              color: '#ffffff',
              fontFamily: '微软雅黑',
              fontSize: 14,
              fontWeight: 'normal'
            }
          },
          {
            top: '50%',
            left: 'center',
            text: '12%变形点2运行期间管道位移变化曲线图2',
            textStyle:{
              color: '#ffffff',
              fontFamily: '微软雅黑',
              fontSize: 14,
              fontWeight: 'normal'
            }
          }
        ],
        animation: false,
        tooltip: {
          trigger: 'axis',
          backgroundColor: 'rgba(0, 0, 0, 0.7)',
          borderColor: 'rgba(255, 255, 255, 0.2)',
          borderWidth: 1,
          textStyle: {
            color: '#fff',
            fontSize: 12
          },
          axisPointer: {
            type: 'line',
            lineStyle: {
              color: '#7A7A7A',
              width: 1
            }
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
            axisLine: {
              lineStyle: {
                color: '#4c5058'
              }
            },
            axisTick: {
              show: false
            },
            axisLabel: {
              color: '#fff',
              fontSize: 12,
              formatter: function(value) {
                // 假设日期格式为 "MM/DD/YY HH:mm"
                const datePart = value.split(' ')[0]; // 提取日期部分
                const parts = datePart.split('/');
                if (parts.length === 3) {
                  return `${parts[0]}/${parts[1]}`; // 返回 "MM/DD"
                }
                return value; // 如果格式不对，返回原值
              }
            }
          },
          {
            data: [],
            gridIndex: 1,
            axisLine: {
              lineStyle: {
                color: '#4c5058'
              }
            },
            axisTick: {
              show: false
            },
            axisLabel: {
              color: '#fff',
              fontSize: 12,
              formatter: function(value) {
                // 假设日期格式为 "MM/DD/YY HH:mm"
                const datePart = value.split(' ')[0]; // 提取日期部分
                const parts = datePart.split('/');
                if (parts.length === 3) {
                  return `${parts[0]}/${parts[1]}`; // 返回 "MM/DD"
                }
                return value; // 如果格式不对，返回原值
              }
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            axisLabel: {
              formatter: '{value} mm',
              color: '#fff',
              fontSize: 12
            },
            splitLine: {
              lineStyle: {
                color: 'rgba(255, 255, 255, 0.1)'
              }
            },
            axisLine: {
              lineStyle: {
                color: '#4c5058'
              }
            },
          },
          {
            type: 'value',
            gridIndex: 1,
            axisLabel: {
              formatter: '{value} mm',
              color: '#fff',
              fontSize: 12
            },
            splitLine: {
              lineStyle: {
                color: 'rgba(255, 255, 255, 0.1)'
              }
            },
            axisLine: {
              lineStyle: {
                color: '#4c5058'
              }
            },
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
            top: '15%',
            bottom: '55%',
            left: '12%',
            right: '4%'
          },
          {
            top: '63%',
            bottom: '8%',
            left: '12%',
            right: '4%'
          }
        ],
        series: [
          {
            data: [],
            type: 'line',
            smooth: true,
            symbol: 'circle',
            symbolSize: 5,
            lineStyle: {
              width: 2,
              color: '#5470c6'
            },
            itemStyle: {
              color: '#5470c6',
              borderWidth: 2
            },
            markPoint: {
              data: [
                { type: 'max', name: '最大值' },
                { type: 'min', name: '最小值' }
              ],
              label: {
                color: '#fff'
              }
            },
          },
          {
            data: [],
            type: 'line',
            smooth: true,
            symbol: 'circle',
            symbolSize: 5,
            xAxisIndex: 1,
            yAxisIndex: 1,
            lineStyle: {
              width: 2,
              color: '#91cc75'
            },
            itemStyle: {
              color: '#91cc75',
              borderWidth: 2
            },
            markPoint: {
              data: [
                { type: 'max', name: '最大值' },
                { type: 'min', name: '最小值' }
              ],
              label: {
                color: '#fff'
              }
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
.centerBottomChart {
  width: 100%;
  height: 100%;
  background-color: #0f1c2d;
}
</style>