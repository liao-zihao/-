<template>
  <div class = 'container'>
    <header></header>
    <transition name = 'slide-fade'>
      <router-view></router-view>
    </transition>
    <footer></footer>
  </div>
</template>
