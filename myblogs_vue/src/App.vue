<template>
  <Main v-if="loginStatus"></Main>
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
      loginStatus:function() {
        let toLogin = "/login/toLogin";
        let toRegister = "/login/toRegister";
        //当前路由
        let localPath = this.$route.path;
        // if(localPath!=toLogin&&localPath!=toRegister){//访问非登陆页面
        //     if(store.state.token == ''){//如果不存在token
        //       this.$router.push({path:'/login/toLogin'})
        //     }else{
        //       return true;
        //     }
        // }else{
        //   return false;
        // }
        return localPath!=toLogin&&localPath!=toRegister
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
