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
import {message} from "ant-design-vue";
let data = {};
let range2 = ref();
let startTime = ref();
let endTime = ref();
let columns = ref([]);
let dataRef = ref()
var fullYear = new Date(new Date().setFullYear(2022, 7, 29));//其实是8月29日
//var frontOneHourTimeStamp = new Date(fullYear.setHours(15, 0, 0, 0)).getTime();
//var currentHourTimeStamp = new Date(fullYear.setHours(15, 59, 59, 0)).getTime();
//如果没有选时间控件，就默认展示上个小时的内容
var frontOneHourTimeStamp = startTime.value == null ? startTime.value : new Date(new Date().setHours(new Date().getHours() - 1, 0, 0, 0)).getTime();
var currentHourTimeStamp = endTime.value == null ? endTime.value : new Date(new Date().setHours(new Date().getHours(), 0, 0, 0)).getTime();
function dateToGMT(strDate){
  const dateStr = strDate.split(" ");
  const strGMT = dateStr[0] + " " + dateStr[1] + " " + dateStr[2] + " " + dateStr[5] + " " + dateStr[3] + " GMT+0800";
  const date = new Date(Date.parse(strGMT));
  return date;
}
const handleTableColumn = function (){
    columns.value = [];
    columns.value.push(
        {
          title: "创建时间",
          dataIndex: "createTime",
          key: "createTime",
        },
        {
          title: "传感器",
          dataIndex: "hexadecimal",
          key: "hexadecimal",
        },
        {
          title: "角度x",
          dataIndex: "angleX",
          key: "angleX",
        },
        {
          title: "角度y",
          dataIndex: "angleY",
          key: "angleY",
        },
        {
          title: "角度z",
          dataIndex: "angleZ",
          key: "angleZ",
        },
        {
          title: "加速度x",
          dataIndex: "aX",
          key: "aX",
        },
        {
          title: "加速度y",
          dataIndex: "aY",
          key: "aY",
        },
        {
          title: "加速度z",
          dataIndex: "aZ",
          key: "aZ",
        },
        {
          title: "角加速度x",
          dataIndex: "wX",
          key: "wX",
        },
        {
          title: "角加速度y",
          dataIndex: "wY",
          key: "wY",
        },
        {
          title: "角加速度z",
          dataIndex: "wZ",
          key: "wZ",
        },
    )
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
          startTime.value,
          endTime.value
      )
    }
    const handleQueryList = function (page, pageSize, startTime, endTime) {
      console.log(page, pageSize, startTime, endTime)
      // return new Promise((resolve) => {
      axios.get("/txt/listPosition", {
        params:{
          page: page,
          pageSize: pageSize,
          startTime: startTime,
          endTime: endTime
        }
      }).then(function (res) {
        if (res.data.content == null){
          return;
        }
        console.log("MEMS", res.data.content.list)
        for(let i = 0; i < res.data.content.list.length; i++){
          res.data.content.list[i].createTime = new Date(res.data.content.list[i].createTime).toLocaleString('zh');
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
        currentHourTimeStamp = endTime.value
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