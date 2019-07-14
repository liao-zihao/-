<template>
  <div class="head">
    <h1>{{ ms }}</h1>
  </div>
</template>

<script>
  export default {
    name: 'Head',//页面源码显示的名字
    data () {
      return {
        ms: 'this is head'
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .head{
    background-color: #42b983;
  }
</style>
