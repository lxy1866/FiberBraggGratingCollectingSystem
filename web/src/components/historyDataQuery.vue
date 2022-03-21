<template>

  <p>请选择你要查询的日期范围</p>

  <n-space vertical>
    <n-date-picker
        v-model:value="range2"
        type="datetimerange"
        :shortcuts="rangeShortcuts"
    />
    <n-data-table
        size="small"
        :columns="columns"
        :data="data"
        :pagination="pagination"
    />
  </n-space>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref, watch} from 'vue'
import axios from "axios";
let data = ref();
let range2 = ref();
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
declare let formatDate: any;
const formatDateWrapper = (time:any)=>{
  const data = new Date(time);
  return formatDate(data,'yyyy-MM-dd hh:mm:ss');
}
const handleQuery = (params:any)=>{
  axios.get("/pv/list", {
    params:{
      page: params.page,
      size: params.size,
      startTime: params.startTime,
      endTime: params.endTime
    }
  }).then(function (response){
    console.log(response);
    if(response.data.content.list.length !== 0){
      response.data.content.list.forEach((item:any)=>{
        item.createTime = formatDateWrapper(Number(item.createTime))
      })
      data.value = response.data.content.list;
    }
  })
};
export default defineComponent({
  components:{
  },
  setup () {
    /**
     * 数据查询
     * @param params
     */
    onMounted(()=>{
      console.log("onMounted")
      handleQuery({
        page: 1,
        size: 10
      })
      watch(range2,(newValue, oldValue)=>{
        console.log(newValue,oldValue);
        console.log("range2.value[0]"+range2.value[0]);
        console.log("range2.value[1]"+range2.value[1])
        handleQuery({
          page:1,
          size:10,
          startTime: range2.value[0],
          endTime: range2.value[1]
        })
      });
    });

    return {
      columns,
      data,
      range2
    }
  }
})
</script>