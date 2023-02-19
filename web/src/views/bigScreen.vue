<template>
  <the-header></the-header>
  <a-layout-content style="padding: 0; background: #001529;   overflow-y: hidden;overflow-x: hidden;" :style="{margin: 0, height: height_top.height}">

    <div style="position: relative; display: flex;justify-content: space-between" >
      <div style="width:250px;height:75px;margin-top: 45px">
        <div style="font-size: 15px; text-align:center;margin-top: 10px" class="fontClass" >设备数量</div>
        <div style="font-size: 15px; color: white;text-align:center;" class="fontClass" >50个</div>
      </div>
      <div style="width:250px;height:75px;margin-top: 45px">
        <div style="font-size: 15px; text-align:center;margin-top: 10px" class="fontClass " >在线数量</div>
        <div style="font-size: 15px; color: white;text-align:center;" class="fontClass" >50个</div>
      </div>
      <div style="width:100px;height:75px;margin-top: 45px"></div>
      <div style="width:400px;height:160px;display: flex; flex-flow: column; justify-content: center; margin-top: -50px;margin-right: 10px;">
        <div style="font-size: 30px; color: cadetblue;text-align:center;" class="fontClass" >海底电缆智能健康监测系统</div>
      </div>
      <div style="width:100px;height:75px;margin-top: 45px"></div>
      <div style="width:250px;height:75px;margin-top: 40px">
        <div style="font-size: 15px; text-align:center;margin-top: 2px" class="fontClass" >温度阈值：<span style="color:white">50°C</span></div>
        <div style="font-size: 15px; text-align:center;" class="fontClass" >位移阈值：<span style="color:white">10cm</span></div>
        <div style="font-size: 15px; text-align:center;" class="fontClass" >振动信号短时能量阈值：<span style="color:white">20dB</span></div>
      </div>
      <div style="width:250px;height:75px;margin-top: 45px">
        <div style="font-size: 15px; text-align:center;margin-top: 10px" class="fontClass" >预警信息</div>
        <div style="font-size: 15px; color: white;text-align:center;" class="fontClass" >无</div>
      </div>
    </div>
    <div class="container fontClass" style="margin-top: 10px">
      <div class="box2 top">
        <dv-border-box-7 class="border-box" >
          <div style="height: 350px; border-color: white">
            <div class="textAnnotation" >
              海缆监测示意图
            </div>
            <img src="@/assets/1111.png" style="height: 100%; width: 100%" alt="">
            <div style="display: flex; flex-direction: row-reverse; margin-top: 10px" >
              <a-button @click="pageTurning">曲线图翻页</a-button>
              <a-button @click="HispageTurning">历史数据图</a-button>
            </div>
          </div>
        </dv-border-box-7>
      </div>
      <div class="box2 bottom" id="prev" v-show="showCurvePrev">
          <dv-border-box-7 class="border-box">
            <line-chart-strain class="charts" style="height: 400px"/>
          </dv-border-box-7>
          <dv-border-box-7 class="border-box">
            <three-temperature class="charts" style="height: 400px"></three-temperature>
<!--            <real-temperature class="charts" style="height: 400px; "/>-->
          </dv-border-box-7>
          <dv-border-box-7 class="border-box">
            <viberation-wave class="charts" style="height: 400px;"></viberation-wave>
          </dv-border-box-7>
          <dv-border-box-7 class="border-box">
            <line3d-shape class="charts" style="height: 400px;"/>
          </dv-border-box-7>
      </div>
      <div class="box2 bottom" id="next" v-show="showCurveNext">
        <dv-border-box-7 class="border-box">
          <line-chart-vibration class="charts" style="height: 400px; width: 400px"/>
        </dv-border-box-7>
        <dv-border-box-7 class="border-box">
          <max-offset   class="charts" style="height:400px; width: 400px"/>
        </dv-border-box-7>
        <dv-border-box-7 class="border-box">
          <line-chart-temperature class="charts" style="height: 400px; width:600px"></line-chart-temperature>
        </dv-border-box-7>
      </div>
      <div class="box2 bottom" id="his" v-show="showCurveHis">
        <dv-border-box-7 class="border-box">
          <temperatureHistory class="charts" style="height:400px; width: 500px"></temperatureHistory>
        </dv-border-box-7>
        <dv-border-box-7 class="border-box">
          <strainHistory class="charts" style="height:400px; width: 500px"></strainHistory>
        </dv-border-box-7>
        <dv-border-box-7 class="border-box">
          <img src="@/assets/vibration.png" style="height: 400px;" alt="">
        </dv-border-box-7>
        <dv-border-box-7 class="border-box">
          <img src="@/assets/shortterm.png" style="height: 400px;" alt="">
        </dv-border-box-7>
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
import MaxOffset from '@/components/maxOffset.vue';
import ViberationWave from '@/components/viberationWave.vue';
import ThreeTemperature from "@/components/threeTemperature.vue";
import functionCall from "@/functionCall";
import temperatureHistory from "@/components/temperatureHistory.vue";
import strainHistory from "@/components/strainHistory.vue";
let showCurvePrev = ref(true);
let showCurveNext = ref(false);
let showCurveHis = ref(false);
const pageTurning = ()=>{
  if(showCurvePrev.value === true && showCurveNext.value === false){
    showCurvePrev.value = false;
    showCurveNext.value = true;
    showCurveHis.value = false;
  } else if(showCurveHis.value === true){
    showCurvePrev.value = true;
    showCurveNext.value = false;
    showCurveHis.value = false;
  } else{
    showCurvePrev.value = true;
    showCurveNext.value = false;
    showCurveHis.value = false;
  }
}
const HispageTurning = ()=>{
    showCurvePrev.value = false;
    showCurveNext.value = false;
    showCurveHis.value = true
}
export default defineComponent({
  components:{
    CenterTop,
    LineChartTemperature,
    LineChartVibration,
    LineChartStrain,
    Line3dShape,
    RealTemperature,
    TheHeader,
    TheFooter,
    MaxOffset,
    ViberationWave,
    ThreeTemperature,
    strainHistory,
    temperatureHistory
  },

  setup(){
    const height_top = ref({
      height: ''
    });

    onMounted(async ()=>{
      const { data } = await new functionCall().handleQueryOnline();
      if(window.innerHeight != null){
        height_top.value.height = window.innerHeight - 64 +'px';
      }
      window.onresize = () => {
        return (() => {
          height_top.value.height = window.innerHeight - 64 +'px';
        })();
      };

    })
    return{
      height_top,
      functionCall,
      showCurvePrev,
      showCurveNext,
      showCurveHis,
      pageTurning,
      HispageTurning
    }
  }
});
</script>
<style scoped>
.fontClass{
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
  color:  cadetblue;
}

.textAnnotation{
  height: 80px;
  width: 200px;
  font-size: large;
  margin-left:10px;
  margin-top: 10px;
}

.container{
  display: flex;
  flex-direction: column;
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
.top{
  height: 30%;
  width:100%;
}
.bottom{
  height: 40%;
  display: flex;
  flex-direction: row;
}
.columnCenter{
  height: 20%;
  font-size: xx-small;
}
.box{
  display: flex;
  flex-direction: column;
}
.box2{
  display: flex;
  flex-direction: row;
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