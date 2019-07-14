const fs = require('fs')
const path= require('path')
const bodyParser = require('body-parser')
const cors = require('cors')
const express = require('express')
const api = require('./api')
const app= express()
const session = require('express-session')

app.use(cors());
app.use(express.json({limit:'5mb'}));
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended:false}));
app.use(api);

app.listen(6666)
console.log('in listening');
