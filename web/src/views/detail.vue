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
              <a-menu-item key="光纤光栅传感器历史数据" title="光纤光栅传感器历史数据">
                光纤光栅传感器历史数据
              </a-menu-item>
              <a-menu-item key="MEMS传感器历史数据" title="MEMS传感器历史数据">
                MEMS传感器历史数据
              </a-menu-item>
<!--              <a-menu-item key="异常波动数据检测">-->
<!--                异常波动数据检测-->
<!--              </a-menu-item>-->
            </a-sub-menu>
            <a-sub-menu key="传感器列表">
              <template #title>
              <span>
                <database-outlined />
                传感器列表
              </span>
              </template>
              <a-menu-item key="属性查询" >
                属性查询
              </a-menu-item>
              <a-menu-item key="新增传感器" >
                新增传感器
              </a-menu-item>
            </a-sub-menu>

<!--            <a-sub-menu key="断点查询">-->
<!--              <template #title>-->
<!--                <span>-->
<!--                  <user-outlined />-->
<!--                  断点查询-->
<!--                </span>-->
<!--              </template>-->
<!--              <a-menu-item key="断点位置维修情况">-->
<!--                断点位置维修情况-->
<!--              </a-menu-item>-->
<!--            </a-sub-menu>-->
<!--            <a-sub-menu key="实时形状传感">-->
<!--              <template #title>-->
<!--                <span>-->
<!--                <database-outlined />-->
<!--                  实时形状传感-->
<!--                </span>-->
<!--              </template>-->
<!--&lt;!&ndash;              <a-menu-item key="二维形状传感">&ndash;&gt;-->
<!--&lt;!&ndash;                二维形状传感&ndash;&gt;-->
<!--&lt;!&ndash;              </a-menu-item>&ndash;&gt;-->
<!--&lt;!&ndash;              <a-menu-item key="other">&ndash;&gt;-->
<!--&lt;!&ndash;                other&ndash;&gt;-->
<!--&lt;!&ndash;              </a-menu-item>&ndash;&gt;-->
<!--            </a-sub-menu>-->
          </a-menu>
        </a-layout-sider>
        <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
          <div>
            <div v-if="'属性查询' === selectedKey">
              <n-loading-bar-provider>
                <n-message-provider>
                  <n-notification-provider>
                    <n-dialog-provider>
                      <SensorList />
                    </n-dialog-provider>
                  </n-notification-provider>
                </n-message-provider>
              </n-loading-bar-provider>
            </div>
            <div v-else-if="'新增传感器' === selectedKey">
              <n-loading-bar-provider>
                <n-message-provider>
                  <n-notification-provider>
                    <n-dialog-provider>
                      <AdminPhysicalValue />
                    </n-dialog-provider>
                  </n-notification-provider>
                </n-message-provider>
              </n-loading-bar-provider>
            </div>
            <div v-else-if="'光纤光栅传感器历史数据' === selectedKey">
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
            <div v-else-if="'MEMS传感器历史数据' === selectedKey">
              <n-loading-bar-provider>
                <n-message-provider>
                  <n-notification-provider>
                    <n-dialog-provider>
                      <Query />
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
<!--            <div v-else-if="'二维形状传感'=== selectedKey">-->
<!--              <n-loading-bar-provider>-->
<!--                <n-message-provider>-->
<!--                  <n-notification-provider>-->
<!--                    <n-dialog-provider>-->
<!--                      <TwoDimension />-->
<!--                    </n-dialog-provider>-->
<!--                  </n-notification-provider>-->
<!--                </n-message-provider>-->
<!--              </n-loading-bar-provider>-->
<!--            </div>-->
<!--            <div v-else-if="'other'=== selectedKey">-->
<!--              <n-loading-bar-provider>-->
<!--                <n-message-provider>-->
<!--                  <n-notification-provider>-->
<!--                    <n-dialog-provider>-->
<!--                      <TwoDimension />-->
<!--                    </n-dialog-provider>-->
<!--                  </n-notification-provider>-->
<!--                </n-message-provider>-->
<!--              </n-loading-bar-provider>-->
<!--            </div>-->
            <div v-else>
              <n-loading-bar-provider>
                <n-message-provider>
                  <n-notification-provider>
                    <n-dialog-provider>
                      <SensorList />
                    </n-dialog-provider>
                  </n-notification-provider>
                </n-message-provider>
              </n-loading-bar-provider>
            </div>
          </div>
        </a-layout-content>
      </a-layout>
    </a-layout-content>
  </div>
</template>
<script >
import { defineComponent, ref, watch, onMounted} from "vue";
import HistoryDataQuery from "../components/historyDataQuery.vue";
import AbnormalDataQuery from "../components/abnormalDataQuery.vue";
import Breakpoint from "../components/breakpoint.vue";
import TwoDimension from "../components/twoDimension.vue";
import ThreeDimension from "../components/threeDimension.vue";
import TheHeader from '@/components/theHeader.vue';
import TheFooter from '@/components/theFooter.vue';
import SensorList from "@/components/sensorlist.vue";
import Query from "@/components/MEMSQuery.vue"
import AdminPhysicalValue from "@/views/admin/adminPhysicalValue";

export default defineComponent({
  components: {
    AdminPhysicalValue,
    SensorList,
    Query,
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