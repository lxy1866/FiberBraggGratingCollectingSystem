<template>
    <p style="color: red; font-family: 'Adobe 宋体 Std L';">请选择你要查询的日期范围：</p>
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
    title: 'createTime',
    key: 'createTime',
    dataIndex: 'createTime'
  },
  {
    title: 'msg',
    dataIndex: 'msg',
    key: 'msg',
  },
];
declare let formatDate: any;
const formatDateWrapper = (time:any)=>{
  const data = new Date(time);
  return formatDate(data,'yyyy-MM-dd hh:mm:ss');
}
const handleQuery = (params:any)=>{
  axios.get("/fbg/abnormalList", {
    params:{
      page: params.page,
      size: params.size,
      startTime: params.startTime,
      endTime: params.endTime
    }
  }).then(function (response){
    let resultList: any[] = []
    console.log(response);
    response.data.content.list.forEach((item:any)=>{
      let obj = JSON.parse(item)
      obj.createTime =  formatDateWrapper(Number(obj.createTime));
      resultList.push(obj);
    })
    data.value = resultList;
  })
};
export default defineComponent({
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