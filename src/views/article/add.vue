<template>
  <mu-container>
    <mu-card class="mu-shadow mu-hoverable" style="margin: 10px 0">
      <div class=" mu-card-title-container">
        <mu-form :model="article">
          <mu-form-item prop="title" label="标题">
            <mu-text-field v-model="article.title" placeholder="请输入标题"></mu-text-field>
          </mu-form-item>

          <mu-form-item prop="topicId" label="主题">
            <mu-select v-model="article.topicId" placeholder="请选择主题">
              <mu-option v-for="topic in topicList" :key="topic.id" :label="topic.name" :value="topic.id"></mu-option>
            </mu-select>
          </mu-form-item>
        </mu-form>
      </div>
      <mu-card-text class="mu-typo">
        <wangeditor :content="initContent" @highlight="highlight" @input="getContent"></wangeditor>
      </mu-card-text>
      <mu-card-actions style="overflow: hidden">
        <mu-button color="secondary" @click="" style="float: right">发表</mu-button>
      </mu-card-actions>
    </mu-card>
  </mu-container>
</template>

<script>
  import hljs from 'highlight.js';
  import wangeditor from "@/components/wangeditor";

  export default {
    name: "article-add",
    components: {wangeditor},
    data() {
      return {
        editor: null,
        article: {
          id: null,
          title: "",
          topicId: null,
          content: "",
        },
        initContent: "",
        topicList: [],

      }
    },
    methods: {
      // 获取主题
      getAllTopic() {
        this.$requests.get("/topic/getAll", null).then((res) => {
          if (res.data.code === 0) {
            this.topicList = res.data.data;
          }
        })
      },
      highlight() {
        let blocks = this.$el.querySelectorAll('pre code');
        blocks.forEach((block) => {
          hljs.highlightBlock(block);
        })
      },
      getArticle() {
        this.$requests.get("/article/getById", {id: this.article.id}).then(res => {
          if (res.data.code === 0) {
            if (res.data.data != null) {
              this.article.topicId = res.data.data.topicId;
              this.article.title = res.data.data.title;
              this.initContent = res.data.data.content;
            }
          }
        })
      },
      getContent(html) {
        this.article.content = html;
      },
    },
    created() {
      this.getAllTopic();
      this.article.id = this.$route.query.id;
      if (this.article.id) {
        this.getArticle();
      }
    },
    mounted() {

    },
    watch: {
      '$route': function (to, from) {
        this.article.id = this.$route.query.id;
        if (this.article.id) {
          this.getArticle();
        }
      }
    }
  }
</script>

<style lang="less">

</style>
