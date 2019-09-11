// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import MuseUI from 'muse-ui';
import Toast from 'muse-ui-toast';
import 'muse-ui/dist/muse-ui.css';
import theme from 'muse-ui/lib/theme';
import {router} from './router/index'
import requests from './libs/ajax';
import store from './store';
import $ from 'jquery';

const toastOption = {
  position: 'top', // 弹出的位置
  time: 3000, // 显示的时长
  closeIcon: 'close',
  close: true,
  successIcon: 'check_circle',
  infoIcon: 'info',
  warningIcon: 'priority_high',
  errorIcon: 'warning'
}

Vue.config.productionTip = false;
Vue.use(MuseUI);
Vue.use(Toast, toastOption);
Vue.prototype.$requests = requests;


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

    // a标签悬浮底色
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
      `;
    });
    theme.use('teal');
  }
});
