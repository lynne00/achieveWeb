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
    redirect:"/index",
    children: [
      { path: 'index', name: 'index', component: () => import('../modules/Home.vue'), meta: { name: "首页" }},
      { path: 'userInfo', name: 'userInfo', component: () => import('../modules/UserInfo.vue'), meta: { name: "个人资料" }},
      {
        path: 'manageItem', name: 'manageItem',
        children: [
          {
            path: 'createProject', name: 'createProject', 
            children: [
              { path: 'category', name: 'category', component: () => import('../modules/Category.vue'), meta: { name: "分类管理" } },
              { path: 'tag', name: 'tag', component: () => import('../modules/Tag.vue') , meta: { name: "标签管理" }},
              // { path: 'item', name: 'item', component: () => import('../modules/Item.vue'), meta: { name: "项目管理" } },
              { path: 'itemRecord', name: 'itemRecord', component: () => import('../modules/ItemRecord.vue'), meta: { name: "项目管理" } },
              // { path: 'addTag', name: 'addTag', component: () => import('../modules/AddTag.vue') },
            ]
          },
        ]
      },
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
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
router.beforeEach((to, from, next) => {//beforeEach是router的钩子函数，在进入路由前执行
  //页面标题
  if (to.meta.name) {//判断是否有标题
    document.title = to.meta.name
  }
  next(); // 确保继续导航
})
export default router
