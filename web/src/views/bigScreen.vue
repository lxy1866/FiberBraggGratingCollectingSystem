<template>
  <a-layout-content style="padding: 0; background: #001529" >
    <div style="display: flex;justify-content: space-between" >
      <dv-decoration-8 style="width:300px;height:50px;" />
      <div style="display: flex; flex-flow: column; justify-content: center; margin-top: -6px">
        <div style="font-size: 20px; color: cadetblue;text-align:center;" >海底电缆智能健康监测系统</div>
        <div><dv-decoration-3 style="width:250px;height:30px;" /></div>
      </div>
      <dv-decoration-8 :reverse="true" style="width:300px;height:50px;" />
    </div>
    <div class="container" style="margin-top: 10px">
      <div class="box left">
        <dv-border-box-8 class="border-box">
          <line-chart-strain class="charts" style="height:300px;"/>
        </dv-border-box-8>
        <dv-border-box-8 class="border-box">
          <temperature class="charts" style="height:300px; margin-left: 50px;"/>
        </dv-border-box-8>

        <dv-border-box-8 class="border-box">
          <dv-capsule-chart :config="config7" class="charts" style="height:300px" />
        </dv-border-box-8>
      </div>
      <div class="box center">
        <dv-border-box-8 class="border-box" >
          <CenterTop   class="charts" style="height:300px;"/>
        </dv-border-box-8>
        <dv-border-box-8 class="border-box">
          <line-chart-temperature class="charts" style="height: 300px;"/>
        </dv-border-box-8>
        <dv-border-box-8 class="border-box">
        </dv-border-box-8>
      </div>
      <div class="box right">
        <dv-border-box-8 class="border-box">
          <line-chart-vibration class="charts" style="height: 300px;"/>
        </dv-border-box-8>
        <dv-border-box-8  class="border-box">
          <line3d-shape class="charts" style="height: 300px;"/>
        </dv-border-box-8>
        <dv-border-box-8 class="border-box">
          <dv-capsule-chart :config="config7" class="charts" style="height:300px" />
        </dv-border-box-8>
      </div>
    </div>
  </a-layout-content>
</template>

<script>
import {defineComponent, onMounted} from "vue";

import {config1,config2,config3,config4,config5,config6,config7,option2} from "./config.js";
import * as echarts from "echarts";
import Temperature from '../components/temperature.vue'
import CenterTop from '../components/center-top.vue'
import LineChartTemperature from '../components/line-chart-temperature.vue'
import LineChartStrain from '../components/line-chart-strain.vue'
import Line3dShape from '../components/line-3d-shape.vue'
import LineChartVibration from '../components/line-chart-vibration.vue'

export default defineComponent({

  components:{
    Temperature,
    CenterTop,
    LineChartTemperature,
    LineChartVibration,
    LineChartStrain,
    Line3dShape
  },
  setup(){
    onMounted(()=>{
    });
    // let chart = echarts.init(document.getElementById('GuangdongMap'));
    // echarts.registerMap("GuangdongMap", GuangdongJson)
    // chart.setOption({
    //   geo: {
    //     map: 'GuangdongMap'
    //   }
    // });
    return{
      config1,
      config2,
      config3,
      config4,
      config5,
      config6,
      config7,
      option2,

    }
  }
});

</script>
<style scoped>
.container{
  display: flex;
  flex-direction: row;
  padding-bottom: 75px;
}
.left{
  width: 30%;
}
.right{
  width: 30%;
}
.center{
  width: 40%;
}
.box{
  display: flex;
  flex-direction: column;
}
.charts{
  width: 94%;
  margin: 3%;
}
#pie.charts{
  height: 100%;
  width: 100%;
}
img{
  width: auto;
  height: auto;
  max-width: 98%;
  margin-top: 1%;
  margin-left: 1%;
  margin-right: 1%;
  max-height: 64%;
}
.border-box{
  width: 100%;
  height: 100%;
}
</style>