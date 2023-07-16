<template>
  <the-header></the-header>
  <a-layout-content style="padding: 0; background: #001529;  overflow-x: hidden;" :style="{margin: 0, height: height_top.height}">
    <div style=" height:60px; display: flex;justify-content: space-between" >
      <dv-decoration-8 style="width:350px;" />
        <div style="font-size: 25px; width:500px;color: cadetblue;text-align:center;display: inline-block;vertical-align: center;"  >
          <div>基于MEMS传感阵列的海缆形状检测系统</div>
        </div>
      <dv-decoration-8 :reverse=true style="width:350px;" />
    </div>
    <div class="screen-wrapper fontClass" style="margin-top: 10px">
      <div class="screen">
        <dv-border-box-7 class="section">
          <mems-reshape class="charts section2"  />
        </dv-border-box-7>
        <dv-border-box-7 class="section" >
          <motor-reshape class="charts section2" />
        </dv-border-box-7>
        <dv-border-box-7 class="section">
          <shape-control class="charts "   />
        </dv-border-box-7>
        <dv-border-box-7 class="section">
          <reshape-precision class="charts " />
        </dv-border-box-7>
      </div>
    </div>
  </a-layout-content>
</template>
<script>
import {defineComponent, onMounted, ref} from "vue";
import functionCall from "@/functionCall";
import shapeControl from "@/components/shapeControl";
import reshapePrecision from "@/components/reshapePrecision";
import leftTopChart from "@/components/leftTopChart";
import theHeader from "@/components/theHeader";
import motorReshape from "@/components/motorReshape.vue"
import memsReshape from "@/components/memsReshape.vue"
export default defineComponent({
  components:{
    shapeControl,
    reshapePrecision,
    leftTopChart,
    theHeader,
    memsReshape,
    motorReshape
  },
  setup(){
    const height_top = ref({
      height: ''
    });
    onMounted(async ()=>{
      const { data } = await new functionCall().handleQueryOnline();

    })
    return{
      height_top,
      functionCall,
    }
  }
});
</script>
<style scoped>

@keyframes blink {
  0%{opacity: 1;}
  100%{opacity: 0}
}
.fontClass{
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
  color:  cadetblue;
}
li{
  display: inline-block;
  height: 60px;
  margin-left: 100px;
  text-align:center;
  color: white;
  vertical-align: center;
}
ul:last-child{
  margin-right: 100px;
}
.charts{
  width: 94%;
  margin: 3%;
  display: flex;
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
.screen-wrapper {
  height: 105vh;
  width: 100vw;
  background-color:  #001529;
  .screen{
    display: inline-block;
    background: transparent;
    transform-origin: 0 0;
    position: absolute;
    width: 100%;
    height: 50%;
    .section{
      height: 100%;
      width: 50%;
      line-height: 200px;
      font-size: 40px;
      text-align: center;
      display: inline-block;
      .section2 {
        height: 98%;
        width: 100%;
        margin: 0;
        line-height: 200px;
        font-size: 40px;
        text-align: center;
        display: inline-block;
      }
    }
  }
}
</style>