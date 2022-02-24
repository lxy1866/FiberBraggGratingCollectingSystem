import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@surely-vue/table/dist/index.less'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import * as Icons from '@ant-design/icons-vue';
import naive from "naive-ui";
import STable from "@surely-vue/table";
const app = createApp(App)


app.use(store).use(router).use(Antd).use(STable).use(naive).mount('#app');
//全局使用图标
const icons : any = Icons;
for(const i in icons){
    app.component(i, icons[i]);
}

