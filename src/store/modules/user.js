const user = {
  state: {
    id: 0,
    username: "",
    nickname: "",
    avatar: "",
  },
  mutations: {
    setUser(state, obj){
      state.id = obj.id;
      state.username = obj.username;
      state.nickname = obj.nickname;
      state.avatar = obj.avatar;
    },
    removeUser(state){
      state.id = 0;
      state.username = "";
      state.nickname = "";
      state.avatar = "";
    }
  }
}
export default user;
