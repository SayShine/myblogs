<template>
  <Main v-if="isoutContent"></Main>
  <router-view v-else></router-view>
</template>

<script>
  import Main from "./components/Main";
  import store from '@/store';

  export default {
    components: {Main: Main},
    name: 'App',
    data(){
      return{

      }
    },
    computed:{
      isoutContent:function() {
        //登录页面
        let toLogin = "/login/toLogin";
        //Markdown文本编辑页面
        let MarkdownDetail = "/tool/Markdown/Detail";
        //当前路由
        let localPath = this.$route.path;

        let isout = localPath!=toLogin&&localPath!=MarkdownDetail
        // if(localPath!=toLogin&&localPath!=toRegister){//访问非登陆页面
        //     if(store.state.token == ''){//如果不存在token
        //       this.$router.push({path:'/login/toLogin'})
        //     }else{
        //       return true;
        //     }
        // }else{
        //   return false;
        // }
        return isout
      }
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
