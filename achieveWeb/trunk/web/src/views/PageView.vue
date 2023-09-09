<template>
  <n-space vertical>
    <n-layout has-sider>
      <n-layout-sider style="height: 715px" bordered collapse-mode="width" :collapsed-width="155" :width="250"
        show-trigger :inverted="true">
        <Aside />
      </n-layout-sider>
      <n-layout>
        <n-layout-header>
          <Head />
        </n-layout-header>
        <n-layout-content content-style="padding: 24px;">
          <RouterView></RouterView>
        </n-layout-content>
      </n-layout>
    </n-layout>
  </n-space>
</template>
  
<script setup>
import Aside from '@/components/Aside.vue'
import Head from '@/components/Head.vue'
import Home from '@/modules/Home.vue'
</script>
  
<style scoped lang="scss">/* 样式代码 */</style>