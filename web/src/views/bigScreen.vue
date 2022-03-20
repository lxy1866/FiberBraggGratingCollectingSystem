<template>
  <a-layout-content style="padding: 0; background: #001529" >
    <div style="display: flex;justify-content: space-between" >
      <dv-decoration-8 style="width:300px;height:50px;" />
      <div style="display: flex; flex-flow: column; justify-content: center; margin-top: -6px">
        <div style="font-size: 20px; color: cadetblue;text-align:center;" >数据概览</div>
        <div><dv-decoration-3 style="width:250px;height:30px;" /></div>
      </div>
      <dv-decoration-8 :reverse="true" style="width:300px;height:50px;" />
    </div>
    <div class="container" style="margin-top: 10px">
      <div class="box left">
        <dv-border-box-8 class="border-box">
          <dv-scroll-board :config="config3" class="charts" style="height:300px" />
        </dv-border-box-8>
        <dv-border-box-8 class="border-box">
          <dv-scroll-board :config="config2" class="charts" style="height:300px" />
        </dv-border-box-8>
        <dv-border-box-8 class="border-box">
          <dv-capsule-chart :config="config7" class="charts" style="height:300px" />
        </dv-border-box-8>
      </div>
      <div class="box center">
        <dv-border-box-8 class="border-box">
          <img src="../assets/map.png">
          <img src="../assets/bridge.png" />
          <img src="../assets/tunel.jpg"/>
        </dv-border-box-8>
      </div>
      <div class="box right">
        <dv-border-box-8 class="border-box">
          <dv-scroll-ranking-board :config="config3" class="charts" style="height:300px" />
        </dv-border-box-8>
        <dv-border-box-8 :reverse="true" class="border-box">
          <div  style="height:300px" id="pie"></div>
        </dv-border-box-8>
        <dv-border-box-8 class="border-box">
          <dv-capsule-chart :config="config7" class="charts" style="height:300px" />
        </dv-border-box-8>
      </div>
    </div>

  </a-layout-content>
</template>

<script>
import {defineComponent, onMounted, ref} from "vue";

import {config1,config2,config3,config4,config5,config6,config7,option2} from "./config.js";
import Charts from "@jiaminghi/charts";
import * as echarts from "echarts";
import GuangdongJson from '../assets/GuangdongJson.json' // 这个是json引用

export default defineComponent({

  setup(){
    onMounted(()=>{
      const pie = document.getElementById('pie')
      const myChart = new Charts(pie)
      myChart.setOption(option2)
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
#GuangdongMap{
  width: auto;
  height: auto;
  max-width: 98%;
  margin-top: 1%;
  margin-left: 1%;
  margin-right: 1%;
  max-height: 64%;
}
</style>