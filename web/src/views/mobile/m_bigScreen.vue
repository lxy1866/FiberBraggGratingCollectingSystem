<template>
  <MTheHeader/>
  <van-tabs v-model:active="activeName">
    <van-tab title="海缆实时应变值" name="a">
      <dv-border-box-8 class="border-box">
      <m-line-chart-strain class="charts" style="height: 450px"/>
      </dv-border-box-8>
    </van-tab>

    <van-tab title="海缆统计概况" name="b">
      <dv-border-box-8 class="border-box">
        <m-center-top  class="charts" style="height: 370px"/>
      </dv-border-box-8>
    </van-tab>
    <van-tab title="海缆实时振动值" name="c">
      <dv-border-box-8 class="border-box">
        <m-line-chart-vibration  class="charts" style="height: 300px"/>
      </dv-border-box-8>
    </van-tab>
  </van-tabs>
  <van-tabs v-model:active="activeName">
    <van-tab title="海缆实时温度值" name="a">
      <dv-border-box-8 class="border-box">
        <m-real-time-temperature  class="charts" style=" width: 300px; height: 300px; margin-left: 80px"/>
      </dv-border-box-8>
    </van-tab>
    <van-tab title="过去24小时温度最大最小值" name="b">
      <dv-border-box-8 class="border-box">
        <m-line-chart-temperature  class="charts" style="height: 300px;"/>
      </dv-border-box-8>
    </van-tab>
    <van-tab title="海缆实时形状" name="c">
      <dv-border-box-8 class="border-box">
        <m-line-3d-shape  class="charts" style="height: 300px; width: 300px; margin-left: 30px"/>
      </dv-border-box-8>
    </van-tab>
  </van-tabs>
  <div style="height: 50px"></div>
  <MTheFooter/>
</template>
<script>
import { defineComponent } from "vue";
import MTheHeader from '@/components/mobile/m_the-header.vue';
import MTheFooter from '@/components/mobile/m_the-footer.vue';
import MLineChartStrain from '@/components/mobile/m_line-chart-strain.vue'
import MCenterTop from '@/components/mobile/m_center-top.vue';
import MRealTimeTemperature from '@/components/mobile/m_realTemperature.vue'
import MLineChartVibration from '@/components/mobile/m_line-chart-vibration.vue'
import MLineChartTemperature from '@/components/mobile/m_line-chart-temperature.vue'
import MLine3dShape from '@/components/mobile/m_line-3d-shape.vue'
export default defineComponent({
  name:'m_bigScreen',
  components:{
    MTheHeader,
    MTheFooter,
    MLineChartStrain,
    MCenterTop,
    MRealTimeTemperature,
    MLineChartVibration,
    MLineChartTemperature,
    MLine3dShape
  }
})
</script>
<style>
.border-box{
  width: 100%;
  height: 100%;
  display: flex;
}
.charts{
  width: 94%;
  margin: 3%;
  display: flex;
}
</style>