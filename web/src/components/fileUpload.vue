<template>
  <n-card
      title="📖 上传文件到阿里云OSS对象存储【测试中】，默认是本地程序定时每天中午12点晚上00点上传数据。"
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
          @change="handleChange"
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
    <p class="middlePart">或者选择</p>
    <div class="rightPart">
      <a-upload
          v-model:fileList="fileList2"
          accept=".xlsx, .xls, .csv"
          @change="handleChange2"
          @drop="handleDrop2"
          :remove="handleDownloadFileRemove2"
          :customRequest="uploadFilesCustomRequest2"
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
interface FileItem {
  uid: string;
  name?: string;
  status?: string;
  response?: string;
  url?: string;
}

interface FileInfo {
  file: FileItem;
  fileList: FileItem[];
}
// const info: FileInfo = {
//   file:{uid:'1',name:'file',status:'upload',response:'',url:'baidu.com'},
//   fileList:[{uid:'1',name:'file',status:'upload',response:'',url:'baidu.com'}]
// };
const fileList = ref([])
const fileList2 = ref([])
export default defineComponent({
  components: {
    InboxOutlined,
    TheHeader,
    TheFooter,
    UploadOutlined,
  },
  setup() {
    const uploadFilesCustomRequest = (options: any)=>{
      let formData = new FormData();
      if (options.data) {
        Object.keys(options.data).forEach(key => {
          formData.append(key, options.data[key]);
        });
      }
      formData.append('file',options.file)
      upload(formData).then((res)=>{
        if(res.data) {
          options.onSuccess(res, options.file)
          options.file.status = 'done';
          options.file.url = res.data.url;
        }else{
          options.onError()
          options.file.status = 'error'
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
    const handleChange = (info: FileInfo) => {
      const status = info.file.status;
      console.log(info.file)
      if (status !== 'uploading') {
        console.log(info.file, info.fileList);
      }
      if (status === 'done') {
        message.success(`${info.file.name} file uploaded successfully.`);
      } else if (status === 'error') {
        message.error(`${info.file.name} file upload failed.`);
      }
    };

    const uploadFilesCustomRequest2 = (options: any)=>{
      let formData = new FormData();
      if (options.data) {
        Object.keys(options.data).forEach(key => {
          formData.append(key, options.data[key]);
        });
      }
      formData.append('file',options.file)
      upload(formData).then((res)=>{
        if(res.data) {
          options.onSuccess(res, options.file)
          options.file.status = 'done';
          options.file.url = res.data.url;
        }else{
          options.onError()
          options.file.status = 'error'
        }
      })
    };

    //删除某个已上传的文件的时候，就是调用的这里
    const handleDownloadFileRemove2 = (file: any)=>{
      const index = fileList2.value.indexOf(file as never);
      const newFileList = fileList2.value.slice();
      newFileList.slice(index,1)
      fileList2.value = newFileList;
    };
    const handleChange2 = (info: FileInfo) => {
      const status = info.file.status;
      console.log(info.file)
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
      handleDrop: (e: DragEvent) => {
        console.log("DragEvent",e);
      },
      uploadFilesCustomRequest,
      handleDownloadFileRemove,
      fileList,

      handleChange2,
      handleDrop2: (e: DragEvent) => {
        console.log("DragEvent",e);
      },
      uploadFilesCustomRequest2,
      handleDownloadFileRemove2,
      fileList2,
    };
  },
});
</script>
<style>
.leftPart{
  width: 45%;
  height: 200px
}
.rightPart{
  width: 45%;
  margin-left:10px
}
.middlePart{
  width: 10%;
  height:100%;
  margin-left:70px
}
.upload{
  display: flex;
  flex-direction: row;
  margin-top: 50px;
  width:100%;
  padding-left: 70px;
}
</style>