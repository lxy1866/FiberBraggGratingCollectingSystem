<template>
  <the-header></the-header>
  <a-layout-content style="padding: 0; background: #001529;  overflow-x: hidden;" :style="{margin: 0, height: height_top.height}">
    <div style=" height:60px; display: flex;justify-content: space-between" >
      <dv-decoration-8 style="width:350px;" />
        <div style="font-size: 20px; width:400px;color: cadetblue;text-align:center;display: inline-block;vertical-align: center;"  >
          <div>基于光纤光栅传感器的应变、振动监测系统</div>
        </div>
      <dv-decoration-8 :reverse=true style="width:350px;" />
    </div>
    <div class="container fontClass" style="margin-top: 10px">
      <div class="box2 bottom" id="prev">
          <dv-border-box-7 class="border-box">
            <line-chart-vibration class="charts" style="height: 400px; width: 600px"/>
          </dv-border-box-7>
          <dv-border-box-7 class="border-box">
            <line-chart-vibration class="charts" style="height: 400px; width: 600px"/>
          </dv-border-box-7>
      </div>
      <div class="box2 bottom" id="next" >
        <dv-border-box-7 class="border-box">
          <line-chart-vibration class="charts" style="height: 400px; width: 600px"/>
        </dv-border-box-7>
        <dv-border-box-7 class="border-box">
          <line-chart-vibration class="charts" style="height: 400px; width: 600px"/>
        </dv-border-box-7>
      </div>
    </div>
  </a-layout-content>
</template>
<script>
import {defineComponent, onMounted, ref} from "vue";
import LineChartVibration from '../components/rightTopChart.vue'
import RealTemperature from '../components/leftBottomChart.vue'
import TheHeader from '@/components/theHeader.vue';
import functionCall from "@/functionCall";
export default defineComponent({
  components:{
    LineChartVibration,
    RealTemperature,
    TheHeader,
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
      functionCall
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

.container{
  display: flex;
  flex-direction: column;
}

.bottom{
  height: 40%;
  display: flex;
  flex-direction: row;
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

</style>