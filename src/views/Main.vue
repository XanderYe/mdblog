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
          <mu-button flat style="width: 117px" :href="owner.github">GITHUB</mu-button>
          <mu-button flat style="width: 117px" :href="'mailto:' + owner.email">邮件</mu-button>
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

      </mu-list>

    </mu-drawer>

    <mu-appbar :class="['mu-appbar-header', isOpen]" color="primary">
      <mu-button icon slot="left" @click="toggleNav" v-if="!desktop">
        <mu-icon value="menu"></mu-icon>
      </mu-button>
      {{appBarName}}

      <!--未登录-->
      <div slot="right" v-if="!isLogin" style="margin-right: 5px;">
        <mu-button flat @click="loginDialog = true">登录</mu-button>
        <mu-button flat @click="registerDialog = true">注册</mu-button>
      </div>

      <!--已登录-->
      <template slot="right" v-else>

      </template>

    </mu-appbar>

    <div :class="['mu-container', isOpen]">
      <router-view/>
    </div>

    <mu-dialog width="448" transition="scale" :fullscreen="!desktop" :open.sync="loginDialog" class="login-dialog">
      <div>
        <mu-appbar color="primary" title="登录">
          <mu-button slot="left" icon @click="loginDialog = false">
            <mu-icon value="close"></mu-icon>
          </mu-button>
        </mu-appbar>

        <div class="login-form" style="height: 350px;">
          <mu-form ref="loginForm" :model="loginData" label-position="top" label-width="100">
            <mu-form-item prop="username" label="用户名" :rules="usernameRules">
              <mu-text-field v-model="loginData.username"></mu-text-field>
            </mu-form-item>

            <mu-form-item prop="password" label="密码" :rules="passwordRules">
              <mu-text-field v-model="loginData.password"></mu-text-field>
            </mu-form-item>

            <mu-form-item>
              <mu-checkbox v-model="loginData.remember" value="true" label="记住密码"></mu-checkbox>
            </mu-form-item>

            <mu-button style="float: right;" color="secondary" @click="login">登录</mu-button>

          </mu-form>
        </div>
      </div>

    </mu-dialog>

    <mu-dialog width="448" transition="scale" :fullscreen="!desktop" :open.sync="registerDialog"
               class="register-dialog">
      <div>
        <mu-appbar color="primary" title="注册">
          <mu-button slot="left" icon @click="registerDialog = false">
            <mu-icon value="close"></mu-icon>
          </mu-button>
        </mu-appbar>

        <div class="register-form" style="height: 350px;">
          <mu-form ref="registerForm" :model="registerData" label-position="top" label-width="100">
            <mu-form-item prop="username" label="用户名" :rules="usernameRules">
              <mu-text-field v-model="registerData.username"></mu-text-field>
            </mu-form-item>

            <mu-form-item prop="password" label="密码" :rules="passwordRules">
              <mu-text-field v-model="registerData.password"></mu-text-field>
            </mu-form-item>

            <mu-form-item prop="password2" label="重复密码" :rules="password2Rules">
              <mu-text-field v-model="registerData.password2"></mu-text-field>
            </mu-form-item>

            <mu-button style="float: right;" color="secondary" @click="register">注册</mu-button>

          </mu-form>
        </div>
      </div>

    </mu-dialog>
  </div>

</template>

<script>
  export default {
    data() {
      const desktop = this.isDesktop();
      return {
        isLogin: false,
        topicList: [],
        open: desktop,
        docked: desktop,
        desktop: desktop,
        isOpen: desktop ? "is-open" : "",
        openItem: "",
        appBarName: "首页",
        owner: {
          owner: "XanderYe",
          avatar: "/static/img/my.jpg",
          email: "mailto:XanderYe@outlook.com",
          github: "https://github.com/XanderYe",
          description: "这里是一条咸鱼的博客",
          occupation: "java开发工程师"
        },
        loginDialog: false,
        registerDialog: false,
        loginData: {
          username: "",
          password: "",
          remember: false
        },
        registerData: {
          username: "",
          password: "",
          password2: "",
        },
        usernameRules: [
          { validate: (val) => !!val, message: '必须填写用户名'},
        ],
        passwordRules: [
          { validate: (val) => !!val, message: '必须填写密码'},
          { validate: (val) => val.length >= 6 && val.length <= 12, message: '密码长度大于6小于12'}
        ],
        password2Rules: [
          { validate: (val) => !!val, message: '必须填写密码'},
          { validate: (val) => val == this.registerData.password, message: '两次密码不一致'}
        ],
      }
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

      changeNavName(name) {
        this.appBarName = name;
      },

      getOwner() {
        this.$requests.get("/user/getOwner", null).then(res => {
          if (res.data.code == 0) {
            this.owner = res.data.data;
            this.owner.avatar = ajaxUrl + res.data.data.avatar;
          }
        })
      },

      getAllTopic() {
        this.$requests.get("/topic/getAll", null).then((res) => {
          if (res.data.code == 0) {
            this.topicList = res.data.data;
          }
        })
      },

      isDesktop() {
        return window.innerWidth > 993;
      },

      login(){
        this.$refs.loginForm.validate().then((validate) => {
          if(validate){

          }
        })
      },

      register(){
        this.$refs.registerForm.validate().then((validate) => {
          if(validate){

          }
        })
      },
    },
    created() {
      this.getOwner();
      this.getAllTopic();

      this.changeNav();
      this.handleResize = () => {
        this.changeNav();
      };
      window.addEventListener('resize', this.handleResize);
      // 判断登录状态
      this.isLogin = localStorage.getItem("md-token") != null;
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
