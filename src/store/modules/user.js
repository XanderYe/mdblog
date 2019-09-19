const user = {
  state: {
    username: "",
    avatar: "",
    token: "",
  },
  mutations: {
    setUser(state, obj){
      state.id = obj.id;
      state.username = obj.username;
      state.avatar = obj.avatar;
      state.token = obj.token;
    }
  }
}
export default user;
