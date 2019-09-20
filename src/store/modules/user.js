const user = {
  state: {
    username: "",
    nickname: "",
    avatar: "",
    token: "",
  },
  mutations: {
    setUser(state, obj){
      state.id = obj.id;
      state.username = obj.username;
      state.nickname = obj.nickname;
      state.avatar = obj.avatar;
      state.token = obj.token;
    }
  }
}
export default user;
