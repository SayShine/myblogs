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
  getMdList(params){
    return axios.get('/tool/getMdListByUsername',{params:params})
  },
  //保存博客列表
  saveMdList(params){
    return axios.request({
      url:'/tool/savaMdList',
      method:'post',
      headers:{
        'Content-Type': 'application/json'
      },
      data :JSON.stringify(params)
    })
  },
  //批量删除
  deleteMdList(params){
    return axios.get('/tool/deleteMdList',{params:params})
    // return axios.request({
    //   url:'/tool/deleteMdList',
    //   method:'post',
    //   headers:{'Content-Type':'application/x-www-form-urlencoded'},
    //   data :{params:params}
    // })
  }
}

export default Tool;
