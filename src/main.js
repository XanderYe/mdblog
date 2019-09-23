// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import MuseUI from 'muse-ui';
import 'muse-ui/dist/muse-ui.css';
import theme from 'muse-ui/lib/theme';
import {router} from './router/index'
import requests from './libs/ajax';
import store from './store';
import $ from 'jquery';
import NProgress from 'nprogress';
import hljs from 'highlight.js';

Vue.config.productionTip = false;
Vue.use(MuseUI);
Vue.prototype.$requests = requests;

Vue.directive('highlight',function (el) {
  let blocks = el.querySelectorAll('pre code');
  blocks.forEach((block)=>{
    hljs.highlightBlock(block);
  })
});

NProgress.configure({
  // 动画方式
  easing: 'ease',
  // 递增进度条的速度
  speed: 500,
  // 是否显示加载ico
  showSpinner: false,
  // 自动递增间隔
  trickleSpeed: 200,
  // 初始化时的最小百分比
  minimum: 0.3
});

//当路由开始跳转时
router.beforeEach((to, from, next) => {
  // 进度条
  NProgress.start();
  next();
});
//当路由跳转结束后
router.afterEach(() => {
  // 关闭进度条
  NProgress.done();
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router: router,
  store: store,
  components: {App},
  template: '<App/>',
  mounted() {
    theme.add('teal', {
      primary: '#009688',
      secondary: '#009688',
    }, 'light');

    // 需要主题颜色的css加入主题控制
    theme.addCreateTheme((theme) => {
      return `
        .mu-typo a:before {
          background-color: ${theme.secondary};
        }
        
        .mu-form-item__focus, .mu-input__focus {
          color: ${theme.secondary};
        }
        
        .mu-checkbox-checked {
          color: ${theme.secondary};
        }
        
        .mu-pagination-item.mu-button.is-current {
          background-color: ${theme.secondary};
        }
        
        #nprogress .bar {
          background: ${theme.secondary};
        }
        
        #nprogress .peg {
          box-shadow: 0 0 10px ${theme.secondary}, 0 0 5px ${theme.secondary};
        }
      `;
    });
    theme.use('teal');
  }
});
