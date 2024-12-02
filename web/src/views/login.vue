<!-- web/src/views/login.vue -->
<template>
  <a-layout-content
      :style="{margin: 0, height: height_top.height}"
      style="background-image: url(https://s1.ax1x.com/2022/04/17/Lauu59.jpg); background-size: cover;">
      <div class="center">
        <div class="center-right">
          <div style="text-align: center; margin-bottom: 20px;">
            <div class="fontClass">海底管道智能健康监测系统</div>
            <!-- 去掉装饰 -->
          </div>
          <n-card class="login-card">
            <a-form
                :model="LoginUser"
                name="basic"
                :label-col="{ span: 8 }"
                :wrapper-col="{ span: 16 }"
                autocomplete="off"
                @finish="onFinish"
                @finishFailed="onFinishFailed"
                style="margin-top: 50px;"
            >
              <a-form-item
                  label="NAME"
                  name="loginName"
                  :rules="[{ required: true, message: 'Please input your name!' }]">
                <a-input v-model:value="LoginUser.loginName" />
              </a-form-item>

              <a-form-item
                  label="PASSWORD"
                  name="password"
                  :rules="[{ required: true, message: 'Please input your password!' }]">
                <a-input-password v-model:value="LoginUser.password" />
              </a-form-item>

              <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
                <a-button class="login-button" html-type="submit">LOGIN</a-button>
              </a-form-item>
            </a-form>
          </n-card>
        </div>
      </div>
  </a-layout-content>
</template>

<style scoped>
.center {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}

.center-right {
  width: 30%;
  height: auto; /* 自适应高度 */
  position: relative;
  margin-left: 700px;
  margin-top: 100px;
}

.login-card {
  background-color: rgba(0, 0, 0, 0.7); /* 半透明背景 */
  border-radius: 10px; /* 圆角 */
  padding: 20px; /* 内边距 */
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.5); /* 阴影 */
}

.fontClass {
  font-size: 32px;
  color: #003366; /* 修改为更深的蓝色 */
  text-align: center;
  font-family: 宋体;
}

.login-button {
  background-color: #1890ff; /* 按钮背景色 */
  color: white; /* 按钮文字颜色 */
  border: none; /* 去掉边框 */
  border-radius: 5px; /* 圆角 */
  width: 100%; /* 按钮宽度 */
  height: 40px; /* 按钮高度 */
  transition: background-color 0.3s; /* 动画效果 */
}

.login-button:hover {
  background-color: #40a9ff; /* 悬停时的背景色 */
}
</style>
<script lang="ts" >
import {defineComponent, ref, onMounted} from 'vue';
import { useRouter } from 'vue-router'
import axios from 'axios';
import { message } from 'ant-design-vue';
import store from "@/store";
import TheHeader from '@/components/theHeader.vue';
import TheFooter from '@/components/theFooter.vue';
declare let hexMd5: any;
declare let KEY: any;

export default defineComponent({

  components:{
    TheHeader,
    TheFooter
  },
  setup() {
    const router=useRouter()
    //用来登录
    const LoginUser = ref({password:null,loginName:null});

    const height_top = ref({
      height: ''
    });
    onMounted(()=>{
      if(window.innerHeight != null){
        height_top.value.height = window.innerHeight+'px';
      }
      window.onresize = () => {
        return (() => {
          height_top.value.height = window.innerHeight+'px';
        })();
      };

    })
    const onFinish = () => {
      LoginUser.value.password = hexMd5(LoginUser.value.password + KEY);
      //console.log("mima", LoginUser.value.password)
      axios.post("/user/login", LoginUser.value).then((response) => {
        const data = response.data; // data = commonResp
        if (data.success) {
          router.push({ path: '/' })
          store.commit("setUser", data.content)
          message.success("登录成功");
        } else {
          message.error(data.message);
        }
      });
    };

    const onFinishFailed = (errorInfo: any) => {
      //console.log('Failed:', errorInfo);
    };
    return {
      onFinish,
      onFinishFailed,
      LoginUser,
      height_top
    };
  },
});
</script>
