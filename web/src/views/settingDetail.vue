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
            <a-sub-menu key="传感器设置">
              <template #title>
              <span>
                <database-outlined />
                传感器设置
              </span>
              </template>
              <a-menu-item key="新增传感器" >
                新增传感器
              </a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="文件读取设置">
              <template #title>
              <span>
                <database-outlined />
                首页展示读取设置
              </span>
              </template>
              <a-menu-item key="文件上传" >
                文件上传
              </a-menu-item>
              <a-menu-item key="读取" >
                图表相关设置
              </a-menu-item>
            </a-sub-menu>
          </a-menu>
        </a-layout-sider>
        <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
          <div>
            <div v-if="'新增传感器' === selectedKey">
              <n-loading-bar-provider>
                <n-message-provider>
                  <n-notification-provider>
                    <n-dialog-provider>
                      <admin-physical-value/>
                    </n-dialog-provider>
                  </n-notification-provider>
                </n-message-provider>
              </n-loading-bar-provider>
            </div>
            <div v-else-if="'文件上传' === selectedKey">
              <n-loading-bar-provider>
                <n-message-provider>
                  <n-notification-provider>
                    <n-dialog-provider>
                      <FileUpload />
                    </n-dialog-provider>
                  </n-notification-provider>
                </n-message-provider>
              </n-loading-bar-provider>
            </div>
            <div v-else-if="'读取' === selectedKey">
              <n-loading-bar-provider>
                <n-message-provider>
                  <n-notification-provider>
                    <n-dialog-provider>
                      <FileSetting />
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

  <the-footer></the-footer>
</template>
<script>

import { defineComponent, ref, watch, onMounted} from "vue";
import FileUpload from '@/components/fileUpload.vue'
import TheHeader from '@/components/theHeader.vue';
import TheFooter from '@/components/theFooter.vue';
import AdminPhysicalValue from './admin/adminPhysicalValue.vue';
import FileSetting from '@/components/fileSetting.vue'

export default defineComponent({
  components: {
    FileUpload,
    TheHeader,
    TheFooter,
    AdminPhysicalValue,
    FileSetting
  },
  setup(){
    const selectedKeys = ref();
    const openKeys = ref();
    const selectedKey = ref();
    onMounted(()=>{
      watch(selectedKeys,()=>{
        console.log('selectedKeys=',selectedKeys.value)
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