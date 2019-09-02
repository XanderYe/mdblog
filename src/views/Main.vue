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
      <mu-list>
        <mu-list-item button>
          <mu-list-item-title>Menu Item 1</mu-list-item-title>
        </mu-list-item>
        <mu-list-item button>
          <mu-list-item-title>Menu Item 2</mu-list-item-title>
        </mu-list-item>
        <mu-list-item @click="open = false" button>
          <mu-list-item-title>Close</mu-list-item-title>
        </mu-list-item>
      </mu-list>
    </mu-drawer>

    <mu-appbar :class="['mu-appbar-header', isOpen]" color="primary">
      <mu-button icon slot="left" @click="open = true" v-if="!desktop">
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
        isOpen: "is-open",
        user:{
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

      toggleNav () {
        this.open = !this.open
      },
      changeNav () {
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
    mounted () {
      this.getAllTopic();

      this.changeNav();
      this.handleResize = () => {
        this.changeNav();
      }
      window.addEventListener('resize', this.handleResize)
    },
  }
</script>

<style lang="less" scoped>
  .mu-appbar-header {
    position: fixed;
    left: 0;
    right: 0;
    top: 0;
    overflow: hidden;
  }

  .mu-appbar-header.is-open {
    left: 256px;
  }
</style>

<!--全局普通css-->
<style>
  /*滚动条颜色*/
  ::-webkit-scrollbar-thumb {
    border-radius: 8px;
    background: #757575;
  }
</style>
