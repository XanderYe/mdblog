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

        <mu-list-item button :to="{name:'index'}">
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
                        :to="{name:'index', query:{id : topic.id}}">
            <mu-list-item-title>{{topic.name}}</mu-list-item-title>
          </mu-list-item>

        </mu-list-item>

      </mu-list>

    </mu-drawer>

    <mu-appbar :class="['mu-appbar-header', isOpen]" color="primary">
      <mu-button icon slot="left" @click="toggleNav" v-if="!desktop">
        <mu-icon value="menu"></mu-icon>
      </mu-button>
      XanderYe的博客

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
      <!--桌面端-->
      <div v-if="desktop" class="desktop-login-dialog">
        <mu-appbar color="primary" title="登录">
          <mu-button slot="left" icon @click="loginDialog = false">
            <mu-icon value="close"></mu-icon>
          </mu-button>
        </mu-appbar>

        <div style="height: 350px;">

        </div>
      </div>
      <!--移动端-->
      <div v-else>
        <mu-appbar color="primary" title="登录">
          <mu-button slot="left" icon @click="loginDialog = false">
            <mu-icon value="close"></mu-icon>
          </mu-button>


        </mu-appbar>
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
        return window.innerWidth > 993
      }
    },
    mounted() {
      this.getOwner();
      this.getAllTopic();

      this.changeNav();
      this.handleResize = () => {
        this.changeNav();
      };
      window.addEventListener('resize', this.handleResize)
      // 判断登录状态
      if (localStorage.getItem("md-token") != null) {
        this.isLogin = true;
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
