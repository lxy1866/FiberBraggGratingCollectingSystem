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
import * as echarts from 'echarts';
import axios from "axios";
import {Toast} from "vant";
import {message} from "ant-design-vue";
let data = {};
let range2 = ref();
let startTime = ref();
let endTime = ref();
let dataRef = ref();

export default defineComponent({
  components:{
  },
  setup () {
    const options = [
        {label: "温度传感器", value: 't'},
        {label: "振动传感器", value: 'v'},
        {label: "应变传感器", value: 's'},
    ]
    let selected = ref()

    const handleQueryList = async (params: any) => {
      await axios.get("/historyData/query", {
        params: {
          startTime: params.startTime,
          endTime: params.endTime
        }
      })
    };

    var optionsT = {
      title: {
        text: '温度传感器'
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
      dataZoom: [
        {
          type: 'inside'
        }
      ],
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
          name: '温度传感器1',
          type: 'line',
          smooth: true
        },
        {
          data: [],
          name: '温度传感器2',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '温度传感器3',
          smooth: true,
          type: 'line'
        }
      ]
    }

    var optionsV = {
      title: {
        text: '振动传感器'
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
      dataZoom: [
        {
          type: 'inside'
        }
      ],
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
          smooth: true,
          type: 'line'
        }
      ]
    }

    var optionsS = {
      title: {
        text: '应变传感器'
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
      dataZoom: [
        {
          type: 'inside'
        }
      ],
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
          name: '应变传感器1',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '应变传感器2',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '应变传感器3',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '应变传感器4',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '应变传感器5',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '应变传感器6',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '应变传感器7',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '应变传感器8',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '应变传感器9',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '应变传感器10',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '应变传感器11',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '应变传感器12',
          smooth: true,
          type: 'line'
        },
        {
          data: [],
          name: '应变传感器13',
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
      console.log("onMounted被触发")

      const chartDom = document.getElementById('chart');
      const myChart = echarts.init(chartDom);
      watch([range2,selected], async (newValue, oldValue) => {

        await axios.get("/historyData/query", {
          params: {
            startTime: range2.value[0],
            endTime: range2.value[1]
          }
        }).then(res =>{
          optionsT.xAxis.data=res.data.content["time"];
          optionsV.xAxis.data=res.data.content["time"];
          optionsS.xAxis.data=res.data.content["time"];
          optionsT.series[0].data = res.data.content["temperature1"];
          optionsT.series[1].data = res.data.content["temperature2"];
          optionsT.series[2].data = res.data.content["temperature3"];
          optionsV.series[0].data = res.data.content["vibration"];
          optionsS.series[0].data = res.data.content["strain1"];
          optionsS.series[1].data = res.data.content["strain2"];
          optionsS.series[2].data = res.data.content["strain3"];
          optionsS.series[3].data = res.data.content["strain4"];
          optionsS.series[4].data = res.data.content["strain5"];
          optionsS.series[5].data = res.data.content["strain6"];
          optionsS.series[6].data = res.data.content["strain7"];
          optionsS.series[7].data = res.data.content["strain8"];
          optionsS.series[8].data = res.data.content["strain9"];
          optionsS.series[9].data = res.data.content["strain10"];
          optionsS.series[10].data = res.data.content["strain11"];
          optionsS.series[11].data = res.data.content["strain12"];
          optionsS.series[12].data = res.data.content["strain13"];
        })

        if(selected.value=='t'){
          if(myChart != null){
            myChart.clear()
          }
          myChart.setOption(optionsT)
        }else if(selected.value=='v'){
          if(myChart != null){
            myChart.clear()
          }
          myChart.setOption(optionsV)
        }else{
          if(myChart != null){
            myChart.clear()
          }
          myChart.setOption(optionsS)
        }
      });
    })
    return {

      options,
      selected,
      data: dataRef,
      range2,
      startTime,
      endTime,
      handleQueryList,
    }
  }
})
</script>