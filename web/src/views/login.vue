<template>
  <a-layout-content style=" background: #001529;" :style="height_top">
    <div class="right">
        <div class="innerForm">
          <dv-decoration-3 style="width:250px;height:30px;"  />
          <n-card title="FBG" size="huge"
                  font-color="white"
                  font-style="italic"
                  style="--n-color: #001529;
                  --n-title-font-size: 37px;
                  --n-title-text-color: white"
          >
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

.right{
  display: flex;
  align-items: center;
  width: 40%;
  margin-top: 100px;
  margin-left: 700px;
}
.innerForm{
  width: 94%;
  margin: 3%;
  height: 335px;
}
</style>