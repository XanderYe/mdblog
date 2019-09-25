<template>
  <div>
    <mu-drawer :open.sync="open" :docked="docked" :z-depth="1">

      <mu-card style="width: 100%; max-width: 375px; margin: 0 auto">
        <mu-card-header :title="owner.owner" :sub-title="owner.occupation" style="padding: 9px;">
          <mu-avatar slot="avatar">
            <img :src="owner.avatar">
          </mu-avatar>
        </mu-card-header>
        <mu-card-media :sub-title="owner.description">
          <img src="/static/img/card.jpg">
        </mu-card-media>
        <mu-card-actions>

          <mu-button flat :href="owner.github" style="width: 117px">
            GITHUB
            <mu-icon :size="24" right value=":mudocs-icon-custom-github"></mu-icon>
          </mu-button>

          <mu-button flat :href="'mailto:' + owner.email" style="width: 117px">
            EMAIL
            <mu-icon :size="24" right value="email"></mu-icon>
          </mu-button>

        </mu-card-actions>
      </mu-card>
      <mu-list toggle-nested>

        <mu-list-item button :to="{name:'index'}" @click="changeNavName('首页')">
          <mu-list-item-action>
            <mu-icon value="home"></mu-icon>
          </mu-list-item-action>
          <mu-list-item-title>首页</mu-list-item-title>
        </mu-list-item>

        <mu-list-item button nested :open="openItem === '主题'" @toggle-nested="openItem = arguments[0] ? '主题' : ''">
          <mu-list-item-action>
            <mu-icon value="list"></mu-icon>
          </mu-list-item-action>
          <mu-list-item-title>主题</mu-list-item-title>
          <mu-list-item-action>
            <mu-icon class="toggle-icon" size="24" value="keyboard_arrow_down"></mu-icon>
          </mu-list-item-action>

          <mu-list-item button slot="nested" v-for="topic in topicList" :key="topic.id"
                        :to="{name:'index', query:{id : topic.id}}" @click="changeNavName(topic.name)">
            <mu-list-item-title>{{topic.name}}</mu-list-item-title>
          </mu-list-item>

        </mu-list-item>

        <mu-list-item v-if="isOwner" button nested :open="openItem === '博客管理'"
                      @toggle-nested="openItem = arguments[0] ? '博客管理' : ''">
          <mu-list-item-action>
            <mu-icon value="settings"></mu-icon>
          </mu-list-item-action>
          <mu-list-item-title>博客管理</mu-list-item-title>
          <mu-list-item-action>
            <mu-icon class="toggle-icon" size="24" value="keyboard_arrow_down"></mu-icon>
          </mu-list-item-action>

          <mu-list-item button slot="nested" :to="{name:'topic-list'}" @click="changeNavName('主题管理')">
            <mu-list-item-title>主题管理</mu-list-item-title>
          </mu-list-item>

          <mu-list-item button slot="nested" :to="{name:'article-list'}" @click="changeNavName('文章管理')">
            <mu-list-item-title>文章管理</mu-list-item-title>
          </mu-list-item>

        </mu-list-item>

      </mu-list>

    </mu-drawer>

    <mu-appbar :class="['mu-appbar-header', isOpen]" color="primary">
      <mu-button icon slot="left" @click="toggleNav" v-if="!desktop">
        <mu-icon value="menu"></mu-icon>
      </mu-button>
      {{appBarName}}

      <!--未登录-->
      <div slot="right" class="anon-buttons" v-if="!isLogin" style="margin-right: 5px;">
        <div>
          <mu-button flat @click="openLogin">登录</mu-button>
          <mu-button flat @click="openRegister">注册</mu-button>
        </div>
      </div>

      <!--已登录-->
      <div slot="right" class="avatar-button" v-else="isLogin" style="margin-right: 5px;">
        <mu-button flat ref="avatarButton" @click="openUserMenu">
          <img :src="user.avatar">
        </mu-button>
        <mu-popover cover placement="left-start" :open.sync="userMenu" :trigger="userMenuTrigger">
          <div class="popover-div">
            <div class="info">
              <div class="avatar">
                <div class="avatar-upload">
                  <mu-button fab class="upload-button">
                    <mu-icon value="camera_alt"></mu-icon>
                  </mu-button>
                  <input type="file" title=" " accept="image/*" id="upload-avatar">
                </div>
                <img :src="user.avatar" width="96" height="96">
              </div>
              <div class="username">{{user.nickname}}</div>
              <mu-button color="secondary" style="margin: 16px 0 0 18px">个人中心</mu-button>
            </div>
            <div class="bottom">
              <mu-button style="float: right" @click="logout">退出</mu-button>
            </div>
          </div>
        </mu-popover>
      </div>

    </mu-appbar>

    <div :class="['mu-container', isOpen]">

      <keep-alive :include="['index']">
        <router-view></router-view>
      </keep-alive>


      <!--返回顶部按钮-->
      <mu-scale-transition>
        <mu-button class="scroll-btn" fab color="secondary" @click="toTop" v-show="scrollBtnStatus">
          <mu-icon value="arrow_upward"></mu-icon>
        </mu-button>
      </mu-scale-transition>
    </div>

    <mu-dialog width="448" transition="scale" :fullscreen="!desktop" :open.sync="loginDialog" :overlay-close="false"
               class="login-dialog">
      <div>
        <mu-appbar color="primary" title="登录">
          <mu-button slot="left" icon @click="loginDialog = false">
            <mu-icon value="close"></mu-icon>
          </mu-button>
        </mu-appbar>

        <div class="login-form" style="height: 350px;">
          <mu-form ref="loginForm" :model="loginData" label-position="top" label-width="100">
            <mu-form-item prop="username" label="用户名" :rules="usernameRules" icon="account_circle">
              <mu-text-field v-model="loginData.username"></mu-text-field>
            </mu-form-item>

            <mu-form-item prop="password" label="密码" :rules="passwordRules" icon="locked">
              <mu-text-field v-model="loginData.password"
                             :action-icon="loginVisibility ? 'visibility_off' : 'visibility'"
                             :action-click="() => (loginVisibility = !loginVisibility)"
                             :type="loginVisibility ? 'text' : 'password'"></mu-text-field>
            </mu-form-item>

            <mu-form-item style="margin-left: 16px;">
              <mu-checkbox v-model="loginData.remember" value="true" label="记住密码"></mu-checkbox>
            </mu-form-item>

            <mu-button style="float: right;" color="secondary" @click="login">登录</mu-button>

          </mu-form>
        </div>
      </div>

    </mu-dialog>

    <mu-dialog width="448" transition="scale" :fullscreen="!desktop" :open.sync="registerDialog"
               :overlay-close="false" class="register-dialog">
      <div>
        <mu-appbar color="primary" title="注册">
          <mu-button slot="left" icon @click="registerDialog = false">
            <mu-icon value="close"></mu-icon>
          </mu-button>
        </mu-appbar>

        <div class="register-form" style="height: 500px;">
          <mu-form ref="registerForm" :model="registerData" label-position="top" label-width="100">
            <mu-form-item prop="username" label="用户名" :rules="username2Rules" icon="account_circle">
              <mu-text-field v-model="registerData.username"></mu-text-field>
            </mu-form-item>

            <mu-form-item prop="username" label="昵称" :rules="nicknameRules" icon="supervised_user_circle">
              <mu-text-field v-model="registerData.nickname"></mu-text-field>
            </mu-form-item>

            <mu-form-item prop="password" label="密码" :rules="password1Rules" icon="locked">
              <mu-text-field v-model="registerData.password"
                             :action-icon="registerVisibility1 ? 'visibility_off' : 'visibility'"
                             :action-click="() => (registerVisibility1 = !registerVisibility1)"
                             :type="registerVisibility1 ? 'text' : 'password'"></mu-text-field>
            </mu-form-item>

            <mu-form-item prop="password2" label="确认密码" :rules="password2Rules" icon="done">
              <mu-text-field v-model="registerData.password2"
                             :action-icon="registerVisibility2 ? 'visibility_off' : 'visibility'"
                             :action-click="() => (registerVisibility2 = !registerVisibility2)"
                             :type="registerVisibility2 ? 'text' : 'password'"></mu-text-field>
            </mu-form-item>

            <mu-form-item prop="verCode" label="验证码" :rules="verCodeRules" icon="image">
              <mu-text-field v-model="registerData.verCode" type="text" maxLength="4"
                             style="width: 50%;"></mu-text-field>
              <div style="width: 50%; height: 30px; text-align: right;">
                <img style="line-height:30px;" :src="imgSrc" @click="changeCode">
              </div>
            </mu-form-item>

            <mu-button style="float: right;" color="secondary" @click="register">注册</mu-button>

          </mu-form>
        </div>
      </div>

    </mu-dialog>

    <mu-snackbar position="top" :open.sync="snackbar.open">
      {{snackbar.message}}
      <mu-button flat slot="action" color="secondary" @click="snackbar.open = false">关闭</mu-button>
    </mu-snackbar>
  </div>

</template>

<script>

    export default {
        data() {
            const desktop = this.isDesktop();
            return {
                // 登录状态
                isLogin: false,
                user: {
                    id: 0,
                    username: "",
                    nickname: "",
                    avatar: "",
                },
                topicList: [],
                open: desktop,
                docked: desktop,
                desktop: desktop,
                isOpen: desktop ? "is-open" : "",
                openItem: "",
                // 导航栏名称
                appBarName: "首页",
                owner: {
                    id: 1,
                    owner: "XanderYe",
                    avatar: "/static/img/my.jpg",
                    email: "XanderYe@outlook.com",
                    github: "https://github.com/XanderYe",
                    description: "这里是一条咸鱼的博客",
                    occupation: "java开发工程师"
                },
                // 用户菜单
                userMenu: false,
                // 菜单弹出绑定元素
                userMenuTrigger: null,
                // 登录dialog
                loginDialog: false,
                // 注册dialog
                registerDialog: false,
                // 登录密码状态
                loginVisibility: false,
                // 注册密码状态
                registerVisibility1: false,
                // 注册确认密码状态
                registerVisibility2: false,
                loginData: {
                    username: "",
                    password: "",
                    remember: false
                },
                registerData: {
                    username: "",
                    nickname: "",
                    password: "",
                    password2: "",
                    verCode: "",
                    uuid: "",
                },
                snackbar: {
                    message: "",
                    open: false,
                },
                // 回顶部按钮状态
                scrollBtnStatus: false,
                // 验证码src
                imgSrc: "",
                usernameRules: [
                    {validate: (val) => !!val, message: '必须填写用户名'},
                ],
                username2Rules: [
                    {validate: (val) => !!val, message: '必须填写用户名'},
                    {validate: (val) => this.checkUsername(val), message: '用户名已存在'},
                ],
                nicknameRules: [
                    {validate: (val) => !!val, message: '必须填写昵称'},
                ],
                passwordRules: [
                    {validate: (val) => !!val, message: '必须填写密码'},
                ],
                password1Rules: [
                    {validate: (val) => !!val, message: '必须填写密码'},
                    {validate: (val) => val.length >= 6 && val.length <= 12, message: '密码长度大于6小于12'}
                ],
                password2Rules: [
                    {validate: (val) => !!val, message: '必须填写密码'},
                    {validate: (val) => val === this.registerData.password, message: '两次密码不一致'}
                ],
                verCodeRules: [
                    {validate: (val) => !!val, message: '必须填写验证码'},
                    {validate: (val) => val.length === 4, message: '验证码必须是4位数'}
                ],
            }
        },
        computed: {
            isOwner() {
                return this.user.id === this.owner.id;
            },
        },
        methods: {

            toggleNav() {
                this.open = !this.open
            },
            changeNav() {
                const desktop = this.isDesktop();
                this.docked = desktop;
                if (desktop === this.desktop) {
                    return;
                }
                if (!desktop && this.desktop && this.open) {
                    this.open = false;
                    this.isOpen = "";
                }
                if (desktop && !this.desktop && !this.open) {
                    this.open = true;
                    this.isOpen = "is-open";
                }
                this.desktop = desktop
            },

            // 改变菜单栏名称
            changeNavName(name) {
                this.appBarName = name;
            },

            getOwner() {
                this.$requests.get("/user/getOwner", null).then(res => {
                    if (res.data.code === 0) {
                        this.owner = res.data.data;
                        this.owner.avatar = ajaxUrl + res.data.data.avatar;
                    }
                })
            },

            // 获取主题
            getAllTopic() {
                this.$requests.get("/topic/getAll", null).then((res) => {
                    if (res.data.code === 0) {
                        this.topicList = res.data.data;
                    }
                })
            },

            // 是否是桌面端
            isDesktop() {
                return window.innerWidth > 993;
            },

            // 打开通知
            openSnackbar(msg) {
                this.snackbar.message = msg;
                this.snackbar.open = true;
                setTimeout(() => {
                    if (this.snackbar.open) {
                        this.snackbar.open = false;
                    }
                }, 3000)
            },

            toTop() {
                document.documentElement.scrollTop = 0;
                document.body.scrollTop = 0;
            },

            // 滚到顶部
            scrollToTop() {
                let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
                let browserHeight = window.outerHeight;
                this.scrollBtnStatus = scrollTop > browserHeight;
            },

            // 更改验证码
            changeCode() {
                this.$requests.get("/captcha?r=" + Math.random(), null).then(res => {
                    if (res.data.code === 0) {
                        this.imgSrc = "data:image/png;base64," + res.data.data.image;
                        this.registerData.uuid = res.data.data.uuid;
                    }
                })

            },

            openUserMenu() {
                this.userMenu = true;
            },

            // 打开登录框
            openLogin() {
                this.loginDialog = true;
                this.loginVisibility = false;
                let username = localStorage.getItem("username");
                let password = localStorage.getItem("password");
                if (username != null && password != null) {
                    this.loginData.username = username;
                    this.loginData.password = password;
                }
            },

            // 登录
            login() {
                this.$refs.loginForm.validate().then((validate) => {
                    if (validate) {
                        if (this.loginData.remember) {
                            localStorage.setItem("username", this.loginData.username);
                            localStorage.setItem("password", this.loginData.password);
                        }
                        let form = new FormData;
                        form.append("username", this.loginData.username);
                        form.append("password", this.loginData.password);
                        this.$requests.post("/user/login", form).then(res => {
                            if (res.data.code === 0) {
                                this.openSnackbar("登录成功");
                                let user = res.data.data;
                                // 存储到localStorage
                                localStorage.removeItem("user");
                                localStorage.setItem("user", JSON.stringify(user));
                                // 存储到vueX
                                this.$store.commit("setUser", user);
                                this.user = user;
                                this.user.avatar = ajaxUrl + user.avatar;
                                this.isLogin = true;
                                this.loginDialog = false;
                                this.$nextTick(() => {
                                    //绑定菜单弹出元素
                                    this.userMenuTrigger = this.$refs.avatarButton.$el;
                                })

                            } else {
                                this.openSnackbar(res.data.msg);
                            }
                        })
                    }
                })
            },

            // 打开注册框
            openRegister() {
                this.registerDialog = true;
                this.registerVisibility1 = false;
                this.registerVisibility2 = false;
                this.registerData = {
                    username: "",
                    password: "",
                    password2: "",
                    verCode: "",
                    uuid: "",
                };
                this.changeCode();
            },

            // 检查用户名
            checkUsername(username) {
                let result = true;
                this.$requests.asynGet("/user/check", {username: username}, (res) => {
                    result = res.code === 0;
                });
                return result;
            },

            // 注册
            register() {
                this.$refs.registerForm.validate().then((validate) => {
                    if (validate) {
                        let form = new FormData;
                        form.append("username", this.registerData.username);
                        form.append("nickname", this.registerData.nickname);
                        form.append("password", this.registerData.password);
                        form.append("password2", this.registerData.password2);
                        form.append("verCode", this.registerData.verCode);
                        form.append("uuid", this.registerData.uuid);
                        this.$requests.post("/user/register", form).then(res => {
                            if (res.data.code === 0) {
                                this.openSnackbar("注册成功");
                                this.isLogin = true;
                                this.registerDialog = false;
                                this.loginDialog = true;
                            } else {
                                this.changeCode();
                                this.openSnackbar(res.data.msg);
                            }
                        })
                    }
                })
            },

            // 注销
            logout() {
                this.userMenu = false;
                this.isLogin = false;
                this.user = {
                    id: 0,
                    username: "",
                    nickname: "",
                    avatar: "",
                };
                this.$store.commit("removeUser");
                this.openSnackbar("注销成功");

            },
        },
        created() {
            this.getOwner();
            this.getAllTopic();
            this.changeNav();
            this.changeCode();

            // 判断登录状态
            const user = JSON.parse(localStorage.getItem("user"));
            if (user) {
                this.isLogin = true;
                this.user = user;
                if(user.avatar){
                    this.user.avatar = ajaxUrl + user.avatar;
                }
                this.$store.commit("setUser", user);
            }
        },
        mounted() {
            this.handleResize = () => {
                this.changeNav();
            };
            // 拖动窗口事件
            window.addEventListener('resize', this.handleResize);

            // 滚动事件
            window.addEventListener('scroll', this.scrollToTop, true);

            if(this.isLogin){
                //绑定菜单弹出元素
                this.userMenuTrigger = this.$refs.avatarButton.$el;
            }
        },
        watch: {
            '$route': function (to, from) {
                // 移动端点击后自动关闭抽屉
                if (!this.isDesktop()) {
                    if (this.open) {
                        this.open = false;
                    }
                }
            }
        }
    }
</script>

<style lang="less" scoped>

</style>
