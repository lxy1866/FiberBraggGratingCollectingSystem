<template>
  <the-header></the-header>
    <a-layout-content
        :style="{background: '#ffffff', padding: '24px', margin: 0, minHeight: '280px', height: height_top.height}"
    >
      <p>
        <n-input v-model:value="searchLoginName" type="text" placeholder="请输入登录名搜索" style="width: 182px"/>
        &nbsp;&nbsp;&nbsp;
        <n-button strong secondary type="primary" @click="handleQuery({page: 1, size: pagination.pageSize})">
          查询
        </n-button>
        &nbsp;&nbsp;&nbsp;
        <n-button strong secondary type="primary" @click="add()" :disabled="codeIsRight">
          新增
        </n-button>
        &nbsp;&nbsp;&nbsp;
        <a-button type="primary" @click="showModalCodeVisible" ghost >
          验证授权码
        </a-button>
      </p>
      <a-table
          :columns="columns"
          :row-key="record => record.id"
          :data-source="users"
          :pagination="pagination"
          :loading="loading"
      >
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <a-button type="primary" @click="resetPassword(record)" ghost :disabled="codeIsRight">
              重置密码
            </a-button>
            <a-popconfirm
                title="删除后你将退出登陆，是否继续?"
                ok-text="是"
                cancel-text="否"
                @confirm="handleDelete(record)"
            >
              <a-button type="danger" ghost :disabled="codeIsRight">
                删除
              </a-button>
            </a-popconfirm>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  <n-modal
      v-model:show="addModalVisible"
      preset="dialog"
      title="新增"
      positive-text="确认"
      negative-text="取消"
      @positive-click="handleAddModalOk"
      @negative-click="onNegativeClick"
  >
    <n-input
        v-model:value="user.loginName"
        placeholder="登陆名"
        :maxlength="8"
    />
    <br/><br/>
    <n-input
        v-model:value="user.password"
        type="password"
        show-password-on="mousedown"
        placeholder="密码"
        :maxlength="8"
    />
  </n-modal>
  <n-modal
      v-model:show="resetModalVisible"
      :mask-closable="false"
      preset="dialog"
      title="输入新密码"
      positive-text="确认"
      negative-text="取消"
      @positive-click="handleResetModalOk"
      @negative-click="onNegativeClick"
  >
    <n-input
        v-model:value="user.password"
        type="password"
        show-password-on="mousedown"
        placeholder="密码"
        :maxlength="8"
    />
  </n-modal>
  <n-modal
      v-model:show="modalCodeVisible"
      :mask-closable="false"
      preset="dialog"
      title="输入授权码"
      positive-text="确认"
      negative-text="取消"
      @positive-click="onPositiveClickCode"
      @negative-click="onNegativeClick"
  >
    <n-input
        v-model:value="code"
        type="password"
        show-password-on="mousedown"
        placeholder="密码"
        :maxlength="8"
    />
  </n-modal>

</template>

<script lang="ts">
import {computed, defineComponent, onMounted, ref} from 'vue';
import axios from 'axios';
import {message} from 'ant-design-vue';
import {Tool} from "@/util/tool";
import TheHeader from '@/components/theHeader.vue';
import TheFooter from '@/components/theFooter.vue';
import store from "@/store";
import {useRouter} from 'vue-router'

declare let hexMd5: any;
declare let KEY: any;

export default defineComponent({
  name: 'AdminUser',

  components:{
    TheHeader,
    TheFooter
  },
  setup() {
    const router=useRouter()
    const storeUser = computed(()=>{
      return store.state.user
    });
    const searchLoginName = ref();
    const users = ref();
    const pagination = ref({
      current: 1,
      pageSize: 10,
      total: 0
    });
    const loading = ref(false);
    const code = ref();
    const codeIsRight = ref(true);
    const modalCodeVisible = ref(false);
    const columns = [
      {
        title: '登陆名',
        dataIndex: 'loginName'
      },
      {
        title: '密码（已加密）',
        dataIndex: 'password'
      },
      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' }
      }
    ];
    const onNegativeClick =()=> {
      message.success('Cancel')
      resetModalVisible.value = false
    };
    const showModalCodeVisible = ()=>{
      modalCodeVisible.value = true;
    }
    const onPositiveClickCode = ()=> {
      //验证授权码是否正确
      if(code.value === 'test'){
        message.success('授权码正确')
        modalCodeVisible.value = false;
        codeIsRight.value = false;
      }else{
        message.success('授权码错误')
      }

    }
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
          pageSize: params.size,
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
          message.error(data.message);
        }
      });
    };
    /**
     * 表格点击页码时触发
     */
    const handleTableChange = (pagination: any) => {
      //console.log("看看自带的分页参数都有啥：" + pagination);
      handleQuery({
        page: pagination.current,
        pageSize: pagination.pageSize
      });
    };
    // -------- 表单 ---------
    const user = ref();
    const addModalVisible = ref(false);
    const handleAddModalOk = () => {
      user.value.password = hexMd5(user.value.password + KEY);
      axios.post("/user/save", user.value).then((response) => {
        const data = response.data; // data = commonResp
        if (data.success) {
          addModalVisible.value = false;
          message.success(data.message);
          // 重新加载列表
          handleQuery({
            page: pagination.value.current,
            pageSize: pagination.value.pageSize,
          });
        } else {
          message.error(data.message);
        }
      });
    };
    /**
     * 新增
     */
    const add = () => {
      addModalVisible.value = true;
      user.value = {};
    };

    const logout = (storeUser: any) =>{
      //console.log("退出登录开始");
      //console.log(typeof storeUser.value.token)
      axios.get('/user/logout/'+storeUser.value.token).then((response)=>{
        const data = response.data;
        //console.log(data);
        if(data.success){
          router.push({ path: '/p_index/login' })
          message.success("退出登录成功");
          store.commit("setUser", {});
        }else {
          message.error(data.message);
          store.commit("setUser", {});
        }
      })
    }
    const handleDelete = (record: any) => {
      if(record.loginName === storeUser.value.loginName){
        axios.delete("/user/delete/" + record.id).then((response) => {
          const data = response.data; // data = commonResp
          if (data.success) {
            // // 重新加载列表
            // handleQuery({
            //   page: pagination.value.current,
            //   size: pagination.value.pageSize,
            // });
            logout(storeUser);
          } else {
            message.error(data.message);
          }
        });
      }else{
        message.error("你没有删除别人账号密码的权限！")
      }
    };

    // -------- 重置密码 ---------
    const resetModalVisible = ref(false);
    const handleResetModalOk = () => {
      user.value.password = hexMd5(user.value.password + KEY);
      axios.post("/user/reset-password", user.value).then((response) => {
        const data = response.data; // data = commonResp
        if (data.success) {
          message.success(data.message)
          resetModalVisible.value = false;
          searchLoginName.value = null
          // 重新加载列表
          handleQuery({
            page: pagination.value.current,
            pageSize: pagination.value.pageSize,
          });
        } else {
          message.error(data.message);
        }
      });
    };

    /**
     * 重置密码
     */
    const resetPassword = (record:any) => {
      if(record.loginName === storeUser.value.loginName){
        resetModalVisible.value = true;
        user.value = Tool.copy(record);
        user.value.password = null;
      }else{
        message.error("你没有重置别人密码的权限！")
      }
    };
    onMounted(() => {
      handleQuery({
        page: 1,
        size: pagination.value.pageSize,
      });
    });
    const height_top = ref({
      height: ''
    });
    const windowHeight = ref({
      height: ''
    });
    onMounted(()=>{
      if(window.innerHeight != null){
        height_top.value.height = window.innerHeight-134+'px';
        windowHeight.value.height = window.innerHeight-70+'px'
      }

      window.onresize = () => {
        return (() => {
          height_top.value.height = window.innerHeight-70+'px';
          windowHeight.value.height = window.innerHeight+'px'
        })();
      };

    })

    return {
      searchLoginName,
      users,
      pagination,
      columns,
      loading,

      handleQuery,
      add,
      user,
      addModalVisible,
      handleAddModalOk,
      handleDelete,
      resetModalVisible,
      handleResetModalOk,
      resetPassword,
      height_top,
      windowHeight,
      onNegativeClick,
      onPositiveClickCode,
      code,
      modalCodeVisible,
      codeIsRight,
      showModalCodeVisible
    }
  }
});
</script>

<style scoped>
img {
  width: 50px;
  height: 50px;
}
</style>
