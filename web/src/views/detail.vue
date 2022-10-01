<template>
  <the-header></the-header>
  <div  class="components-layout-fixed" >
    <a-layout-content >

      <a-layout style="padding: 10px 0; background: #fff">
        <a-layout-sider width="200"  style="background: #fff; height: 530px" >
          <a-menu
              v-model:selectedKeys="selectedKeys"
              v-model:openKeys="openKeys"
              mode="inline"
              :style="{ height: '100%', borderRight: 0 }"
          >
            <a-sub-menu key="数据查询">
              <template #title>
              <span>
                <database-outlined />
                数据查询
              </span>
              </template>
              <a-menu-item key="历史数据查询" >
                历史数据查询
              </a-menu-item>
              <a-menu-item key="异常波动数据检测">
                异常波动数据检测
              </a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="断点查询">
              <template #title>
                <span>
                  <user-outlined />
                  断点查询
                </span>
              </template>
              <a-menu-item key="断点位置维修情况">
                断点位置维修情况
              </a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="实时形状传感">
              <template #title>
                <span>
                  <user-outlined />
                  实时形状传感
                </span>
              </template>
              <a-menu-item key="二维形状传感">
                二维形状传感
              </a-menu-item>
              <a-menu-item key="三维形状传感">
                三维形状传感
              </a-menu-item>
            </a-sub-menu>
          </a-menu>
        </a-layout-sider>
        <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
          <div>
            <div v-if="'历史数据查询' === selectedKey">
              <n-loading-bar-provider>
                <n-message-provider>
                  <n-notification-provider>
                    <n-dialog-provider>
                      <HistoryDataQuery />
                    </n-dialog-provider>
                  </n-notification-provider>
                </n-message-provider>
              </n-loading-bar-provider>
            </div>
            <div v-else-if="'异常波动数据检测' === selectedKey">
              <n-loading-bar-provider>
                <n-message-provider>
                  <n-notification-provider>
                    <n-dialog-provider>
                      <AbnormalDataQuery />
                    </n-dialog-provider>
                  </n-notification-provider>
                </n-message-provider>
              </n-loading-bar-provider>
            </div>
            <div v-else-if="'断点位置维修情况' === selectedKey">
              <n-loading-bar-provider>
                <n-message-provider>
                  <n-notification-provider>
                    <n-dialog-provider>
                      <Breakpoint/>
                    </n-dialog-provider>
                  </n-notification-provider>
                </n-message-provider>
              </n-loading-bar-provider>
            </div>
            <div v-else-if="'二维形状传感'=== selectedKey">
              <n-loading-bar-provider>
                <n-message-provider>
                  <n-notification-provider>
                    <n-dialog-provider>
                      <TwoDimension />
                    </n-dialog-provider>
                  </n-notification-provider>
                </n-message-provider>
              </n-loading-bar-provider>
            </div>
            <div v-else-if="'三维形状传感' === selectedKey">
              <n-loading-bar-provider>
                <n-message-provider>
                  <n-notification-provider>
                    <n-dialog-provider>
                      <ThreeDimension />
                    </n-dialog-provider>
                  </n-notification-provider>
                </n-message-provider>
              </n-loading-bar-provider>
            </div>
            <div v-else>
              <n-loading-bar-provider>
                <n-message-provider>
                  <n-notification-provider>
                    <n-dialog-provider>
                      <Introduction />
                    </n-dialog-provider>
                  </n-notification-provider>
                </n-message-provider>
              </n-loading-bar-provider></div>
          </div>
        </a-layout-content>
      </a-layout>
    </a-layout-content>
  </div>

</template>
<script>
import { defineComponent, ref, watch, onMounted} from "vue";
import HistoryDataQuery from "../components/historyDataQuery.vue";
import AbnormalDataQuery from "../components/abnormalDataQuery.vue";
import Breakpoint from "../components/breakpoint.vue";
import TwoDimension from "../components/twoDimension.vue";
import ThreeDimension from "../components/threeDimension.vue";
import Introduction from "@/components/sensorlist.vue";
import TheHeader from '@/components/theHeader.vue';
import TheFooter from '@/components/theFooter.vue';
export default defineComponent({
  components: {
    Introduction,
    HistoryDataQuery,
    AbnormalDataQuery,
    Breakpoint,
    TwoDimension,
    ThreeDimension,
    TheHeader,
    TheFooter
  },
  setup(){
    const selectedKeys = ref();
    const openKeys = ref();
    const selectedKey = ref();
    onMounted(()=>{
      watch(selectedKeys,()=>{
        //console.log('selectedKeys=',selectedKeys.value)
        selectedKey.value = selectedKeys.value[0];
      })
    });
    return{
      selectedKeys,
      openKeys,
      selectedKey
    }
  },

});
</script>
<style scoped>
.components-layout-fixed{
  margin-bottom: 70px;
  padding-bottom: 50px;
}
</style>