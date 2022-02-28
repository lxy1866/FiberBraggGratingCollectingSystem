<template>
  <a-layout-header class="header">
    <a-menu
        theme="light"
        mode="horizontal"
        v-model:selectedKeys="selectedKeys1"
        :style="{ lineHeight: '64px' }"
    >
      <div class="header-wrap">
        <div class="header-left">
          <div>光纤光栅采集系统</div>
          <a-menu-item key="/">
            <transition :name="transitionName">
              <router-link to="/">首页</router-link>
            </transition>
          </a-menu-item>
          <a-menu-item key="/admin/user">
            <transition :name="transitionName">
              <router-link to="/admin/user" :style="user.id? {} : {display:'none'}">用户管理</router-link>
            </transition>
          </a-menu-item>
          <a-menu-item key="/admin/physicalValue">
            <transition :name="transitionName">
              <router-link to="/admin/physicalValue" :style="user.id? {} : {display:'none'}">应变值</router-link>
            </transition>
          </a-menu-item>

        </div>
        <div class="header-right">
          <a-menu-item key="/about">
            <transition :name="transitionName">
              <router-link to="/about">关于我们</router-link>
            </transition>
          </a-menu-item>
          <a-menu-item key="/login">
              <router-link to="/login" v-show="!user.id">登录</router-link>
          </a-menu-item>
          <a-menu-item key="/logout" >
            <a-popconfirm
                title="确认退出登录?"
                ok-text="是"
                cancel-text="否"
                @confirm="logout()"
            >
              <a  v-show="user.id">
                <span>退出登录</span>
              </a>
            </a-popconfirm>
          </a-menu-item>
        </div>
      </div>
    </a-menu>
  </a-layout-header>
</template>
<script lang="ts">
import { defineComponent, ref, computed, watch } from 'vue';
import store from "@/store";
import axios from "axios";
import {message} from "ant-design-vue";
export default defineComponent({

  name: 'the-header',

  setup(){
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
          message.success("退出登录成功");
          store.commit("setUser", {});
        }else {
          message.error(data.message);
        }
      })
    }
    // watch: {
    //   //使用watch 监听$router的变化
    //   $route(to, from) {
    //     //如果to索引大于from索引,判断为前进状态,反之则为后退状态
    //     console.log(to, "to");
    //     console.log(from, "from");
    //     if (to.meta.index > from.meta.index) {
    //       //设置动画名称
    //       transitionName.value = "slide-left";
    //     } else {
    //       transitionName.value = "slide-right";
    //     }
    //   }
    // }
    return {
      transitionName,
      logout,
      user
    }
  },
});
</script>
<style>
.header-wrap{
  padding-top: 2px;
  padding-bottom: 2px;;
  display: flex;
  justify-content: space-between;
  width: 100%;
}
.header-left{
  display: flex;
  justify-content: left;
}
.header-right{
  display: flex;
  justify-content: left;
}
.slide-right-enter-active, .slide-right-leave-active, .slide-left-enter-active, .slide-left-leave-active {
  will-change: transform;
  transition: all 500ms;
  position: absolute;
}
.slide-right-enter {
  opacity: 0;
  transform: translate3d(-100%, 0, 0);
}

.slide-right-leave-active {
  opacity: 0;
  transform: translate3d(100%, 0, 0);
}

.slide-left-enter {
  opacity: 0;
  transform: translate3d(100%, 0, 0);
}

.slide-left-leave-active {
  opacity: 0;
  transform: translate3d(-100%, 0, 0);
}
</style>