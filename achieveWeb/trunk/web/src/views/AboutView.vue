<template>
  <n-button @click="showModal = true">
    来吧
  </n-button>
  <n-modal v-model:show="showModal" preset="dialog" title="Dialog">
    <template #header>
      <div>标题</div>
      <n-input v-model="formInfo.email" placeholder="输入性别" />
    </template>
    <div>内容</div>
    <template #action>
      <div>操作</div>
    </template>
  </n-modal>
</template>

<script>
import { defineComponent, ref } from "vue";

export default defineComponent({
  setup() {
    return {
      showModal: ref(false),
      formInfo: ref({
        sex: "",
        email: ""
      })
    };
  }
});
</script>