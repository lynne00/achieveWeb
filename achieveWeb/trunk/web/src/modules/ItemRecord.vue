<template>
    <n-space style="margin-bottom: 25px;">
        <div class="modules-title">项目管理</div>
        <div>
            <n-button style="margin-left: 5px; margin-right: 10px;" @click="addItem">新建项目</n-button>
            <n-button @click="addRecord">填写记录</n-button>
        </div>
    </n-space>
    <n-space vertical style="margin-top: 25px;">
        <n-data-table :columns="columns" :data="data" :pagination="pagination" :single-line="false" />
    </n-space>
    <n-modal v-model:show="isVisible" :trap-focus="false">
        <n-form :model="newItem" :size="big" class="form" :rules="rules">
            <n-form-item label="选择分类">
                <n-select :allow-input="reg" v-model:value="newItem.categoryName" placeholder="选择分类"
                    :options="categrayOptions" />
            </n-form-item>
            <n-form-item label="项目名">
                <n-input :allow-input="reg" v-model:value="newItem.itemName" placeholder="输入项目名" />
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
    <n-modal v-model:show="isRecVisible" :trap-focus="false">
        <n-form :model="newItemRecord" :size="big" class="form" :rules="rules">
            <n-form-item label="项目名">
                <n-select :allow-input="reg" v-model:value="newItemRecord.itemName" placeholder="选择项目"
                    :options="itemOptions" />
            </n-form-item>
            <n-form-item label="达成数量">
                <n-input-number style="width: 100%;" :min="0" :allow-input="reg" v-model:value="newItemRecord.reachQuantity"
                    placeholder="输入达成数" />
            </n-form-item>
            <n-form-item label="达成时间">
                <n-date-picker style="width: 100%;" v-model:value="newItemRecord.reachDate" type="date"
                    placeholder="输入达成时间" />
            </n-form-item>
            <n-form-item class="button-justify">
                <n-button class="form-button" style="margin-left: 20px;" @click="submitRec">
                    提交
                </n-button>
                <n-button class="form-button" style="margin-left: 60px;" @click="closeRec">
                    取消
                </n-button>
            </n-form-item>
        </n-form>
    </n-modal>
    <n-modal v-model:show="isTagVisible" :trap-focus="false">
        <n-form :model="tag" :size="big" class="form" :rules="rules">
            <n-form-item label="项目名">
                <n-input :value="newAddTag.itemName" />
            </n-form-item>
            <n-form-item label="选择标签">
                <n-select :allow-input="reg" v-model:value="newAddTag.tagName" placeholder="选择标签" :options="tagOptions" />
            </n-form-item>
            <n-form-item class="button-justify">
                <n-button class="form-button" style="margin-left: 20px;" @click="submitTag">
                    提交
                </n-button>
                <n-button class="form-button" style="margin-left: 60px;" @click="closeTag">
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
import { useMessage } from 'naive-ui'
const message = useMessage();
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
        title: "达成次数",
        key: "reachQuantity"
    },
    {
        title: "达成时间",
        key: "reachDate"
    },
    {
        title: "创建时间",
        key: "createAt"
    },
    {
        title: "标签",
        key: "tag",
        render(row) {
            console.log(123123, row.itemName)
            request.post('/achieve/getTagByItem', row.itemName).then(result => {
                console.log(123333, result)
                if (result.data.data.length != 0) {
                    row.tags = result.data.data
                }
            })
            if (row.tagName != null) {
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
//分类信息
let categrayOptions = ref([])
//项目信息
let itemOptions = ref([])
//标签信息
let tagOptions = ref([])
onMounted(() => {
    //获取分类列表信息
    request.post('/achieve/getCategory').then(result => {
        if (result.data.data.length != 0) {
            categrayOptions.value = result.data.data.map(category => ({
                label: category,
                value: category,
            }));
        }
    })
    //获取项目列表信息
    request.post('/achieve/getItem').then(result => {
        if (result.data.data.length != 0) {
            itemOptions.value = result.data.data.map(item => ({
                label: item,
                value: item,
            }));
        }
    })
    //获取项目记录
    request.post('/achieve/getItemRecord').then(result => {
        if (result.data.data.length != 0) {
            data.value = result.data.data
        }
    })
    //获取标签信息
    request.post('/achieve/getTag').then(result => {
        if (result.data.data.length != 0) {
            tagOptions.value = result.data.data.map(tag => ({
                label: tag,
                value: tag,
            }));
        }
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
    categoryName: null,
    itemName: null,
})
const submit = () => {
    if (newItem.value.categoryName == null || newItem.value.itemName == null) {
        message.error("输入不能为空")
    }
    else {
        request.post('/achieve/newItem',
            {
                categoryName: newItem.value.categoryName,
                itemName: newItem.value.itemName
            }).then(result => {
                message.success(result.data.data)
            })
        location.reload()
    }
}
//填写记录
let isRecVisible = ref(false)
const addRecord = () => {
    isRecVisible.value = true
}
const closeRec = () => {
    isRecVisible.value = false
}
let newItemRecord = ref({
    itemName: null,
    reachQuantity: 1,
    reachDate: null
})
const submitRec = () => {
    if (newItemRecord.value.itemName == null || newItemRecord.value.reachQuantity == null || newItemRecord.value.reachDate == null) {
        message.error("输入不能为空")
    }
    else {
        request.post('/achieve/newItemRecord',
            {
                itemName: newItemRecord.value.itemName,
                reachQuantity: newItemRecord.value.reachQuantity,
                reachDate: new Date(newItemRecord.value.reachDate + 28800000)
            }).then(result => {
                message.success(result.data.data)
                location.reload()
            })
    }
}
//添加标签
let isTagVisible = ref(false)
const addTag = (row) => {
    newAddTag.value.itemName = row.itemName
    isTagVisible.value = true
}
let newAddTag = ref({
    tagName: null,
    itemName: null,
})
const closeTag = () => {
    isTagVisible.value = false
}
const submitTag = () => {
    if (newAddTag.value.tagName == null) {
        message.error("输入不能为空")
    }
    else {
        request.post('/achieve/addTag',
            {
                tagName: newAddTag.value.tagName,
                itemName: newAddTag.value.itemName
            }).then(result => {
                message.success(result.data.data)
            })
        location.reload()
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