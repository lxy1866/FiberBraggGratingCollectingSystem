<template>
  <p style="color: #1890ff; font-family: 'Adobe 宋体 Std L';">请选择你要查询的日期范围：</p>
  <n-space vertical>
    <n-date-picker
        v-model:value="range2"
        type="datetimerange"
    />
    <n-data-table
        remote
        ref="table"
        :columns="columns"
        :data="data"
        :pagination="pagination"
        @update:page="handlePageChange"
    />
  </n-space>
</template>
<script lang="js">
import {defineComponent, onMounted, reactive, ref, watch} from 'vue';
import axios from "axios";
import {Toast} from "vant";
import {message} from "ant-design-vue";
let data = {};
let range2 = ref();
let startTime = ref();
let endTime = ref();
let columns = ref([]);
let dataRef = ref()
// var fullYear = new Date(new Date().setFullYear(2022, 8, 2));
// var frontOneHourTimeStamp = new Date(fullYear.setHours(0, 0, 0, 0)).getTime();
// var currentHourTimeStamp = new Date(new Date().setHours(0, 59, 59, 0)).getTime();
var frontOneHourTimeStamp = new Date(new Date().setHours(new Date().getHours(), 0, 0, 0)).getTime();
var currentHourTimeStamp = new Date(new Date().setHours(new Date().getHours(), 59, 59, 0)).getTime();
const handleTableColumn = function (){
  axios.get("/nr/getAll").then(function (res){
    columns.value = [];
    columns.value.push({
      title: "createTime",
      dataIndex: "createTime",
      key: "createTime"
    })
    for(let i = 0; i < res.data.content.length; i++){
      columns.value.push({
        title: res.data.content[i].propertyName,
        key: i+1
      })
    }
  })
}
//解决办法：
function dateToGMT(strDate){
  const dateStr = strDate.split(" ");
  const strGMT = dateStr[0] + " " + dateStr[1] + " " + dateStr[2] + " " + dateStr[5] + " " + dateStr[3] + " GMT+0800";
  const date = new Date(Date.parse(strGMT));
  return date;
}
export default defineComponent({
  components:{
  },
  setup () {
    const paginationReactive = reactive({
      page: 1,
      pageCount: 1,
      pageSize: 25,
      prefix ({ itemCount }) {
        return `Total is ${itemCount}.`
      },
      onChange: (page) => {
        paginationReactive.page = page;
      },
    })
    const handlePageChange = function (currentPage) {
      handleQueryList(
          currentPage,
          paginationReactive.pageSize,
          frontOneHourTimeStamp,
          currentHourTimeStamp
      )
    }
    const handleQueryList = function (page, pageSize, startTime, endTime) {
      console.log(page, pageSize, startTime, endTime)
      // return new Promise((resolve) => {
      axios.get("/fbg/list", {
        params:{
          page: page,
          pageSize: pageSize,
          startTime: startTime,
          endTime: endTime
        }
      }).then(function (res) {
        console.log("history", res.data.content.list);
        for(let i = 0; i < res.data.content.list.length; i++){
          res.data.content.list[i].createTime = res.data.content.list[i].createTime.toLocaleString('zh');
          //res.data.content.list[i].createTime = new Date(res.data.content.list[i].createTime.replace("CST",'GMT+0800')).toLocaleString()
        }
        dataRef.value = res.data.content.list
        paginationReactive.pageCount = res.data.content.pageCount
        paginationReactive.itemCount = res.data.content.total
      })
    }
    /**
     * 数据查询
     * @param params
     */
    onMounted(()=> {
      console.log("onMounted被触发")
      handleTableColumn();
      handleQueryList(
          paginationReactive.page,
          paginationReactive.pageSize,
          frontOneHourTimeStamp,
          currentHourTimeStamp)
      watch(range2, (newValue, oldValue) => {
        // console.log(newValue, oldValue);
        // console.log("range2.value[0]" + range2.value[0]);
        // console.log("range2.value[1]" + range2.value[1]);
        startTime.value = range2.value[0];
        frontOneHourTimeStamp = startTime.value
        endTime.value = range2.value[1];
        currentHourTimeStamp = endTime.value;
        if(endTime.value - startTime.value > 3600000){
          message.success("选择的时间范围过大，请将时间范围缩短在一个小时以内");
        }else{
          handleQueryList(
              paginationReactive.page,
              paginationReactive.pageSize,
              frontOneHourTimeStamp,
              currentHourTimeStamp
          )
        }
      });
    })
    return {
      columns,
      data: dataRef,
      range2,
      startTime,
      endTime,
      handleTableColumn,
      handlePageChange,
      handleQueryList,
      pagination: paginationReactive,
    }
  }
})
</script>