<template>
  <div id="leftTopChart" class="leftTopChart"></div>
</template>

<script lang="js">
import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";
import {Tool} from "@/util/tool";

export default defineComponent({
  name: 'leftTopChart',
  setup: function () {
    Date.prototype.format = function (fmt) {
      var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
      };
      if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
      }
      for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
      }
      return fmt;
    }
    const strainColors = {
      strain1: '#3d2939',
      strain2: '#000',
      strain3: '#403897',
      strain4: '#f93',
      strain5: '#bc002d',
      strain6: '#924fa2',
      strain7: '#044500',
      strain8: '#09947d',
      strain9: '#ef2b2d',
      strain10: '#82043c',
      strain11: '#442b1e',
      strain12: '#330a17',
      strain13: '#70247d',
    };
    const generateSimulatedData = (() => {
      let previousValues = {
        strain1: 15,
        strain2: -12,
        strain3: 8,
        strain4: -18,
        strain5: 22,
        strain6: -15,
        strain7: 10,
        strain8: -8,
        strain9: 25,
        strain10: -20,
        strain11: 12,
        strain12: -16,
        strain13: 18
      };

      return () => {
        const newValues = Object.entries(previousValues).map(([key, value]) => {
          const change = (Math.random() - 0.5) * 2;
          let newValue = value + change;
          newValue = Math.max(-30, Math.min(30, newValue));
          previousValues[key] = newValue;
          return Math.round(newValue * 100) / 100;
        });

        return newValues;
      };
    })();
    onMounted(() => {
      var chartDom = document.getElementById('leftTopChart');
      var myChart = echarts.init(chartDom);
      var option;
      let strain = [];
      option = {
        title: {
          text: '海底管道实时应变值(με)',
          textStyle: {
            color: '#ffffff',
            fontFamily: '宋体',
          },
        },
        xAxis: {
          max: 30,
          min: -30,
          axisLabel: {
            color: '#fff'
          }
        },
        yAxis: {
          type: 'category',
          data: ['strain1', 'strain2', 'strain3', 'strain4', 'strain5', 'strain6', 'strain7', 
                 'strain8', 'strain9', 'strain10', 'strain11', 'strain12', 'strain13'],
          inverse: true,
          animationDuration: 300,
          animationDurationUpdate: 300,
          axisLabel: {
            color: '#fff'
          }
        },
        grid: {
          top: 40,
          bottom: 40,
          left: 80,
          right: 40
        },
        toolbox: {
          show: true,
          feature: {
            dataView: {readOnly: false},
            magicType: {type: ['line', 'bar']},
            restore: {},
            saveAsImage: {}
          },
          iconStyle: {
            color: '#fff'
          }
        },
        series: [
          {
            name: '应变值',
            type: 'bar',
            data: generateSimulatedData(),
            label: {
              show: true,
              color: '#fff',
              position: 'right'
            }
          }
        ],
        animationDuration: 0,
        animationDurationUpdate: 1000,
        animationEasing: 'linear',
        animationEasingUpdate: 'linear',
        visualMap: {
          orient: 'horizontal',
          left: 'center',
          bottom: 0,
          min: -30,
          max: 30,
          text: ['高', '低'],
          textStyle: {
            color: 'white'
          },
          dimension: 0,
          inRange: {
            color: ['#ff4444', '#ffbb33', '#00C851']
          }
        }
      };
      setInterval(() => {
        option.series[0].data = generateSimulatedData();
        option.graphic = {
          elements: [{
            type: 'text',
            left: 'center',
            top: 'bottom',
            style: {
              text: new Date().format("yyyy-MM-dd hh:mm:ss"),
              fill: '#fff',
              fontSize: 14
            }
          }]
        };
        myChart.setOption(option);
      }, 1000);
      myChart.setOption(option);
      window.addEventListener('resize', () => {
        myChart.resize();
      });
    })
  }
})
</script>

<style scoped>
.leftTopChart {
  display: flex;
}
</style>