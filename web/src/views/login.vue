<template>
  <div class="all">
    <div class="left">
      <n-carousel
          direction="vertical">
       <img
            class="carousel-img"
            src="https://cdn.dribbble.com/users/2187949/screenshots/15516290/media/55595638238f89afb7a9a9a204986ef7.jpeg?compress=1&resize=1200x900&vertical=top"
        >
      </n-carousel>
    </div>
    <div class="right">

      <n-card title="账号登录" size="huge">
        <a-form
            :model="LoginUser"
            name="basic"
            :label-col="{ span: 8 }"
            :wrapper-col="{ span: 16 }"
            autocomplete="off"
            @finish="onFinish"
            @finishFailed="onFinishFailed"
        >
          <a-form-item
              label="账号"
              name="loginName"
              :rules="[{ required: true, message: '请输入你的账号!' }]"
          >
            <a-input v-model:value="LoginUser.loginName" />
          </a-form-item>

          <a-form-item
              label="密码"
              name="password"
              :rules="[{ required: true, message: '请输入你都密码!' }]"
          >
            <a-input-password v-model:value="LoginUser.password" />
          </a-form-item>

          <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
            <a-button  html-type="submit" >提交</a-button>
          </a-form-item>
        </a-form>
      </n-card>

    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, ref, computed } from 'vue';
import axios from 'axios';
import { message } from 'ant-design-vue';
import {Tool} from "@/util/tool";
import store from "@/store";
declare let hexMd5: any;
declare let KEY: any;

export default defineComponent({

  setup() {
    //用来登录
    const LoginUser = ref({password:null,loginName:null});

    const onFinish = () => {
      //user.value.password = hexMd5(user.value.password + KEY);
      axios.post("/user/login", LoginUser.value).then((response) => {
        const data = response.data; // data = commonResp
        if (data.success) {
          store.commit("setUser", data.content)
          message.success("登录成功");
        } else {
          message.error(data.message);
        }
      });
    };

    const onFinishFailed = (errorInfo: any) => {
      console.log('Failed:', errorInfo);
    };
    return {
      onFinish,
      onFinishFailed,
      LoginUser
    };
  },
});
</script>
<style>

.n-carousel {
  width: 950px;
  height: 570px;
}
.carousel-img{
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 15px;
}
.all{
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.left{
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  margin-left: 40px;
}
.right{
  display: flex;
  align-items: center;
  margin-right: 130px;
}
.logo{
  width: 80px;
  height: 80px;
}
</style>