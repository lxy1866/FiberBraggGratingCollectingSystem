<template>
  <MTheHeader/>
    <van-tabs v-model:active="activeName">
      <van-tab title="历史数据查询" name="a">
        <van-row justify="space-around">
          <van-col span="22">
            <m-history-data-query/>
          </van-col>
        </van-row>
        <div style="height: 50px"></div>
      </van-tab>
      <van-tab title="异常波动数据检测" name="b">
        <van-row justify="space-around">
          <van-col span="22">
            <m-abnormal-data-query/>
          </van-col>
        </van-row>
        <div style="height: 50px"></div>
      </van-tab>
      <van-tab title="断点位置维修情况" name="c">
        <van-row justify="space-around">
          <van-col span="22">
            <m-breakpoint/>
          </van-col>
        </van-row>
        <div style="height: 50px"></div>
      </van-tab>
    </van-tabs>
  <MTheFooter/>
</template>
<script>
import { defineComponent } from "vue";
import MTheHeader from '@/components/mobile/m_the-header.vue';
import MTheFooter from '@/components/mobile/m_the-footer.vue';
import MHistoryDataQuery from '@/components/mobile/m_historyDataQuery.vue';
import MAbnormalDataQuery from '@/components/mobile/m_abnormalDataQuery.vue';
import MBreakpoint from '@/components/mobile/m_breakpoint.vue'
import MTwoDimension from '@/components/mobile/m_twoDimension.vue'
import {Toast} from "vant";
export default defineComponent({
  name: 'm_detail',
  components:{
    MTheHeader,
    MTheFooter,
    MHistoryDataQuery,
    MAbnormalDataQuery,
    MBreakpoint,
    MTwoDimension
  },
})
</script>