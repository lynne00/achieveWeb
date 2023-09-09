<template>
    <div class="header-wrapper">
        <div class="header-title">
            Achieve / {{ routeName }}
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
//退出登录
const message = useMessage()
const logout = () => {
    router.push('/login')
    request.post('/achieve/logOut').then(result => {
        message.success(result.data.data)
    })
}
import { onMounted, onBeforeUnmount,ref } from 'vue';
const routeName = ref('首页') // 用于存储路由名称的响应式变量
onMounted(() => {
    // 在组件挂载后，添加路由变化监听
    router.afterEach((to) => {
        // 检查路由是否有名称并设置页面标题
        if (to.meta.name) {
            routeName.value = to.meta.name;
        } 
    }) // 添加路由变化监听
})

onBeforeUnmount(() => {
    router.afterEach(null) // 在组件销毁前移除路由监听
})

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
