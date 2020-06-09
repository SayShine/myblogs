<template>
  <el-container style="height: 700px; border: 1px solid #eee">
    <el-header style="text-align: right; font-size: 16px">
      <el-menu class="el-menu-demo" mode="horizontal"  @select="HeaderMenuSelect"
               background-color="#545c64" text-color="#fff" active-text-color="#ffd04b"
                style="border-bottom: 0px">
        <el-menu-item index="home">首页</el-menu-item>

        <!--登陆成功选项 并显示用户名-->
        <el-submenu v-if="this.$store.getters.login" index="1"  style="float: right;">
          <template slot="title">{{user==''?'':user}}</template>
          <!--            <el-button type="primary" @click="loginOut()" ></el-button>-->
          <el-menu-item @click="loginOut()" index="1-1-1">退出登录</el-menu-item>
        </el-submenu>
        <!--未成功选项-->
        <el-menu-item v-else index="2" style="float: right;"   @click="loginIn()" >登录</el-menu-item>

      </el-menu>
    </el-header>


    <el-container>
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="[this.$route.name,'tool']" @select="siderMenuSelect">
          <el-submenu index="tool">
            <template slot="title"><i class="el-icon-s-tools"></i>小工具</template>
            <el-menu-item-group>
              <el-menu-item index="ZuanTranslator">祖安语言翻译器</el-menu-item>
              <el-menu-item index="Md5Encode">MD5在线加密</el-menu-item>
              <el-menu-item index="Taobao">网址</el-menu-item>
              <el-menu-item index="Admin">后台管理页面</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
    <el-footer style="text-align: center">

      <el-link type="info" href="http://www.beian.miit.gov.cn">
<!--        xk的-->
        备案号：鄂ICP备19027451号
<!--        tsc的-->
<!--        备案号：鄂ICP备20008807号-->
      </el-link>
    </el-footer>
  </el-container>
</template>


<style>
  .el-header {
    background-color: #545c64;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>

<script>
  import {
    Container,
    Aside,
    Menu,
    Submenu,
    Table,
    Header,
    Main,
    MenuItem,
    TableColumn,
    MenuItemGroup,
    Dropdown,
    DropdownItem,
    DropdownMenu,
    Footer,
    Link,
    Button,
  } from 'element-ui'

  export default {
    name: 'Main',
    components: {
      [Container.name]: Container,
      [Aside.name]: Aside,
      [Menu.name]: Menu,
      [Submenu.name]: Submenu,
      [Table.name]: Table,
      [Header.name]: Header,
      [Main.name]: Main,
      [MenuItem.name]: MenuItem,
      [TableColumn.name]: TableColumn,
      [MenuItemGroup.name]: MenuItemGroup,
      [Dropdown.name]: Dropdown,
      [DropdownItem.name]: DropdownItem,
      [DropdownMenu.name]: DropdownMenu,
      [Footer.name]: Footer,
      [Link.name]: Link,
      [Button.name]:Button
    },
    data() {
      return {
        user : window.localStorage.getItem('username')
      }
    },
    methods: {
      siderMenuSelect(index, indexPath) {
        let urlPath = `/${indexPath.join("/")}`
        this.$router.push({path: urlPath})
      },
      HeaderMenuSelect(index, indexPath) {
        if (indexPath == 'home') {
          this.$router.push({path: '/'})
        }
      },
      loginOut(){
        //清除token
        this.$store.commit('clearToken')
        //同时清除localstroage中存储的用户名信息
        window.localStorage.removeItem('username');
        this.user = '';
        //跳转登陆页面   cxk:2020/06/02跳个锤子跳登录页面
        // this.$router.push({path:'/login/toLogin'})
      },
      loginIn(){
        //跳转登录页面
        this.$router.push({path:'/login/toLogin'})
      }
    }
  };
</script>
