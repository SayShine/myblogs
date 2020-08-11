import Vue from 'vue'
import Router from 'vue-router'
import ZuanTranslator from '@/components/tool/ZuanTranslator'
import MainNotice from '@/components/MainNotice'
import Md5Encode from "../components/tool/Md5Encode"
import Taobao from "../components/tool/Taobao"
import ToLogin from '@/components/login/toLogin'
import Markdown from '@/components/tool/Markdown/Markdown'
import MdTable from '@/components/tool/Markdown/MdTable'
import StudyTable from "@/components/tool/StudyTable";
Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'MainNotice',
      component: MainNotice
      // path: '',
      // redirect: '/login/toLogin',
      // children: [
      //   {
      //     path: '/login/toLogin',
      //     name: 'toLogin',
      //     component: MainNotice,
      //     // component: (resolve) => require(['@/components/MainNotice'], resolve),
      //   }
      // ]
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
      path: '/tool/Markdown/Detail',
      name: 'Markdown',
      component: Markdown
    },
    {
      path: '/tool/MdTable',
      name: 'MdTable',
      component: MdTable
    },
    {
      path: '/tool/StudyTable',
      name: 'StudyTable',
      component: StudyTable
    }
  ]
})
