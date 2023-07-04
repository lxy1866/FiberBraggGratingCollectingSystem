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
    <div class="container fontClass" style="margin-top: 10px">
      <div class="box2 top">
        <dv-border-box-7 class="border-box">
          <left-top-chart class="charts" style="height: 300px"/>
        </dv-border-box-7>
      </div>
      <div class="box2 bottom" id="prev" >
          <dv-border-box-7 class="border-box">
            <shape-control class="charts" style="height: 400px"/>
          </dv-border-box-7>
          <dv-border-box-7 class="border-box">
            <reshape-precision class="charts" style="height: 400px"/>
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
export default defineComponent({
  components:{
    shapeControl,
    reshapePrecision,
    leftTopChart,
    theHeader
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
.top{
  height: 30%;
  width:100%;
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