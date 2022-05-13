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
<!--        <van-cell v-for="item in users" :key="item" :title="item.loginName" />-->

        <van-cell v-for="item in users" :key="item" :title="item.loginName" is-link @click="showPopup(item)"></van-cell>
        <van-popup
            v-model:show="show"
            round
            closeable
            position="bottom"
            :style="{ height: '30%' }"

        >
          <div class="resetClass">
            <a-space>
              <n-input
                  v-model:value="user.password"
                  type="password"
                  show-password-on="mousedown"
                  placeholder="重置密码"
                  :maxlength="9"
              />
              <a-button type="primary" @click="handleResetOk()" ghost>
                提交
              </a-button>
            </a-space>
          </div>
        </van-popup>
      </van-list>
    </van-pull-refresh>
    <div  class="restSpace"></div>
  <MTheFooter/>
</template>
<script lang="ts">
import {computed, defineComponent, onMounted, ref} from 'vue';
import axios from 'axios';

import MTheHeader from '@/components/mobile/m_theHeader.vue';
import MTheFooter from '@/components/mobile/m_theFooter.vue';
import {Toast} from "vant";
import {message} from "ant-design-vue";
declare let hexMd5: any;
declare let KEY: any;
const user = ref();
import store from "@/store";
import {Tool} from "@/util/tool";
export default defineComponent({
  name: 'm_admin-user',
  setup(){
    const show = ref(false);
    const showPopup = (item:any) => {
      if(item.loginName === storeUser.value.loginName){
        user.value = Tool.copy(item);
        user.value.password = null;
        show.value = true;
      }else{
        message.error("你没有重置别人密码的权限！")
      }

    };
    const pagination = ref({
      current: 1,
      pageSize: 10,
      total: 0
    });
    const storeUser = computed(()=>{
      return store.state.user
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
    const handleResetOk = () => {
      user.value.password = hexMd5(user.value.password + KEY);
      axios.post("/user/reset-password", user.value).then((response) => {
        const data = response.data; // data = commonResp
        if (data.success) {
          message.success(data.message)
          searchLoginName.value = null
          // 重新加载列表
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        } else {
          message.error(data.message);
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
      onCancel,
      show,
      showPopup,
      handleResetOk,
      user
    }
  },
  components:{
    MTheHeader,
    MTheFooter
  }
})
</script>
<style>
.resetClass{
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: row;
  margin: 20% 10%;
}
.restSpace{
  background-color: #053343;
  height: 100%;
  width:100%;
  position: absolute;
  margin-bottom: 50px;
}
</style>