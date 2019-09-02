<template>
  <div>
    <mu-drawer :open.sync="open" :docked="docked" :z-depth="1">

      <mu-card style="width: 100%; max-width: 375px; margin: 0 auto">
        <mu-card-header :title="user.nickname" :sub-title="user.occupation" style="padding: 9px;">
          <mu-avatar slot="avatar">
            <img :src="user.avatar">
          </mu-avatar>
        </mu-card-header>
        <mu-card-media :sub-title="user.description">
          <img src="/static/img/card.jpg">
        </mu-card-media>
        <mu-card-actions>
          <mu-button flat style="width: 117px" :href="user.github">GITHUB</mu-button>
          <mu-button flat style="width: 117px" :href="'mailto:' + user.email">邮件</mu-button>
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
      <mu-menu slot="right">
        <mu-button flat>MENU</mu-button>
        <mu-list slot="content">
          <mu-list-item button>
            <mu-list-item-content>
              <mu-list-item-title>Menu Item 1</mu-list-item-title>
            </mu-list-item-content>
          </mu-list-item>
          <mu-list-item button>
            <mu-list-item-content>
              <mu-list-item-title>Menu Item 2</mu-list-item-title>
            </mu-list-item-content>
          </mu-list-item>
        </mu-list>
      </mu-menu>
    </mu-appbar>

    <div :class="['mu-container', isOpen]">
      <router-view/>
    </div>



  </div>

</template>

<script>
  export default {
    data() {
      const desktop = this.isDesktop();
      return {
        topicList: [],
        open: desktop,
        docked: desktop,
        desktop: desktop,
        isOpen: desktop ? "is-open" : "",
        openItem: "",
        user: {
          nickname: "XanderYe",
          avatar: "/static/img/my.jpg",
          email: "mailto:XanderYe@outlook.com",
          github: "https://github.com/XanderYe",
          description: "这里是一条咸鱼的博客",
          occupation: "java开发工程师"
        }
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
      this.getAllTopic();

      this.changeNav();
      this.handleResize = () => {
        this.changeNav();
      };
      window.addEventListener('resize', this.handleResize)
    },
    watch: {
      '$route': function (to, from) {
        // 移动端点击后自动关闭抽屉
        if(!this.isDesktop()){
          if(this.open){
            this.open = false;
          }
        }
      }
    }
  }
</script>

<style lang="less" scoped>

</style>
