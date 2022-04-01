<template>
  <van-tabbar route >
    <van-tabbar-item replace to="/m_index" icon="home-o">首页</van-tabbar-item>
    <van-tabbar-item replace to="/m_index/detail" icon="home-o">传感器列表</van-tabbar-item>
    <van-tabbar-item replace to="/m_index/admin/user" icon="home-o">用户</van-tabbar-item>
    <van-tabbar-item replace to="/m_index/admin/physicalValue" icon="home-o">参数</van-tabbar-item>
    <van-tabbar-item replace to="/m_index/about" icon="home-o">关于</van-tabbar-item>
    <van-tabbar-item replace to="/m_index/login" icon="home-o">登录</van-tabbar-item>
  </van-tabbar>
</template>
<script lang="ts">
import { defineComponent, ref, computed, watch } from 'vue';
import { Toast } from 'vant';
export default defineComponent({
  name: 'm_the-footer',
  setup(){
    return{

    }
  }
})
</script>

