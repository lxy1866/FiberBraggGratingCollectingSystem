<template>
  <div id="rightTopChart" class="rightTopChart"></div>
</template>

<script lang="ts">
import {defineComponent, onMounted, reactive, toRefs} from "vue";
import * as echarts from 'echarts';
import 'echarts-gl';
import axios from "axios";
function handleQuery(){
  return axios.get("/echarts/rightTop")
}
export default defineComponent({
  name: 'line-3d-pipe',
  setup() {
    const state = reactive({
      option: {
        tooltip: {
          axisPointer: {
            type: 'cross'
          }
        },
        xAxis3D: {
          axisLabel: {
            show: true,
            textStyle: {
              color: '#ffffff',  //更改坐标轴文字颜色
              fontSize: 12      //更改坐标轴文字大小
            },
            formatter: '{value} cm'
          },
          min:0,
          max: 1500,
          nameTextStyle: {
            color: '#ffffff'
          }
        },
        yAxis3D: {
          max: 1500,
          min: -1500,
          axisLabel: {
            show: true,
            textStyle: {
              color: '#ffffff',  //更改坐标轴文字颜色
              fontSize: 12      //更改坐标轴文字大小
            },
            formatter: '{value} mm'
          },

          nameTextStyle: {
            color: '#ffffff'
          }
        },
        zAxis3D: {
          max: 1500,
          min: -1500,
          axisLabel: {
            show: true,
            textStyle: {
              color: '#ffffff',  //更改坐标轴文字颜色
              fontSize: 12      //更改坐标轴文字大小
            },
            formatter: '{value} mm'
          },
          nameTextStyle: {
            color: '#ffffff'
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
          text: '12%结构管卡与定位环位置',
          textStyle: {
            color: '#ffffff',
            fontFamily: '宋体',
          },
        },
        grid3D: {},
        series: [
          {
            type: 'surface',
            parametric: true,
            label:{
              show:true,
              formatter:(params:any)=>{
                //console.log("params:", params)
                // return '定位环z轴: '+params.data[2]+"mm";
              },
              color:'white',
              position:'left',
              textStyle:{
                fontSize:15
              }
            },
            shading: 'realistic',
            // shading: 'albedo',
            parametricEquation: {
              u: {
                min: 0,
                max: 2 * Math.PI,
                step: Math.PI / 20
              },
              v: {
                min: 0,
                max: 1500,
                step: 1500 / 20
              },
              x: function (u: any, v: any) {
                return v;
              },
              y: function (u: number, v: any) {
                return 461 * Math.sin(u)+461;
              },
              z: function (u: number, v: any) {
                return 461 * Math.cos(u)+461;
              }
            },
            tooltip: {},
          },
          {
            type: 'surface',
            parametric: true,
            shading: 'realistic',
            // shading: 'albedo',
            parametricEquation: {
              u: {
                min: 0,
                max: 2 * Math.PI,
                step: Math.PI / 20
              },
              k:{},
              w:{
                min: 0,
                max: 2 * Math.PI,
                step: Math.PI / 20
              },
              x: function (w:number) {
                return 300+Math.sin(w);
              },
              y: function (u: number, w: number) {
                return (420+70*Math.cos(w))*Math.cos(u)+461;
              },
              z: function (u:number,w:number) {
                return (420+70*Math.cos(w))*Math.sin(u)+461;
              }
            },
            tooltip: {
              trigger: 'axis',
            },
          },
          {
            type: 'scatter3D',
            symbol: ['arrow','circle'],
            label:{
              show:true,
              formatter:(params:any)=>{
                return '结构管卡z轴: '+params.data[2]+"mm";
              },
              color:'white',
              position:'left',
              textStyle:{
                fontSize:15
              }
            },
            zlevel: 999,
            itemStyle:{
              color: 'white'
            },
            data:[[750,-480,461]],
            tooltip:{
              trigger:'item',
            }
          },
          {
            type: 'scatter3D',
            symbol: ['arrow','circle'],
            label:{
              show:true,
              formatter:(params:any)=>{
                return '定位环z轴: '+params.data[2]+"mm";
              },
              color:'white',
              position:'left',
              textStyle:{
                fontSize:15
              }
            },
            zlevel: 999,
            itemStyle:{
              color: 'white'
            },
            data:[
              [300,0,920]
            ],
            tooltip:{
              trigger:'item'
            }
          },
        ]
      }
    })
    onMounted(async ()=>{
      const chartDom = document.getElementById('rightTopChart')!;
      const myChart = echarts.init(chartDom);
      const {data} = await handleQuery();
      console.log(data);
      const array1_12 = data.content[0];
      const array2_10 = data.content[1];
      //定位环
      state.option.series[3].data = [[300,0,Number(array1_12.toFixed(2))]];
      //结构管卡
      state.option.series[2].data = [[750,0,Number(array2_10.toFixed(2))]];
      state.option && myChart.setOption(state.option);
    })
    return{
      ...toRefs(state)
    }
  },
})
</script>
<style scoped>
.rightTopChart{
  display: flex;
}
</style>