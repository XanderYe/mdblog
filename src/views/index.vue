<template>
  <div>
    <!--<md-content class="md-scrollbar">
      <md-card md-with-hover md-theme="primary" v-for="article in articleList" :key="article.id">
        <md-card-header>
          <md-card-header-text>
            <router-link :to="{path: 'article', query: {id: article.id}}">
              <div class="md-title">{{article.title}}</div>
            </router-link>
            <div class="md-subhead">{{article.createTime}}</div>
          </md-card-header-text>
        </md-card-header>

        <md-card-content v-html="article.content">
        </md-card-content>

        <md-card-actions md-alignment="left">
          <md-button class="md-primary" :to="{path: 'article', query: {id: article.id}}">继续阅读</md-button>
        </md-card-actions>
      </md-card>
    </md-content>-->
  </div>
</template>

<script>
  export default {
    name: "index",
    data: () => ({
      topicId: null,
      articleList: [],
      page: 1,
    }),
    methods: {
      getArticleList() {
        this.$requests.get("/article/getRecentArticles", {tid: this.topicId, page: this.page}).then((res) => {
          if (res.data.code == 0) {
            this.articleList = res.data.data.list;
          }
        })
      }
    },
    created() {
      this.getArticleList();
    },
    watch: {
      '$route': function (to, from) {
        this.topicId = this.$route.query.id;
        this.getArticleList();
      }
    }
  }
</script>

<style scoped>
  .md-card{
    margin-bottom: 10px;
  }
</style>
