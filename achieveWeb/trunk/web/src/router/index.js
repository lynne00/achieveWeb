import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/LoginView.vue'),
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('../views/PageView.vue'),
    children:[
      {path: 'index', name: 'index', component: () => import('../modules/Home.vue')},
      {path: 'userInfo', name: 'userInfo', component: () => import('../modules/UserInfo.vue')},
    ]
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/login'
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../components/Aside.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
