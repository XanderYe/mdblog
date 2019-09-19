import Cookies from 'js-cookie';
import Vue from 'vue';

const app = {
  state:{
    topicMenu:[],
  },
  mutations:{
    setTopicMenu(state, obj){
      state.topicMenu = obj;
    }
  }
}

export default app;
