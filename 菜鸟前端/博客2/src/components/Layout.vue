<template>
  <div id="layout">
    <Head></Head>
    <div class="container">
      <div class="row">
        <div class="left col-3 ">
          <Profile></Profile>
          <Message></Message>
        </div>
        <div class="right col" >
          <router-view></router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import Head from '@/components/Head'
  import Profile from '@/components/Profile'
  import Message from '@/components/Message'
  export default {
    components:{
      Head,Profile,Message
    }
  }
</script >

<style scoped>
  #layout{
    font-family: 微软雅黑;
  }
  .left{
    height: 100%;
    background-color: rgb(237, 224, 246);
  }
  .right{
    height: 300px;
  }
  @media screen and (max-width: 800px){
    .col-3{
      display: none;
    }
  }
</style>
