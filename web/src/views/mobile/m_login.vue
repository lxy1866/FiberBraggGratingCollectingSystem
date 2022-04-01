<template>
  <div >
    <MTheHeader/>
    <br/>
    <br/>
    <van-form @submit="onSubmit">
      <van-cell-group inset>
        <van-field
            v-model="LoginUser.loginName"
            name="loginName"
            label="NAME"
            :rules="[{ required: true, message: 'Please input your name!' }]"
        />
        <van-field
            v-model="LoginUser.password"
            type="password"
            name="password"
            label="PASSWORD"
            :rules="[{ required: true, message: 'Please input your password!' }]"
        />
      </van-cell-group>
      <div style="margin: 16px;">
        <van-button round block type="primary" native-type="submit">
          LOGIN
        </van-button>
      </div>
    </van-form>
    <div class="logoImg">
      <img src="@/assets/Logo_stu.png"  />
      <img src="@/assets/zhejiangUniversity.png" />&nbsp;
      <img src="@/assets/guojiadianwang.png" />
    </div>
    <MTheFooter/>
  </div>
</template>
<script lang="ts">
import { Toast } from 'vant';
import { useRouter } from 'vue-router'
import axios from 'axios';
import store from "@/store";
import MTheHeader from '@/components/mobile/m_the-header.vue';
import MTheFooter from '@/components/mobile/m_the-footer.vue';
import { defineComponent, ref, computed, watch } from 'vue';
declare let hexMd5: any;
declare let KEY: any;
export default defineComponent({
  components:{
    MTheFooter,
    MTheHeader
  },
  setup() {
    const router=useRouter()
    //用来登录
    const LoginUser = ref({password:null,loginName:null});
    const onSubmit = ()=>{
      LoginUser.value.password = hexMd5(LoginUser.value.password + KEY);
      axios.post("/user/login", LoginUser.value).then((response) => {
        const data = response.data; // data = commonResp
        if (data.success) {
          router.push({ path: '/' })
          store.commit("setUser", data.content)
          Toast.success("登录成功");

        } else {
          Toast.fail(data.message);
        }
      });
    }
    return {
      LoginUser,
      onSubmit
    };
  },
});
</script>
<style scoped>

.logoImg{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.logoImg img{
  width: 45px;
  height: 45px;
}
</style>