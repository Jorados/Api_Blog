<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import Header from "@/components/Header.vue"
import Content from "@/components/Content.vue";
</script>

<template>
  <div>
      <Header />
      <RouterView />
  </div>

</template>

<style>
body{
  margin: 0;
  width: 100%;
  font-family: Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto,
  "Helvetica Neue", "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR",
  "Malgun Gothic", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol",
  sans-serif;
}

</style>

