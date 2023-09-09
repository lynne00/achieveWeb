<template>
    <div v-if="isEmpty" style="justify-content: center;">
        <n-empty style="   --n-icon-size: 52px;
    --n-font-size: 18px;" class="empty" size="huge" description="暂无数据">
        </n-empty>
    </div>
</template>

<script setup>
defineProps({
    isEmpty: Boolean
})
</script>

<style lang="scss">
.empty {
    flex-direction: row;
    margin-right: 45px;
    justify-content: center;
}
</style>

