<template>
  <MTheHeader/>
  <van-tabs>
    <van-tab title="管道位移数据"  >
      <dv-border-box-8 class="border-box">
<!--      <m-bar-pipe-displayment class="charts" style="height: 400px;width: 360px"/>-->
        <m-left-top-temp class="charts" style="height: 600px;width: 360px"/>
      </dv-border-box-8>
    </van-tab>
    <van-tab title="统计概况" >
        <dv-border-box-8 class="border-box">
          <m-center-top class="charts" style="height: 400px"></m-center-top>
        </dv-border-box-8>
    </van-tab>
  </van-tabs>
  <van-tabs>
    <van-tab title="一个月内管道位移曲线图" >
      <dv-border-box-8 class="border-box">
        <m-line-chart-displayment class="charts" style="height: 300px;width: 360px"/>
      </dv-border-box-8>
    </van-tab>
    <van-tab title="管道三维形状" >
      <dv-border-box-8 class="border-box">
        <m-line3d-shape  class="charts" style="height: 300px;width: 360px"/>
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
import MBarPipeDisplayment from '@/components/mobile/m_leftTopChart.vue'
import MCenterTop from '@/components/mobile/m_centerTopChart.vue'
import MLine3dShape from '@/components/mobile/m_rightTopChart'
import MLineChartDisplayment from '@/components/mobile/m_centerBottomChart.vue'
import MLeftTopTemp from '@/components/mobile/m_leftTopTemp.vue'
export default defineComponent({
  name:'m_bigScreen',
  components:{
    MTheHeader,
    MTheFooter,
    MBarPipeDisplayment,
    MCenterTop,
    MLine3dShape,
    MLineChartDisplayment,
    MLeftTopTemp
  }
})
</script>
<style scoped>
.border-box{
  width: 100%;
  height: 100%;
  display: flex;
  background-color: #053343;
  z-index: 999;
}
.charts{
  width: 94%;
  margin: 3%;
  display: flex;
}
.van-tab{
  color: cadetblue;
}
/deep/.van-tabs__nav {
  background: #053343;
}
/deep/.van-tab__text{
  color:cadetblue;
}
/deep/.van-tab--active {
  color: cadetblue;
  font-weight: var(--van-font-weight-bold);
}
</style>