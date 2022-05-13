<template>

  <div class="upload">
    <a-upload-dragger
        v-model:fileList="fileList"
        name="file"
        :multiple="true"
        action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
        @change="handleChange"
        @drop="handleDrop"
    >
      <p class="ant-upload-drag-icon">
        <inbox-outlined></inbox-outlined>
      </p>
      <p class="ant-upload-text">点击或者拖拽到此区域进行上传</p>
      <p class="ant-upload-hint">
        支持单个或批量上传
      </p>
    </a-upload-dragger>
  </div>
</template>
<script lang="ts">
import TheHeader from "@/components/theHeader.vue"
import TheFooter from "@/components/theFooter.vue";
import { InboxOutlined } from '@ant-design/icons-vue';
import { message } from 'ant-design-vue';
import { defineComponent, ref } from 'vue';
//import type { UploadChangeParam } from 'ant-design-vue';

export default defineComponent({
  components: {
    InboxOutlined,
    TheHeader,
    TheFooter
  },
  setup() {
    const handleChange = (info: { file: { status: any; name: any; }; fileList: any; }) => {
      const status = info.file.status;
      if (status !== 'uploading') {
        console.log(info.file, info.fileList);
      }
      if (status === 'done') {
        message.success(`${info.file.name} file uploaded successfully.`);
      } else if (status === 'error') {
        message.error(`${info.file.name} file upload failed.`);
      }
    };
    return {
      handleChange,
      fileList: ref([]),
      handleDrop: (e: DragEvent) => {
        console.log(e);
      },
    };
  },
});
</script>
<style>
.upload{
  margin-top: 100px;
  margin-left: 20%;
  margin-right: 20%;
}
</style>