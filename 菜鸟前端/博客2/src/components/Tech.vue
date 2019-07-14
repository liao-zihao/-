<template>
  <div class="row">
    <div class="col">
      <div class="card" v-for="(item) in items" :key="item.id">
        <router-link :to="'/techdetail/'+item._id">
          <p class="card_title">{{item.title}}</p>
        </router-link>
        <div class="keywords">
          <span>关键字：</span>
          <span v-for="(keyword) in item.keywords">
            <span class="badge badge-info">{{keyword}}</span>
            <span>&nbsp;</span>
          </span>
        </div>
        <p class="date">{{item.date}}</p>
      </div>
    </div>
    <div class="col-3">
      <Cate></Cate>
      <Date></Date>
    </div>
  </div>
</template>
<script>
  import Cate from '@/components/Cate'
  import Date from '@/components/Date'
  export default {
    data(){
      return{
        items:[]
      }
    },
    components:{
      Cate,Date
    },
    created(){
      this.$axios.get('/api/'+'techList')
        .then((res)=>{
          this.items=res.data.reverse();
        })
    }
  }
</script>
<style scoped>
  .row{
    height: 100%;
  }
  .card {
    background-color: rgb(248,248,253);
    margin: 10px 0 0 0;
    border-bottom: 1px solid #eee;
  }
  .card_title{
    font-size: 26px;
    font-weight: 600;
    color: #0085A1;
  }
  .date{
    font-style: italic;
    font-family: Lora,'Times New Roman',serif;
    color: #808080;
  }
  @media screen and (max-width: 1200px){
    .col-3{
      display: none;
    }
  }
</style>
