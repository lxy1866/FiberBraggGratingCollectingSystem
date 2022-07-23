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
/deep/.ant-form {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  color: #053343;
  font-size: 25px;
  font-variant: contextual;
  line-height: 1.5715;
  list-style: none;
  font-feature-settings: 'tnum';
}
/deep/.ant-form-item-label {
  display: inline-block;
  flex-grow: 0;
  overflow: hidden;
  white-space: nowrap;
  text-align: right;
  vertical-align: middle;
  margin-right: 10px;
}
/deep/.ant-form-item-label > label {
  position: relative;
  display: inline-flex;
  align-items: center;
  height: 32px;
  color: cadetblue;
  font-size: 15px;
  font-style: normal;
}
/deep/.ant-input {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  font-variant: tabular-nums;
  list-style: none;
  font-feature-settings: 'tnum';
  position: relative;
  display: inline-block;
  width: 100%;
  padding: 4px 11px;
  color: white;
  font-size: 14px;
  line-height: 1.5715;
  background-color: transparent;
  background-image: none;
  border: 1px solid cadetblue;
  border-radius: 2px;
  transition: all 0.3s;
}
/deep/.ant-form-item-label > label.ant-form-item-required:not(.ant-form-item-required-mark-optional)::before {
  content: '';
}
/deep/.ant-btn {
  line-height: 1.5715;
  position: relative;
  display: inline-block;
  font-weight: 400;
  white-space: nowrap;
  text-align: center;
  border: 1px solid white;
  cursor: pointer;
  transition: all 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  touch-action: manipulation;
  height: 32px;
  padding: 4px 15px;
  font-size: 14px;
  border-radius: 2px;
  color: cadetblue;
  background: transparent;
}
/deep/.ant-btn:hover, .ant-btn:focus {
  color: #ff4d4f;
  background: transparent;
  border-color: white;
}
/deep/.ant-form-item-has-error .ant-input, .ant-form-item-has-error .ant-input-affix-wrapper, .ant-form-item-has-error .ant-input:hover, .ant-form-item-has-error .ant-input-affix-wrapper:hover {
  background-color: transparent;
  border-color: white;
}
/deep/.ant-input-affix-wrapper {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  font-variant: tabular-nums;
  list-style: none;
  font-feature-settings: 'tnum';
  position: relative;
  display: flex;
  border: 1px solid white;
  border-radius: 2px;
  padding: 4px 11px;
  width: 100%;
  text-align: start;
  background-color: #053343;
  background-image: none;
  color: white;
  font-size: 14px;
  line-height: 1.5715;
}
/deep/.ant-input-password-icon {
  color: white;
  cursor: pointer;
  transition: all 0.3s;
}

/deep/#basic_password.ant-input{
  background:#053343;
}
</style>