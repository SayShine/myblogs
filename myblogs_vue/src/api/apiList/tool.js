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


  //博客start-------------------------------------------
  //根据用户名获取博客列表
  getMdListByUserName(username){
    return axios.get('/tool/MdList/'+username+'/');
  },

  //saveMdlist
  updateMdList(params){
    return axios.put('/tool/MdList/',JSON.stringify(params),
      {headers:{'Content-Type': 'application/json'}
      })
  },

  insertMdList(params){
    return axios.post('/tool/MdList/'+params.username+'/',JSON.stringify(params),
      {headers:{'Content-Type': 'application/json'}}
      )
  },

  //批量删除
  deleteMdList(params){
    // return axios.get(`/tool/deleteMdList/`, {params: params})
    return axios.put('/tool/deleteMdList/',JSON.stringify(params),
      {headers:{'Content-Type': 'application/json'}
      })
  },

  //博客end-------------------------------------------------

  //学习博客start-------------------------------------------------
  //获取个人学习网站
  getStudyList(){
    return axios.get('/tool/studyList');
  },

  //插入操作
  insertStudyList(params){
    return axios.post('/tool/studyList', JSON.stringify(params),
      {headers:{'Content-Type': 'application/json'}}
      )

  },

  //saveMdlist
  updateStudyList(params){
    return axios.put('/tool/studyList/',JSON.stringify(params),
      {headers:{'Content-Type': 'application/json'}
      })
  },

  //批量修改状态 （目前用于删除）
  deleteStudyList(params){
    // return axios.get(`/tool/deleteMdList/`, {params: params})
    return axios.put('/tool/allStudyList/',JSON.stringify(params),
      {headers:{'Content-Type': 'application/json'}
      })
  },


  //学习博客end---------------------------------------------------




}

export default Tool;
