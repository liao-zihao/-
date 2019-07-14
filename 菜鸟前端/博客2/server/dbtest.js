const db = require('./db');

// (function insertUser() {
//   var user = new db.User({
//     name:'zero',
//     password:'123456'
//   });
//
//   user.save(function (err) {
//     if(err){
//       console.log(err);
//     }else{
//       console.log('succeed');
//     }
//   })
// })()

// (function insertTech() {
//   for(var i=0;i<30;i++){
//     var tech = new db.Tech({
//       title: 'Abc',
//       date: '2019-3-4 12:00',
//       keywords: ['node','java','html','js'],
//       content: 'abc啊abc',
//       times:1
//     });
//
//     tech.save(function (err) {
//       if(err){
//         console.log(err);
//       }else{
//         console.log('succeed');
//       }
//     })
//   }
//
// })()

// (function insertArt() {
//   for(var i=0;i<30;i++){
//     var art = new db.Art({
//       title: 'Abc',
//       date: '2019-3-4 12:00',
//       keywords: ['电影','韩剧','现代','恐怖'],
//       content: '恶人传',
//       times:1
//     });
//
//     art.save(function (err) {
//       if(err){
//         console.log(err);
//       }else{
//         console.log('succeed');
//       }
//     })
//   }
//
// })()

(function insertNote() {
  for(var i=0;i<30;i++){
    var note = new db.Note({
      title: 'nn',
      date: '2019-1-1 12:00',
      content: 'this is note'
    });

    note.save(function (err) {
      if(err){
        console.log(err);
      }else{
        console.log('succeed');
      }
    })
  }

})()

// (function insertDiary() {
//   for(var i=0;i<30;i++){
//     var diary = new db.Diary({
//       date: '2019-1-1 12:00',
//       content: 'this is diary'
//     });
//
//     diary.save(function (err) {
//       if(err){
//         console.log(err);
//       }else{
//         console.log('succeed');
//       }
//     })
//   }
//
// })()
//
// (function insertStory() {
//   for(var i=0;i<30;i++){
//     var story = new db.Story({
//       title:'story 1',
//       date: '2019-1-1 12:00',
//       content: 'this is story'
//     });
//
//     story.save(function (err) {
//       if(err){
//         console.log(err);
//       }else{
//         console.log('succeed');
//       }
//     })
//   }
//
// })()
