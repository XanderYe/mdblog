<template>
  <mu-container>
    <mu-card class="mu-shadow mu-hoverable" v-for="article in articleList" :key="article.id" style="margin: 10px 0">
      <div class=" mu-card-title-container mu-typo">
        <div class="mu-card-title">
          <router-link :to="{path: 'article', query: {id: article.id}}">{{article.title}}</router-link>
        </div>
        <div class="mu-card-sub-title">
          {{article.createTime}}
        </div>
      </div>
      <mu-card-text class="mu-typo" v-html="article.content">
      </mu-card-text>
      <mu-card-actions>
        <mu-button flat :to="{path: 'article', query: {id: article.id}}">继续阅读</mu-button>
      </mu-card-actions>
    </mu-card>
  </mu-container>
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
        this.$requests.get("/article/getRecentArticles", {topicId: this.topicId, page: this.page}).then((res) => {
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
  .md-card {
    margin-bottom: 10px;
  }
</style>
