<template>
  <mu-container>
    <mu-card class="mu-shadow mu-hoverable">
      <mu-card-title class="mu-typo" :title="article.title" :sub-title="article.createTime"></mu-card-title>
      <mu-card-text class="mu-typo" v-html="article.content" v-highlight>
      </mu-card-text>
      <mu-card-actions style="overflow: hidden">
        <mu-button color="secondary" v-if="$store.state.app.isOwner"
                   :to="{name: 'article-add', query: {id: article.id}}" style="float: right">编辑
        </mu-button>
      </mu-card-actions>
    </mu-card>
  </mu-container>
</template>

<script>
  export default {
    name: "article-detail",
    data() {
      return {
        articleId: 0,
        article: {},
      }
    },
    methods: {
      getArticle() {
        this.$requests.get("/article/getById", {id: this.articleId}).then(res => {
          if (res.data.code === 0) {
            this.article = res.data.data;
          }
        })
      },
    },
    created() {
      // 记录滚动条位置
      sessionStorage.setItem("scroll", document.getElementById("app").scrollTop.toString());
      document.getElementById("app").scrollTop = 0;
      this.articleId = this.$route.query.id;
      this.getArticle();
    },
  }
</script>

<style scoped>

</style>
