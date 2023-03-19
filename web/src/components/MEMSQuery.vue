<template>
  <p style="color: #1890ff; font-family: 'Adobe 宋体 Std L';">请选择你要查询的日期范围：</p>
  <n-space vertical>
    <n-date-picker
        v-model:value="range2"
        type="datetimerange"
    />
    <div style="color: #1890ff; font-family: 'Adobe 宋体 Std L';">请选择要查询的传感器类型：</div>
    <n-select v-model:value="selected" :options="options"></n-select>
  </n-space>
  <div id="chart" class="chart" style="height: 500px;margin-top: 20px" ></div>
</template>
<script lang="ts">
import {defineComponent, onMounted, reactive, ref, watch} from 'vue';
import axios from "axios";
import * as echarts from 'echarts';
import {message} from "ant-design-vue";
let data = {};
let range2 = ref();
let startTime = ref();
let endTime = ref();
let dataRef = ref()

export default defineComponent({
  components:{
  },
  setup () {

    const options = [
      {label: "25", value: '25'},
      {label: "26", value: '26'},
      {label: "27", value: '27'},
      {label: "28", value: '28'},
      {label: "29", value: '29'},
      {label: "30", value: '30'},
      {label: "31", value: '31'},
      {label: "32", value: '32'},
      {label: "33", value: '33'},
      {label: "34", value: '34'},
      {label: "35", value: '35'},
      {label: "36", value: '36'},
      {label: "37", value: '37'},
      {label: "38", value: '38'},
      {label: "39", value: '39'},
    ]
    let selected = ref()

    var options25 = {
      title: {
        text: '0x25'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var options26 = {
      title: {
        text: '0x26'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var options27 = {
      title: {
        text: '0x27'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var options28 = {
      title: {
        text: '0x28'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var options29 = {
      title: {
        text: '0x29'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var options30 = {
      title: {
        text: '0x30'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var options31 = {
      title: {
        text: '0x31'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var options32 = {
      title: {
        text: '0x32'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var options33 = {
      title: {
        text: '0x33'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var options34 = {
      title: {
        text: '0x34'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var options35 = {
      title: {
        text: '0x35'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var options36 = {
      title: {
        text: '0x36'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var options37 = {
      title: {
        text: '0x37'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var options38 = {
      title: {
        text: '0x38'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var options39 = {
      title: {
        text: '0x39'
      },
      tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(32, 33, 36,.7)',
        borderColor: 'rgba(32, 33, 36,0.20)',
        borderWidth: 1,
        textStyle: {
          color: '#fff',
          fontSize: '12'
        },
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          name: '加速度x',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '加速度y',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '加速度z',
          smooth: true,
          type: 'line'
        }
      ]
    }



    /**
     * 数据查询
     * @param params
     */
    onMounted(()=> {
      const chartDom = document.getElementById('chart');
      const myChart = echarts.init(chartDom);
      watch([range2,selected], async (newValue, oldValue) => {

          await axios.get("/historyData/queryMEMS", {
            params: {
              startTime: range2.value[0],
              endTime: range2.value[1]
            }
          }).then(res =>{
            options25.xAxis.data=res.data.content["time25"];
            options26.xAxis.data=res.data.content["time26"];
            options27.xAxis.data=res.data.content["time27"];
            options28.xAxis.data=res.data.content["time28"];
            options29.xAxis.data=res.data.content["time29"];
            options30.xAxis.data=res.data.content["time30"];
            options31.xAxis.data=res.data.content["time31"];
            options32.xAxis.data=res.data.content["time32"];
            options33.xAxis.data=res.data.content["time33"];
            options34.xAxis.data=res.data.content["time34"];
            options35.xAxis.data=res.data.content["time35"];
            options36.xAxis.data=res.data.content["time36"];
            options37.xAxis.data=res.data.content["time37"];
            options38.xAxis.data=res.data.content["time38"];
            options39.xAxis.data=res.data.content["time39"];
            options25.series[0].data=res.data.content["x25"];
            options25.series[1].data=res.data.content["y25"];
            options25.series[2].data=res.data.content["z25"];
            options26.series[0].data=res.data.content["x26"];
            options26.series[1].data=res.data.content["y26"];
            options26.series[2].data=res.data.content["z26"];
            options27.series[0].data=res.data.content["x27"];
            options27.series[1].data=res.data.content["y27"];
            options27.series[2].data=res.data.content["z27"];
            options28.series[0].data=res.data.content["x28"];
            options28.series[1].data=res.data.content["y28"];
            options28.series[2].data=res.data.content["z28"];
            options29.series[0].data=res.data.content["x29"];
            options29.series[1].data=res.data.content["y29"];
            options29.series[2].data=res.data.content["z29"];
            options30.series[0].data=res.data.content["x30"];
            options30.series[1].data=res.data.content["y30"];
            options30.series[2].data=res.data.content["z30"];
            options31.series[0].data=res.data.content["x31"];
            options31.series[1].data=res.data.content["y31"];
            options31.series[2].data=res.data.content["z31"];
            options32.series[0].data=res.data.content["x32"];
            options32.series[1].data=res.data.content["y32"];
            options32.series[2].data=res.data.content["z32"];
            options33.series[0].data=res.data.content["x33"];
            options33.series[1].data=res.data.content["y33"];
            options33.series[2].data=res.data.content["z33"];
            options34.series[0].data=res.data.content["x34"];
            options34.series[1].data=res.data.content["y34"];
            options34.series[2].data=res.data.content["z34"];
            options35.series[0].data=res.data.content["x35"];
            options35.series[1].data=res.data.content["y35"];
            options35.series[2].data=res.data.content["z35"];
            options36.series[0].data=res.data.content["x36"];
            options36.series[1].data=res.data.content["y36"];
            options36.series[2].data=res.data.content["z36"];
            options37.series[0].data=res.data.content["x37"];
            options37.series[1].data=res.data.content["y37"];
            options37.series[2].data=res.data.content["z37"];
            options38.series[0].data=res.data.content["x38"];
            options28.series[1].data=res.data.content["y38"];
            options38.series[2].data=res.data.content["z38"];
            options39.series[0].data=res.data.content["x39"];
            options39.series[1].data=res.data.content["y39"];
            options39.series[2].data=res.data.content["z39"];
          })

          if(selected.value=='25'){
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options25)
          }else if(selected.value=='26'){
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options26)
          }else if(selected.value=='27'){
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options27)
          }else if(selected.value=='28'){
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options28)
          }else if(selected.value=='29'){
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options29)
          }else if(selected.value=='30'){
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options30)
          }else if(selected.value=='31'){
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options31)
          }else if(selected.value=='32'){
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options32)
          }else if(selected.value=='33'){
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options33)
          }else if(selected.value=='34'){
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options34)
          }else if(selected.value=='35'){
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options35)
          }else if(selected.value=='36'){
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options36)
          }else if(selected.value=='37'){
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options37)
          }else if(selected.value=='38'){
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options38)
          } else{
            if(myChart != null){
              myChart.clear()
            }
            myChart.setOption(options39)
          }


      });
    })
    return {
      selected,
      options,
      data: dataRef,
      range2,
      startTime,
      endTime,
    }
  }
})
</script>