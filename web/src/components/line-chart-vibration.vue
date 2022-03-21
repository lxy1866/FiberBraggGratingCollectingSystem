<template>
  <div id="lineChartVibration"></div>
</template>

<script>
import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";
export default defineComponent({
  name: 'line-chart-vibration',
  setup() {
    onMounted(()=>{
      const chartDom = document.getElementById('lineChartVibration');
      const myChart = echarts.init(chartDom);
      let option;
      function randomData() {
        now = new Date(+now - oneDay);
        value = value + Math.random() * 21 - 10;
        return {
          name: now.toString(),
          value: [
            [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
            Math.round(value)
          ]
        };
      }
      let data = [];
      let now = new Date();
      let oneDay = 24 * 3600 * 1000;
      let value = Math.random() * 1000;
      for (let i = 0; i < 1000; i++) {
        data.push(randomData());
      }
      option = {
        title: {
          text: '动态实时振动值',
          textStyle:{
            color: '#ffffff',
            fontFamily: '宋体',
          }
        },
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ['line', 'bar'] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        tooltip: {
          trigger: 'axis',
          formatter: function (params) {
            params = params[0];
            const date = new Date(params.name);
            return (
                date.getDate() +
                '/' +
                (date.getMonth() + 1) +
                '/' +
                date.getFullYear() +
                ' : ' +
                params.value[1]
            );
          },
          axisPointer: {
            animation: true
          }
        },
        xAxis: {
          type: 'time',
          splitLine: {
            show: false
          },
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
          boundaryGap: [0, '100%'],
          axisLine:{
            show: true
          },
          splitLine: {
            show: false
          },
          axisLabel: {
            show: true,
            textStyle: {
              color: '#ffffff',  //更改坐标轴文字颜色
              fontSize : 12      //更改坐标轴文字大小
            }
          },
        },
        series: [
          {
            name: 'Fake Data',
            type: 'line',
            showSymbol: false,
            data: data
          }
        ]
      };
      setInterval(function () {
        for (let i = 0; i < 5; i++) {
          data.shift();
          data.push(randomData());
        }
        myChart.setOption({
          series: [
            {
              data: data
            }
          ]
        });
      }, 1000);

      option && myChart.setOption(option);
    });
    return{
    }
  },
})
</script>

<style scoped>
</style>