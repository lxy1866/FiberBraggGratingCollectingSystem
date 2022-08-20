<template>
  <div id="leftTopTemp" class="leftTopTemp"></div>
</template>

<script lang="ts">
import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";
import axios from "axios";
import {message} from "ant-design-vue";
//多少天 多少个传感器 第一天所有的传感器都赋值
interface avgSensor {
  id:number,
  arraySn:number,
  sensorNodeName: string,
  avg: number,
  date: string
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
//原来是统计一个月
//let day = getLastMonthTotalDay() // 31

//const title = myDate.getFullYear() + '年' + (myDate.getMonth()) + '月管道位移数据';
const title = myDate.getFullYear() + '年' + (myDate.getMonth()) + '月管道位移数据';
function handleQueryLeftTopAttribute() {
  return axios.get("/home/leftTopAttributeGet")
}
function handleQueryAvg(){
  return axios.get("/home/leftTopTempDataGet")
}
export default defineComponent({
  name: 'leftTopTemp',
  setup() {
    onMounted( async ()=>{
      let leftTopAttributeContent = {
        eachArrayNum:'',
        arrayTotal:2
      };
      let leftTopDataGet;
      // await handleQueryLeftTopAttribute().then(res => {
      //   leftTopAttributeContent = res.data.content
      // })
      let array: avgSensor[][];
      array = await handleQueryAvg().then(res =>{
        leftTopDataGet = res.data.content
        console.log("res.data.content", leftTopDataGet)
        if(!res.data.success){
          message.info("左图后端数据被污染，请联系管理员")
        }
        return leftTopDataGet;
      })
      let day = 3
      if(array.length == getLastMonthTotalDay()*2){
        day = getLastMonthTotalDay() *2;
      }
      let sensorNum1 = Number(leftTopAttributeContent.eachArrayNum.split("_")[0])
      let sensorNum2 = Number(leftTopAttributeContent.eachArrayNum.split("_")[1])
      //let  sensorNum = sensorNum1 + sensorNum2
      let sensorNum = 12
      let data :number[][] = [];
      for(let i = 0; i < day; i++){
        data[i] = [];
        for(let j = 0; j < sensorNum; j++){
          data[i][j] = Number((array[i][j].avg).toFixed(2))
        }
      }
      const chartDom = document.getElementById('leftTopTemp')!;
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
            text: '管道位移数据(单位：mm)',
            textStyle: {
              color: '#ffffff',
              fontFamily: '宋体',
            },
          },
          calculable: true,
          grid: {
            top: 60,
            left:100,
            right:50,
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
                fontFamily: 'monospace',
              }
            },
          ],
        },
        options:[{}]
      }
      let timelineData = [];
      let optionsData = [];
      let yData = [];
      for(let j = 1; j <= 12; j++){
          yData.push("阵列1编号"+j)
      }
      for(let i = 0 ; i < 3; i++){
        //时间线
        timelineData.push((i+1).toString())
        optionsData.push(
                {
                  title:{
                    text: array[i][0].date.toString().split(" ")[0]+" 管道位移平均值"
                  },
                  series: [
                    {
                      data: data[i],
                    },
                  ]
                })
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
.leftTopTemp {
  display: flex;
}
</style>