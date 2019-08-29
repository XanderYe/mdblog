<template>
  <div class="page-container">
    <md-app md-mode="fixed">
      <md-app-toolbar class="md-primary">
        <md-button class="md-icon-button" @click="toggleMenu" v-if="!menuVisible">
          <md-icon>menu</md-icon>
        </md-button>

        <span class="md-title">XanderYe的博客</span>
      </md-app-toolbar>

      <md-app-drawer :md-active.sync="menuVisible" :md-persistent="persistent">
        <md-toolbar class="md-transparent" md-elevation="0">
          <span>Navigation</span>

          <div class="md-toolbar-section-end">
            <md-button class="md-icon-button md-dense" @click="toggleMenu">
              <md-icon>keyboard_arrow_left</md-icon>
            </md-button>
          </div>
        </md-toolbar>

        <md-list :md-expand-single="true">

          <md-list-item :to="{path: 'index'}">
            <md-icon>home</md-icon>
            <span class="md-list-item-text" router>首页</span>
          </md-list-item>


          <md-list-item md-expand>
            <md-icon>list</md-icon>
            <span class="md-list-item-text">主题</span>

            <md-list slot="md-expand">
              <md-list-item class="md-inset" v-for="(topic,index) in topicList" :key="topic.id"
                            :to="{path: 'index',query: {id: topic.id}}">
                {{topic.name}}
              </md-list-item>
            </md-list>
          </md-list-item>

          <md-list-item md-expand>
            <md-icon>video_library</md-icon>
            <span class="md-list-item-text">Video</span>

            <md-list slot="md-expand">
              <md-list-item class="md-inset">Humor</md-list-item>
              <md-list-item class="md-inset">Music</md-list-item>
              <md-list-item class="md-inset">Movies</md-list-item>
              <md-list-item class="md-inset">TV Shows</md-list-item>
            </md-list>
          </md-list-item>

          <md-list-item>
            <md-icon>shopping_basket</md-icon>
            <span class="md-list-item-text">Shop</span>
          </md-list-item>
        </md-list>
      </md-app-drawer>

      <md-app-content>
        <router-view/>
      </md-app-content>
    </md-app>
  </div>

</template>

<script>
  export default {
    data: () => ({
      topicList: [],
      menuVisible: true,
      persistent: "mini"
    }),
    methods: {
      toggleMenu() {
        this.menuVisible = !this.menuVisible
      },
      getAllTopic() {
        this.$requests.get("/topic/getAll", null).then((res) => {
          if (res.data.code == 0) {
            this.topicList = res.data.data;
          }
        })
      },

      isMobile() {
        return navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      }
    },
    created() {
      this.getAllTopic();

      if (this.isMobile()) {
        this.menuVisible = false;
        this.persistent = "full";
      }
    },
  }
</script>

<!--组件scss-->
<style lang="scss" scoped>

  .md-drawer {
    width: 230px;
    max-width: calc(100vw - 125px);
  }

  .md-app-content{
    height:500px;
  }

  @import "~vue-material/dist/theme/engine";

  @include md-register-theme("default", (
    primary: md-get-palette-color(blue, A200),
    accent: md-get-palette-color(red, A200)
  ));

  @import "~vue-material/dist/theme/all";
</style>

<!--全局普通css-->
<style>
  /*滚动条颜色*/
  ::-webkit-scrollbar-thumb{
    border-radius: 8px;
    background: #757575;
  }
  /*换行*/
  p, span, code{
    white-space: normal;
    word-break: break-all;
  }
  a:visited{

  }
</style>
