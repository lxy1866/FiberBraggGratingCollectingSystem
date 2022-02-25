import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/home.vue'
import About from '../views/about.vue'
import AdminPhysicalValue from '../views/admin/admin-physicalValue.vue'

import component from "*.vue";
import AdminUser from '../views/admin/admin-user.vue'
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
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
    component: AdminPhysicalValue
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

export default router
