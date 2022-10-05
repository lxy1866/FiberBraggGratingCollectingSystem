<template>
  <van-tabbar route >
    <van-tabbar-item replace to="/m_index" icon="home-o">首页</van-tabbar-item>
    <van-tabbar-item replace to="/m_index/sensorDetail" icon="home-o">传感器列表</van-tabbar-item>
    <van-tabbar-item replace to="/m_index/admin/user" icon="home-o">用户</van-tabbar-item>
    <van-tabbar-item replace to="/m_index/settingDetail" icon="home-o">设置</van-tabbar-item>
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
import { defineComponent,computed} from 'vue';
import store from "@/store";
import axios from "axios";
import router from "@/router";
import {message} from "ant-design-vue";
export default defineComponent({
  name: 'm_theFooter',
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
<style scoped>
.van-tabbar {
  background-color: #053343;
}
.van-tabbar-item--active {
  color: white;
  background-color: darkslateblue;
}
.van-tabbar-item {
  color: cadetblue;
}
</style>

