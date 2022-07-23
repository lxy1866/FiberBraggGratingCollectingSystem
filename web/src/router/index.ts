import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import {Tool} from "@/util/tool";
import store from "@/store";

//首页用重定向的方式来进行适配的方案：
const redirectPath = /Android|webOS|iPhone|iPod|iPad|BlackBerry|liPad/i.test(navigator.userAgent) ? '/m_index' : '/p_index';
const routes: Array<RouteRecordRaw> = [
  {
    path:'/',
    redirect:redirectPath
  },
  {
    path: '/p_index',
    name: 'p_BigScreen',
    component: () => import('../views/pipeBigScreen.vue'),
    meta:{
      "type":'pc',
      loginRequire: true
    }
  },
  {
    path: '/p_index/sensorDetail',
    name: 'p_sensorDetail',
    component: () => import('../views/sensorDetail.vue'),
    meta: {
      "type":'pc',
      loginRequire: true
    },
  },
  {
    path: '/p_index/settingDetail',
    name: 'p_settingDetail',
    component: () => import('../views/settingDetail.vue'),
    meta: {
      "type":'pc',
      loginRequire: true
    },
  },
  {
    path: '/p_index/login',
    name: 'p_Login',
    component: ()=>import('../views/login.vue'),
    meta: {
      "type":'pc'
    },
  },
  {
    path: '/p_index/about',
    name: 'p_About',
    component: ()=>import('../views/about.vue'),
    meta:{
      "type":'pc',
      loginRequire: true
    }
  },
  {
    path: '/p_index/admin/physicalValue',
    name: 'p_AdminPhysicalValue',
    component: ()=>import('../views/admin/adminPhysicalValue.vue'),
    meta:{
      "type":'pc',
      loginRequire: true
    }
  },
  {
    path: '/p_index/admin/user',
    name: 'p_AdminUser',
    component: ()=>import('../views/admin/adminUser.vue'),
    meta: {
      "type":'pc',
      loginRequire: true
    }
  },
  //mobile
  {
    path: '/m_index',
    name: 'm_BigScreen',
    component: () => import('../views/mobile/m_bigScreen.vue'),
    meta:{
      "type":'mobile',
      loginRequire: true
    }
  },
  {
    path: '/m_index/sensorDetail',
    name: 'm_sensorDetail',
    component: () => import('../views/mobile/m_sensorDetail.vue'),
    meta: {
      "type":'mobile',
      loginRequire: true
    },
  },
  {
    path: '/m_index/settingDetail',
    name: 'm_settingDetail',
    component: () => import('../views/mobile/m_settingDetail.vue'),
    meta: {
      "type":'mobile',
      loginRequire: true
    },
  },
  {
    path: '/m_index/login',
    name: 'm_Login',
    component: ()=>import('../views/mobile/m_login.vue'),
    meta:{
      "type":'mobile',
    }
  },
  {
    path: '/m_index/about',
    name: 'm_About',
    component: ()=>import('../views/mobile/m_about.vue'),
    meta:{
      "type":'mobile',
      loginRequire: true
    }
  },
  {
    path: '/m_index/admin/physicalValue',
    name: 'm_AdminPhysicalValue',
    component: ()=>import('../views/admin/mobile/m_adminPhysicalValue.vue'),
    meta:{
      "type":'mobile',
      loginRequire: true
    }
  },
  {
    path: '/m_index/admin/user',
    name: 'm_AdminUser',
    component: ()=>import( '../views/admin/mobile/m_adminUser.vue'),
    meta: {
      "type":'mobile',
      loginRequire: true
    }
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
//路由登录拦截
router.beforeEach((to, from, next) => {
  // 要不要对meta.loginRequire属性做监控拦截
  if (to.matched.some(function (item) {
    //console.log(item, "是否需要登录校验：", item.meta.loginRequire);
    return item.meta.loginRequire
  })) {
    const loginUser = store.state.user;
    if (Tool.isEmpty(loginUser)) {
      const path = redirectPath+"/login"
      console.log(path)
      next(path);
    } else {
      next();
    }
  } else {
    next();
  }
})
export default router
