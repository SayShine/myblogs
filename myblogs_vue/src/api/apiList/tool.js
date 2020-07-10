/**
 * 工具接口列表
 */
import baseUrl from '../baseUrl'; // 导入接口域名列表
import axios from '../../http'; // 导入http中创建的axios实例
import qs from 'qs'

const Tool = {
  toTranslateWords(params) {
    return axios.get(`/tool/toTranslate`, {params: params})
  },
  toEncoding(params) {
    return axios.get(`/tool/toEncode`, {params: params})
  },


  //根据用户名获取博客列表
  getMdListByUserName(username){
    return axios.get('/tool/MdList/'+username+'/');
  },

  //saveMdlist
  updateMdList(params){
    return axios.post('/tool/MdList/',JSON.stringify(params),
      {headers:{'Content-Type': 'application/json'}
      })
  },

  insertMdList(params){
    return axios.put('/tool/MdList/',JSON.stringify(params),
      {headers:{'Content-Type': 'application/json'}
      })
  },

  //批量删除
  deleteMdList(params){
    return axios.get('/tool/deleteMdList',{params:params})
  }
}

export default Tool;
