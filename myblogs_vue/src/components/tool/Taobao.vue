<template>
  <div>
    <el-link href="https://www.taobao.com" target="_blank">淘宝</el-link>
    <el-link href="https://www.baidu.com" target="_blank">百度</el-link>
    <el-link href="https://www.weibo.com" target="_blank">微博</el-link>
    <el-link href="https://www.douyu.com" target="_blank">斗鱼</el-link>
  </div>
</template>

<script>
  import {Link} from 'element-ui'
  export default {
    name: "Taobao",
    components: {
      [Link.name]: Link,
    }
  }
</script>

<style scoped>

</style>
