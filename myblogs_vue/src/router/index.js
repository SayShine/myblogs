import Vue from 'vue'
import Router from 'vue-router'
import ZuanTranslator from '@/components/ZuanTranslator'
import MainNotice from '@/components/MainNotice'
import Md5Encode from "../components/Md5Encode";
Vue.use(Router)

export default new Router({
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
    }
  ]
})
