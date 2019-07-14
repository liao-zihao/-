const mongoose = require('mongoose');

mongoose.connect('mongodb://localhost/liao');

const db = mongoose.connection;
db.once('error',() => console.log('Mongo connection error'));
db.once('open',() => console.log('Mongo connection successed'));

const userSchema = new mongoose.Schema({
  name: String,
  password: String,
  token: String
})

const techSchema = new mongoose.Schema({
  title: String,
  date: String,
  keywords: Array,
  content: String,
  times:Number
})
const artSchema = new mongoose.Schema({
  title: String,
  date: String,
  keywords: Array,
  content: String,
  times:Number
})

const noteSchema = new mongoose.Schema({
  title: String,
  date: String,
  content: String
})

const diarySchema = new mongoose.Schema({
  date: String,
  content: String
})

const storySchema = new mongoose.Schema({
  title: String,
  date: String,
  content: String
})

const Models = {
  User: mongoose.model('User', userSchema),
  Tech: mongoose.model('Tech', techSchema),
  Art: mongoose.model('Art', artSchema),
  Note: mongoose.model('Note', noteSchema),
  Diary: mongoose.model('Diary', diarySchema),
  Story: mongoose.model('Story', storySchema),
}

module.exports = Models;
