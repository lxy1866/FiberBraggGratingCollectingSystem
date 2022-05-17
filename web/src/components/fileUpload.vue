<template>
  <n-card
      title="📖 上传文件【测试中】"
      embedded
      :bordered="false"
  >
  </n-card>
  <div class="upload">
    <div class="leftPart">
      <a-upload-dragger
          v-model:fileList="fileList"
          accept=".xlsx, .xls, .csv"
          name="file"
          :multiple="true"
          @drop="handleDrop"
          :remove="handleDownloadFileRemove"
          :customRequest="uploadFilesCustomRequest"
      >
        <p class="ant-upload-drag-icon">
          <inbox-outlined></inbox-outlined>
        </p>
        <p class="ant-upload-text">点击或者拖拽到此区域进行上传</p>
        <p class="ant-upload-hint">
          支持单个或批量文件上传
        </p>
      </a-upload-dragger>
    </div>
    <div class="rightPart">
      <a-upload
          accept=".xlsx, .xls, .csv"
          directory>
        <a-button>
          <upload-outlined></upload-outlined>
          上传整个文件夹
        </a-button>
      </a-upload>
    </div>
  </div>
</template>
<script lang="ts">
import TheHeader from "@/components/theHeader.vue"
import TheFooter from "@/components/theFooter.vue";
import { InboxOutlined, UploadOutlined } from '@ant-design/icons-vue';
import { message } from 'ant-design-vue';
import { defineComponent, ref } from 'vue';
import axios from "axios";
const fileList = ref([]);
let fileUrl = '';
export default defineComponent({
  components: {
    InboxOutlined,
    TheHeader,
    TheFooter,
    UploadOutlined,
  },
  setup() {
    const uploadFilesCustomRequest = (file: any)=>{
      let formData = new FormData();
      formData.append('file',file.file)
      upload(formData).then((res)=>{
        if(res.data.success) {
          message.success("上传成功")
          fileUrl = res.data.content.url
        }else{
          message.error("上传失败")
        }
      })
    };
    const upload = (parameter:any)=>{
      return axios({
        url: '/fileUpload/uploadOneFile',
        method: 'post',
        headers: {
          'Content-Type': 'multipart/form-data',  //成功的关键
        },
        data: parameter
      })
    }
    //删除某个已上传的文件的时候，就是调用的这里
    const handleDownloadFileRemove = (file: any)=>{
      const index = fileList.value.indexOf(file as never);
      const newFileList = fileList.value.slice();
      newFileList.slice(index,1)
      fileList.value = newFileList;
    };
    const fileFormatter = (data: any)=>{
      let file = {
        uid:data.uuid,
        name:data.name,
        status:'done',
        response: '{"status": "success"}', //服务端响应内容
      }
      return file
    }
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
      fileList,
      handleDrop: (e: DragEvent) => {
        console.log("DragEvent",e);
      },
      fileUrl,
      uploadFilesCustomRequest,
      handleDownloadFileRemove
    };
  },
});
</script>
<style>
.leftPart{
  width: 50%;
  height: 200px
}
.rightPart{
  width: 50%;
  padding-left: 20%;
}
.upload{
  display: flex;
  flex-direction: row;
  margin-top: 50px;
  width:100%;
  padding-left: 70px;
}
</style>