<template>
  <div id="maxOffset" class="maxOffset"></div>
</template>

<script lang="js">
import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";

export default defineComponent({
  name: 'maxOffset',
  setup: function () {
    onMounted(() => {
      var chartDom = document.getElementById('maxOffset');
      var myChart = echarts.init(chartDom);
      var option;
      option = {
        title: {
          text: '随桥电缆每周最大位移变化',
          textStyle: {
            color: '#ffffff',
            fontFamily: '宋体',
          },
        },
        grid: {
          top: 45,
          bottom: 40,
          left: 60,
          right: 10
        },
        xAxis: {
          max: 'category',
          data:[],
          name: '周数（周）',
          nameLocation: 'center',
          nameGap: 25
        },
        yAxis: {
          type: 'value',
          name: '最大位移（mm）',
          nameLocation: 'center',
          nameGap: 40
        },
        toolbox: {
          show: true,
          feature: {
            dataView: {readOnly: false},
            magicType: {type: ['line', 'bar']},
            restore: {},
            saveAsImage: {}
          }
        },
        series: [
          {
            data:[],
            type: 'line'
          }
        ],
      };
      for(let i = 1; i <= 20; i++){
        option.xAxis.data.push(i)
      }
      option.series[0].data = [2.852, 1.366, 1.858, 1.261, 1.764,
        1.069, 1.483, 1.852, 1.707, 1.810,
        1.509, 1.774, 0.941, 1.089, 1.119,
        1.221, 2.352, 2.462, 1.079, 1.920]
      option && myChart.setOption(option);
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    })
  }
})
</script>

<style scoped>
.maxOffset {
  display: flex;
}
</style>