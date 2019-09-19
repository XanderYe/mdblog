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
          path: 'topic',
          title: '主题',
          name: 'topic',
          component: () => import('@/views/topic.vue'),
        },
        {
          path: 'article',
          title: '文章',
          name: 'article-detail',
          component: () => import('@/views/article-detail.vue'),
        }
      ]
    }
  ]
});
