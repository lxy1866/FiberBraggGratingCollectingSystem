<template>
  <the-header></the-header>
  <a-layout-content style=" margin-bottom: 70px ">
    <a-breadcrumb style="margin: 16px 0">

    </a-breadcrumb>
    <a-layout style="padding: 24px 0; background: #fff">
      <div class="wrapper" style="margin-left: 20px">
    <n-timeline>
      <n-timeline-item
          type="success"
          title="基于曲率信息的曲面重构算法"
          content="结构曲率的FBG检测技术、基于曲率的曲面重构算法、基于曲面片拼接的曲面重构算法、算法仿真分析"
          time="2018-04-03 20:46"
      />
      <n-timeline-item
          type="error"
          title="基于正交曲率信息的空间曲线重构算法"
          content="基于坐标变换的平面曲线拟合算法、空间曲线基本理论、基于正交曲率的空间曲线重构算法"
          time="2018-04-03 20:46" />
      <n-timeline-item
          type="warning"
          title="离散曲率连续化插值方法分析"
          content="线性插值法、平方平均法、二次函数法、B 样条插值法"
          time="2018-04-03 20:46"
      />
      <n-timeline-item
          type="info"
          title="实验模型结构光栅测点曲率连续化研究"
          content="实验模型结构光栅测点曲率连续化研究、翼面结构光栅测点曲率连续化方法"
          time="2018-04-03 20:46"
      />
      <n-timeline-item
          type="success"
          title="空间曲线重构算法仿真分析"
          time="2018-04-03 20:46"
      />
      <n-timeline-item
          type="error"
          title="FBG传感阵列设计与封装"
          content="FBG曲率检测单元设计、光栅点中心波长确定、FBG正交曲率检测阵列单元封装"
          time="2018-04-03 20:46"
      />
      <n-timeline-item
          type="warning"
          title="FBG曲率检测单元实验分析与验证"
          content="光纤光栅标定实验、FBG曲率检测单元形态感知与可视化重构实验分析"
          time="2018-04-03 20:46"
      />
      <n-timeline-item
          type="info"
          title="实验模型结构形态感知与重构实验分析与验证"
          content="框架结构动态形态感知重构实验、翼面结构的形态感知重构实验"
          time="2018-04-03 20:46"
      />
      <n-timeline-item
          type="success"
          title="实验模型结构重构精度评价"
          content="光栅测点的实际偏移量激光测量、框架结构形态感知实验效果评价、翼面结构形态感知实验效果评价"
          time="2018-04-03 20:46"
      />
      <n-timeline-item
          type="error"
          title="实验误差分析"
          content="温度变化所引起的误差及补偿、封装制作引起的误差、标定误差、重构算法所引起的误差"
          time="2018-04-03 20:46"
      />
    </n-timeline>
  </div>
    </a-layout>
  </a-layout-content>
  <the-footer></the-footer>

</template>
<script lang="ts">
import {defineComponent} from "vue";
import TheHeader from '@/components/the-header.vue';
import TheFooter from '@/components/the-footer.vue';
export default defineComponent({
  components:{
    TheHeader,
    TheFooter
  },
  setup(){

  }
})
</script>
<style scoped>
.wrapper{
  display: flex;
  justify-content: space-between;
}
</style>