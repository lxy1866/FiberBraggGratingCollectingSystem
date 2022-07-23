<template>
  <a-layout-header class="header">
    <a-menu
        theme="dark"
        mode="horizontal"
        v-model:selectedKeys="selectedKeys1"
        :style="{ lineHeight: '41px' }"
    >
      <div class="header-wrap">
        <div class="header-left">
          <a-menu-item key="/p_index">
              <router-link to="/p_index" :style="user.id? {} : {display:'none'}">首页</router-link>
          </a-menu-item>
          <a-menu-item key="/p_index/sensorDetail">
              <router-link to="/p_index/sensorDetail" :style="user.id? {} : {display:'none'}">传感器列表</router-link>

          </a-menu-item>
          <a-menu-item key="/p_index/admin/user">
              <router-link to="/p_index/admin/user" :style="user.id? {} : {display:'none'}">用户</router-link>
          </a-menu-item>
          <a-menu-item key="/p_index/admin/settingDetail">
              <router-link to="/p_index/settingDetail" :style="user.id? {} : {display:'none'}">设置</router-link>
          </a-menu-item>
        </div>
        <div class="header-right">
          <a-menu-item key="/p_index/about" >
              <router-link to="/p_index/about" :style="user.id? {} : {display:'none'}">关于</router-link>
          </a-menu-item>
          <a-menu-item key="/p_index/login">
              <router-link to="/p_index/login" v-show="!user.id">登录</router-link>
          </a-menu-item>
          <a-menu-item key="/p_index/logout" >
            <a-popconfirm
                title="确认退出登录?"
                ok-text="是"
                cancel-text="否"
                @confirm="logout()"
            >
              <a  v-show="user.id">
                <span>退出</span>
              </a>
            </a-popconfirm>
          </a-menu-item>
          <a-menu-item>
          </a-menu-item>
        </div>
      </div>
    </a-menu>
  </a-layout-header>
</template>
<script lang="ts">
import { defineComponent, ref, computed } from 'vue';
import store from "@/store";
import axios from "axios";
import {message} from "ant-design-vue";
import { useRouter } from 'vue-router'
export default defineComponent({

  name: 'the-header',

  setup(){
    const router=useRouter()
    const user = computed(()=>{
      return store.state.user
    });
    let transitionName = ref()
    const logout = () =>{
      console.log("退出登录开始");
      console.log(typeof user.value.token)
      axios.get('/user/logout/'+user.value.token).then((response)=>{
        const data = response.data;
        console.log(data);
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
    return {

      logout,
      user
    }
  },
});
</script>
<style scoped>
.header-wrap{
  display: flex;
  justify-content: space-between;
  width: 100%;
  background-color: #053343;
}
.header-left{
  display: flex;
  justify-content: left;
}
.header-right{
  display: flex;
  justify-content: left;
}

img{
  position: relative;
  width: 45px;
  height: 45px;
  margin-top: 10px;
  margin-right: 10px;
}
.header{
  top: 0px;
  left: 0px;
  right: 0px;
}
.ant-layout-header{
  height: 40px;
  padding-left: 30px;
  padding-right: 0;
  background: #053343;
}
</style>