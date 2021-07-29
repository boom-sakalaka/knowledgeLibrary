<!--
 * @Author: GZH
 * @Date: 2021-07-13 06:59:24
 * @LastEditors: GZH
 * @LastEditTime: 2021-07-29 22:30:01
 * @FilePath: \web\src\components\the-header.vue
 * @Description: 
-->
<template>
  <a-layout-header class="header">
    <div class="header-box">
      <div class="logo" />
      <div class="menu-box">
        <a-menu theme="dark" mode="horizontal" :style="{ lineHeight: '64px' }">
          <a-menu-item key="/">
            <router-link to="/">首页</router-link>
          </a-menu-item>
          <a-menu-item key="/admin/user">
            <router-link to="/admin/user">用户管理</router-link>
          </a-menu-item>
          <a-menu-item key="/admin/ebook">
            <router-link to="/admin/ebook">电子书管理</router-link>
          </a-menu-item>
          <a-menu-item key="/admin/category">
            <router-link to="/admin/category">分类管理</router-link>
          </a-menu-item>
          <a-menu-item key="/about">
            <router-link to="/about">关于我们</router-link>
          </a-menu-item>
        </a-menu>
      </div>
      <div>
        <a class="login-menu" @click="showLoginModal">
          <span>登录</span>
        </a>
      </div>
    </div>

    <a-modal title="登录" v-model:visible="loginModalVisible" :confirm-loading="loginModalLoading" @ok="login">
      <a-form :model="loginUser" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
        <a-form-item label="登录名">
          <a-input v-model:value="loginUser.loginName" />
        </a-form-item>
        <a-form-item label="密码">
          <a-input v-model:value="loginUser.password" type="password" />
        </a-form-item>
      </a-form>
    </a-modal>
  </a-layout-header>
</template>

<script>
import { defineComponent, ref } from 'vue';
export default defineComponent({
  name: 'the-header',
  setup() {
    // 用来登录
    const loginUser = ref({
      loginName: 'test',
      password: 'test',
    });

    const loginModalVisible = ref(false);
    const loginModalLoading = ref(false);
    const showLoginModal = () => {
      loginModalVisible.value = true;
    };

    const login = () => {
      console.log('登录中');
    };
    return {
      loginModalVisible,
      loginModalLoading,
      loginUser,
      showLoginModal,
      login,
    };
  },
});
</script>

<style scoped>
.logo {
  width: 120px;
  height: 31px;
  /*background: rgba(255, 255, 255, 0.2);*/
  /*margin: 16px 28px 16px 0;*/
  float: left;
  color: white;
  font-size: 18px;
}
.header-box {
  display: flex;
}
.menu-box {
  flex: 1;
}
.login-menu {
  float: right;
  color: white;
  padding-left: 10px;
  width: 100px;
}
</style>
