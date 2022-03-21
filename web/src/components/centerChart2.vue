<template>
  <div>
    <div id="centerChart2" style="width:110px;height:110px;"></div>
  </div>
</template>

<script>
import {defineComponent, onMounted} from "vue";
import * as echarts from "echarts";

let tips = 40;
let option = {
  title: [
    {
      text: tips * 1 + "%",
      x: "center",
      y: "center",
      textStyle: {
        color: "#67e0e3",
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
      color: ["#f3ffb070", "transparent"],
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
              color: "#ff9800",
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
      const chart = echarts.init(document.getElementById('centerChart2'));
      chart.setOption(option);
    });
    return{
    }
  },
})
</script>

<style  scoped>
</style>