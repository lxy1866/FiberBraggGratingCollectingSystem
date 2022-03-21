<template>
  <div v-if="selectData === '未处理'">
    <n-popconfirm
        @positive-click="handlePositiveClick"
        @negative-click="handleNegativeClick"
    >
      <template #trigger>
        <n-button>完成维修</n-button>
      </template>
      是否已经维修完成？
    </n-popconfirm>
  </div>
  <div v-else>处理完成</div>

</template>

<script lang="ts">
import {defineComponent, ref, inject} from 'vue'
import axios from "axios";
import { message } from 'ant-design-vue';
const data = ref();

const handleUpdate = (rowDataId:any)=> {
  axios.get("/bpr/update/"+Number(rowDataId)).then(function (response) {
    console.log(response);
    data.value = response.data.content;
  });
}

export default defineComponent({
  setup () {
    const selectData:any = inject('selectData');
    let rowDataId:any = inject('rowDataId');
    console.log(rowDataId.value)
    return {
      handlePositiveClick () {
        message.info('确认成功')
        handleUpdate(rowDataId.value)
      },
      handleNegativeClick () {
        message.info('取消成功')
      },
      handleUpdate,
      selectData,
      rowDataId
    }
  }

})
</script>