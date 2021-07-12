/*
 * @Author: GZH
 * @Date: 2021-07-12 21:53:00
 * @LastEditors: GZH
 * @LastEditTime: 2021-07-13 06:18:40
 * @FilePath: \knowledgeLibrary\web\src\main.ts
 * @Description:
 */
import { createApp } from 'vue';

import App from './App.vue';
import router from './router';
import store from './store';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

createApp(App)
  .use(Antd)
  .use(store)
  .use(router)
  .mount('#app');
