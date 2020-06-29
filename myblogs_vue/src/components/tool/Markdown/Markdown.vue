<template>
  <el-container>
    <el-header style="text-align: right; font-size: 16px">

      <el-menu class="el-menu-demo" mode="horizontal"  @select="HeaderMenuSelect"
               background-color="#545c64" text-color="#fff" active-text-color="#ffd04b"
               style="border-bottom: 0px">
        <!--登陆成功选项 并显示用户名-->
        <el-menu-item index="home">首页</el-menu-item>
        <el-submenu  index="1"  style="float: right;">
          <template slot="title">{{user==''?'':user}}</template>
          <!--            <el-button type="primary" @click="loginOut()" ></el-button>-->
          <el-menu-item @click="loginOut()" index="1-1-1">退出登录</el-menu-item>
        </el-submenu>
        <el-menu-item  index="2" style="float: right;"  @click="submit" >保存</el-menu-item>

      </el-menu>

    </el-header>

    <el-main>
      <div>
        <mavon-editor
          v-model="content"
          ref="md"
          @change="change"
          style="min-height: 600px"
        />
      </div>
    </el-main>
    <el-footer></el-footer>
  </el-container>
</template>

<script>
  import {
    Container,
    Header,
    Main,
    Footer,
    Link,
    Button,
    Menu,
    Submenu,
    MenuItem,
  } from 'element-ui'
  // 导入Markdown组件 及 组件样式
  import { mavonEditor } from 'mavon-editor';
  import 'mavon-editor/dist/css/index.css'

  export default {
    name: "Markdown",
    components: {
      [Link.name]: Link,
      [Button.name]: Button,
      [Header.name]:Header,
      [Main.name]:Main,
      [Footer.name]:Footer,
      [Container.name]:Container,
      [Menu.name]:Menu,
      [Submenu.name]:Submenu,
      [MenuItem.name]:MenuItem,
      mavonEditor,
    },
    data(){
      return {
        content:'', // 输入的markdown
        html:'',    // 及时转的html
        user : window.localStorage.getItem('username'),
      }
    },
    methods:{
      // 所有操作都会被解析重新渲染
      change(value, render){
        // render 为 markdown 解析后的结果[html]
        this.html = render;
      },
      // 提交
      submit(){
        console.log(this.content);
        console.log(this.html);
      },
      HeaderMenuSelect(index, indexPath) {
        if (indexPath == 'home') {
          this.$router.push({path: '/'})
        }
      },
    },
    mounted() {
    },
  }
</script>

<style scoped>
  .submit-btn{float: right}

</style>
