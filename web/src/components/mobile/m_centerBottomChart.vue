<template>
  <div id="m_lineChartDisplayment" class="lineChartDisplayment"></div>
</template>

<script lang="ts">
import {defineComponent, onMounted, toRefs, reactive} from "vue";
import * as echarts from "echarts";
import axios from "axios";
function getLastWeekFirstDay(){
  // 获取当前时间
  let currentDate = new Date()
  // 返回date是一周中的某一天
  let week = currentDate.getDay()
  // 返回date是一个月中的某一天
  // let month = currentDate.getDate()
  // 一天的毫秒数
  let millisecond = 1000 * 60 * 60 * 24
  // 减去的天数
  let minusDay = week !== 0 ? week - 1 : 6
  // 获得当前周的第一天
  let currentWeekDayOne = new Date(currentDate.getTime() - (millisecond * minusDay))
  // 上周最后一天即本周开始的前一天
  let priorWeekLastDay = new Date(currentWeekDayOne.getTime() - millisecond)
  // 上周的第一天
  let priorWeekFirstDay = new Date(priorWeekLastDay.getTime() - (millisecond * 6))
  return priorWeekFirstDay;
}
function handleQuery(){
  return axios.get("/home/middleBottomGet")
}
function generateData() {
  // let data = [];
  // for (let i = -200; i <= 200; i += 0.1) {
  //   data.push([i, func(i)]);
  // }
  // return data;
}
const myDate = new Date();
let year = myDate.getFullYear(); //获取完整的年份(4位,1970-???)
let lastMonth = myDate.getMonth();
if(lastMonth == 0){
  lastMonth=12;
  year=year-1;
}
function getLastMonthTotalDay(){
  const date = new Date();
  const year = date.getFullYear();
  const month = date.getMonth();
  const d = new Date(year, month, 0);
  return d.getDate();
}
export default defineComponent({
  name: 'line-chart-displayment',
  setup() {
    const colors = ['#5470C6', '#EE6666'];
    const state = reactive({
      option : {
        title: {
          text: year + '年' + lastMonth+ '月管道位移变化曲线图',
          textStyle:{
            color: '#ffffff',
            fontFamily: '宋体',
          }
        },
        animation: false,
        grid: {
          top: 70,
          left: 80,
          right: 40,
          bottom: 30
        },
        tooltip: {
          trigger: 'none',
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
          name:'日期',
          data: ['1', '2', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
          axisTick: {
            alignWithLabel: true
          },
          axisLine: {
            onZero: false,
            lineStyle: {
              color: colors[0]
            }
          },
          splitLine: {show: true},
          axisPointer: {
            label: {
              formatter: function (params: { value: string; seriesData: string | any[]; }) {
                return (
                    lastMonth+"月"+
                    params.value +"日"+
                    (params.seriesData.length ? '：' + params.seriesData[0].data+"mm" : '')
                );
              }
            }
          },
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            formatter: '{value} mm'
          },

        },
        legend: {
          orient:"vertical",
          backgroundColor: "transparent",
          inactiveColor: "white",
          textStyle:{
            color: "blue",
          },
          left: '60%',
          top:'7%'
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
            name: '定位环',
            type: 'line',
            data: [],
            smooth: true,
            markPoint: {
              data: [
                { type: 'max', name: 'Max' },
                { type: 'min', name: 'Min' }
              ]
            },
            markLine: {
              data: [{ type: 'average', name: 'Avg' }]
            },
          },
          {
            name: '结构管卡',
            type: 'line',
            data: [],
            smooth: true,
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
        ]
      }
    })
    onMounted(async ()=>{
      const {data} = await handleQuery();
      //console.log(data.content)
      for(let i = 0; i < 2; i++){
        for(let j = 0; j < getLastMonthTotalDay(); j++){
          data.content[i][j] = Number(data.content[i][j].toFixed(2))
        }
      }
      state.option.series[0].data = data.content[0]
      state.option.series[1].data = data.content[1]
      let xAxisArray:string[] = []
      for(let i = 0; i < getLastMonthTotalDay(); i++){
        xAxisArray[i] = (i+1).toString();
      }
      state.option.xAxis.data = xAxisArray
      type EChartsOption = echarts.EChartsOption;
      const chartDom = document.getElementById('m_lineChartDisplayment')!;
      const myChart = echarts.init(chartDom);

      //window.onresize = chart.resize;
      myChart.setOption(state.option);
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