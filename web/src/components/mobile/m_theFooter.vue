<template>
  <van-tabbar route >
    <van-tabbar-item replace to="/m_index" icon="home-o">首页</van-tabbar-item>
    <van-tabbar-item replace to="/m_index/detail" icon="home-o">传感器列表</van-tabbar-item>
    <van-tabbar-item replace to="/m_index/admin/user" icon="home-o">用户</van-tabbar-item>
    <van-tabbar-item replace to="/m_index/admin/physicalValue" icon="home-o">新增传感器</van-tabbar-item>
    <van-tabbar-item replace to="/m_index/about" icon="home-o">关于</van-tabbar-item>
    <van-tabbar-item icon="home-o">
      <a-popconfirm
          title="确认退出登录?"
          ok-text="是"
          cancel-text="否"
          @confirm="logout()"
      >
        <a  v-show="user.id">
          <span style="color: cadetblue">退出登录</span>
        </a>
      </a-popconfirm>
    </van-tabbar-item>
  </van-tabbar>
</template>
<script lang="ts">
import { message } from 'ant-design-vue';
import {computed, defineComponent} from 'vue';
import axios from "axios";
import store from "@/store";
import router from "@/router";
export default defineComponent({
  name: 'm_the-footer',
  setup(){
    const user = computed(()=>{
      return store.state.user
    });
    const logout = () =>{
      //console.log("退出登录开始");
      //console.log(typeof user.value.token)
      axios.get('/user/logout/'+user.value.token).then((response)=>{
        const data = response.data;
        //console.log(data);
        if(data.success){
          router.push({ path: '/m_index/login' })
          message.success("退出登录成功");
          store.commit("setUser", {});
        }else {
          message.error(data.message);
          store.commit("setUser", {});
        }
      })
    }
    return{
      logout,
      user
    }
  }
})
</script>

