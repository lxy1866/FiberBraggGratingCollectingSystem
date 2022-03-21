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
        title: {
          text: '同姓氏数量统计',
          x: 'center'
        },
        legend: {
          type: 'scroll',
          orient: 'vertical',
          right: 10,
          top: 20,
          bottom: 20,
          // x: 'right',
          selected: data.selectedList
        },
        tooltip: {
          formatter: "{b} : {c}"
        },
        grid: {},
        xAxis: {type: 'category'},
        yAxis: {type: 'value'},
        series: data.showDataArr,
        animationEasing: "elasticOut",
      }

      //生成数据的方法
      function genData(count = 7) {
        const nameList = [
          'val1', 'val2', 'val3', 'val4', 'val5', 'val6', '水', '窦', '章', '云', '苏', '潘', '葛', '奚', '范', '臧', '计', '伏', '成', '戴', '谈', '宋', '茅', '庞', '熊', '纪', '舒', '屈', '项', '危'
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

      //模拟加载
      myChart.showLoading({
            text: 'loading',
            color: '#37A2DA',
            textColor: '#37A2DA',
            zlevel: 0,
          },
      );
      setTimeout(() => {
        myChart.hideLoading()
        myChart.setOption(option)
      }, 1000)
    })
  }
})
</script>

<style scoped>
</style>