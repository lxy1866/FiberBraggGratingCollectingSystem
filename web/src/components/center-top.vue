<template>
  <div id="center">
    <div class="up">
      <div class="bg-color-black item" v-for="item in titleItem" :key="item.title">
        <p class="ml-3 colorBlue fw-b">{{item.title}}</p>
        <div>
          <dv-digital-flop :config="item.number" style="width:100px;height:50px;" />
        </div>
      </div>
    </div>
    <div class="down">
      <div class="percent">
        <div class="item  bg-color-black">
          <span>今日在线率</span>
          <centerChart1 />
        </div>
        <div class="item  bg-color-black">
          <span>今日任务达标率</span>
          <centerChart2 />
        </div>
      </div>
      <div class="card">
        <n-card title="预警信息"
                font-color="white"
                style="--n-color: #001529;
                  --n-title-font-size: 15px;
                  --n-title-text-color: white"
        >
          <p style="color: red">{{warnMessage}}</p>
        </n-card>
      </div>
    </div>
  </div>
</template>

<script>
import centerChart1 from "./centerChart1.vue";
import centerChart2 from "./centerChart2.vue";
import {defineComponent} from "vue";
const warnMessage = '暂无'
export default defineComponent({
  name: 'center-top',
  setup() {
    return {
      titleItem: [
        {
          title: "今日传感器总量",
          number: {
            number: [120],
            toFixed: 1,
            content: "{nt}"
          }
        },
        {
          title: "今日传感器在线个数",
          number: {
            number: [18],
            toFixed: 1,
            content: "{nt}"
          }
        },
        {
          title: "今日累计异常波动次数",
          number: {
            number: [2],
            toFixed: 1,
            content: "{nt}"
          }
        },
        {
          title: "今年累计异常波动次数",
          number: {
            number: [14],
            toFixed: 1,
            content: "{nt}"
          }
        },
        {
          title: "今年成功任务次数",
          number: {
            number: [106],
            toFixed: 1,
            content: "{nt}"
          }
        },
        {
          title: "今年达标任务个数",
          number: {
            number: [100],
            toFixed: 1,
            content: "{nt}"
          }
        }
      ],
      water: {
        data: [24, 45],
        shape: 'roundRect',
        formatter:'{value}%',
        waveNum:3
      },
      warnMessage
    };
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
}
</style>