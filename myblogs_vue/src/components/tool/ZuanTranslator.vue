<template>
  <div>
    <div style="margin-bottom: 20px;margin-left: 60px">
      <el-select v-model="translateFlag" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <el-button type="primary" style="margin-left: 20px" @click="toTranslate">翻译</el-button>
      <el-button @click="callDaige">人工翻译</el-button>
    </div>
    <div style="text-align: center">
      <div style="width: 45%;display: inline-block;">
        <el-input
          type="textarea"
          :rows="15"
          placeholder="请输入你要翻译的文字"
          v-model="leftTextarea">
        </el-input>
      </div>
      <div style="width: 45%;display: inline-block">
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
  import ToolApi from '../../api/apiList/tool'

  export default {
    name: "ZuanTranslator",
    components: {
      [Input.name]: Input,
      [Select.name]: Select,
      [Option.name]: Option,
      [Button.name]: Button,
      [MessageBox.name]: MessageBox
    },
    data() {
      const options = [{
        value: 'zuanToChinese',
        label: '祖安          >>          中文'
      }, {
        value: 'chineseToZuan',
        label: '中文          >>          祖安'
      }]
      return {
        leftTextarea: '',
        rightResult: '',
        options: options,
        translateFlag: 'zuanToChinese',
      }
    },
    methods: {
      callDaige() {
        MessageBox.alert('请联系带哥', '祖安提示')
      },
      toTranslate() {
        if (this.leftTextarea == '') {
          MessageBox.alert('翻译内容不能为空', '祖安提示')
        } else {
          let param = {
            "translateFlag": this.translateFlag,
            "content": this.leftTextarea
          }
          ToolApi.toTranslateWords(param).then(res => {
              this.rightResult = res.data.body
          })
        }
      }
    }
  }
</script>

<style scoped>

</style>
