<template>
  <MTheHeader/>
  <van-tabs v-model:active="activeName">
    <van-tab title="新增传感器" name="a">
      <van-row justify="space-around">
        <van-col span="22">
          <admin-physical-value/>
        </van-col>
      </van-row>
      <div style="height: 50px"></div>
    </van-tab>
    <van-tab title="文件上传" name="b">
      <van-row justify="space-around">
        <van-col span="22">
          <FileUpload />
        </van-col>
      </van-row>
      <div style="height: 50px"></div>
    </van-tab>
    <van-tab title="图表相关设置" name="c">
      <van-row justify="space-around">
        <van-col span="22">
          <FileSetting />
        </van-col>
      </van-row>
      <div style="height: 50px"></div>
    </van-tab>
  </van-tabs>
  <MTheFooter/>
</template>
<script lang="ts">

import { defineComponent, ref, watch, onMounted} from "vue";
import FileUpload from '@/components/mobile/m_fileUpload.vue'
import MTheHeader from '@/components/mobile/m_theHeader.vue';
import MTheFooter from '@/components/mobile/m_theFooter.vue';
import AdminPhysicalValue from '../admin/mobile/m_adminPhysicalValue.vue';
import FileSetting from '@/components/mobile/m_fileSetting.vue'

export default defineComponent({
  components: {
    FileUpload,
    MTheHeader,
    MTheFooter,
    AdminPhysicalValue,
    FileSetting
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