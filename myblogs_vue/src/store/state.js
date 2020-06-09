// 如果本地缓存里有token，就将token赋值
let defaultToken = '';
try {
  if (localStorage.token) {
    defaultToken = localStorage.token
  }
} catch (e) {}

export default {
  token: defaultToken
}
