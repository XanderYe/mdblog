import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/views/Main.vue';

Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'main',
      redirect: '/index',
      component: Main,
      children: [
        {
          path: 'index',
          title: '首页',
          name: 'index',
          component: () => import('@/views/index.vue'),
        },
        {
          path: 'article/detail',
          title: '文章详情',
          name: 'article-detail',
          component: () => import('@/views/article/detail.vue'),
        },
        {
          path: 'article/list',
          title: '文章列表',
          name: 'article-list',
          component: () => import('@/views/article/list.vue'),
        },
        {
          path: 'article/add',
          title: '发表文章',
          name: 'article-add',
          component: () => import('@/views/article/add.vue'),
        },
        {
          path: 'article/edit',
          title: '编辑文章',
          name: 'article-edit',
          component: () => import('@/views/article/add.vue'),
        },
        {
          path: 'topic/list',
          title: '主题管理',
          name: 'topic-list',
          component: () => import('@/views/topic/list.vue'),
        },
      ]
    }
  ]
});
