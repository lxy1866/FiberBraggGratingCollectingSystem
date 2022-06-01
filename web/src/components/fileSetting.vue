<template>

  <a-button type="primary" @click="showDrawer1">
    <template #icon><appstore-two-tone /></template>
    设置首页管道位移数据相关属性
  </a-button>
  <br/>
  <br/>
  <a-button type="primary" @click="showDrawer2">
    <template #icon><appstore-two-tone /></template>
    设置首页一个月内管道位移变化曲线图相关属性
  </a-button>
  
  <a-drawer
      title="首页管道位移数据相关属性"
      :width="720"
      :visible="visible1"
      :body-style="{ paddingBottom: '80px' }"
      :footer-style="{ textAlign: 'right' }"
      @close="onClose1"
  >
    <a-form :model="form1" :rules="rules" layout="vertical">
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="设置读取展示频率" name="frequence">
            <a-select v-model:value="form1.frequency" >
              <a-select-option value="a">循环昨天【上午下午】</a-select-option>
              <a-select-option value="b">循环上一周【上一周每天上午下午】</a-select-option>
              <a-select-option value="c">循环上个月【上个月每天上午下午】</a-select-option>
              <a-select-option value="d">循环去年【去年每天上午下午】</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="阵列条数" name="arrayTotal">
            <n-input v-model:value="form1.arrayTotal" placeholder="请输入阵列条数" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="每条阵列的传感器数量" name="eachArrayNum">
            <n-dynamic-input
                v-model:value="form1.eachArrayNum"
                placeholder="请输入数量"
                :min="form1.arrayTotal"
                :max="form1.arrayTotal"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="上午采集几组数据" name="groupNumAm">
            <n-input v-model:value="form1.groupNumAm" placeholder="请输入组数" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="下午采集几组数据" name="groupNumPm">
            <n-input v-model:value="form1.groupNumPm" placeholder="请输入组数" />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="读取文件的格式" name="fileFormat">
            <n-input v-model:value="form1.fileFormat" placeholder="请输入文件格式" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="读取文件的哪一列数据（如z轴就填写z轴）" name="colName">
            <n-input v-model:value="form1.colName" placeholder="请输入" />
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
    <a-space>
      <a-button @click="onClose1">Cancel</a-button>
      <n-popconfirm
          @positive-click="handlePositiveClick1"
          @negative-click="handleNegativeClick"
      >
        <template #trigger>
          <n-button type="primary">Submit</n-button>
        </template>
        是否设置完成？
      </n-popconfirm>
    </a-space>
  </a-drawer>
  <a-drawer
      title="首页一个月内管道位移变化曲线图相关属性"
      :width="720"
      :visible="visible2"
      :body-style="{ paddingBottom: '80px' }"
      :footer-style="{ textAlign: 'right' }"
      @close="onClose2"
  >
    <a-form :model="form2" :rules="rules" layout="vertical">
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="曲线条数" name="curveTotal">
            <n-input v-model:value="form2.curveTotal" placeholder="请输入曲线条数" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="阵列号以及内部编号" name="arrayNode">
            <n-dynamic-input
                v-model:value="form2.arrayNode"
                preset="pair"
                key-placeholder="阵列号"
                value-placeholder="内部节点编号"
                :min="form2.curveTotal"
                :max="form2.curveTotal"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="读取文件的格式" name="fileFormat">
            <n-input v-model:value="form2.fileFormat" placeholder="请输入文件格式" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="读取文件的哪一列数据（如z轴就填写z轴）" name="colName">
            <n-input v-model:value="form2.colName" placeholder="请输入排第几列" />
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>

    <a-space>
      <a-button @click="onClose2">Cancel</a-button>
      <n-popconfirm
          @positive-click="handlePositiveClick2"
          @negative-click="handleNegativeClick"
      >
        <template #trigger>
          <n-button type="primary" >Submit</n-button>
        </template>
        是否设置完成？
      </n-popconfirm>
    </a-space>
  </a-drawer>
</template>
<script lang="ts">
import { PlusOutlined } from '@ant-design/icons-vue';
import { defineComponent, reactive, ref } from 'vue';
import axios from "axios";
import {message} from "ant-design-vue";
export default defineComponent({
  components: {
    PlusOutlined,
  },
  setup() {

    const form1 = reactive({
      frequency: 'b',
      arrayTotal: 2,
      eachArrayNum:[12,10],
      groupNumAm:10,
      groupNumPm:10,
      fileFormat:'txt',
      colName:'z轴',
    });
    const form2 = reactive({
      curveTotal:2,
      arrayNode:[{key:0,value: 15}],
      fileFormat:'txt',
      colName:'z轴'
    });
    const visible1 = ref<boolean>(false);
    const visible2 = ref<boolean>(false);
    const showDrawer1 = () => {
      visible1.value = true;
    };
    const showDrawer2 = () => {
      visible2.value = true;
    };
    const onClose1 = () => {
      visible1.value = false;
    };
    const onClose2 = () => {
      visible2.value = false;
    };
    const handlePositiveClick1  = () =>{
        axios.post("/home/leftTopAttributeSave", form1).then((response) => {
          const data = response.data;
          if (data.success) {
            message.success("保存成功");
          } else {
            message.error(data.message);
          }
        });
    };
    const handlePositiveClick2  = () =>{
      axios.post("/home/middleBottomAttributeSave", form2).then((response) => {
        const data = response.data;
        if (data.success) {
          message.success("保存成功");
        } else {
          message.error(data.message);
        }
      });
    };
    const handleNegativeClick =()=> {
      message.info('取消成功')
    }
    const value1 = ref<string>('a');
    const value2 = ref<string>('c');
    return {
      form1,
      form2,
      visible1,
      visible2,
      showDrawer1,
      showDrawer2,
      onClose1,
      onClose2,
      value1,
      value2,
      handleNegativeClick,
      handlePositiveClick1,
      handlePositiveClick2
    };
  },
});
</script>