<script setup lang="ts">
//반응형 변수로.

import {ref} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";
const title  =ref("")
const content =ref("")
const count = ref(0);
const router=  useRouter();

//데이터를 서버로 던지는 법 -> axios 라이브러리
const write = function (){
  axios
    .post("/api/posts",{
      title:title.value,
      content:content.value
    })
    .then(() => {
      router.replace({name:"home"});
    })
}
</script>

<template>
  <div>
    <el-input v-model="title" type="text" placeholder="제목을 입력해주세요"/>
  </div>

  <div class="mt-2">
    <el-input v-model="content" type="textarea" rows="15"></el-input>
  </div>

  <div class="mt-2">
    <div class="d-flex justify-content-end">
      <el-button type="primary" @click="write()">작성완료</el-button>
    </div>
  </div>

</template>

<style>

</style>
