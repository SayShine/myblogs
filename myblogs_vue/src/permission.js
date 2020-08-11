import router from './router'
import store from './store'
import { Message } from 'element-ui'
import localStorage from "./assets/utils/js/localStorage";

router.beforeEach(((to, from, next) => {
  if(localStorage.get('token')){
    //hasToken
    if(to.path === '/login/toLogin') {
      next({path: '/'})
    }
    next()
  }else {
    if('/login/toLogin'.indexOf(to.path) !== -1 && to.path !== '/'){
      next()
    }else{
      next(`/login/toLogin?redirect=${to.fullPath}`)
    }

  }

}))
