// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import MuseUI from 'muse-ui';
import 'muse-ui/dist/muse-ui.css';
import {router} from './router/index'
import requests from './libs/ajax';
import store from './store';
import $ from 'jquery';

Vue.config.productionTip = false
Vue.use(MuseUI);
Vue.prototype.$requests = requests;


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router: router,
  store: store,
  components: { App },
  template: '<App/>'
})
