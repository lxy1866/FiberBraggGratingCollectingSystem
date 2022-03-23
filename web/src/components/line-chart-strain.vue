<template>
  <div id="lineChartStrain"></div>
</template>

<script>

import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";

export default defineComponent({
  name: 'line-chart-strain',
  setup() {
    onMounted(() => {
      const chartDom = document.getElementById('lineChartStrain');
      const myChart = echarts.init(chartDom);

      //生成数据  //自定义初始显示选择个数
      const data = genData();

      const option = {
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        title: {
          text: '传感阵列的动态实时应变值',
          textStyle:{
            color: '#ffffff',
            fontFamily: '宋体',
          },
        },
        legend: {
          type: 'scroll',
          orient: 'vertical',
          right: 10,
          backgroundColor: "transparent",
          top: 20,
          bottom: 20,
          // x: 'right',
          selected: data.selectedList,
          textStyle: {
            color: "blue"
          }
        },
        tooltip: {
          formatter: "{b} : {c}"
        },
        grid: {},
        xAxis: {
          type: 'category',
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
          axisLabel: {
            show: true,
            textStyle: {
              color: '#ffffff',  //更改坐标轴文字颜色
              fontSize : 12      //更改坐标轴文字大小
            }
          },
        },
        series: data.showDataArr,
        animationEasing: "elasticOut",
      }

      //生成数据的方法
      function genData(count = 7) {
        const nameList = [
          'val1', 'val2', 'val3', 'val4', 'val5', 'val6', 'val7', 'val8', 'val9', 'val10', 'val11', 'val12', 'val13', 'val14', 'val15', 'val16', 'val17', 'val18', 'val19', 'val20', 'val21', 'val22', 'val23', 'val24', 'val25', 'val26', 'val27', 'val28', 'val29', 'val30'
        ];
        const legendData = [];
        const seriesData = [];
        const selectedList = {};
        let name;
        for (let i = 0; i < nameList.length; i++) {
          name = nameList[i]
          legendData.push(name)
          seriesData.push({
            name: name,
            value: Math.round(Math.random() * 10000)
          })
          selectedList[name] = i < count
        }

        const showDataArr = [];
        seriesData.forEach((value)=>{
          const showDataObj = {};
          showDataObj.type = 'bar'
          showDataObj.name = value.name
          showDataObj.data = [value]
          showDataObj.label = {show: true, position: "top",}
          showDataArr.push(showDataObj)
        })
        console.log(showDataArr)
        return {
          legendData: legendData,
          seriesData: seriesData,
          showDataArr: showDataArr,
          selectedList: selectedList
        }
      }
      myChart.setOption(option)
    })
  }
})
</script>

<style scoped>
</style>