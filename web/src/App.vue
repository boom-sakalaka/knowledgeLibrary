<!--
 * @Author: GZH
 * @Date: 2021-07-12 21:53:00
 * @LastEditors: GZH
 * @LastEditTime: 2021-07-13 07:11:17
 * @FilePath: \web\src\App.vue
 * @Description: 
-->
<template>
  <a-layout style="min-height: 100vh">
    <the-header />
    <router-view />
    <the-footer />
  </a-layout>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import TheHeader from '@/components/the-header.vue';
import TheFooter from '@/components/the-footer.vue';

export default defineComponent({
  name: 'app',
  components: {
    TheHeader,
    TheFooter,
  },
});
</script>

<style>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>
