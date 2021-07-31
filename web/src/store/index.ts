/*
 * @Author: GZH
 * @Date: 2021-07-12 21:53:00
 * @LastEditors: GZH
 * @LastEditTime: 2021-07-31 21:39:03
 * @FilePath: \web\src\store\index.ts
 * @Description:
 */
import { createStore } from 'vuex';

declare let SessionStorage: any;
const USER = 'USER';

const store = createStore({
  state: {
    user: SessionStorage.get(USER) || {},
  },
  mutations: {
    setUser(state, user) {
      console.log('store user：', user);
      state.user = user;
      SessionStorage.set(USER, user);
    },
  },
  actions: {},
  modules: {},
});

export default store;
