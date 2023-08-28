<template>
  <div id="strainVerticalView" class="strainVerticalView">
<!--    <span>俯视图</span>-->
    <span class="bigRectangle">
      <span v-for="(value,index) in interpolatedValues" :key="index" class="rectangle" :style="{backgroundColor:getColor(value)}"></span>
    </span>
  </div>
</template>

<script lang="js">
import {ref,onMounted,defineComponent} from "vue";
import {Tool} from "@/util/tool";

export default defineComponent({
  name:'strainVerticalView',
  setup:function (){
    const values=ref([]);
    const interpolatedValues=ref([]);

    const getColor=(value)=>{
      if(value<=-0.0015){
        return "#0000FF";
      }else if(value<=-0.001){
        return "#1E90FF";
      }else if(value<=-0.0005){
        return "#00FFFF";
      }else if(value<=0){
        return "#7FFFD4";
      }else if(value<=0.0005){
        return "#FFC0CB";
      }else if(value<=0.001){
        return "#FA8072";
      }else if(value<=0.0015){
        return "#FF7F50";
      }else if(value<=0.002){
        return "#FF4500";
      }
      else {
          return "green";
        }
    };

    const interpolateValues=()=>{
      interpolatedValues.value=[];
      for (let i = 0; i < values.value.length-1; i++) {
        const startValue=values.value[i];
        const endValue=values.value[i+1];
        const stepSize=(endValue-startValue)/9;
        for (let j = 0; j < 9; j++) {
          const interpolatedValue=startValue+(j*stepSize);
          interpolatedValues.value.push(interpolatedValue);
        }
      }
    };

    onMounted(()=>{
      let token;
      token = Tool.uuid(10);
      const socket = new WebSocket(process.env.VUE_APP_WS_SERVER + '/ws/' + token);
      socket.addEventListener('message',(event)=>{
        values.value=JSON.parse(event.data);
        interpolateValues();
      });
    });

    return{
      interpolatedValues,
      getColor,
    };
  },
})
</script>

<style scoped>
.strainVerticalView{
  display: flex;
}
.bigRectangle{
  margin-left: 150px;
  margin-right: 100px;
  display: flex;
}
.rectangle{
  width: 40px;
  height: 20px;
}
</style>