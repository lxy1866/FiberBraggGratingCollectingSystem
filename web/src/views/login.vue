<template>
  <the-header></the-header>
  <a-layout-content
      :style="{margin: 0,height: height_top.height}"
      style="background-image: url(https://s1.ax1x.com/2022/04/17/Lauu59.jpg);
      background-color: #053343;
      ">
<!--    https://i-1-lanrentuku.52tup.com/2020/12/9/e0758bac-bba3-497f-ab0b-fa278f8114a6.jpg?imageView2/2/w/1024/-->
      <div class="center">
        <div class="center-right">

          <div style="display: flex;justify-content: space-between; " class="fontClass">册镇海底管道位移监测</div>
<!--          <div style="display: flex;justify-content: space-between; "><dv-decoration-3 style="width:240px;height:20px;" /></div>-->
          <n-card style="--n-color:#053343;
        --n-title-text-color: white; display: flex;
        --n-border-color: black;
        --n-border-radius: 10px">

            <a-form
                :model="LoginUser"
                name="basic"
                :label-col="{ span: 8 }"
                :wrapper-col="{ span: 16 }"
                autocomplete="off"
                @finish="onFinish"
                @finishFailed="onFinishFailed"
                style="margin-top: 50px;margin-left: -12.5px"
            >
              <a-form-item
                  label="NAME"
                  name="loginName"
                  :rules="[{ required: true, message: 'Please input your name!' }]"
              >
                <a-input v-model:value="LoginUser.loginName" />
              </a-form-item>

              <a-form-item
                  label="PASSWORD"
                  name="password"
                  :rules="[{ required: true, message: 'Please input your password!' }]"
              >
                <a-input-password v-model:value="LoginUser.password" />
              </a-form-item>
              <a-form-item :wrapper-col="{ offset: 14, span: 16 }">
                <a-button  html-type="submit" >LOGIN</a-button>
              </a-form-item>
            </a-form>
          </n-card>
        </div>
      </div>
      <div class="logoImg">
<!--        <img src="@/assets/Logo_stu.png"/>&nbsp;-->
        <img src="@/assets/zhejiangUniversity.png"/>&nbsp;
<!--        <img src="@/assets/guojiadianwang_bg_white.png"/>-->
      </div>
  </a-layout-content>
  <the-footer></the-footer>
</template>
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
      console.log("mima", LoginUser.value.password)
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
      console.log('Failed:', errorInfo);
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
<style scoped>
.center{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}
.center-right{
  width: 30%;
  height: 58%;
  position: relative;
  margin-left: 800px;
  margin-top: 130px;
}
.logoImg img{
  position: relative;
  width: 45px;
  height: 45px;
}
.logoImg{
  display: flex;
  flex-direction: row;
  margin-top: 120px;
  justify-content: center;
  align-items: center;
}
.border-box{
  width: 100%;
  height: 100%;
  display: flex;
}
.fontClass{
  font-size: 32px;
  color:black;
  text-align: center;
  font-family: 宋体;
}
</style>