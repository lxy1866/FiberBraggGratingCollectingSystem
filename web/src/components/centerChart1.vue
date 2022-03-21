<template>
    <div id="centerChart1" style="width:110px;height:110px"></div>
</template>

<script>
import {defineComponent, onMounted} from "vue";

import * as echarts from "echarts";
let tips = 60;
let option = {
    title: [
      {
        text: tips * 1 + "%",
        x: "center",
        y: "center",
        textStyle: {
          color: "#3fc0fb",
          fontSize: 16
        }
      }
    ],
    series: [
      {
        type: "pie",
        radius: ["75%", "80%"],
        center: ["50%", "50%"],
        hoverAnimation: false,
        color: ["#00bcd44a", "transparent"],
        label: {
          normal: {
            show: false
          }
        },
        data: [
          {
            value: tips,
            itemStyle: {
              normal: {
                color: "#03a9f4",
                shadowBlur: 10,
                shadowColor: "#97e2f5"
              }
            }
          },
          {
            value: 100 - tips
          }
        ]
      }
    ]
  };
export default defineComponent({

  name: 'centerChart1',
  setup() {
    onMounted(()=>{
      const chart = echarts.init(document.getElementById('centerChart1'));
      chart.setOption(option);
    });
    return{
    }
  },
})
</script>

<style scoped>
</style>