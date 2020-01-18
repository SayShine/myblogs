<template>
  <el-container>
    <el-header></el-header>
    <el-main>
      <el-form ref="loginForm" :model="form" label-width="80px">
        <el-form-item label="账号">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" type="password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="toLogin('loginForm')">登录</el-button>
          <el-button @click="toRegister">注册</el-button>
        </el-form-item>
      </el-form>
    </el-main>
    <el-footer></el-footer>
  </el-container>
</template>

<script>
  import {Form,FormItem,Header,Main,Footer,Container,Input,Button} from 'element-ui'
  import AdminApi from '../../api/apiList/admin'
  import store from '@/store'
    export default {
        name: "toLogin",
        components:{
          [Form.name]:Form,
          [FormItem.name]:FormItem,
          [Header.name]:Header,
          [Main.name]:Main,
          [Footer.name]:Footer,
          [Input.name]:Input,
          [Container.name]:Container,
          [Button.name]:Button,
        },
        data(){
          return {
            form:{
              username:'',
              password:''
            }
          }
        },
        methods:{
          toLogin(formName){
            this.$refs[formName].validate((valid)=>{
              AdminApi.toLogin(this.form).then(res=>{
                if(res.data.code == '0'){
                  store.commit('changeToken',res.data.body)
                  let that = this
                  setTimeout(function () {
                    that.$router.push({path:'/tool/zuanTranslator'})
                  },1000)
                }else{
                  alert(res.data.msg)
                  this.form.password=''
                }
              })
            })
          },
          toRegister(){
            alert("暂未开放注册功能！")
          }
        },
      created() {
        if(this.$store.getters.login){
          let that = this
          setTimeout(function () {
            alert("您已登陆，无需重复登录！")
            that.$router.push({path:'/tool/zuanTranslator'})
          },1000)
        }
      }
    }
</script>

<style scoped>

</style>
