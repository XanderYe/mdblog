<template>
  <mu-container>
    <mu-card v-for="article in articleList" :key="article.id" style="margin: 10px 0">
      <mu-card-title :title="article.title" :sub-title="article.createTime"></mu-card-title>
      <mu-card-text v-html="article.content">
      </mu-card-text>
      <mu-card-actions>
        <mu-button flat>继续阅读</mu-button>
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
    mounted() {
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
