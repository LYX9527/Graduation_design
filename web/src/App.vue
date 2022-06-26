<script setup lang="ts">
// This starter template is using Vue 3 <script setup> SFCs
// Check out https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup
import HelloWorld from './components/HelloWorld.vue'
</script>

<template>
  <!-- <HelloWorld msg="Hello Vue 3 + TypeScript + Vite" /> -->
  <router-view></router-view>
</template>

<style lang="less">
#app {
    width: 100vw;
    position: relative;
    overflow: hidden;
    box-sizing: border-box;
    height: 100%;
}
.mbox {
    &::-webkit-scrollbar {
        display: none;
    }
}
</style>
