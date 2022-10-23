import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import locale from 'element-plus/lib/locale/lang/zh-cn'
import 'ant-design-vue/dist/antd.css'
import naive from 'naive-ui'
import {decoration8, decoration3, digitalFlop, borderBox8} from '@jiaminghi/data-view';

import setupVant from '@/util/vant';
import setupAtnd from '@/util/antDesignVue';

axios.defaults.baseURL = process.env.VUE_APP_SERVER;
import '../static/reset.less'
import {Tool} from "@/util/tool";
/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config){
    //console.log('请求参数:',config);
    const token = store.state.user.token;
    if(Tool.isNotEmpty(token)){
        //console.log("token不为空");
        // @ts-ignore
        config.headers.token = token;
        //console.log("请求headers增加token:", token);
    }
    return config;
},error => {
    return Promise.reject(error);
});
axios.interceptors.response.use(function (response){
    //console.log('返回结果:',response);
    return response;
}, error => {
    //console.log('返回错误:',error)
    return Promise.reject(error);
})
const app = createApp(App)
setupAtnd(app)
setupVant(app)
app.use(store).use(router).use(ElementPlus,{locale}).use(decoration8).use(decoration3).use(digitalFlop).use(borderBox8).use(naive).mount('#app');


