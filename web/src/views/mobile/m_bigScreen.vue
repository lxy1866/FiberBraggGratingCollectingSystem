<template>
  <MTheHeader/>
  <van-tabs>
    <van-tab title="实时应变值"  >
      <dv-border-box-8 class="border-box">
      <m-line-chart-strain class="charts" style="height: 400px;width: 350px;"/>
      </dv-border-box-8>
    </van-tab>
    <van-tab title="统计概况" >
        <dv-border-box-8 class="border-box">
          <m-center-top  class="charts" style="height: 400px"/>
        </dv-border-box-8>
    </van-tab>
    <van-tab title="实时振动值" >
      <dv-border-box-8 class="border-box">
        <m-line-chart-vibration  class="charts" style="height: 400px;width: 350px;"/>
      </dv-border-box-8>
    </van-tab>
  </van-tabs>
  <van-tabs >
    <van-tab title="实时温度值" >
      <dv-border-box-8 class="border-box">
        <m-real-time-temperature  class="charts" style=" width: 300px; height: 350px; margin-left: 80px"/>
      </dv-border-box-8>
    </van-tab>
    <van-tab title="过去24小时温度最大最小值" >
      <dv-border-box-8 class="border-box">
        <m-line-chart-temperature  class="charts" style="height: 300px;width: 350px;margin-left: 40px"/>
      </dv-border-box-8>
    </van-tab>
    <van-tab title="实时形状" >
      <dv-border-box-8 class="border-box">
        <m-line-3d-shape  class="charts" style="height: 300px; width: 350px; margin-left: 40px"/>
      </dv-border-box-8>
    </van-tab>
  </van-tabs>
  <div style="height: 50px"></div>
  <MTheFooter style="z-index: 1000;"/>
</template>
<script>
import { defineComponent } from "vue";
import MTheHeader from '@/components/mobile/m_theHeader.vue';
import MTheFooter from '@/components/mobile/m_theFooter.vue';
import MLineChartStrain from '@/components/mobile/m_leftTopChart.vue'
import MCenterTop from '@/components/mobile/m_centerTopChart.vue';
import MRealTimeTemperature from '@/components/mobile/m_leftBottomChart.vue'
import MLineChartVibration from '@/components/mobile/m_rightTopChart.vue'
import MLineChartTemperature from '@/components/mobile/m_centerBottomChart.vue'
import MLine3dShape from '@/components/mobile/m_rightBottomChart.vue'
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
<style scoped>
.border-box{
  width: 100%;
  height: 100%;
  display: flex;
  background-color: #001529;
  z-index: 999;
}
.fontClass{
  font-family: 宋体;
}
.charts{
  width: 94%;
  margin: 3%;
  display: flex;
}
</style>