

const app = {
  state:{
    topicMenu:[],
    isOwner: false,
    isDesktop: true,
  },
  mutations:{
    setTopicMenu(state, obj){
      state.topicMenu = obj;
    },
    setIsOwner(state, obj){
      state.isOwner = obj;
    },
    setIsDesktop(state, obj){
      state.isDesktop = obj;
    }
  }
}

export default app;
