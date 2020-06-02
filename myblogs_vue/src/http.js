/**
 * axios封装
 * 请求拦截、响应拦截、错误统一处理
 */

import axios from 'axios';
import store from '@/store';
import router from './router';
import qs from 'qs'
/**
 * 跳转登录页
 * 携带当前页面路由，(用于在登录页面完成登录后返回当前页面)
 */
const toLogin = () => {
  router.replace({
    path: '/login/toLogin',
    query: {
      redirect: router.currentRoute.fullPath
    }
  });
}

/**
 * 请求失败后的错误统一处理
 * @param {Number} status 请求失败的状态码
 */
const errorHandle = (status, other) => {
  // 状态码判断
  switch (status) {
    // 401: 未登录状态，跳转登录页
    case 401:
      alert('请先登陆！')
      toLogin();
      break;
    // 402 token过期 清除token并跳转登录页
    case 402:
      alert('登录过期，请重新登录');
      store.commit('clearToken');
      setTimeout(() => {
        toLogin();
      }, 1000);
      break;
    // 403无权限访问
    case 403:
      alert('无权限访问');
      break;
    // 403请求不存在
    case 404:
      alert('请求不存在');
      break;
    default:
      console.log(other);
  }
}

// 创建axios实例
var instance = axios.create({
  timeout: 1000 * 12,
  headers:{'Content-Type':'application/x-www-form-urlencoded'}
});

/**
 * 请求拦截器
 * 每次请求前，如果存在token则在请求头中携带token
 */
instance.interceptors.request.use(
  config => {
    // post请求进行序列化
    if(config.method=='post'){
      config.data = qs.stringify(config.data);
    }
    // 用于判断用户登录情况
    const token = store.state.token;
    token && (config.headers.Authorization = token);
    return config;
  },
  error => Promise.error(error));

// 响应拦截器
instance.interceptors.response.use(
  // 请求成功
  res => {
    if (res.status === 200) {
      return Promise.resolve(res);
    } else {
      return Promise.reject(res);
    }
  },
  // 请求失败
  error => {
    const {
      response
    } = error;
    if (response) {
      errorHandle(response.status, response.data.message);
      return Promise.reject(response).catch(error => error);
    } else {
      // 处理断网的情况
      if (!window.navigator.onLine) {
        store.commit('changeNetwork', false);
      } else {
        return Promise.reject(error);
      }
    }
  });

// 导出
export default instance;
