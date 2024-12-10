<template>
  <div id="vibrationWave" class="vibrationWave"></div>
</template>
<script lang="js">
import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";

export default defineComponent({
  name: 'vibrationWave',
  setup: function () {
    // 生成模拟振动数据
    const generateVibrationData = () => {
      const data = [];
      for (let i = 0; i < 100; i++) {
        let value = Math.sin(i * 0.1) * 0.5;
        value += Math.sin(i * 0.5) * 0.2;
        value += (Math.random() - 0.5) * 0.3;
        value += Math.sin(i * 0.01) * 0.3;
        data.push(Number(value.toFixed(3)));
      }
      return data;
    };

    onMounted(() => {
      var chartDom = document.getElementById('vibrationWave');
      var myChart = echarts.init(chartDom);
      let time = new Date();
      
      function generateData() {
        const categoryData = [];
        const valueData = generateVibrationData();
        time = new Date();
        
        for (let i = 0; i < 100; i++) {
          if (i % 10 === 0) {
            categoryData.push(
              echarts.format.formatTime('hh:mm:ss', new Date(time.getTime() - (100 - i) * 1000))
            );
          } else {
            categoryData.push('');
          }
        }
        
        return {
          categoryData: categoryData,
          valueData: valueData
        };
      }

      let option = {
        title: {
          text: '海底管道振动波形',
          textStyle: {
            color: '#ffffff',
            fontFamily: '宋体',
          },
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        toolbox: {
          show: true,
          feature: {
            dataZoom: {
              yAxisIndex: false
            },
            saveAsImage: {
              pixelRatio: 2
            }
          },
          iconStyle: {
            color: '#fff'
          }
        },
        grid: {
          left: '5%',
          right: '5%',
          bottom: '15%',
          top: '15%'
        },
        xAxis: {
          type: 'category',
          data: [],
          axisLabel: {
            color: '#fff',
            rotate: 30,
            fontSize: 12,
            interval: 'auto'
          },
          splitLine: {
            show: false
          }
        },
        yAxis: {
          type: 'value',
          scale: true,
          axisLabel: {
            color: '#fff'
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: 'rgba(255,255,255,0.1)'
            }
          }
        },
        dataZoom: [
          {
            type: 'inside',
            start: 0,
            end: 100
          },
          {
            show: true,
            type: 'slider',
            top: '90%',
            start: 0,
            end: 100,
            textStyle: {
              color: '#fff'
            }
          }
        ],
        series: [
          {
            name: '振动值',
            type: 'line',
            data: [],
            sampling: 'lttb',
            symbol: 'none',
            lineStyle: {
              width: 2,
              color: '#00C851'
            },
            areaStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: 'rgba(0, 200, 81, 0.3)'
                },
                {
                  offset: 1,
                  color: 'rgba(0, 200, 81, 0)'
                }
              ])
            }
          }
        ],
        animation: false
      };

      // 初始化数据
      const updateData = () => {
        const data = generateData();
        option.xAxis.data = data.categoryData;
        option.series[0].data = data.valueData;
        myChart.setOption(option);
      };

      // 首次渲染
      updateData();

      // 定时更新数据
      setInterval(updateData, 2000);

      // 响应式处理
      window.addEventListener('resize', () => {
        myChart.resize();
      });
    });
  }
});
</script>

<style scoped>
.vibrationWave{
  display: flex;
}
</style>