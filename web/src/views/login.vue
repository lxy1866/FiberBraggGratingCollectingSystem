<template>
  <a-layout-content style="padding: 0; background: #001529;" :style="height_top">
    <div class="all" >
    <div class="left">
    </div>
    <div class="right">
      <dv-border-box-8 >
        <div class="innerForm">
          <n-card title="FBG" size="huge"
                  style=" font-weight: bold;
                  font-style: italic;
                  --n-color: #001529;
                  --n-border-radius: 3px;
                  --n-color-modal: #001529;
                  --n-color-popover: #fff;
                  --n-color-target: #18a058;
                  --n-text-color: white;
                  --n-line-height: 1.6;
                  --n-action-color: rgb(250, 250, 252);
                  --n-title-text-color: white;
                  --n-title-font-weight: 500;
                  --n-close-color: rgba(122, 122, 122, 1);
                  --n-close-color-hover: rgba(89, 89, 89, 1);
                  --n-close-color-pressed: rgba(149, 149, 149, 1);
                  --n-border-color: rgb(239, 239, 245);
                  --n-box-shadow: 0 1px 2px -2px rgba(0, 0, 0, 0.08), 0 3px 6px 0 rgba(0, 0, 0, 0.06), 0 5px 12px 4px rgba(0, 0, 0, 0.04);
                  --n-padding-top: 27px;
                  --n-padding-bottom: 28px;
                  --n-padding-left: 40px;
                  --n-font-size: 14px;
                  --n-title-font-size: 30px;
                  --n-close-size: 18px;
                  font-weight: bold;">

            <a-form
                :model="LoginUser"
                name="basic"
                :label-col="{ span: 8 }"
                :wrapper-col="{ span: 16 }"
                autocomplete="off"
                @finish="onFinish"
                @finishFailed="onFinishFailed"
            >
              <div style="display: flex; justify-content: center">
                <dv-decoration-3 style="width:250px;height:30px;"  />
              </div>
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
      </dv-border-box-8>

    </div>

  </div>
  </a-layout-content>
</template>
<script lang="ts">
import {defineComponent, ref, computed, onMounted} from 'vue';
import axios from 'axios';
import { message } from 'ant-design-vue';
import {Tool} from "@/util/tool";
import store from "@/store";
import {anchorDark} from "naive-ui";
declare let hexMd5: any;
declare let KEY: any;


export default defineComponent({

  setup() {
    //用来登录
    const LoginUser = ref({password:null,loginName:null});

    const height_top = ref({
      height: ''
    });
    onMounted(()=>{
      if(window.innerHeight != null){
        height_top.value.height = window.innerHeight-134+'px';
      }

      window.onresize = () => {
        return (() => {
          height_top.value.height = window.innerHeight-70+'px';
        })();
      };

    })
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
      LoginUser,
      height_top
    };
  },
});
</script>
<style scoped>
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
  width: 40%;
  margin-top: 100px;
  margin-right: 200px;
}
.innerForm{
  width: 94%;
  margin: 3%;
  height: 335px;
}
.logo{
  width: 80px;
  height: 80px;
}
</style>