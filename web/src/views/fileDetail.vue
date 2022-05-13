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
            <a-sub-menu key="设置">
              <template #title>
              <span>
                <database-outlined />
                设置
              </span>
              </template>
              <a-menu-item key="文件上传" >
                文件上传
              </a-menu-item>
              <a-menu-item key="文件读取">
                文件路径读取
              </a-menu-item>
            </a-sub-menu>
          </a-menu>
        </a-layout-sider>
        <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
          <div>
            <div v-if="'文件上传' === selectedKey">
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
            <div v-else-if="'文件读取' === selectedKey">
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
export default defineComponent({
  components: {
    FileUpload,
    TheHeader,
    TheFooter
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