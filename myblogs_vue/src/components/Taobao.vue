<template>
  <div>
    <el-link href="https://www.taobao.com" target="_blank">淘宝</el-link>
  </div>
</template>

<script>
  import {Link} from 'element-ui'

  export default {
    name: "Taobao",
    components: {
      [Link.name]: Link,
    },
  }
</script>

<style scoped>

</style>
