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
import { useMessage } from 'naive-ui'
import axios from "axios";
const data = ref();
const handleUpdate = (params:any)=> {
  axios.get("/bpr/update/", {
    params: {
      id: params.id,
    }
  }).then(function (response) {
    console.log(response);
    data.value = response.data.content;
  });
}
type rowDataType = {
  id: number
  createTime: number
  tag: number
  state: number
}
export default defineComponent({
  setup () {
    const message = useMessage();
    const selectData = inject('selectData');
    const rowDataId = inject('rowDataId');
    return {
      handlePositiveClick () {
        message.info('确认')
        console.log(rowDataId)
        handleUpdate({
          id: rowDataId
        })
      },
      handleNegativeClick () {
        message.info('取消')
      },
      handleUpdate,
      selectData
    }
  }

})
</script>