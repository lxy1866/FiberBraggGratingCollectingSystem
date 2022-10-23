<template>
  <p style="color: red; font-family: 'Adobe 宋体 Std L';">请选择你要查询的日期范围：</p>

  <div class="block">
    <el-date-picker
      v-model="range2"
      type="datetimerange"
      value-format="YYYY-MM-DD HH:mm:ss"
      format="YYYY-MM-DD HH:mm:ss"
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      style="width: 100%"
      size="large"
    />
  </div>

  <n-data-table
      size="small"
      :columns="columns"
      :data="tableData"
      :pagination="pagination"
      />
  </template>

<script lang="ts">
import {defineComponent, onMounted, ref, watch,reactive,toRefs} from 'vue'
import axios from "axios";

let columns = ref([
  {
    title: '日期',
    dataIndex: 'date',
    key: 'date'
  },
  {
    title: '12%变形点施工期间位移',
    dataIndex: 'twelve_construction_value',
    key: 'twelve_construction_value'
  },
  {
    title: '22%变形点施工期间位移',
    dataIndex: 'twentytwo_construction_value',
    key: 'twentytwo_construction_value'
  },
  {
    title: '12%变形点1运行期间位移',
    dataIndex: 'twelve_one_operation_value',
    key: 'twelve_one_operation_value'
  },
  {
    title: '12%变形点2运行期间位移',
    dataIndex: 'twelve_two_operation_value',
    key: 'twelve_two_operation_value'
  },
  {
    title: '22%变形点运行期间位移',
    dataIndex: 'twentytwo_operation_value',
    key: 'twentytwo_operation_value'
  },
]);

export default defineComponent({
  components:{
  },
  setup () {
    /**
     * 数据查询
     * @param params
     */

    let tableData = ref();
    let range2 = ref();

    const handleQuery = async (params: any) => {
      await axios.get("/history/list", {
        params: {
          startTime: params.startTime,
          endTime: params.endTime
        }
      }).then(res =>{
        console.log(res)
        tableData.value = res.data.content

      })
    };

    onMounted(()=> {
      //console.log("onMounted")

      watch(range2, (newValue, oldValue) => {
        //console.log(newValue, oldValue);
        //console.log("range2.value[0]" + range2.value[0]);
        //console.log("range2.value[1]" + range2.value[1]);

        handleQuery({
          startTime: range2.value[0],
          endTime: range2.value[1]
        })
      });
    })
    return {
      columns,
      tableData,
      range2,
      pagination: { pageSize: 10 },
    }
  }
})
</script>

<style scoped>
.block {
  padding: 10px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}
.block:last-child {
  border-right: none;
}
.block .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 10px;
}
.headerBg {
  background: #eee!important;
}
</style>