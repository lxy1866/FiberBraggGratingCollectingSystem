<template>
  <the-header></the-header>
  <a-layout-content style="padding: 0; background: #001529" :style="{margin: 0, height: height_top.height}">
    <div style="position: relative; display: flex;justify-content: space-between" >
      <dv-decoration-8 style="width:300px;height:50px;" />
      <div style="display: flex; flex-flow: column; justify-content: center; margin-top: -6px">
        <div style="font-size: 20px; color: cadetblue;text-align:center;" class="fontClass" >海底电缆智能健康监测系统</div>
        <div><dv-decoration-3 style="width:250px;height:30px;" /></div>
      </div>
      <dv-decoration-8 :reverse="true" style="width:300px;height:50px;" />
    </div>
    <div class="container" style="margin-top: 10px">
      <div class="box left">
        <dv-border-box-8 class="border-box">
          <line-chart-strain class="charts" style="height: 300px"/>
        </dv-border-box-8>
        <dv-border-box-8  class="border-box"  >
          <real-temperature class="charts" style="height: 300px; margin-left: 90px"/>
        </dv-border-box-8>
      </div>
      <div class="box center">
        <dv-border-box-8 class="border-box" >
          <CenterTop   class="charts" style="height:300px;"/>
        </dv-border-box-8>
        <dv-border-box-8 class="border-box">
          <line-chart-temperature class="charts" style="height: 300px;"/>
        </dv-border-box-8>
      </div>
      <div class="box right">
        <dv-border-box-8 class="border-box">
          <line-chart-vibration class="charts" style="height: 300px;"/>
        </dv-border-box-8>
        <dv-border-box-8  class="border-box">
          <line3d-shape class="charts" style="height: 300px;"/>
        </dv-border-box-8>
      </div>
    </div>
  </a-layout-content>
</template>

<script>
import {defineComponent, onMounted, ref} from "vue";

import CenterTop from '../components/centerTopChart.vue'
import LineChartTemperature from '../components/centerBottomChart.vue'
import LineChartStrain from '../components/leftTopChart.vue'
import Line3dShape from '../components/rightBottomChart.vue'
import LineChartVibration from '../components/rightTopChart.vue'
import RealTemperature from '../components/leftBottomChart.vue'
import TheHeader from '@/components/theHeader.vue';
import TheFooter from '@/components/theFooter.vue';
export default defineComponent({
  components:{
    CenterTop,
    LineChartTemperature,
    LineChartVibration,
    LineChartStrain,
    Line3dShape,
    RealTemperature,
    TheHeader,
    TheFooter
  },
  setup(){
    const height_top = ref({
      height: ''
    });
    onMounted(()=>{
      if(window.innerHeight != null){
        height_top.value.height = window.innerHeight+'px';
      }
      window.onresize = () => {
        return (() => {
          height_top.value.height = window.innerHeight+'px';
        })();
      };

    })
    return{
      height_top
    }
  }
});
</script>
<style scoped>
.fontClass{
  font-font: 宋体;
}
.container{
  display: flex;
  flex-direction: row;
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
  display: flex;
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
  display: flex;
}
.spacial-border-box{
  width: 100%;
  height: fit-content;
  display: flex;
}
/*@media screen and(max-device-width: 450px){*/
/*  .container{*/
/*    display: flex;*/
/*    flex-direction: column;*/
/*    padding-bottom: 75px;*/
/*  }*/
/*  .left{*/
/*    width: 100%;*/
/*  }*/
/*  .right{*/
/*    width: 100%;*/
/*  }*/
/*  .center{*/
/*    width: 100%;*/
/*  }*/
/*  .box{*/
/*    display: flex;*/
/*    flex-direction: column;*/
/*  }*/
/*}*/
</style>