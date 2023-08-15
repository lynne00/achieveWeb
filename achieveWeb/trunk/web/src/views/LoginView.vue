<template>
    <div class="login-box">
        <p>{{ isLogin ? "登录" : "注册" }}</p>
        <form :model="user">
            <div class="user-box">
                <input required="" name="username" type="text" v-model="user.username">
                <label>请输入用户名</label>
            </div>
            <div class="user-box">
                <input required="" name="" type="password" v-model="user.password">
                <label>请输入密码</label>
            </div>
            <div v-if="!isLogin" class="user-box">
                <input required="" name="" type="password" v-model="user.passwordAgain">
                <label>再次输入密码</label>
            </div>
            <div v-if="isLogin" class="user-box">
                <input required="" name="" type="text" v-model="user.captchaCode">
                <label>请输入验证码 </label>
                <div class="getCaptcha">
                    <img :src="codeImg" @click="getimgCaptcha()" title="看不清，点击换一张" loading="lazy" alt="验证码" />
                </div>
            </div>
            <n-radio-group class="login-radio" v-if="isLogin" v-model:value="roleNum" name="radiogroup">
                <n-space>
                    <n-radio style=";" v-for="role in roles" :key="role.value" :value="role.value">
                        <div style="font-size: 5px;color: #fff;"> {{ role.label }}</div>
                    </n-radio>
                </n-space>
            </n-radio-group>
            <p v-if="!isLogin">此注册只用于注册用户帐号</p>
            <a href="#" id="loginButton" @click="handleLogin()">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                提交
            </a>
        </form>
        <p>{{ isLogin ? "没" : "已" }}有账号？点此<a href="#" class="a2" @click="toggleLogin()">{{ isLogin ? "注册！" : "登录！" }}</a>
        </p>
    </div>
</template>
<!-- 组合式 --> 
<script setup>
import { ref, onMounted } from 'vue';
import { hashPassword } from "@/utils/passwordUtil";
import request from "@/utils/request";

//切换登录/注册页面
const isLogin = ref(true)
const user = ref({
    username: "",
    password: "",
    captchaCode: ""
})
const roleNum = ref('1')
const roles = ref([
    {
        value: '1',
        label: '用户'
    }, {
        value: '0',
        label: '管理员'
    }
])

onMounted(() => {
    //组件挂载后调用验证码函数
    getimgCaptcha();
});
const codeImg = ref("")
//像后端获取验证码
const getimgCaptcha = () => {
    request.post("/captcha").then(result => {
        console.log(result);
        codeImg.value = result.data;
    });
};
//处理登录/注册
const handleLogin = () => {
    const hashedPassword = hashPassword(user.value.password);
    // 使用 SHA-512 对密码进行哈希处理
    if (isLogin.value) {
        request.post("/achieve/login", {
            username: user.value.username,
            password: hashedPassword,
            captchaCode: user.value.captchaCode,
            role:roleNum.value
        }).then(result => {
            console.log(result.data)
            if (result.data.msg === 'success') {
                console.log(1, result.status)
            }
            else {
                console.log(222, result.data)
            }
        });
    }
    else {
        if (user.value.password !== user.value.passwordAgain) {
            console.log("两次输入密码不一致")
        }
        else {
            request.post("/achieve/register", {
                username: user.value.username,
                password: hashedPassword
            }).then(result => {
                console.log(result.data)
                if (result.data.msg === 'success') {
                    console.log(1, result.status)
                }
                else {
                    console.log(222, result.data)
                }
            });
        }
    }
}
const toggleLogin = () => {
    isLogin.value = !isLogin.value
    user.value = {
        username: "",
        password: "",
        captchaCode: ""
    }
    if (isLogin.value) {
        getimgCaptcha();
    }
}
// 选项式
// export default {
//     data() {
//         return {
//             user: {
//                 username: "",
//                 password: ""
//             }
//         }
//     },
//     methods: {
//         handleLogin() {
//             // 使用 SHA-512 对密码进行哈希处理
//             const hashedPassword = hashPassword(this.user.password);
//             request.post("/userLogin/login", {
//                 username: this.user.username,
//                 password: hashedPassword
//             }).then(result => {
//                 console.log(result.data)
//                 if (result.data.msg === 'success') {
//                     console.log(1, result.status)
//                 }
//                 else {
//                     console.log(222, result.data)
//                 }
//             });
//         }
//     }
// }
</script>
  
<style scoped lang="scss">
.login-box {
    position: absolute;
    top: 46%;
    left: 50%;
    width: 400px;
    padding: 40px;
    transform: translate(-50%, -50%);
    background: rgba(0, 0, 0, 0.9);
    box-sizing: border-box;
    box-shadow: 35px 35px rgba(0, 0, 0, .6);
    border-radius: 20px;
}

.login-box p:first-child {
    margin: 0 0 25px;
    padding: 0;
    color: #fff;
    text-align: center;
    font-size: 1.5rem;
    font-weight: bold;
    letter-spacing: 1px;
}

.login-box .user-box {
    position: relative;
}

.login-box .user-box input {
    width: 100%;
    padding: 10px 0;
    font-size: 16px;
    color: #fff;
    margin-bottom: 24px;
    border: none;
    border-bottom: 1px solid #ffc9c9;
    outline: none;
    background: transparent;
}

.login-box .user-box label {
    position: absolute;
    top: 0;
    left: 0;
    padding: 7px 0;
    font-size: 16px;
    color: #fff;
    pointer-events: none;
    transition: .5s;
}

/* 当用户在输入框获得焦点时，其后的标签 <label> 将会被选择。 */
.login-box .user-box input:focus~label,
/* 当该 <input> 元素的内容满足其设定的校验条件时，选择其后的所有 <label> 元素 */
.login-box .user-box input:valid~label {
    top: -20px;
    left: 0;
    color: #fff;
    font-size: 12px;
}

.getCaptcha {
    position: absolute;
    top: -10px;
    right: 5px;
}

.login-radio {
    position: absolute;
    margin-top: -15px;
    left: 40px;
    color: #fff;
}

.login-box form a {
    position: relative;
    display: inline-block;
    padding: 10px 20px;
    font-weight: bold;
    color: #fff;
    font-size: 16px;
    text-decoration: none;
    overflow: hidden;
    transition: .5s;
    margin-top: 12px;
    letter-spacing: 3px
}

.login-box a:hover {
    background: #fff;
    color: #272727;
    border-radius: 5px;
}

.login-box a span {
    position: absolute;
    display: block;
}

.login-box a span:nth-child(1) {
    top: 0;
    left: -100%;
    width: 100%;
    height: 2px;
    /* linear-gradient 是一种线性渐变的背景样式     
    180deg: 表示渐变的方向，以角度来指定。在这里，180deg 表示从上到下的垂直渐变。
    transparent: 渐变的起始颜色。在这里，transparent 表示起始颜色为透明。
    #fff: 渐变的结束颜色。在这里，#fff 表示结束颜色为白色。 */
    background: linear-gradient(90deg, transparent, #fff);
    animation: btn-anim1 1.5s linear infinite;
}

@keyframes btn-anim1 {
    0% {
        left: -100%;
    }

    50%,
    100% {
        left: 100%;
    }
}

.login-box a span:nth-child(2) {
    top: -100%;
    right: 0;
    width: 2px;
    height: 100%;
    background: linear-gradient(180deg, transparent, #fff);
    animation: btn-anim2 1.5s linear infinite;
    animation-delay: .375s
}

@keyframes btn-anim2 {
    0% {
        top: -100%;
    }

    50%,
    100% {
        top: 100%;
    }
}

.login-box a span:nth-child(3) {
    bottom: 0;
    right: -100%;
    width: 100%;
    height: 2px;
    background: linear-gradient(270deg, transparent, #fff);
    animation: btn-anim3 1.5s linear infinite;
    animation-delay: .75s
}

@keyframes btn-anim3 {
    0% {
        right: -100%;
    }

    50%,
    100% {
        right: 100%;
    }
}

.login-box a span:nth-child(4) {
    bottom: -100%;
    left: 0;
    width: 2px;
    height: 100%;
    background: linear-gradient(360deg, transparent, #fff);
    animation: btn-anim4 1.5s linear infinite;
    animation-delay: 1.125s
}

@keyframes btn-anim4 {
    0% {
        bottom: -100%;
    }

    50%,
    100% {
        bottom: 100%;
    }
}

.login-box p:nth-last-child(2) {
    position: absolute;
    color: #aaa;
    font-size: 5px;
    margin-top: -15px;
    left: 40px;
}

.login-box p:last-child {
    color: #aaa;
    font-size: 14px;
    margin-top: 0;
}

.login-box a.a2 {
    color: #fff;
    text-decoration: none;
}

.login-box a.a2:hover {
    background: transparent;
    color: #aaa;
    border-radius: 5px;
}
</style>