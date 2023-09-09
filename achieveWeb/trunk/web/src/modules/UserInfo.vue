<template>
    <n-space style="margin-bottom: 25px;">
        <div class="modules-title">个人资料</div>
        <div>
            <n-button style="margin-left: 5px; margin-right: 10px;" @click="updatePwd">修改密码</n-button>
            <n-button @click="updateInfo">修改个人信息</n-button>
        </div>
    </n-space>
    <n-space vertical style="margin-top: 25px;">
        <n-table :single-line="false">
            <tbody>
                <tr>
                    <th>用户名</th>
                    <td>{{ userInfo.name }}</td>
                    <th>身份</th>
                    <td>{{ userInfo.role }}</td>
                </tr>
                <tr>
                    <th>性别</th>
                    <td>{{ userInfo.sex }}</td>
                    <th>邮箱</th>
                    <td>{{ userInfo.email }}</td>
                </tr>
                <tr>
                    <th>账户创建时间</th>
                    <td>{{ userInfo.createAt }}</td>
                    <th>上次修改时间</th>
                    <td>{{ userInfo.updateAt }}</td>
                </tr>
            </tbody>
        </n-table>
    </n-space>
    <n-modal v-model:show="isInfoVisible" :trap-focus="false">
        <n-form :model="formInfo" :size="big" class="form">
            <n-form-item label="性别">
                <n-select v-model:value="formInfo.sex" placeholder="输入性别" :options="sexOptions" />
            </n-form-item>
            <n-form-item label="邮箱">
                <n-input :allow-input="reg" v-model:value="formInfo.email" placeholder="输入邮箱" />
            </n-form-item>
            <n-form-item class="button-justify">
                <n-button class="form-button" style="margin-left: 20px;" @click="submitInfo">
                    提交
                </n-button>
                <n-button class="form-button" style="margin-left: 60px;" @click="closeUpdateInfo">
                    取消
                </n-button>
            </n-form-item>
        </n-form>
    </n-modal>
    <n-modal v-model:show="isPwdVisible" :trap-focus="false">
        <n-form :model="formInfo" :size="big" class="form">
            <n-form-item label="密码" path="password">
                <n-input :allow-input="reg" v-model:value="formPwd.password" type="password" placeholder="输入密码" />
            </n-form-item>
            <n-form-item label="重复密码" path="reenteredPassword">
                <n-input :allow-input="reg" v-model:value="formPwd.reenteredPassword" :disabled="!formPwd.password"
                    type="password" placeholder="重复密码" />
            </n-form-item>
            <n-form-item class="button-justify">
                <n-button class="form-button" style="margin-left: 20px;" @click="submitPwd">
                    提交
                </n-button>
                <n-button class="form-button" style="margin-left: 60px;" @click="closeUpdatePwd">
                    取消
                </n-button>
            </n-form-item>
        </n-form>
    </n-modal>
</template>

<script setup>
import request from '@/utils/request';
import { onMounted, ref } from 'vue';
import { useMessage } from 'naive-ui'
import router from '@/router/index.js'
import { hashPassword } from "@/utils/passwordUtil";
//个人资料
let userInfo = ref({
    name: "",
    role: "",
    sex: "",
    createAt: "",
    updateAt: "",
    email: ""
})
onMounted(() => {
    //获取个人资料
    request.post('/achieve/getInfo').then(result => {
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
//修改资料表单
let formInfo = ref({
    sex: null,
    email: null
})
const sexOptions = ref([
    {
        label: "男",
        value: 0,
    }, {
        label: "女",
        value: 1,
    }
])
let isInfoVisible = ref(false)
const updateInfo = () => {
    isInfoVisible.value = true
}
//提交资料表单
const message = useMessage()
const submitInfo = () => {
    console.log(formInfo.value.sex)
    request.post('/achieve/updateInfo', {
        sex: formInfo.value.sex,
        email: formInfo.value.email
    }).then(result => {
        message.success(result.data.data)
        location.reload()
    })
}
const closeUpdateInfo = () => {
    isInfoVisible.value = false
}
//修改密码
let isPwdVisible = ref(false)
const updatePwd = () => {
    isPwdVisible.value = true
}
const closeUpdatePwd = () => {
    isPwdVisible.value = false
}
let formPwd = ref({
    password: null,
    reenteredPassword: null
})
const submitPwd = () => {
    if (formPwd.value.password == null) {
        message.error("输入不能为空")
    }
    else {
        const hashedPassword = hashPassword(formPwd.value.password);
        console.log(hashedPassword, formPwd.value.password, formPwd.value.reenteredPassword)
        if (formPwd.value.password === formPwd.value.reenteredPassword) {
            request.post('/achieve/updatePassword',
                hashedPassword).then(result => {
                    message.success(result.data.data)
                })
            router.push('/login')
        }
        else {
            message.error("两次输入密码不一致")
        }
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
</style>