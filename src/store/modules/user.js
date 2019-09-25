const user = {
  state: {
    id: 0,
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
    },
    removeUser(state){
      state.id = 0;
      state.username = "";
      state.nickname = "";
      state.avatar = "";
      state.token = "";
      localStorage.removeItem("user");
    }
  }
}
export default user;
