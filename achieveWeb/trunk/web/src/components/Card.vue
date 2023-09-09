<template>
    <div class="module-content" v-if="isEmpty">
        <n-thing class="module-card" v-for="module in moduleList">
            <template #header>
                {{ title }}
            </template>
            <template #header-extra>
                <n-button v-if="isDel" :bordered="false" size="small">
                    <template #icon>
                        <CloseOutline />
                    </template>
                </n-button>
            </template>
            <template #description>
                {{ module }}
            </template>
        </n-thing>
    </div>
</template>

<script setup>

import {
  CloseOutline as CloseOutline
} from "@vicons/ionicons5";

defineProps({
    isEmpty: Boolean,
    moduleList: Array,
    title: String,
    isDel: Boolean
})
</script>

<style scoped lang="scss">
.module-card {
    width: 250px;
    border: 1px solid #efeff5;
    padding: 15px;
    padding-bottom: 30px;
}


.module-content {
    width: 100%;
    margin-top: 35px;
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    grid-gap: 20px;
}
</style>