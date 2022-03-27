<template>
  <div class="temperatureGauge">
    <div id="temperatureGauge" style="height: 50px; width: 100%"></div>
    <dv-border-box-10 style="width: 200px;height: 100px ">
      <div class="valueStyle">
        {{TP_value+'℃'}}
      </div>
    </dv-border-box-10>
  </div>
</template>
<script lang="ts">
import * as echarts from 'echarts';
import {defineComponent, onMounted, ref} from "vue";
import axios from "axios";
type EChartsOption = echarts.EChartsOption;
let TP_value = ref();
function handleQueryTemperatureNow(){
  return axios.get("/fbg/temperatureNow").then(function (response){
    TP_value.value = response.data.content
  })
}
export default defineComponent({
  name: 'temperatureGauge',
  setup() {
    handleQueryTemperatureNow();
    onMounted( ()=> {
      //添加页面改变监听
      const chartDom = document.getElementById('temperatureGauge')!;
      const myChart = echarts.init(chartDom);
      let option: EChartsOption;
      option = {
        title: {
          text: '海缆实时温度值',
          textStyle:{
            color: '#ffffff',
            fontFamily: '宋体',
          }
        },
        toolbox: {
          show: true,
          feature: {
            dataView: { readOnly: false },
            restore: {},
            saveAsImage: {}
          }
        },
        series:{

        }
      };
      myChart.resize();
      option && myChart.setOption(option);
    })
    return{
      TP_value
    }
  }
})
</script>
<style scoped>
.temperatureGauge{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.valueStyle{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: white;
  font-size: 28px;
  margin: 12%;
}
</style>