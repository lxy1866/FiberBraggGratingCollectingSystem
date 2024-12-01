<template>
  <div id="center">
    <div class="up">
      <div class="bg-color-white item" v-for="item in titleItem" :key="item.title">
        <p style="font-size: 14px" class="fontClass">{{item.title}}</p>
        <div  style="width:70%;height: 70%; color: wheat; display: flex; justify-content: center; align-items: center" >{{item.number}}</div>
      </div>
    </div>
    <div class="down">
      <div class="percent">
        <div class="item  bg-color-white">
          <span style="font-size: 12px" class="fontClass">今日在线率</span>
          <MCenterChart1 />
        </div>
        <div class="item  bg-color-white">
          <span style="font-size: 12px" class="fontClass">今日任务达标率</span>
          <MCenterChart2 />
        </div>
      </div>
      <div class="card">
        <n-card
        style="background: #809ed4">
          <div style="font-size: 10px;color: #c48d29" class="fontClass">海缆健康状态预警信息</div>
          <p style="color: #c48d29" class="fontClass">{{warnMessage}}</p>
        </n-card>
      </div>
    </div>
  </div>
</template>
<script>
import MCenterChart1 from './m_centerChart1.vue';
import MCenterChart2 from './m_centerChart2.vue';
import {defineComponent, onMounted, toRefs,reactive} from "vue";
import axios from "axios";

const warnMessage = '暂无'
function handleQueryOnline() {
  return axios.get("/ct/calculateOnLine")
}
export default defineComponent({
  name: 'm_center-top',
  components: {
    MCenterChart1,
    MCenterChart2
  },
  setup() {
    const state =  reactive({
      titleItem: [
        {
          title: "今日传感器总量",
          number: 16
        },
        {
          title: "今日传感器在线个数",
          number: 16
        },
        {
          title: "今日累计异常波动次数",
          number: 15
        },
        {
          title: "今年累计异常波动次数",
          number: 15
        },
        {
          title: "今年成功任务次数",
          number: 0
        },
        {
          title: "今年达标任务个数",
          number: 0
        }
      ]
    })

    /**
     * 大概清楚了，就是要新建一个newTitleItem 设置里面的值来替换state中的titleItem
     */
    onMounted(async ()=>{
      const { data } = await handleQueryOnline();
      //console.log(data.content)
      // state.titleItem = [{
      //   title: "今日传感器总量",
      //   number: data.content.total
      // },
      //   {
      //     title: "今日传感器在线个数",
      //     number: data.content.onLine
      //   },
      //   {
      //     title: "今日累计异常波动次数",
      //     number: data.content.abnormalTimes
      //   },
      //   {
      //     title: "今年累计异常波动次数",
      //     number: data.content.thisYearAbnormalTimes
      //   },
      //   {
      //     title: "今年成功任务次数",
      //     number: data.content.onLine
      //   },
      //   {
      //     title: "今年达标任务个数",
      //     number: data.content.onLine
      //   }
      // ]
    })
    return {
      ...toRefs(state),
      warnMessage,
    }
  },
})
</script>


<style  scoped>
.fontClass{
  font-family: 宋体;
}
#center {
  color: white;
  display: flex;
  flex-direction: column;
}
.up {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  margin-left: 0px;
}
.up .item {
  margin-top: 0.5rem;
  border-radius: 5px;
  width: 40%;
  height: 67px;
}
.down {
  padding-top: 3px;
  width: 100%;
  display: flex;
  justify-content: space-between;
}
.down .percent {
  margin-top: 10px;
  width: 50%;
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
  margin-left: 10px;
  margin-top:20px;
  width: 50%;
  height: fit-content;
  display: flex;
}
</style>