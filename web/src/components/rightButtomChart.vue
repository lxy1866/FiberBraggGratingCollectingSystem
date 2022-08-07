<template>
  <div id="rightButtomChart" class="RightButtomChart"></div>
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
  return axios.get("/home/leftTopDataGet")
}
export default defineComponent({
  name: 'RightButtomChart',
  setup() {
    onMounted( async ()=>{
      let leftTopAttributeContent = {
        eachArrayNum:'',
        arrayTotal:2
      };
      let leftTopDataGet;
      await handleQueryLeftTopAttribute().then(res => {
        leftTopAttributeContent = res.data.content
      })
      let array: avgSensor[][];
      array = await handleQueryAvg().then(res =>{
        leftTopDataGet = res.data.content
        console.log("res.data.content", leftTopDataGet)
        if(!res.data.success){
          message.info("右图后端数据被污染，请联系管理员")
        }
        return leftTopDataGet;
      })
      let day = 14
      if(array.length == getLastMonthTotalDay()*2){
        day = getLastMonthTotalDay() *2;
      }
      let sensorNum1 = Number(leftTopAttributeContent.eachArrayNum.split("_")[0])
      let sensorNum2 = Number(leftTopAttributeContent.eachArrayNum.split("_")[1])
      let  sensorNum = sensorNum1 + sensorNum2
      let data :number[][] = [];
      for(let i = 0; i < day; i++){
        data[i] = [];
        for(let j = 0; j < sensorNum; j++){
          data[i][j] = Number((array[i][j].avg).toFixed(2))
        }
      }
      const chartDom = document.getElementById('rightButtomChart')!;
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
            text: '基于MEMS传感阵列的岸坡位移变形监测 y-z曲线',
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
          xAxis: [
            {
              name:'y轴间隔0.5米',
              type: 'category',
              axisLabel: {interval: 0},
              data: [''],
              splitLine: {show: false}
            }
          ],
          yAxis: [
            {
              type: 'value',
              name: 'z轴',
              nameLocation:'end',
            }
          ],
          // visualMap:{
          //   orient: 'vertical',
          //   inRange: {
          //     color: ['#ef2b2d','#82043c', '#442b1e', '#330a17','#70247d',
          //       '#b22234','#ef7878', '#559069', '#a45060', '#670044','#565965',
          //       '#340569', '#586042','#619405', '#945006','#586473', '#124995',
          //       '#759594', '#c56600','#4899e7', '#678900', '#895697',]
          //   },
          //   min: 0,
          //   max: 30,
          // },
          series: [
            {
              name: 'x-z轴',
              type: 'line',
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
      //day应该按照后端的数据长度来设置
      if(day == 14){
        //day是上一周天数的2倍
        for(let i = 0 ; i < day; i++){
          //时间线
          timelineData.push((i+1).toString())
          if(i % 2 == 0){
            //一周的话就插入7次
            optionsData.push(
                {
                  title:{
                    text: year + '年' + (((getLastWeekFirstDay().getDate()+(Math.floor(i/2))) <= getLastMonthTotalDay()) ? (getLastWeekFirstDay().getMonth()+1):(getLastWeekFirstDay().getMonth()+2))+ '月' +
                        (((getLastWeekFirstDay().getDate()+(Math.floor(i/2)))<= getLastMonthTotalDay()) ? (getLastWeekFirstDay().getDate()+(Math.floor(i/2))):(getLastWeekFirstDay().getDate()+(Math.floor(i/2)) - getLastMonthTotalDay()))+'日'+
                        ("星期"+"一二三四五六日".charAt(getLastWeekFirstDay().getDay()+(Math.floor(i/2))-1))+
                        '上午不同深度的应力应变值'
                  },
                  series: [
                    {
                      data: data[i],
                    },
                  ]
                }
            )
          }else {
            optionsData.push(
                {
                  // title:{
                  //   text: year + '年' + (((getLastWeekFirstDay().getDate()+(Math.floor(i/2))) <= getLastMonthTotalDay()) ? (getLastWeekFirstDay().getMonth()+1):(getLastWeekFirstDay().getMonth()+2))+ '月' +
                  //       (((getLastWeekFirstDay().getDate()+(Math.floor(i/2)))<= getLastMonthTotalDay()) ? (getLastWeekFirstDay().getDate()+(Math.floor(i/2))):(getLastWeekFirstDay().getDate()+(Math.floor(i/2)) - getLastMonthTotalDay()))+'日'+
                  //       ("星期"+"一二三四五六日".charAt(getLastWeekFirstDay().getDay()+(Math.floor(i/2))-1))+
                  //       '下午不同深度的应力应变值'
                  // },
                  series: [
                    {
                      data: data[i],
                    },
                  ]
                }
            )
          }
        }
      }else {
        //day是上个月的天数的2倍
        for(let i = 0 ; i < day; i++){
          //时间线
          timelineData.push((i+1).toString())
          if(i % 2 == 0){
            //一周的话就插入7次
            optionsData.push(
                {
                  // title:{
                  //   text: year + '年' + lastMonth + '月'+ (Math.floor(i/2)+1 )  +'日'+
                  //       '上午不同深度的应力应变值'
                  // },
                  series: [
                    {
                      data: data[i],
                    },
                  ]
                }
            )
          }else {
            optionsData.push(
                {
                  // title:{
                  //   text: year + '年' + lastMonth + '月' + (Math.floor(i/2)+1)  +'日'+
                  //       '下午不同深度的应力应变值'
                  // },
                  series: [
                    {
                      data: data[i],
                    },
                  ]
                }
            )
          }
        }
      }

      let xData = [];
      // for(let i = 1; i <= leftTopAttributeContent.arrayTotal; i++){
      //   let nums = Number(leftTopAttributeContent.eachArrayNum.split("_")[i-1])
      //   for(let j = 1; j <= nums; j++){
      //     yData.push("阵列"+i+"编号"+j)
      //     yData.push("阵列"+i+"编号"+j)
      //   }
      // }
      for(let i = 1; i <= 5; i = i + 0.5){
          xData.push(i+"米")
      }

      option.baseOption.timeline.data = timelineData;
      option.baseOption.xAxis[0].data = xData
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
.RightButtomChart {
  display: flex;
}
</style>