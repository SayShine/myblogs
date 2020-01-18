/**
 * 登录接口列表
 */
import baseUrl from '../baseUrl'; // 导入接口域名列表
import axios from '../../http'; // 导入http中创建的axios实例

const Admin = {
  toLogin(params) {
    return axios.post(`${baseUrl.url}/admin/toLogin`, params)
  },
  loginOut(params) {
    return axios.post(`${baseUrl.url}/admin/loginOut`, params)
  },
  register(param) {
    return axios.post(`${baseUrl.url}/admin/toRegister`, params)
  },
  changePassword(params){
    return axios.post(`${baseUrl.url}/admin/changePassword`, params)
  }
}

export default Admin;
