import Vue from 'vue'
import Router from 'vue-router'
import ZuanTranslator from '@/components/tool/ZuanTranslator'
import MainNotice from '@/components/MainNotice'
import Md5Encode from "../components/tool/Md5Encode"
import Taobao from "../components/tool/Taobao"
import ToLogin from '@/components/login/toLogin'
import Admin from '@/components/Admin'
Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'MainNotice',
      component: MainNotice
    },
    {
      path: '/tool/zuanTranslator',
      name: 'ZuanTranslator',
      component: ZuanTranslator
    },
    {
      path: '/tool/Md5Encode',
      name: 'md5',
      component: Md5Encode
    },
    {
      path: '/tool/Taobao',
      name: 'taobao',
      component: Taobao
    },
    {
      path: '/login/toLogin',
      name: 'toLogin',
      component: ToLogin
    },
    {
      path: '/tool/Admin',
      name: 'Admin',
      component: Admin
    }
  ]
})
