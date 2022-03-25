import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import About from '../views/about.vue'
import AdminPhysicalValue from '../views/admin/admin-physicalValue.vue'
import Login from '../views/login.vue'
import {Tool} from "@/util/tool";
import store from "@/store";
import AdminUser from '../views/admin/admin-user.vue'
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'BigScreen',
    component: () => import('../views/bigScreen.vue'),
    meta:{
      loginRequire: true
    }
  },
  {
    path: '/detail',
    name: 'Detail',
    component: () => import('../views/detail.vue'),
    meta: {
      loginRequire: true
    },
    //定义嵌套路由
    children:[
        {
          path: '/home',
          name:'历史数据查询',
          component:()=> import('../views/detail.vue'),
          meta:{
            loginRequire: true
          }
        },
        {
          path: '/abnormalDataQuery',
          name:'异常波动数据查询',
          component:()=> import('../components/abnormalDataQuery.vue'),
          meta:{
            loginRequire: true
          }
        },
    ],
  },

  {
    path: '/breakpoint',
    name: '断点位置维修情况',
    component: () => import(/* webpackChunkName: "about" */ '../components/breakpoint.vue'),
  },
  {
    path: '/realTimeShapeSensor',
    name: '实时形状传感',
    component:()=>import('../components/realTimeShapeSensor.vue'),
    children:[
      {
        path: '/twoDimension',
        name:'二维形状传感',
        component:() =>import('../components/twoDimension.vue')
      },
      {
        path:'/threeDimension',
        name:'三维形状传感',
        component:()=>import('../components/threeDimension.vue')
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/about',
    name: 'About',
    component: About,
    meta:{
      loginRequire: true
    }
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
      next('/login');
    }else {
      next();
    }
  }else {
    next();
  }
})
export default router
