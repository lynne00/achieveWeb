<template>
    <n-space style="margin-bottom: 25px;">
        <div class="title">个人资料</div>
        <div>
            <n-button style="margin-left: 20px; margin-right: 10px;" @click="updatePwd">修改密码</n-button>
            <n-button @click="updateInfo">修改个人信息</n-button>
        </div>
    </n-space>
    <n-space vertical>
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
    <n-modal v-model:show="isInfoVisible" title="Dialog" preset="dialog">
        <n-form :model="formInfo" :size="big" class="form">
            <n-form-item label="性别">
                <n-select v-model:value="formInfo.sex" placeholder="输入性别" :options="sexOptions" />
            </n-form-item>
            <n-form-item label="邮箱">
                <n-input v-model:value="formInfo.email" placeholder="输入邮箱" />
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
    <n-modal v-model:show="isPwdVisible" title="Dialog" preset="dialog">
        <n-form :model="formInfo" :size="big" class="form">
            <n-form-item label="密码">
                <n-input v-model:value="formPwd.password" type="password" placeholder="输入密码" />
            </n-form-item>
            <n-form-item label="重复密码">
                <n-input v-model:value="formPwd.reenteredPassword" :disabled="!formPwd.password" type="password"
                    placeholder="重复密码" />
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
const userInfo = ref({
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
        if (userInfo.value.sex == 0) {
            userInfo.value.sex = "男"
        }
        else if (userInfo.value.sex == 1) {
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
const formInfo = ref({
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
const isInfoVisible = ref(false)
const updateInfo = () => {
    isInfoVisible.value = true
}
//提交资料表单
const message = useMessage()
const submitInfo = () => {
    request.post('/achieve/updateInfo', {
        sex: formInfo.value.sex,
        email: formInfo.value.email
    }).then(result => {
        message.success(result.data.data)
    })
    location.reload()
}
const closeUpdateInfo = () => {
    isInfoVisible.value = false
}
//修改密码
const isPwdVisible = ref(false)
const updatePwd = () => {
    isPwdVisible.value = true
}
const closeUpdatePwd = () => {
    isPwdVisible.value = false
}
const formPwd = ref({
    password: null,
    reenteredPassword: null
})
const submitPwd = () => {
    const hashedPassword = hashPassword(formPwd.value.password);
    console.log(hashedPassword,formPwd.value.password, formPwd.value.reenteredPassword)
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
</script>

<style scoped lang="scss">
.form {
    padding: 30px;
    background-color: #020401;
    width: 300px;

    .button-justify {
        display: flex;
        justify-content: space-around;
        margin-top: 10px;

        .form-button {
            color: white;
            width: 100px;
        }
    }
}

::v-deep.n-form-item .n-form-item-label {
    color: white;
}

.title {
    text-align: left;
    font-size: 20px;

}
</style>