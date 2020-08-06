<template>
  <div>
    <el-link href="https://www.taobao.com" target="_blank">淘宝</el-link>
    <el-link href="https://www.baidu.com" target="_blank">百度</el-link>
    <el-link href="https://www.weibo.com" target="_blank">微博</el-link>
    <el-link href="https://www.douyu.com" target="_blank">斗鱼</el-link>
    <el-button type="primary" @click="test">并发测试</el-button>
  </div>
</template>

<script>
  import {Link, Button, Notification} from 'element-ui'
  import ToolApi from '../../api/apiList/tool'
  export default {
    name: "Taobao",
    components: {
      [Link.name]: Link,
      [Button.name]: Button
    },
    methods: {
      test() {
        let start = new Date();

        //并发抢购10000次
        for(let i=0; i<1; i++){
          ToolApi.purchaseProduct(1,1).then(res => {
            if(res.data.code == 0){
              console.log(res)
            }
          });
        }


        // for(let i=0; i<100; i++){
        //   console.log("循环第"+i+"次")
        //   ToolApi.purchaseProduct(1,1).then(res => {
        //     console.log(res);
        //     if(res.data.code == 0){
        //       console.log(222)
        //     }
        //   });
        // }


      },
    }
  }
</script>

<style scoped>

</style>
