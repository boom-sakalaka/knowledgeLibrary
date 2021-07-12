<!--
 * @Author: GZH
 * @Date: 2021-07-13 06:59:24
 * @LastEditors: GZH
 * @LastEditTime: 2021-07-13 07:20:56
 * @FilePath: \web\src\components\the-header.vue
 * @Description: 
-->
<template>
  <a-layout-header class="header">
    <div class="logo" />
    <a-menu theme="dark" mode="horizontal" :style="{ lineHeight: '64px' }">
      <a-menu-item key="1">nav 1</a-menu-item>
      <a-menu-item key="2">nav 2</a-menu-item>
      <a-menu-item key="3">nav 3</a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script>
export default {
  name: 'the-header',
};
</script>

<style lang="scss" scoped></style>
