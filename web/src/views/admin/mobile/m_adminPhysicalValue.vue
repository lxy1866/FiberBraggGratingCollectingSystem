<template>
  <MTheHeader/>
  <n-card
      title="📖 设置光纤光栅传感器阵列的参数量程以及初始位置"
      embedded
      :bordered="false"
  >
    每个光纤光栅传感器都有自己的应变量程（如果是温度传感器则为温度量程，如果是振动为振动量程），超出范围的记录会被展示到异常波动数据页面中。<br/>
    参数一旦设置就不能修改，如果真的需要修改，请联系管理员获取授权码。注意：先点击重新修改再点击确认提交，传感器类别：应变填1，温度填2，振动填3
  </n-card>
    <!-- 每个元素的两侧间隔相等 -->

    <div style="padding-top: 10px">
      <van-row justify="space-between" >
        <van-col span="16" offset="1">
          <p class="fontClass">请先输入光纤光栅传感阵列的个数：</p>
        </van-col>
      </van-row>
      <van-row justify="space-between">
        <van-col span="16" offset="1">
          <n-input-number  style="width: 70%" v-model:value="inputNum" />
        </van-col>
      </van-row>
      <br/>
      <van-row justify="space-around">
        <van-col span="22">
          <p class="fontClass" >请输入每个光纤光栅传感阵列的应变值的量程（或者温度量程，振动量程）以及初始位置：</p>
        </van-col>
      </van-row>
      <n-form :model="model" >
        <n-dynamic-input
            v-model:value="model.dynamicInputValue"
            item-style="margin-bottom: 0;"
            :on-create="onCreate"
            #="{ index, value }"
            :min="inputNum"
            :max="inputNum"

        >
          <van-row justify="space-around">
            <van-col span="20">
              <n-form-item
                  ignore-path-change
                  :show-label="false"
                  :path="`dynamicInputValue[${index}].minValue`"
                  :rule="dynamicInputRule"
              >
                <n-input
                    v-model:value="model.dynamicInputValue[index].minValue"
                    placeholder="minValue"
                    @keydown.enter.prevent

                />
              </n-form-item>
              <n-form-item
                  ignore-path-change
                  :show-label="false"
                  :path="`dynamicInputValue[${index}].name`"
                  :rule="dynamicInputRule"
              >
                <n-input
                    v-model:value="model.dynamicInputValue[index].name"
                    placeholder="name"
                    @keydown.enter.prevent

                />
                <!--
                  由于在 input 元素里按回车会导致 form 里面的 button 被点击，所以阻止了默认行为
                -->
              </n-form-item>
              <n-form-item
                  ignore-path-change
                  :show-label="false"
                  :path="`dynamicInputValue[${index}].maxValue`"
                  :rul="dynamicInputRule"
              >
                <n-input
                    v-model:value="model.dynamicInputValue[index].maxValue"
                    placeholder="maxValue"
                    @keydown.enter.prevent

                />
              </n-form-item>
            </van-col>
          </van-row >
          <van-row justify="space-around">
            <van-col span="28">
              <n-form-item
                  ignore-path-change
                  :show-label="false"
                  :path="`dynamicInputValue[${index}].distance`"
                  :rul="dynamicInputRule"
              >
                <n-input
                    v-model:value="model.dynamicInputValue[index].distance"
                    placeholder="初始位置"
                    @keydown.enter.prevent

                />
              </n-form-item>
              <n-form-item
                  ignore-path-change
                  :show-label="false"
                  :path="`dynamicInputValue[${index}].category`"
                  :rul="dynamicInputRule"
              >
                <n-input
                    v-model:value="model.dynamicInputValue[index].category"
                    placeholder="传感器类别"
                    @keydown.enter.prevent
                    style="height: 100%;"
                />
              </n-form-item>
              <n-form-item
                  ignore-path-change
                  :show-label="false"
                  :path="`dynamicInputValue[${index}].channel`"
                  :rul="dynamicInputRule"
              >
                <n-input
                    v-model:value="model.dynamicInputValue[index].channel"
                    placeholder="通道号"
                    @keydown.enter.prevent
                    style="height: 100%;"
                />
              </n-form-item>
              <n-form-item
                  ignore-path-change
                  :show-label="false"
                  :path="`dynamicInputValue[${index}].arrayNum`"
                  :rul="dynamicInputRule"
              >
                <n-input
                    v-model:value="model.dynamicInputValue[index].arrayNum"
                    placeholder="阵列序号"
                    @keydown.enter.prevent
                    style="height: 100%;"
                />
              </n-form-item>
            </van-col>
          </van-row>
        </n-dynamic-input>
        <n-popconfirm
            @positive-click="handlePositiveClick"
            @negative-click="handleNegativeClick"
        >
          <template #trigger>
            <n-button round :disabled="code !== 'test'" style="margin-left: 35px;">
              确认提交
            </n-button>
          </template>
          是否设置完参数？
        </n-popconfirm>
        <n-popconfirm
            @positive-click="handlePopModifyFrame"
            @negative-click="handleNegativeClick"
        >
          <template #trigger>
            <n-button round style="margin-left: 115px;--n-text-color: #9ebad6">
              重新修改
            </n-button>
          </template>
          是否重新设置参数？
        </n-popconfirm>
        <n-modal
            v-model:show="showModalRef"
            :mask-closable="false"
            preset="dialog"
            title="输入授权码"
            positive-text="确认"
            negative-text="取消"
            @positive-click="onPositiveClick"
            @negative-click="onNegativeClick"
        >
          <n-input
              v-model:value="code"
              type="password"
              show-password-on="mousedown"
              placeholder="密码"
              :maxlength="8"
          />
        </n-modal>
      </n-form>
      <pre style="color: rgb(190 202 214)">{{ JSON.stringify(model.dynamicInputValue, null, 3) }}</pre>
    </div>
  <div style="height: 30px;"></div>
  <MTheFooter/>
</template>
<script lang="ts">
import MTheHeader from '@/components/mobile/m_theHeader.vue';
import MTheFooter from '@/components/mobile/m_theFooter.vue';
import axios from 'axios';
import {defineComponent, onMounted, ref, watch} from "vue";
import {message} from "ant-design-vue";
const model = ref({
  dynamicInputValue: [{ minValue:'',name: '',maxValue: '',distance:'', category:'',channel:'',arrayNum:''}]
})

const code = ref();
let inputNum = ref(null);
const handlePositiveClick  = () =>{
  console.log(inputNum.value)
  if(model.value.dynamicInputValue.length === inputNum.value){
    //请求后端接口保存6个范围值
    axios.post("/nr/save", model.value.dynamicInputValue).then((response) => {
      const data = response.data;
      if (data.success) {
        message.success("保存成功");
        code.value = "";
      } else {
        message.error(data.message);
      }
    });
  }else{
    console.log("model.value.dynamicInputValue的类型", typeof model.value.dynamicInputValue)
    message.info("输入的光纤光栅传感阵列的个数与设置的参数范围个数不匹配！")
  }
};
export default defineComponent({
  name: 'm_admin-physicalValue',
  components:{
    MTheHeader,
    MTheFooter
  },
  setup(){
    const showModalRef = ref(false)
    const onNegativeClick =()=> {
      message.success('Cancel')
      showModalRef.value = false
    };
    const onPositiveClick = ()=> {
      //验证授权码是否正确
      if(code.value === 'test'){
        message.success('授权码正确')
        showModalRef.value = false
      }else{
        message.success('授权码错误')
      }
    }
    const handleNegativeClick =()=> {
      message.info('取消成功')
    }
    const handlePopModifyFrame = ()=>{
      showModalRef.value = true
    }
    const height_top = ref({
      height: ''
    });
    const windowHeight = ref({
      height: ''
    })
    onMounted(()=>{
      watch(inputNum,(newValue, oldValue)=>{
        console.log(newValue,oldValue);
      })
    })
    return {
      dynamicInputRule: {
        trigger: 'input',
        // validator (rule: unknown, value: string) {
        //   //if (value.length >= 15) return new Error('最多输入15个字符')
        //   return true
        // }
      },
      model,
      onCreate () {
        return {
          minValue:'',
          name: '',
          maxValue:'',
          distance:'',
          category:'',
          channel:'',
          arrayNum:''
        }
      },
      handlePositiveClick,
      handleNegativeClick,
      onPositiveClick,
      onNegativeClick,
      showModalRef,
      handlePopModifyFrame,
      code,
      height_top,
      windowHeight,
      inputNum
    }
  }
})
</script>
<style scoped>
.fontClass{
  font-family:-apple-system,BlinkMacSystemFont,Helvetica Neue,Helvetica,Segoe UI,Arial,Roboto,PingFang SC,miui,Hiragino Sans GB,Microsoft Yahei,sans-serif;;
  color:  rgb(190 202 214);
}


</style>