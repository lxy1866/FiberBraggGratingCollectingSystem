import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import About from '../views/about.vue'
import AdminPhysicalValue from '../views/admin/admin-physicalValue.vue'
import AdminUser from '../views/admin/admin-user.vue'
import Login from '../views/login.vue'

import m_About from '../views/mobile/m_about.vue'
import m_AdminPhysicalValue from '../views/admin/mobile/m_admin-physicalValue.vue'
import m_AdminUser from '../views/admin/mobile/m_admin-user.vue'
import m_Login from '../views/mobile/m_login.vue'

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
    name: 'p_BigScreen',
    component: () => import('../views/bigScreen.vue'),
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
    path: '/p_index/login',
    name: 'p_Login',
    component: Login,
    meta: {
      "type":'pc'
    },
  },
  {
    path: '/p_index/about',
    name: 'p_About',
    component: About,
    meta:{
      "type":'pc',
      loginRequire: true
    }
  },
  {
    path: '/p_index/admin/physicalValue',
    name: 'p_AdminPhysicalValue',
    component: AdminPhysicalValue,
    meta:{
      "type":'pc',
      loginRequire: true
    }
  },
  {
    path: '/p_index/admin/user',
    name: 'p_AdminUser',
    component: AdminUser,
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
    component: m_Login,
    meta:{
      "type":'mobile',
    }
  },
  {
    path: '/m_index/about',
    name: 'm_About',
    component: m_About,
    meta:{
      "type":'mobile',
      loginRequire: true
    }
  },
  {
    path: '/m_index/admin/physicalValue',
    name: 'm_AdminPhysicalValue',
    component: m_AdminPhysicalValue,
    meta:{
      "type":'mobile',
      loginRequire: true
    }
  },
  {
    path: '/m_index/admin/user',
    name: 'm_AdminUser',
    component: m_AdminUser,
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
    console.log(item, "是否需要登录校验：", item.meta.loginRequire);
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
