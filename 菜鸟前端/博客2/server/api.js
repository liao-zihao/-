"use strict"
const db = require('./db')
const express = require("express")
const router = express.Router()
const jwt = require('jsonwebtoken')
const path = require('path')

router.get('/',(req,res)=>{
  res.send('hhhhh')
})

//登录
router.post('/api/signin',(req,res)=>{
  db.User.find({name:req.body.name,password:req.body.password},(err,docs)=>{
    if(err){
      res.send(err);
      return
    }
    if(docs.length>0){
      let content = {name:req.body.name};
      let key = "123456"
      let token = jwt.sign(content,key,{
        expireIn:60*60*24*7
      });

      docs[0].token = token
      db.User(docs[0].save(function (err) {
        if(err){
          res.status(500).send()
          return
        }
        res.send({'status':1,'msg':'登录成功','name':docs[0].name,'password':docs[0].password,'token':docs[0].token})
      }))
    }else{
      res.send({'status':0,'msg':'登录失败'})
    }
  })
})
//获取tech列表
router.get('/api/techList',(req,res)=>{
  db.Tech.find({},(err,data)=>{
    if(err){
      res.send(err);
      return
    }
    for(let i=0;i<data.length;i++){
      data[i]['content']=null;
    }
    res.send(data)
  })
})
//文章详情页
router.get('/api/techDetail/:id',function (req, res) {
  db.Tech.findOne({_id:req.params.id},function (err, docs) {
    if(err){
      console.error(err)
      return
    }
    res.send(docs)
  })
})

router.get('/api/artList',(req,res)=>{
  db.Art.find({},(err,data)=>{
    if(err){
      res.send(err);
      return
    }
    for(let i=0;i<data.length;i++){
      data[i]['content']=null;
    }
    res.send(data)
  })
})
//文章详情页
router.get('/api/artDetail/:id',function (req, res) {
  db.Art.findOne({_id:req.params.id},function (err, docs) {
    if(err){
      console.error(err)
      return
    }
    res.send(docs)
  })
})

router.get('/api/noteList',(req,res)=>{
  db.Note.find({},(err,data)=>{
    if(err){
      res.send(err);
      return
    }
    res.send(data)
  })
})
router.get('/api/diaryList',(req,res)=>{
  db.Diary.find({},(err,data)=>{
    if(err){
      res.send(err);
      return
    }
    res.send(data)
  })
})
router.get('/api/storyList',(req,res)=>{
  db.Story.find({},(err,data)=>{
    if(err){
      res.send(err);
      return
    }
    for(let i=0;i<data.length;i++){
      data[i]['content']=null;
    }
    res.send(data)
  })
})
module.exports = router;
