<template>
    <n-space style="margin-bottom: 25px;">
        <div class="modules-title">项目管理</div>
        <div>
            <n-button style="margin-left: 5px; margin-right: 10px;" @click="addItem">新建项目</n-button>
            <n-button @click="delItem">删除项目</n-button>
        </div>
    </n-space>
    <n-space vertical style="margin-top: 25px;">
        <n-data-table :columns="columns" :data="data" :pagination="pagination" :single-line="false" />
    </n-space>
    <n-modal v-model:show="isVisible" :trap-focus="false">
        <n-form :model="newItem" :size="big" class="form" :rules="rules">
            <n-form-item label="新建项目">
                <n-input :allow-input="reg" v-model:value="newItem.categoryName" placeholder="选择分类" />
                <n-input :allow-input="reg" v-model:value="newItem.categoryName" placeholder="输入项目名" />
                <n-input :allow-input="reg" v-model:value="newItem.categoryName" placeholder="输入分类名" />
                <n-input :allow-input="reg" v-model:value="newItem.categoryName" placeholder="输入分类名" />
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
import { onMounted, ref, h } from 'vue';
import { NButton, NTag } from 'naive-ui';
const columns = ref([
    {
        title: "分类名称",
        key: "categoryName"
    },
    {
        title: "项目名称",
        key: "itemName"
    },
    {
        title: "标签",
        key: "tag",
        render(row) {
            const tags = row.tags.map((tagKey) => {
                return h(
                    NTag,
                    {
                        style: {
                            marginRight: "6px"
                        },
                        type: "info",
                        bordered: false
                    },
                    {
                        default: () => tagKey
                    }
                );
            });
            return tags;
        }
    },
    {
        title: "操作",
        key: "actions",
        render(row) {
            return h("div", [
                h(
                    NButton,
                    {
                        size: "small",
                        onClick: () => addTag(row),
                        style: { marginRight: "20px" }
                    },
                    "添加标签"
                ),
                h(
                    NButton,
                    {
                        size: "small",
                        onClick: () => delItem(row)
                    },
                    "删除项目"
                )
            ]);
        },
    }])
//项目信息
let data = ref([])
onMounted(() => {
    //获取个人资料
    request.post('/achieve/getItemRecord').then(result => {
        console.log(result)
        userInfo.value.name = result.data.data.username
        if (result.data.data.role == 1) {
            userInfo.value.role = "用户"
        }
        else {
            userInfo.value.role = "管理员"
        }
        if (result.data.data.sex == 0) {
            userInfo.value.sex = "男"
        }
        else if (result.data.data.sex == 1) {
            userInfo.value.sex = "女"
        }
        else {
            userInfo.value.sex = ""
        }
        userInfo.value.createAt = result.data.data.createdAt
        userInfo.value.updateAt = result.data.data.updateAt
        userInfo.value.email = result.data.data.email
    })
});
//添加项目
let isVisible = ref(false)
const addItem = () => {
    isVisible.value = true
}
const close = () => {
    isVisible.value = false
}
let newItem = ref({
    categoryName:null,
    itemName:null,
    reachQuantity:null,
    reachDate:null
})
const submit = () => {
    if (newCategory.value == null) {
        message.error("输入不能为空")
    }
    else {
        request.post('/achieve/newCategory',
            {
                categoryName: newCategory.value
            }).then(result => {
                message.success(result.data.data)
                location.reload()
            })
    }
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

::v-deep .n-data-table .n-data-table-empty .n-empty {
    flex-direction: row;
    margin-right: 45px;
    justify-content: center;
}
</style>