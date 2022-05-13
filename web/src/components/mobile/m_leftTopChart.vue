<template>
  <div id="barPipeDisplayment" class="barPipeDisplayment"></div>
</template>

<script lang="ts">
import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";
//多少天 多少个传感器 第一天所有的传感器都赋值
const data :any[] = [];
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
let day = getLastMonthTotalDay() // 31

let sensorNum = 26
for(let i = 0; i < day; i++){
  data[i]  = [];
  for(let j = 0; j < sensorNum; j++){
    data[i].push(Math.floor(Math.random()*1000+500))
  }
}
const title = myDate.getFullYear() + '年' + (myDate.getMonth()) + '月管道位移数据';
export default defineComponent({
  name: 'line-pipe-displayment',
  setup() {
    const strainColors: Record<string, string> = {
      0: '#3d2939',
      1: '#000',
      2: '#403897',
      3: '#f93',
      4: '#bc002d',
      5: '#924fa2',
      6: '#044500',
      7: '#09947d',
      8: '#ef2b2d',
      9: '#82043c',
      10: '#442b1e',
      11: '#330a17',
      12: '#70247d',
      13: '#b22234',
      14: '#ef7878',
      15: '#559069',
      16: '#a45060',
      17: '#670044',
      18: '#565965',
      19: '#340569',
      20: '#586042',
      21: '#619405',
      22: '#945006',
      23: '#586473',
      24: '#124995',
      25: '#759594',
      27: '#c56600',
      28: '#4899e7',
      29: '#678900',
      30: '#895697',
    };
    onMounted( async ()=>{
      const chartDom = document.getElementById('barPipeDisplayment')!;
      const myChart = echarts.init(chartDom);
      const option={
        baseOption: {
          timeline: {
            axisType: 'category',
            // realtime: false,
            // loop: false,
            autoPlay: true,
            // currentIndex: 2,
            playInterval: 1000,
            // controlStyle: {
            //     position: 'left'
            // },
            data: [''],
          },
          toolbox: {
            show: true,
            feature: {
              dataView: {readOnly: false},
              restore: {},
              magicType: { type: ['line', 'bar'] },
              saveAsImage: {}
            }
          },
          title: {
            text: '管道位移数据',
            textStyle: {
              color: '#ffffff',
              fontFamily: '宋体',
            },
          },
          calculable: true,
          grid: {
            top: 60,
            left:50,
            right:60,
            bottom: 70,
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                type: 'shadow',
                label: {
                  show: true
                }
              }
            }
          },
          yAxis: [
            {
              name:'管道传感器编号',
              type: 'category',
              axisLabel: {interval: 0},
              data: [''],
              splitLine: {show: false}
            }
          ],
          xAxis: [
            {
              type: 'value',
              name: '位移值',
              nameLocation:'end',
            }
          ],
          visualMap:{
            orient: 'vertical',
            inRange: {
              color: ['#ef2b2d','#82043c', '#442b1e', '#330a17','#70247d',
                '#b22234','#ef7878', '#559069', '#a45060', '#670044','#565965',
                '#340569', '#586042','#619405', '#945006','#586473', '#124995',
                '#759594', '#c56600','#4899e7', '#678900', '#895697',]
            },
            min: 0,
            max: 30,
          },
          series: [
            {
              name: '位移值',
              type: 'bar',
              label: {
                show: true,
                precision: 0,
                position: 'right',
                valueAnimation: true,
                fontFamily: 'monospace'
              }
            },
          ],
        },
        options:[{}]
      }
      let timelineData = [];
      let optionsData = [];
      for(let i = 1 ; i <= day; i++){
        timelineData.push(i.toString())
        optionsData.push(
            {
              title:{
                text: year + '年' + lastMonth + '月' + i +'日管道位移数据'
              },
              series: [
                {
                  data: data[i],
                },
              ]
            }
        )
      }
      let yData = [];
      for(let j = 1; j < sensorNum; j++){
        yData.push(j.toString())
      }
      option.baseOption.timeline.data = timelineData;
      option.baseOption.yAxis[0].data = yData
      option.options = optionsData;
      //@ts-ignore
      myChart.setOption(option)
    });
    return {
    }
  },
})
</script>

<style scoped>
.barPipeDisplayment {
  display: flex;
  width: 100%;
  height: 100%;
}
</style>