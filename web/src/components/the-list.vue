<template>
  <n-space vertical :size="12">
    <n-data-table
        size="small"
        :columns="columns"
        :data="data"
        :pagination="pagination"
    />
  </n-space>
</template>

<script>
import {defineComponent, onMounted, ref} from 'vue'
import axios from "axios";
let data = ref();
const columns = [
  {
    title: 'id',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: 'val1',
    dataIndex: 'val1',
    key: 'val1',
  },
  {
    title: 'val2',
    dataIndex: 'val2',
    key: 'val2',
  },
  {
    title: 'val3',
    key: 'val3',
    dataIndex: 'val3',
  },
  {
    title: 'val4',
    key: 'val4',
    dataIndex: 'val4'
  },
  {
    title: 'val5',
    key: 'val5',
    dataIndex: 'val5',
  },
  {
    title:'val6',
    key: 'val6',
    dataIndex: 'val6'
  },
  {
    title: 'createTime',
    key: 'createTime',
    dataIndex: 'createTime'
  }
];
export default defineComponent({
  setup () {
    onMounted(()=>{
      console.log("onMounted")
      axios.get("/pv/list",{
        params:{
          page:1,
          size:1000
        }
      }).then(function (response){
        console.log(response);
        data.value = response.data.content.list;
      })
    })
    return {
      columns,
      pagination: {
        pageSize: 10
      },
      data
    }
  }
})
</script>