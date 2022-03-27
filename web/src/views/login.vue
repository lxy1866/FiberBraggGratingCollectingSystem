<template>
  <a-layout-content style=" background: #001529; height: 598px">
    <div style="display: flex;justify-content: center" >
      <div style="display: flex; flex-flow: column; justify-content: center; margin-top: -6px">
        <div style="font-size: 20px; color: cadetblue;text-align:center;" >海底电缆智能健康监测系统</div>
        <div><dv-decoration-3 style="width:250px;height:30px;" /></div>
      </div>
    </div>
    <div class="innerForm">
      <div class="box left">
<!--        <span>-->
<!--          <img src="@/assets/pipeline.jpg"/>-->
<!--        </span>-->
      </div>
      <div class="box right">
        <n-card style="--n-color: #001529;--n-title-text-color: white; display: flex;">
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
  </a-layout-content>
</template>
<script lang="ts">
import {defineComponent, ref, computed, onMounted} from 'vue';
import { useRouter } from 'vue-router'
import axios from 'axios';
import { message } from 'ant-design-vue';
import store from "@/store";
declare let hexMd5: any;
declare let KEY: any;

export default defineComponent({

  setup() {
    const router=useRouter()
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
      LoginUser.value.password = hexMd5(LoginUser.value.password + KEY);
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
.innerForm{
  margin-top: 50px;
  display: flex;
}
.left{
  width: 100%;
  margin-top: 71px;
  margin-left: 100px;
}
.right{
  width: 50%;
  height: 100%;
  margin-top: 70px;
  margin-right: 100px;
  z-index: 9999;
}
img{
  width: 100%;
  height: 260px;
  border-radius: 5px;
}

</style>