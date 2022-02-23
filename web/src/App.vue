<template>
  <a-layout>
    <the-header></the-header>
    <router-view/>
    <a-layout-footer style="text-align: center">
      FiberBraggGrating Collecting System ©2022 Created by Danly
    </a-layout-footer>
  </a-layout>
</template>
<script lang="ts">
import { defineComponent } from 'vue';
import TheHeader from '@/components/the-header.vue';
export default defineComponent({
  name: 'app',
  components: {
    TheHeader,
  },
});
</script>