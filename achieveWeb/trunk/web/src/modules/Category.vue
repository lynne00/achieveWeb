<template>
    <n-space style="margin-bottom: 25px;">
        <div class="modules-title">分类管理</div>
        <div>
            <n-button style="margin-left: 5px; margin-right: 10px;" @click="addCategory">新建分类</n-button>
            <n-button @click="delCategory">删除分类</n-button>
        </div>
    </n-space>
    <Empty :isEmpty="isEmpty" />
    <Card :isEmpty="!isEmpty" :is-del="isDel" :module-list="categoryList" :title="`分类`"/>
    <n-modal v-model:show="isVisible" :trap-focus="false">
        <n-form :model="newCategory" :size="big" class="form">
            <n-form-item label="新建分类">
                <n-input :allow-input="reg" v-model:value="newCategory" placeholder="输入分类名" />
            </n-form-item>
            <n-form-item class="button-justify">
                <n-button class="form-button" style="margin-left: 20px;" @click="submit">
                    提交
                </n-button>
                <n-button class="form-button" style="margin-left: 60px;" @click="close">
                    取消
                </n-button>
            </n-form-item>
        </n-form>
    </n-modal>
</template>

<script setup>
import request from '@/utils/request';
import { onMounted, ref } from 'vue';
import Empty from '@/components/Empty.vue'
import Card from '@/components/Card.vue'
import { useMessage } from 'naive-ui'
const message = useMessage();
//分类列表
let categoryList = ref([]);
//获取列表是否为空
let isEmpty = ref(true)
onMounted(() => {
    //获取分类列表信息
    request.post('/achieve/getCategory').then(result => {
        // console.log(result.data.data)
        if (result.data.data.length != 0) {
            isEmpty.value = false
            categoryList = result.data.data
        }
    })
});
//添加分类
let isVisible = ref(false)
const addCategory = () => {
    isVisible.value = true
}
const close = () => {
    isVisible.value = false
}
let newCategory = ref(null)
const submit = () => {
    if (newCategory.value == null) {
        message.error("输入不能为空")
    }
    else {
        request.post('/achieve/newCategory',
            {
                categoryName: newCategory.value
            }).then(result => {
                if (result.data.msg === 'success') {
                message.success(result.data.data)
                location.reload()
                }
                else{
                    message.error(result.data.msg)
                }
            })
    }
}
//删除分类
let isDel = ref(false)
const delCategory = () => {
    isDel.value = true
}
const delClose = () => {
    isDel.value = false
}
//非空正则
const reg = (value) => {
    return !value || /^\S+$/.test(value)
}
</script>

<style scoped lang="scss">
::v-deep.n-form-item .n-form-item-label {
    color: white;
}

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
    grid-template-columns: repeat(auto-fill,minmax(250px, 1fr));
    grid-gap: 20px;
}
</style>