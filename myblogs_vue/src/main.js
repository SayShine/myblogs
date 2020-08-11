// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

// 解决两次访问相同路由地址报错
import VueRouter from "vue-router";

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
// 导入api接口
import api from './api'
Vue.prototype.$api = api; // 将api挂载到vue的原型上复制代码
Vue.config.productionTip = false;

//导入jquery
import $ from 'jquery'

import store from "./store/index";
//路由
Vue.use(store);
Vue.prototype.$store = store;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router, //路由
  store,
  render: h => h(App)
  //  render: h => h(App)等价于下面
  // components: {App},
  //  声明有哪些组件
  // template: '<App/>'
  //   表示用<app><app/>替换index.html中的<div id="app"></div>


})
