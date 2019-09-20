import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/views/Main.vue';

Vue.use(Router);

export const router = new Router({
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
          path: 'article',
          title: '文章详情',
          name: 'articledetail',
          component: () => import('@/views/article-detail.vue'),
        },
        {
          path: 'topiclist',
          title: '主题管理',
          name: 'topic-list',
          component: () => import('@/views/topic-list.vue'),
        },
        {
          path: 'articlelist',
          title: '文章管理',
          name: 'article-list',
          component: () => import('@/views/article-list.vue'),
        },
      ]
    }
  ]
});
