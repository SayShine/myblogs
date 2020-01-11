<template>
  <div>
    <div style="margin-bottom: 20px">
      <el-input v-model="md5code" placeholder="请输入内容" style="width: 30%"></el-input>
      <el-button type="primary" @click="toEncode">加密</el-button>
    </div>
    <div style="text-align: left">
      <div style="width:50%;display: inline-block">
        <el-input
          disabled
          type="textarea"
          :rows="15"
          v-model="rightResult">
        </el-input>
      </div>
    </div>
  </div>
</template>

<script>
  import {Input, Select, Option, Button, MessageBox} from 'element-ui'
  import ToolApi from '../api/apiList/tool'

  export default {
    name: "Md5Encode",
    components: {
      [Input.name]: Input,
      [Select.name]: Select,
      [Option.name]: Option,
      [Button.name]: Button,
      [MessageBox.name]: MessageBox
    },
    data() {
      return {
        rightResult: '',
        md5code: ''
      }
    },
    methods: {
      toEncode() {
        if (this.md5code == '') {
          MessageBox.alert('不能为空', '祖安提示')
        } else {
          let param = {
            "code": this.md5code
          }
          ToolApi.toEncoding(param).then(res => {
            this.rightResult = res.data.body
          })
        }
      }
    }
  }
</script>

<style scoped>

</style>
