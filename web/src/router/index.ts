import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/home.vue'
import About from '../views/about.vue'
import AdminPhysicalValue from '../views/admin/admin-physicalValue.vue'
import Login from '../views/login.vue'
import {Tool} from "@/util/tool";
import store from "@/store";
import component from "*.vue";
import AdminUser from '../views/admin/admin-user.vue'
import login from "@/views/login.vue";
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/about',
    name: 'About',
    component: About
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    //component: () => import(/* webpackChunkName: "about" */ '../views/about.vue')
  },
  {
    path: '/admin/physicalValue',
    name: 'AdminPhysicalValue',
    component: AdminPhysicalValue,
    meta:{
      loginRequire: true
    }
  },
  {
    path: '/admin/user',
    name: 'AdminUser',
    component: AdminUser,
    meta: {
      loginRequire: true
    }
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
//路由登录拦截
router.beforeEach((to, from, next) =>{
  // 要不要对meta.loginRequire属性做监控拦截
  if(to.matched.some(function (item){
    console.log(item, "是否需要登录校验：", item.meta.loginRequire);
    return item.meta.loginRequire
  })){
    const loginUser = store.state.user;
    if(Tool.isEmpty(loginUser)){
      console.log("用户未登录！");
      next('/');
    }else {
      next();
    }
  }else {
    next();
  }
})
export default router
