<template>
  <van-nav-bar
      title="册镇海底管道位移监测"
      class="fontClass"
  />
</template>
<script>
import { defineComponent} from 'vue';
export default defineComponent({
  name:'m_the_header',
  setup(){

  }
})
</script>
<style scoped>
.fontClass{
  background: #053343;
  color: white;
  font-family: 宋体;
}

/deep/.van-nav-bar__title{
  color: cadetblue;
  font-family: 宋体;
}
</style>