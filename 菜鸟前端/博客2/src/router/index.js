import Vue from 'vue'
import Router from 'vue-router'

import Layout from '@/components/Layout'
import Art from '@/components/Art'
import Collection from '@/components/Collection'
import Diary from '@/components/Diary'
import Note from '@/components/Note'
import Story from '@/components/Story'
import Tech from '@/components/Tech'

Vue.use(Router)
const routes= [
  {
    path: '/',
    component: Layout,
    children:[
      {
        path: '',
        name: 'Tech',
        component: Tech
      },
      {
        path: '/tech',
        name: 'Tech',
        component: Tech
      },
      {
        path: '/art',
        component: Art,
        name: 'Art'
      },
      {
        path: '/note',
        name: 'Note',
        component: Note
      },
      {
        path: '/diary',
        name: 'Diary',
        component: Diary
      },
      {
        path: '/story',
        name: 'Story',
        component: Story
      },
      {
        path: '/collection',
        name: 'Collection',
        component: Collection
      }
    ]
  }
]

export default new Router({
  mode:'history',
  routes
})
