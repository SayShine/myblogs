/**
 * axios封装
 * 请求拦截、响应拦截、错误统一处理
 * 注意: @代表src的路径
 */

import axios from 'axios';
import store from '@/store';
import router from './router';
import qs from 'qs';
import localStorage from '@/assets/utils/js/localStorage';
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
      alert('请先登陆！');
      toLogin();
      break;
    // 403请求不存在
    case 404:
      alert('请求不存在');
      break;
    default:
      console.log(other);
  }
};


// 创建axios实例
var instance = axios.create({
  //配置baseUrl 分为开发和生产环境 见config配置
  baseURL: process.env.BASE_URL,
  timeout: 1000 * 12,
  headers:{'Content-Type':'application/x-www-form-urlencoded'},
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
    // 用于判断用户登录情况refreshToken
    const token = localStorage.get('token')
    token && (config.headers.Authorization = "Bearer " + token);
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
      if(response.status===402){
        //获取请求
        const config = response.config
        // 402 token过期 清除token并跳转登录页
        //不是登录页
        console.log(111)
        if(router.currentRoute.name !== 'toLogin'){
          if (!isRefreshing) {
            isRefreshing = true
            return refreshToken(localStorage.get('username')).then(res => {
              var refreshedtoken = res.data.body
              if(refreshedtoken !== ''&& refreshedtoken !== null){
                console.log("刷新token:"+refreshedtoken)
                localStorage.set('token',refreshedtoken);
                config.headers['Authorization'] = 'Bearer ' + refreshedtoken;
                console.log(config.baseURL)
                // config.baseURL = ''
                // 已经刷新了token，将所有队列中的请求进行重试
                isRefreshing = false;
                requests.forEach(cb => cb('Bear '+refreshedtoken));
                requests = [];
                return instance(config);
              }else{
                  isRefreshing = false;
                  alert('登录过期，请重新登录');
                  store.commit('clearToken');
                  setTimeout(() => {
                    toLogin();
                  }, 1000);
              }
            }).catch(res => {
              console.error('refreshtoken error =>', res)
              window.location.href = '/'
            }).finally(() => {
              isRefreshing = false
            })
          }else{
            //正在刷新token 将返回一个未执行resolve的promise
            return new Promise((resolve) => {
              // 将resolve放进队列，用一个函数形式来保存，等token刷新后直接执行
              requests.push((token) => {
                config.headers['Authorization'] = token;
                console.log(config.baseURL)
                resolve(instance(config))
              })
            })
          }

        }else{
          //在登录页面  给他登录不做处理

          //判断可刷新标志 （登陆成功时改为true）
          // if(window.localStorage.getItem("refreshToken")){
          //
          //
          // }else{
          //   alert('登录过期，请重新登录');
          //   store.commit('clearToken');
          //   setTimeout(() => {
          //     toLogin();
          //   }, 1000);
          // }
        }
      }else{
        errorHandle(response.status, response.data.message);
        return Promise.reject(response).catch(error => error);
      }

    } else {
      // 处理断网的情况
      if (!window.navigator.onLine) {
        store.commit('changeNetwork', false);
      } else {
        return Promise.reject(error);
      }
    }
  });

/**
 * refreshToken 刷新token
 * @param {username} 用户名
 * @return {Boolean} key 对应 localStorage 的值
 */
function refreshToken (username) {
  // instance是当前request.js中已创建的axios实例
  return instance.get('/admin/refreshToken',{params:{"username":username}}).then(res => {
    return res
  })
}

// 是否正在刷新的标记
let isRefreshing = false
// 重试队列，每一项将是一个待执行的函数形式
let requests = []

// 导出
export default instance;
