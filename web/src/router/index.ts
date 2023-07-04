import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import {Tool} from "@/util/tool";
import store from "@/store";

//首页用重定向的方式来进行适配的方案：
const redirectPath = /Android |webos | iphone iPod BlackBerry liPad/i. test (navigator.userAgent) ? '/m_index' : '/p_index';
const routes: Array<RouteRecordRaw> = [
  {
    path:'/',
    redirect:redirectPath
  },
  {
    path: '/p_index',
    name: 'p_BigScreen1',
    component: () => import('../views/bigScreen1.vue'),
    meta:{
      "type":'pc',
      loginRequire: true
    }
  },
  {
    path: '/p_index2',
    name: 'p_BigScreen2',
    component: () => import('../views/bigScreen2.vue'),
    meta:{
      "type":'pc',
      loginRequire: true
    }
  },
  {
    path: '/p_index/detail',
    name: 'p_Detail',
    component: () => import('../views/detail.vue'),
    meta: {
      "type":'pc',
      loginRequire: true
    },
  },
  {
    path: '/p_index/sensor',
    name: 'p_Sensor',
    component: () => import('../views/sensor.vue'),
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
    path: '/m_index/detail',
    name: 'm_Detail',
    component: () => import('../views/mobile/m_detail.vue'),
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
      //console.log(path)
      next(path);
    } else {
      next();
    }
  } else {
    next();
  }
})
export default router
