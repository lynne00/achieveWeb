import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import pinia from '@/store/index.js'
import naive from 'naive-ui/es/preset'
import'./assets/font.css'
import'./assets/form.css'
createApp(App).use(store).use(router).use(pinia).use(naive).mount('#app')
