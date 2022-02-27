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
          <a-menu-item key="/">
            <transition :name="transitionName">
              <router-link to="/">首页</router-link>
            </transition>
          </a-menu-item>
          <a-menu-item key="/admin/user">
            <transition :name="transitionName">
              <router-link to="/admin/user">用户管理</router-link>
            </transition>
          </a-menu-item>
          <a-menu-item key="/admin/physicalValue">
            <transition :name="transitionName">
              <router-link to="/admin/physicalValue">应变值</router-link>
            </transition>
          </a-menu-item>
          <a-menu-item key="/about">
            <transition :name="transitionName">
              <router-link to="/about">关于我们</router-link>
            </transition>
          </a-menu-item>
        </div>
        <div>
          <a-menu-item key="/login">
            <transition :name="transitionName">
              <router-link to="/login" @isChange="getSon" v-show="isShow">登录</router-link>
            </transition>
          </a-menu-item>
        </div>
      </div>
    </a-menu>
  </a-layout-header>
</template>
<script lang="ts">
import { defineComponent, ref, computed, watch } from 'vue';
export default defineComponent({

  name: 'the-header',
  setup(){
    let transitionName = ref()
    let isShow = ref(true);
    function getSon(value:any){
      isShow.value = value;
      console.log(isShow.value);
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
      isShow,
      getSon
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