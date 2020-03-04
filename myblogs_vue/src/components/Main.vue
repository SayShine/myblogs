<template>
  <el-container style="height: 700px; border: 1px solid #eee">
    <el-header style="text-align: right; font-size: 12px">
      <el-menu class="el-menu-demo" mode="horizontal" style="background-color: #B3C0D1" @select="HeaderMenuSelect">
        <el-menu-item index="home">首页</el-menu-item>
        <el-menu-item index="home" style="float: right">
          <el-button type="primary" @click="loginOut()" v-if="this.$store.getters.login">退出登录</el-button>
        </el-menu-item>
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
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
    <el-footer style="text-align: center">
      <el-link type="info" href="http://www.beian.miit.gov.cn">备案号：鄂ICP备19027451号-1</el-link>
    </el-footer>
  </el-container>
</template>


<style>
  .el-header {
    background-color: #B3C0D1;
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
      return {}
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
        //跳转登陆页面
        this.$router.push({path:'/login/toLogin'})
      }
    }
  };
</script>
