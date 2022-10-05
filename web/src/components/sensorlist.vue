<template>
  <n-card
      title="📖 如果想要新增传感器，请到点击菜单栏上的新增传感器"
      embedded
      :bordered="false"
      style="margin-bottom: 20px"
  >
  </n-card>
  <span>
      <a-button type="primary" @click="showModalCodeVisible" ghost style="display: flex; margin-bottom: 20px">
        验证授权码
      </a-button>
  </span>

  <div>
    <div class="fontClass">光纤光栅振动传感器列表</div>
    <a-table
        :columns="columns"
        :row-key="record => record.id"
        :data-source="dataVibration"
    >
      <template v-slot:action="{ text, record }">
        <a-space size="small">
          <a-button type="primary" @click="modify(record)" ghost :disabled="codeIsRight">
            修改
          </a-button>
          <a-popconfirm
              title="是否确认删除"
              ok-text="是"
              cancel-text="否"
              @confirm="handleDelete(record)"
          >
            <a-button type="danger" ghost :disabled="codeIsRight">
              删除
            </a-button>
          </a-popconfirm>
        </a-space>
      </template>
    </a-table>
  </div>

  <div>
    <div class="fontClass">光纤光栅应变传感器列表</div>
    <a-table
        :columns="columns"
        :row-key="record => record.id"
        :data-source="dataStrain"
    >
      <template v-slot:action="{ text, record }">
        <a-space size="small">
          <a-button type="primary" @click="modify(record)" ghost :disabled="codeIsRight">
            修改
          </a-button>
          <a-popconfirm
              title="是否确认删除"
              ok-text="是"
              cancel-text="否"
              @confirm="handleDelete(record)"
          >
            <a-button type="danger" ghost :disabled="codeIsRight" >
              删除
            </a-button>
          </a-popconfirm>
        </a-space>
      </template>
    </a-table>
  </div>
  <div>
    <div class="fontClass">光纤光栅温度传感器列表</div>
    <a-table
        :columns="columns"
        :row-key="record => record.id"
        :data-source="dataTemperature"
    >
      <template v-slot:action="{ text, record }">
        <a-space size="small">
          <a-button type="primary" @click="modify(record)" ghost :disabled="codeIsRight">
            修改
          </a-button>
          <a-popconfirm
              title="是否确认删除"
              ok-text="是"
              cancel-text="否"
              @confirm="handleDelete(record)"
          >
            <a-button type="danger" ghost :disabled="codeIsRight">
              删除
            </a-button>
          </a-popconfirm>
        </a-space>
      </template>
    </a-table>
  </div>
  <n-modal
      v-model:show="addModalVisible"
      preset="dialog"
      title="修改"
      positive-text="确认"
      negative-text="取消"
      @positive-click="handleAddModalOk"
      @negative-click="onNegativeClick"
  >
    <n-input
        v-model:value="fbgRecord.min"
        placeholder="量程的最小值"
        :maxlength="8"
    />
    <br/><br/>
    <n-input
        v-model:value="fbgRecord.max"
        placeholder="量程的最大值"
        :maxlength="8"
    />
    <br/><br/>
    <n-input
        v-model:value="fbgRecord.distance"
        placeholder="初始位置"
        :maxlength="8"
    />
    <br/><br/>
    <n-input
        v-model:value="fbgRecord.channel"
        placeholder="通道号"
        :maxlength="8"
    />
    <br/><br/>
    <n-input
        v-model:value="fbgRecord.arrayNum"
        placeholder="阵列序号"
        :maxlength="8"
    />
  </n-modal>
  <n-modal
      v-model:show="modalCodeVisible"
      :mask-closable="false"
      preset="dialog"
      title="输入授权码"
      positive-text="确认"
      negative-text="取消"
      @positive-click="onPositiveClickCode"
      @negative-click="onNegativeClick"
  >
    <n-input
        v-model:value="code"
        type="password"
        show-password-on="mousedown"
        placeholder="密码"
        :maxlength="8"
    />
  </n-modal>

</template>

<script lang="ts">
import {defineComponent, ref, onMounted} from 'vue'
import axios from "axios";
import {message} from "ant-design-vue";
import {Tool} from "@/util/tool";
declare let formatDate: any;
let dataVibration = ref();
let dataStrain = ref();
let dataTemperature = ref();
const modalCodeVisible = ref(false);
let addModalVisible = ref(false);
// -------- 表单 ---------
const fbgRecord = ref();
const code = ref();
const codeIsRight = ref(true);
const columns = [
  {
    title: '传感器名称',
    key:'propertyName',
    dataIndex: 'propertyName'
  },
  {
    title: '量程的最小值',
    key: 'min',
    dataIndex: 'min'
  },
  {
    title:'量程的最大值',
    key: 'max',
    dataIndex: 'max'
  },
  {
    title: '初始位置（单位米）',
    key:'distance',
    dataIndex: 'distance'
  },
  {
    title: '创建时间',
    key: 'createTime',
    dataIndex: 'createTime'
  },
  {
    title: '类别（1应变，2温度，3振动）',
    key:'category',
    dataIndex: 'category'
  },
  {
    title:'通道号',
    key:'channel',
    dataIndex: 'channel'
  },
  {
    title:'阵列序号',
    key:'arrayNum',
    dataIndex: 'arrayNum'
  },
  {
    title: 'Action',
    key:'action',
    slots: { customRender: 'action' }
  },
];
const formatDateWrapper = (time:any)=>{
  const data = new Date(time);
  return formatDate(data,'yyyy-MM-dd hh:mm:ss');
}
const onNegativeClick =()=> {
  message.success('Cancel')
  addModalVisible.value = false
};
const showModalCodeVisible = ()=>{
  modalCodeVisible.value = true;
}
const handleDelete = (record: any) => {
    axios.delete("/nr/delete/" + record.propertyName).then((response) => {
      const data = response.data; // data = commonResp
      if (data.success) {
        // 重新加载列表
        handleQueryVibration();
        handleQueryStrain();
        handleQueryTemperature();
      } else {
        message.error(data.message);
      }
    });
};
const modify = (record: any)=>{
  addModalVisible.value = true
  fbgRecord.value = Tool.copy(record);
  fbgRecord.value.min = null;
  fbgRecord.value.max = null;
  fbgRecord.value.distance = null;
  fbgRecord.value.channel = null;
  fbgRecord.value.arrayNum = null;
}
const onPositiveClickCode = ()=> {
  //验证授权码是否正确
  if(code.value === 'test'){
    message.success('授权码正确')
    modalCodeVisible.value = false;
    codeIsRight.value = false;
  }else{
    message.success('授权码错误')
  }
}
const handleQueryVibration = ()=>{
  axios.get("/nr/vibrationDistance").then(function (response){
    //console.log(response);
    response.data.content.forEach((item:any)=>{
      item.createTime =  formatDateWrapper(Number(item.createTime));
    })
    dataVibration.value = response.data.content;
  })
}
const handleQueryStrain = ()=>{
  axios.get("/nr/strainDistance").then(function (response){
    //console.log(response);
    response.data.content.forEach((item:any)=>{
      item.createTime =  formatDateWrapper(Number(item.createTime));
    })
    dataStrain.value = response.data.content;
  })
}
const handleQueryTemperature = () => {
  axios.get("/nr/temperature").then(function (response){
    //console.log(response);
    response.data.content.forEach((item:any)=>{
      item.createTime =  formatDateWrapper(Number(item.createTime));
    })
    dataTemperature.value = response.data.content;
  })
}
const handleAddModalOk = () => {
  axios.post("/nr/update", fbgRecord.value).then((response) => {
    const data = response.data;
    if (data.success) {
      addModalVisible.value = false;
      message.success(data.message);
      handleQueryVibration();
      handleQueryTemperature();
      handleQueryStrain()
    } else {
      message.error(data.message);
    }
  });
};
export default defineComponent({
  setup () {

    onMounted(()=>{
      handleQueryVibration();
      handleQueryStrain();
      handleQueryTemperature();
    });
    return {
      columns,
      dataVibration,
      dataStrain,
      dataTemperature,
      handleDelete,
      modify,
      addModalVisible,
      handleAddModalOk,
      onNegativeClick,
      fbgRecord,
      showModalCodeVisible,
      modalCodeVisible,
      onPositiveClickCode,
      code,
      codeIsRight
    }
  }
})
</script>
<style scoped>
.fontClass{
  margin-bottom: 20px;
  font-family: 宋体;
  color: #001529;
}
</style>