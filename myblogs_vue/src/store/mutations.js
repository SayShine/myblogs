export default {
  changeToken (state, token) {
    //登录或者注册时，存储token的方法
    state.token = token
    try {
      // localStorage.token = token
    } catch (e) {}
  },
  clearToken (state) {
    //退出登录时清除token的方法
    localStorage.token = '';
    state.token = '';
  }
}
