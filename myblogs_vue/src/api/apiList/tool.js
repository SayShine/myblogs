/**
 * 工具接口列表
 */
import baseUrl from '../baseUrl'; // 导入接口域名列表
import axios from '../../http'; // 导入http中创建的axios实例

const Tool = {
  toTranslateWords(params){
    return axios.get(`${baseUrl.url}/tool/toTranslate`,{params:params})
  },
  toEncoding(params){
    return axios.get(`${baseUrl.url}/md5/toEncode`,{params:params})
  }
}

export default Tool;
