<template>
  <div id="roundTemperature" style="width:100%;height:100%;" class="roundTemperature">
  </div>
</template>
<script>
import {defineComponent, onMounted, toRefs, reactive} from "vue";
import * as echarts from "echarts";
import axios from "axios";
function handleQueryTemperatureNow(){
  return axios.get("/fbg/temperatureNow")
}
export default defineComponent({
  name: 'roundTemperature',
  setup() {
    const state = reactive({
      option: {
        title: [
          {
            text: 0 + "℃",
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
                value: 0,
                itemStyle: {
                  normal: {
                    color: "#03a9f4",
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

    onMounted(async () => {
      const {data} = await handleQueryTemperatureNow();
      const newOption = {
        title: [
          {
            text: data.content * 1 + "℃",
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
                value: data.content,
                itemStyle: {
                  normal: {
                    color: "#03a9f4",
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
      const chart = echarts.init(document.getElementById('roundTemperature'));
      window.onresize = chart.resize;
      chart.setOption(state.option);
    });
    return {
      ...toRefs(state),
    }
  },
})
</script>

<style scoped>
.roundTemperature {
  display: flex;
}
</style>