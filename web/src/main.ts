/*
 * @Author: GZH
 * @Date: 2021-07-12 21:53:00
 * @LastEditors: GZH
 * @LastEditTime: 2021-07-13 07:55:32
 * @FilePath: \web\src\main.ts
 * @Description:
 */
import { createApp } from 'vue';

import App from './App.vue';
import router from './router';
import store from './store';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import * as Icons from '@ant-design/icons-vue';

const app = createApp(App);
app
  .use(Antd)
  .use(store)
  .use(router)
  .mount('#app');

// 全局使用图标库
const icons: any = Icons;
for (const i in icons) {
  app.component(i, icons[i]);
}
