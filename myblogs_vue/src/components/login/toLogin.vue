<template>
  <el-container>
    <el-header></el-header>
    <el-main>
      <div style="width: 500px; margin:100px auto 0">
      <el-form  :model="form" :rules="loginRules" ref="loginForm" label-width="80px" class="login-box">
        <h3 class="login-title">欢迎登录</h3>

        <el-form-item label="账号：" prop="username">
          <el-input v-model="form.username" auto-complete="off" placeholder="请输入账号" >
            <i slot="prefix" class="iconfont icon-user loginicon"></i>
          </el-input>
        </el-form-item>

        <el-form-item label="密码：" prop="password" >
          <el-input v-model="form.password" auto-complete="off"  :type="isShow?'text':'password'" placeholder="请输入密码">
            <i slot="prefix" class="iconfont loginicon" @click="isShow=!isShow" v-bind:class="isShow?'icon-showpass':'icon-hidepass'"></i>
          </el-input>
        </el-form-item>

        <el-form-item label="验证码："  prop="verifycode">
          <!-- 注意：prop与input绑定的值一定要一致，否则验证规则中的value会报undefined，因为value即为绑定的input输入值 -->
          <el-input v-model="form.verifycode" @keyup.enter.native="toLogin('loginForm')" placeholder="请输入验证码" class="identifyinput">
            <i slot="prefix"  class="iconfont loginicon" v-bind:class="this.form.verifycode === this.identifyCode?'icon-codesuccess':'icon-codefail'"></i>
          </el-input>
        </el-form-item>

        <el-form-item>
          <div class="identifybox">
            <div @click="refreshCode">
              <s-identify :identifyCode="identifyCode"></s-identify>
            </div>
            <el-button @click="refreshCode" type='text' class="textbtn">看不清，换一张</el-button>
          </div>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="toLogin('loginForm')">登录</el-button>
          <el-button @click="toRegister">注册</el-button>
        </el-form-item>
      </el-form>
      </div>


    </el-main>
    <el-footer></el-footer>
  </el-container>
</template>

<script>
  import {Form,FormItem,Header,Main,Footer,Container,Input,Button} from 'element-ui'
  import AdminApi from '../../api/apiList/admin'
  import store from '@/store'
  import SIdentify from '@/components/common/authCode'
  import localStorage from '@/assets/utils/js/localStorage';
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
        SIdentify
      },
      data(){

        // 验证码自定义验证规则
        const validateCode = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入验证码'));
            console.log("输入为空");
          } else if (value !== this.identifyCode) {
            console.log('validateCode:', value);
            callback(new Error('验证码不正确!'));
          } else {
            callback()
          }
        }
        //账号非空验证
        const usernamenotnull = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入账号'))
            console.log("输入为空");
          } else {
            callback()
          }
        }

        //密码非空验证
        const passwordnotnull = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入密码'))
            console.log("输入为空");
          } else {
            callback()
          }
        }
        return {
          //密码显示或者隐藏控制
          isShow:false,
          //登录表单
          form:{
            username:'',
            password:'',
            verifycode:''
          },

          //验证码的取值
          identifyCodes: '1234567890',
          identifyCode: '',
          //登录表单前置验证
          loginRules:{
            username: [
              //trigger: 'blur' 表示失去焦点时候进行表单验证
              {required: true, trigger: 'blur', validator: passwordnotnull}
            ],
            password:[
              {required: true, trigger: 'blur', validator: usernamenotnull}
            ],
            verifycode: [
              { required: true, trigger: 'blur', validator: validateCode}
            ]
          }

        }
      },
      computed:{
      },

      watch:{
      },
      props: [],
      mounted() {
          //验证码初始化
        this.identifyCode = '';
        this.makeCode(this.identifyCodes, 4);
      },

      methods:{
        toLogin(formName){
          this.$refs[formName].validate((valid)=>{
            //表单校验
            if(valid){
              AdminApi.toLogin(this.form).then(res=>{
                //返回登录成功消息
                if(res.data.msg == 'SUCCESS'){
                  store.commit('changeToken',res.data.body)
                  //存储token和用户名
                  localStorage.set('token',res.data.body)
                  localStorage.set('username',this.form.username)
                  let that = this;
                  //同时存储
                  setTimeout(function () {
                    that.$router.push({path:'/tool/zuanTranslator'})
                  },1000)
                }else{
                  alert(res.data.msg)
                  this.form.password=''
                }
              })
            }
          })
        },

        toRegister(){
          alert("暂未开放注册功能！")
        },
        // 生成随机数
        randomNum(min, max) {
          return Math.floor(Math.random() * (max - min) + min)
        },
        // 切换验证码
        refreshCode() {
          this.identifyCode = '';
          this.makeCode(this.identifyCodes, 4)
        },
        // 生成四位随机验证码
        makeCode(o, l) {
          for (let i = 0; i < l; i++) {
            this.identifyCode += this.identifyCodes[
              this.randomNum(0, this.identifyCodes.length)
              ]
          }
          console.log(this.identifyCode)
        }

        },
        created() {
          if(this.$store.getters.login){
            let that = this
            setTimeout(function () {
              alert("您已登陆，无需重复登录！");
              that.$router.push({path:'/tool/zuanTranslator'})
            },1000)
          }
        }
      }
</script>
<style scoped src="@/assets/icon/20200608/iconfont.css"/>
<style scoped>
  .identifybox{
    display: flex;
    justify-content: space-between;
    margin-top:7px;
  }

  .iconstyle{
    color:#409EFF;
  }

  .login-box {
    border: 1px solid #DCDFE6;
    width: 350px;
    margin: 120px auto;
    padding: 35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 10px #909399;
  }

  .login-title {
    text-align: center;
    margin: 0 auto 40px auto;
    color: #303133;
    font-family: "微软雅黑";
    color: gray;
  }

  .loginicon{
    margin: 0 5px;
  }

  .show{
    display: none;
  }

  .active{
    background: black;
  }
</style>
