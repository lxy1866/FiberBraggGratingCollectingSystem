<template>
  <div class="all">
    <div class="left">
      <n-carousel
          direction="vertical"
          dot-placement="right"
          show-arrow
      autoplay>
        <img
            class="carousel-img"
            src="https://cdn.dribbble.com/users/2187949/screenshots/15516290/media/55595638238f89afb7a9a9a204986ef7.jpeg?compress=1&resize=1200x900&vertical=top"
        >
        <img
            class="carousel-img"
            src="https://cdn.dribbble.com/users/2837665/screenshots/17308481/media/9f034db3ef4617f92780bde0719fc043.png?compress=1&resize=1200x900&vertical=top"
        >
        <img
            class="carousel-img"
            src="https://cdn.dribbble.com/users/2837665/screenshots/15589873/media/94f3f3b2a7f92ffc0ebe704c20af3b8b.png?compress=1&resize=1200x900&vertical=top"
        >
        <img
            class="carousel-img"
            src="https://cdn.dribbble.com/users/2837665/screenshots/17222810/media/bf76e18b8e18ba1ba3b304db09ceb6d6.png?compress=1&resize=1200x900&vertical=top"
        >
      </n-carousel>
    </div>
    <div class="right">

      <a-form
          :model="user"
          name="basic"
          :label-col="{ span: 8 }"
          :wrapper-col="{ span: 16 }"
          autocomplete="off"
          @finish="onFinish"
          @finishFailed="onFinishFailed"
      >
        <a-form-item
            label="Username"
            name="loginName"
            :rules="[{ required: true, message: 'Please input your username!' }]"
        >
          <a-input v-model:value="user.loginName" />
        </a-form-item>

        <a-form-item
            label="Password"
            name="password"
            :rules="[{ required: true, message: 'Please input your password!' }]"
        >
          <a-input-password v-model:value="user.password" />
        </a-form-item>

        <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
          <a-button type="primary" html-type="submit" @click="change">Submit</a-button>
        </a-form-item>
      </a-form>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, ref } from 'vue';
import axios from 'axios';
import { message } from 'ant-design-vue';
import {Tool} from "@/util/tool";
import store from "@/store";
declare let hexMd5: any;
declare let KEY: any;

export default defineComponent({

  setup(props, context) {
    let emit = context.emit;
    function change(){
      emit("isChange",false);
    }
    const user = ref({password:null,loginName:null});
    const onFinish = () => {
      //user.value.password = hexMd5(user.value.password + KEY);
      axios.post("/user/login", user.value).then((response) => {
        const data = response.data; // data = commonResp
        if (data.success) {
          user.value = data.content;
          store.commit("setUser", user.value)
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
      user,
      onFinish,
      onFinishFailed,
      change
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