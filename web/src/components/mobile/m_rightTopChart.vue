<template>
  <div id="line-3d-pipe" class="line-3d-pipe"></div>
</template>

<script>
import {defineComponent, onMounted} from "vue";
import * as echarts from 'echarts';
import 'echarts-gl';

export default defineComponent({
  name: 'line-3d-pipe',
  setup() {
    onMounted(()=>{
      const chartDom = document.getElementById('line-3d-pipe');
      const myChart = echarts.init(chartDom);
      let option;
      option = {
        tooltip: {},
        visualMap: {
          show: false,
          dimension: 2,
          min: -1,
          max: 1,
          inRange: {
            color: [
              '#313695',
              '#4575b4',
              '#74add1',
              '#abd9e9',
              '#e0f3f8',
              '#ffffbf',
              '#fee090',
              '#fdae61',
              '#f46d43',
              '#d73027',
              '#a50026'
            ]
          }
        },
        toolbox: {
          show: true,
          feature: {
            dataView: {readOnly: false},
            restore: {},
            saveAsImage: {}
          }
        },
        title: {
          text: '管道三维形状',
          textStyle: {
            color: '#ffffff',
            fontFamily: '宋体',
          },
        },
        xAxis3D: {
          axisLabel: {
            show: true,
            textStyle: {
              color: '#ffffff',  //更改坐标轴文字颜色
              fontSize: 12      //更改坐标轴文字大小
            }
          },
          max: 15,
          nameTextStyle: {
            color: '#ffffff'
          }
        },
        yAxis3D: {
          max:60,
          min:-60,
          axisLabel: {
            show: true,
            textStyle: {
              color: '#ffffff',  //更改坐标轴文字颜色
              fontSize: 12      //更改坐标轴文字大小
            }
          },
          nameTextStyle: {
            color: '#ffffff'
          }
        },
        zAxis3D: {
          max:60,
          min:-60,
          axisLabel: {
            show: true,
            textStyle: {
              color: '#ffffff',  //更改坐标轴文字颜色
              fontSize: 12      //更改坐标轴文字大小
            }
          },
          nameTextStyle: {
            color: '#ffffff'
          }
        },
        grid3D: {},
        series: [
          {
            type: 'surface',
            parametric: true,
            // shading: 'albedo',
            parametricEquation: {
              u: {
                min:0,
                max: 2*Math.PI,
                step: Math.PI / 20
              },
              v: {
                min: 0,
                max: 15,
                step: 15 / 20
              },
              x: function (u, v) {
                return v;
              },
              y: function (u, v) {
                return 10 * Math.sin(u);
              },
              z: function (u, v) {
                return 10 * Math.cos(u);
              }
            }
          }
        ]
      };

      window.onresize = myChart.resize;
      option && myChart.setOption(option);

    })
    return{
    }
  },
})
</script>
<style scoped>
.line-3d-pipe{
  display: flex;
}
</style>