import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import 'element-plus/dist/index.css'
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
// ... 其他导入保持不变 ...

// 创建应用实例
const app = createApp(App);

// 按顺序注册插件
app.use(store);
app.use(router);
app.use(ElementPlus, {
    locale: zhCn,
});
app.use(naive);

// 注册 DataV 组件
app.use(decoration8);
app.use(decoration3);
app.use(digitalFlop);
app.use(borderBox8);

// 注册其他组件
setupAtnd(app);
setupVant(app);

// 最后挂载应用
app.mount('#app');