<template>
    <div class="header-wrapper">
        <div class="header-title">
            Achieve
        </div>
        <div class="header-button">
            <n-button @click="logout">注销</n-button>
        </div>
    </div>
</template>

<script setup>
import router from '@/router/index.js'
import request from '@/utils/request';
import { useMessage } from 'naive-ui'
const message = useMessage()
const logout = () => {
    router.push('/login')
    request.post('/achieve/logOut').then(result=>{
        message.success(result.data.data)
    })
}
</script>

<style scoped>
.header-wrapper {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 10px 0;
    width: 100%;
    border-bottom: 2px solid #636b75;
}

.header-title {
    margin-left: 15px;
}

.header-button {
    margin-right: 15px;
}
</style>
