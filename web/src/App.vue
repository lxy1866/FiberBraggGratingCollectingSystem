<template>
    <router-view/>
</template>
<script lang="ts">
import { defineComponent } from 'vue';
import TheHeader from '@/components/the-header.vue';
import TheFooter from '@/components/the-footer.vue';
export default defineComponent({
  name: 'app',
  components: {
  },
});
</script>