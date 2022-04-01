<template>
  <div id="centerChart2" style="width:100%;height:100%;" class="centerChart2"></div>
</template>

<script>
import {defineComponent, onMounted, toRefs, reactive} from "vue";
import * as echarts from "echarts";
import axios from "axios";

function handleQueryOnline() {
  return axios.get("/ct/calculateOnLine")
}


export default defineComponent({
  name: 'centerChart2',
  setup() {
    const state  = reactive({
      option : {
        title: [
          {
            text: 0 + "%",
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
                value: 0,
                itemStyle: {
                  normal: {
                    color: "#ff9800",
                    shadowBlur: 10,
                    shadowColor: "#97e2f5"
                  }
                }
              },
              {
                value: 100
              }
            ]
          }
        ]
      }
    })
    onMounted(async ()=>{
      const { data } = await handleQueryOnline();
      const newOption =  {
        title: [
          {
            text: data.content.onlineRate * 1 + "%",
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
                value: data.content.onlineRate,
                itemStyle: {
                  normal: {
                    color: "#ff9800",
                    shadowBlur: 10,
                    shadowColor: "#97e2f5"
                  }
                }
              },
            ]
          }
        ]
      };
      state.option = newOption;
      const chart = echarts.init(document.getElementById('centerChart2'));
      window.onresize = chart.resize;
      chart.setOption(state.option);
    });
    return{
      ...toRefs(state)
    }
  },
})
</script>

<style  scoped>
.centerChart2{
  display: flex;
}
</style>