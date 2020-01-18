export default {
  login: state => {
    // 计算登录状态，返回一个boolean值
    return state.token !== ''
  }
}
