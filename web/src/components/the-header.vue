<!--
 * @Author: GZH
 * @Date: 2021-07-13 06:59:24
 * @LastEditors: GZH
 * @LastEditTime: 2021-07-14 20:42:01
 * @FilePath: \web\src\components\the-header.vue
 * @Description: 
-->
<template>
  <a-layout-header class="header">
    <div class="logo" />
    <a-menu theme="dark" mode="horizontal" :style="{ lineHeight: '64px' }">
      <a-menu-item key="1">
        <router-link to="/">首页</router-link>
      </a-menu-item>
      <a-menu-item key="2">
        <router-link to="/admin/ebook">电子书管理</router-link>
      </a-menu-item>
      <a-menu-item key="3">
        <router-link to="/about">关于我们</router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script>
export default {
  name: 'the-header',
};
</script>

<style lang="scss" scoped></style>
