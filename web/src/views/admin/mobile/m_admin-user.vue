<template>
  <MTheHeader/>

    <van-search
        v-model="searchLoginName"
        placeholder="请输入登录名搜索"
        show-action
        @search="handleQuery({page: 1, size: pagination.pageSize})"
        @cancel="onCancel"/>
    <van-pull-refresh >
      <van-list
          finished-text="没有更多了"
      >
        <van-cell v-for="item in users" :key="item" :title="item.loginName" />
      </van-list>
    </van-pull-refresh>

  <MTheFooter/>
</template>
<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import axios from 'axios';
import {Tool} from "@/util/tool";
import MTheHeader from '@/components/mobile/m_the-header.vue';
import MTheFooter from '@/components/mobile/m_the-footer.vue';
import {message} from "ant-design-vue";
import {Toast} from "vant";
declare let hexMd5: any;
declare let KEY: any;
export default defineComponent({
  name: 'm_admin-user',
  setup(){

    const pagination = ref({
      current: 1,
      pageSize: 10,
      total: 0
    });
    const users = ref();
    const searchLoginName = ref();
    const loading = ref(false);
    const finished = ref(false);
    const refreshing = ref(false);
    /**
     * 数据查询
     **/
    const handleQuery = (params: any) => {
      loading.value = true;
      // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
      users.value = [];
      axios.get("/user/list", {
        params: {
          page: params.page,
          size: params.size,
          loginName: searchLoginName.value
        }
      }).then((response) => {
        //console.log("user-token="+store.state.user.token);
        loading.value = false;
        const data = response.data;
        if (data.success) {
          users.value = data.content.list;
          // 重置分页按钮
          pagination.value.current = params.page;
          pagination.value.total = data.content.total;
        } else {
          Toast.fail(data.message);
        }
      });
    };
    const onCancel = () => Toast('取消');
    onMounted(() => {
      handleQuery({
        page: 1,
        size: pagination.value.pageSize,
      });
    });
    return{
      loading,
      finished,
      refreshing,
      handleQuery,
      users,
      searchLoginName,
      pagination,
      onCancel
    }
  },
  components:{
    MTheHeader,
    MTheFooter
  }
})
</script>