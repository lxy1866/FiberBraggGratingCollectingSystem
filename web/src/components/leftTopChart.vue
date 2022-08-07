<template>
  <div id="line-3d-pipe" class="line-3d-pipe"></div>
</template>

<script lang="ts">
import {defineComponent, onMounted, reactive, toRefs} from "vue";
import * as echarts from 'echarts';
import 'echarts-gl';
import axios from "axios";
function handleQuery(){
  return axios.get("/home/rightTopGetForLatestData")
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
          min:1500,
          max: -1500,
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
          max: 0,
          min: 1500,
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
          text: '基于光纤光栅阵列的桩基应变监测',
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
                console.log("params:", params)
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
              v: {
                min: 0,
                max: 1500,
                step: 1500 / 20
              },
              u: {
                min: 0,
                max: 2 * Math.PI,
                step: Math.PI / 20
              },
              x: function (u: any, v: any) {
                return 461 * Math.cos(u)+461;
              },
              y: function (u: number, v: any) {
                return 461 * Math.sin(u)+461;
              },
              z: function (u: number, v: any) {
                return v;

              }
            },
            tooltip: {},
          },
          // {
          //   type: 'surface',
          //   parametric: true,
          //   shading: 'realistic',
          //   // shading: 'albedo',
          //   parametricEquation: {
          //     u: {
          //       min: 0,
          //       max: 2 * Math.PI,
          //       step: Math.PI / 20
          //     },
          //     k:{},
          //     w:{
          //       min: 0,
          //       max: 2 * Math.PI,
          //       step: Math.PI / 20
          //     },
          //     x: function (u:number,w:number) {
          //       return (420+70*Math.cos(w))*Math.sin(u)+461;
          //     },
          //     y: function (u: number, w: number) {
          //       return 300+Math.sin(w);
          //     },
          //     z: function (u:number,w:number) {
          //       return (420+70*Math.cos(w))*Math.cos(u)+461;
          //     }
          //   },
          //   tooltip: {
          //     trigger: 'axis',
          //   },
          // },
          {
            type: 'scatter3D',
            symbol: ['arrow','circle'],
            label:{
              show:true,
              formatter:(params:any)=>{
                return "z轴: "+params.data[2]+"mm应变值为299uξ";
              },
              color:'red',
              position:'left',
              textStyle:{
                fontSize:15
              }
            },
            zlevel: 999,
            itemStyle:{
              color: 'red'
            },
            data:[[1140,88,731]],
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
                return 'z轴: '+params.data[2]+"mm应变值为80uξ";
              },
              color:'orange',
              position:'left',
              textStyle:{
                fontSize:15
              }
            },
            zlevel: 999,
            itemStyle:{
              color: 'orange'
            },
            data:[
              [300,0,920]
            ],
            tooltip:{
              trigger:'item'
            }
          },
          {
            type: 'scatter3D',
            symbol: ['arrow','circle'],
            label:{
              show:true,
              formatter:(params:any)=>{
                return 'z轴: '+params.data[2]+"mm应变值为33uξ";
              },
              color:'yellow',
              position:'left',
              textStyle:{
                fontSize:15
              }
            },
            zlevel: 999,
            itemStyle:{
              color: 'yellow'
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
                return 'z轴: '+params.data[2]+"mm应变值为1223uξ";
              },
              color:'green',
              position:'left',
              textStyle:{
                fontSize:15
              }
            },
            zlevel: 999,
            itemStyle:{
              color: 'green'
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
                return 'z轴: '+params.data[2]+"mm应变值为600uξ";
              },
              color:'purple',
              position:'left',
              textStyle:{
                fontSize:15
              }
            },
            zlevel: 999,
            itemStyle:{
              color: 'purple'
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
                return 'z轴: '+params.data[2]+"mm应变值为278uξ";
              },
              color:'cyan',
              position:'left',
              textStyle:{
                fontSize:15
              }
            },
            zlevel: 999,
            itemStyle:{
              color: 'cyan'
            },
            data:[[1140,88,731]],
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
                return 'z轴: '+params.data[2]+"mm应变值为298uξ";
              },
              color:'#577889',
              position:'left',
              textStyle:{
                fontSize:15
              }
            },
            zlevel: 999,
            itemStyle:{
              color: '#577889'
            },
            data:[
              [300,0,920]
            ],
            tooltip:{
              trigger:'item'
            }
          },
          {
            type: 'scatter3D',
            symbol: ['arrow','circle'],
            label:{
              show:true,
              formatter:(params:any)=>{
                return 'z轴: '+params.data[2]+"mm应变值为450uξ";
              },
              color:'#080888',
              position:'left',
              textStyle:{
                fontSize:15
              }
            },
            zlevel: 999,
            itemStyle:{
              color: '#080888'
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
                return 'z轴: '+params.data[2]+"mm应变值为189uξ";
              },
              color:'black',
              position:'left',
              textStyle:{
                fontSize:15
              }
            },
            zlevel: 999,
            itemStyle:{
              color: 'black'
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
                return 'z轴: '+params.data[2]+"mm应变值为798uξ";
              },
              color:'#ffdddd',
              position:'left',
              textStyle:{
                fontSize:15
              }
            },
            zlevel: 999,
            itemStyle:{
              color: '#ffdddd'
            },
            data:[[750,-480,461]],
            tooltip:{
              trigger:'item',
            }
          },
        ]
      }
    })
    onMounted(async ()=>{
      const chartDom = document.getElementById('line-3d-pipe')!;
      const myChart = echarts.init(chartDom);
      const {data} = await handleQuery();
      const array1_12 = data.content[0];
      const array2_10 = data.content[1];
      // //定位环
      // state.option.series[3].data = [[300,0,Number(array1_12.toFixed(2))]];
      // //结构管卡
      // state.option.series[2].data = [[750,0,Number(array2_10.toFixed(2))]];


      state.option.series[1].data = [[916, 533, 1350]]
      state.option.series[2].data = [[786, 135, 1200]]
      state.option.series[3].data = [[922, 461, 1050]]
      state.option.series[4].data = [[786, 135, 900]]
      state.option.series[5].data = [[916, 388, 750]]
      state.option.series[6].data = [[899, 318, 675]]
      state.option.series[7].data = [[833, 190, 375]]
      state.option.series[8].data = [[833, 190, 525]]
      state.option.series[9].data = [[899, 318, 225]]
      state.option.series[10].data = [[899, 603, 75]]

      state.option && myChart.setOption(state.option);
    })
    return{
      ...toRefs(state)
    }
  },
})
</script>
<style scoped>
.line-3d-pipe{
  display: flex;
}
</style>