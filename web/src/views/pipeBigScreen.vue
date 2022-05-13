<template>
  <the-header></the-header>
  <a-layout-content style="padding: 0; background: #053343" >
    <div style="position: relative; display: flex;justify-content: space-between" >
      <dv-decoration-8 style="width:300px;height:50px;" />
      <div style="display: flex; flex-flow: column; justify-content: center; margin-top: -6px">
        <div style="font-size: 20px; color: cadetblue;text-align:center;font-family: 宋体" >册镇海底管道位移监测</div>
        <div><dv-decoration-3 style="width:250px;height:30px;" /></div>
      </div>
      <dv-decoration-8 :reverse="true" style="width:300px;height:50px;" />
    </div>
    <div class="container" style="margin-top: 10px">
      <div class="box left">
        <dv-border-box-8 class="border-box">
          <bar-pipe-displayment class="charts" style="height: 650px"/>
        </dv-border-box-8>
      </div>
      <div class="box center">
        <dv-border-box-8 class="border-box" >
          <PipeCenterTop  class="charts" style="height:300px;"/>
        </dv-border-box-8>
        <dv-border-box-8 class="border-box">
          <line-chart-displayment class="charts" style="height: 300px;"/>
        </dv-border-box-8>
      </div>
      <div class="box right">
        <dv-border-box-8  class="border-box">
          <line3d-pipe class="charts" style="height: 600px;"/>
        </dv-border-box-8>
      </div>
    </div>
  </a-layout-content>
  <the-footer></the-footer>
</template>

<script lang="ts">
import {defineComponent, onMounted} from "vue";

import Line3dPipe from '../components/rightTopChart.vue'
import TheHeader from '@/components/theHeader.vue';
import TheFooter from '@/components/theFooter.vue';
import BarPipeDisplayment from "@/components/leftTopChart.vue";
import PipeCenterTop from '@/components/centerTopChart.vue'
import LineChartDisplayment from '@/components/centerBottomChart.vue';
export default defineComponent({
  components:{
    BarPipeDisplayment,
    LineChartDisplayment,
    Line3dPipe,
    PipeCenterTop,
    TheHeader,
    TheFooter
  },
  setup(){
    onMounted(()=>{


    });
    return{
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