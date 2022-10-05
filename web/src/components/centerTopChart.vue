<template>
  <div id="center">
    <div class="up">
      <div class="bg-color-black item" v-for="item in titleItem" :key="item.title">
        <p style="font-family: '宋体';color: white">{{item.title}}</p>
        <div>
          <dv-digital-flop :config="item.number" style="width:100px;height:50px;" />
        </div>
      </div>
    </div>
    <div class="down">
      <div class="percent">
        <div class="item  bg-color-black">
          <span style="font-family: '宋体';">今日在线率</span>
          <centerChart1 />
        </div>
        <div class="item  bg-color-black">
          <span style="font-family: '宋体';">今日任务达标率</span>
          <centerChart2 />
        </div>
      </div>
      <div class="card">
        <n-card title="12%变形点预警信息"
                font-color="white"
                style="--n-color: #053343;
                  --n-title-font-size: 10px;
                  --n-title-font-family:'宋体';
                  --n-title-text-color: white"
        >
<!--          <span style="color: azure">{{timeslot}}</span>&nbsp;&nbsp;-->
          <span style="color: azure;font-size: 10px">定位环位移：mm</span><br/>
          <span style="color: azure;font-size: 10px">结构管卡位移：mm</span>
        </n-card>
        <n-card title="22%变形点预警信息"
                font-color="white"
                style="--n-color: #053343;
                  --n-title-font-size: 10px;
                  --n-title-font-family:'宋体';
                  --n-title-text-color: white"
        >
<!--          <span style="color: azure">{{timeslot}}</span>&nbsp;&nbsp;-->
          <span style="color: azure;font-size: 10px">位移：mm</span>
        </n-card>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import centerChart1 from "./centerChart1.vue";
import centerChart2 from "./centerChart2.vue";
import {defineComponent, onMounted, ref, toRefs,reactive,onUnmounted} from "vue";
import axios from "axios";

const warnMessage = '暂无'
function handleQueryOnline() {
  return axios.get("/ct/calculateOnLine")
}
let timeslot = ref();
function resetTime(i:any) {
  if (i < 10) {
    return '0' + i
  }
  return i
}
export default defineComponent({
  name: 'center-top-pipe',
  setup() {
    const state =  reactive({
      titleItem: [
        {
          title: "今日传感器总量",
          number: {
            number: [0],
            toFixed: 0,
            content: "{nt}"
          }
        },
        {
          title: "今日传感器在线个数",
          number: {
            number: [0],
            toFixed: 0,
            content: "{nt}"
          }
        },
        {
          title: "今日累计异常波动次数",
          number: {
            number: [0],
            toFixed: 0,
            content: "{nt}"
          }
        },
        {
          title: "今年累计异常波动次数",
          number: {
            number: [0],
            toFixed: 0,
            content: "{nt}"
          }
        },
        {
          title: "今年成功任务次数",
          number: {
            number: [0],
            toFixed: 0,
            content: "{nt}"
          }
        },
        {
          title: "今年达标任务个数",
          number: {
            number: [0],
            toFixed: 0,
            content: "{nt}"
          }
        }
      ]
    })

    /**
     * 大概清楚了，就是要新建一个newTitleItem 设置里面的值来替换state中的titleItem
     */
    onMounted(async ()=>{
      const { data } = await handleQueryOnline();
      //console.log(data.content)
      state.titleItem = [{
        title: "今日传感器总量",
        number: {
          number: [data.content.total],
          toFixed: 0,
          content: "{nt}"
        }
      },
        {
          title: "今日传感器在线个数",
          number: {
            number: [data.content.onLine],
            toFixed: 0,
            content: "{nt}"
          }
        },
        {
          title: "今日累计异常波动次数",
          number: {
            number: [data.content.abnormalTimes],
            toFixed: 0,
            content: "{nt}"
          }
        },
        {
          title: "今年累计异常波动次数",
          number: {
            number: [data.content.thisYearAbnormalTimes],
            toFixed: 0,
            content: "{nt}"
          }
        },
        {
          title: "今年成功任务次数",
          number: {
            number: [data.content.onLine],
            toFixed: 0,
            content: "{nt}"
          }
        },
        {
          title: "今年达标任务个数",
          number: {
            number: [data.content.onLine],
            toFixed: 0,
            content: "{nt}"
          }
        }
      ]
      const interval = setInterval(() => {
        const realTime = new Date();
        timeslot.value =
            resetTime(realTime.getFullYear()) +
            '/'+
            resetTime(realTime.getMonth()+1) +
            '/' +
            resetTime(realTime.getDate()) +
            ' ' +
            resetTime(realTime.getHours()) +
            ':' +
            resetTime(realTime.getMinutes()) +
            ':' +
            resetTime(realTime.getSeconds())
      }, 1000);
      onUnmounted(()=>{
        if(interval){
          clearInterval(interval)
        }
      })
    })
    return {
      ...toRefs(state),
      warnMessage,
      timeslot
    }
  },
  components: {
    centerChart1,
    centerChart2
  }
})
</script>

<style  scoped>
#center {
  color: white;
  display: flex;
  flex-direction: column;
}
.up {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin-left: 30px;
}
.up .item {
  margin-top: 0.5rem;
  border-radius: 5px;
  width: 32%;
  height: 70px;
}
.down {
  padding-top: 3px;
  width: 100%;
  display: flex;
  justify-content: space-between;
}
.down .percent {
  margin-top: 10px;
  width: 40%;
  display: flex;
  flex-wrap: wrap;
}
.down .percent .item {
  width: 50%;
  height: 120px;
}
.down .percent .item span {
  display: flex;
  justify-content: center;
}
.down .card{
  margin-top: 14px;
  margin-left: 20px;
  width: 60%;
  height: 80%;
  margin-bottom: 14px;
  display: flex;
}
</style>