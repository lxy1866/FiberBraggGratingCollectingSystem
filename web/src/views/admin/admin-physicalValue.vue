<template>
  <a-layout-content style="padding: 0 50px">
    <a-breadcrumb style="margin: 16px 0">

    </a-breadcrumb>
    <a-layout style="padding: 24px 0; background: #fff">

      <div class="content-wrapper">
        <div class="content-left" style="margin-top: 30px">
          <n-space vertical>
            <n-card
                title="📖 设置光纤光栅传感器阵列的应变范围值"
                embedded
                :bordered="false"
            >
              每个光纤光栅传感器都有自己的应变范围，超出范围的记录会被展示到异常波动数据页面中。
            </n-card>
            <n-form :model="model">
              <n-dynamic-input
                  v-model:value="model.dynamicInputValue"
                  item-style="margin-bottom: 0;"
                  :on-create="onCreate"
                  #="{ index, value }"
                  :min="6"
                  :max="6"
              >
                <div style="display: flex">
                  <n-form-item
                      ignore-path-change
                      :show-label="false"
                      :path="`dynamicInputValue[${index}].minMalue`"
                      :rule="dynamicInputRule"
                  >
                    <n-input
                        v-model:value="model.dynamicInputValue[index].minValue"
                        placeholder="minValue"
                        @keydown.enter.prevent
                    />
                  </n-form-item>
                  <div style="height: 34px; line-height: 34px; margin: 0 8px">
                    &lt;
                  </div>
                  <n-form-item
                      ignore-path-change
                      :show-label="false"
                      :path="`dynamicInputValue[${index}].name`"
                      :rule="dynamicInputRule"
                  >
                    <n-input
                        v-model:value="model.dynamicInputValue[index].name"
                        placeholder="请输入val+序号【1-6】"
                        @keydown.enter.prevent
                    />
                    <!--
                      由于在 input 元素里按回车会导致 form 里面的 button 被点击，所以阻止了默认行为
                    -->
                  </n-form-item>
                  <div style="height: 34px; line-height: 34px; margin: 0 8px">
                    &lt;
                  </div>
                  <n-form-item
                      ignore-path-change
                      :show-label="false"
                      :path="`dynamicInputValue[${index}].maxValue`"
                      :rule="dynamicInputRule"
                  >
                    <n-input
                        v-model:value="model.dynamicInputValue[index].maxValue"
                        placeholder="maxValue"
                        @keydown.enter.prevent
                    />
                  </n-form-item>
                </div>
              </n-dynamic-input>
              <n-popconfirm
                  @positive-click="handlePositiveClick"
                  @negative-click="handleNegativeClick"
              >
                <template #trigger>
                  <n-button round style="margin-left: 250px">
                    确认提交
                  </n-button>
                </template>
                是否设置完参数？
              </n-popconfirm>
            </n-form>
          </n-space>
        </div>
        <div class="content-right">
          <pre>{{ JSON.stringify(model.dynamicInputValue, null, 3) }}</pre>
        </div>
      </div>
    </a-layout>
  </a-layout-content>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import {message} from "ant-design-vue";
import axios from 'axios';

const model = ref({
  dynamicInputValue: [{ minValue:'',name: '',maxValue: ''}]
})
const handlePositiveClick  = () =>{
  if(model.value.dynamicInputValue.length === 6){
    //请求后端接口保存6个范围值
    axios.post("/nr/save", model.value.dynamicInputValue).then((response) => {
      const data = response.data;
      if (data.success) {
        message.success("保存成功");
      } else {
        message.error(data.message);
      }
    });
  }else{
    console.log("model.value.dynamicInputValue的类型", typeof model.value.dynamicInputValue)
    message.info("不得少于6个参数范围")
  }

};
const handleNegativeClick =()=> {
  message.info('取消成功')
}
export default defineComponent({
  setup () {
    return {
      dynamicInputRule: {
        trigger: 'input',
        validator (rule: unknown, value: string) {
          //if (value.length >= 15) return new Error('最多输入15个字符')
          return true
        }
      },
      model,
      onCreate () {
        return {
          minValue:'',
          name: '',
          maxValue:''
        }
      },
      handlePositiveClick,
      handleNegativeClick
    }
  }
})
</script>
<style>
.content-wrapper{
  display: flex;
  justify-content: space-evenly;
}
</style>