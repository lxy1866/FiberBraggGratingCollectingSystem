<template>
  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <a-table
          :columns="columns"
          :row-key="record => record.id"
          :data-source="physicalValue"
          :pagination="pagination"
          :loading="loading"
          @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:action="{text: record}">
          <a-space size="small">
            <a-button type="primary" @click="edit">
              编辑
            </a-button>
            <a-button type="danger">
              删除
            </a-button>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>

  <a-modal
      title="电子书单"
      v-model:visible="modalVisible"
      :confim-loading="modalLoading"
      @ok="handleModalOk"
  >
    <p>test</p>
  </a-modal>
</template>
<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import axios from 'axios';

export default defineComponent({
  name: 'AdminPhysicalValue',
  setup(){
    const physicalValue = ref();
    const pagination = ref({
      current: 1,
      pageSize: 3,
      total: 0
    });
    const loading = ref(false);

    const columns = [
      {
        title: '序号',
        dataIndex: 'ID',
        slots:{ customRender: 'cover'},
      },
      {
        title: '第一个物理值',
        dataIndex: 'val1'
      },
      {
        title: '第二个物理值',
        dataIndex: 'val2',
      },
      {
        title: '第三个物理值',
        dataIndex: 'val3',
      },
      {
        title:'第四个物理值',
        dataIndex: 'val4',
      },
      {
        title:'第五个物理值',
        dataIndex: 'val5',
      },
      {
        title:'第六个物理值',
        dataIndex: 'val6',
      },
      {
        title: '时间',
        dataIndex: 'createTime'
      },
      {
        title:'Action',
        key:'action',
        slots: {customRender: 'action'}
      }
    ];
    /**
     * 数据查询
     * @param params
     */
    const handleQuery = (params:any)=>{

      loading.value = true;
      axios.get("/pv/list", {
        params:{
          page: params.page,
          size: params.size
        }
      }).then((response)=>{
        loading.value = false;
        const data = response.data;
        physicalValue.value = data.content.list;

        //重置分页按钮
        pagination.value.current = params.page;
        pagination.value.total = data.content.total;
      });
    };
    /**
     * 表格点击页码时触发
     */
    const handleTableChange = (pagination:any) =>{
      console.log("看看自带的分页参数有啥:" + pagination);
      handleQuery({
        page:pagination.current,
        size:pagination.pageSize,
      });
    };
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () =>{
      modalLoading.value = true;
      setTimeout(()=>{
        modalLoading.value = false;
        modalVisible.value = false;
      },2000);
    };
    const edit = ()=>{
      modalVisible.value = true;
    }
    onMounted(()=>{
      handleQuery({
        page: 1,
        size: pagination.value.pageSize
      });
    });
    return {
      pagination,
      columns,
      loading,
      handleTableChange,
      physicalValue,
      edit,

      modalVisible,
      modalLoading,
      handleModalOk
    }
  }
})
</script>

