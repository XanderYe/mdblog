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

        <div ref="editor" class="mu-typo" style="text-align:left" v-highlight></div>

      </div>
      <mu-card-text class="mu-typo" v-html="articleContent" v-highlight>
      </mu-card-text>
      <mu-card-actions style="overflow: hidden">
        <mu-button color="secondary" @click="" style="float: right">发表</mu-button>
      </mu-card-actions>
    </mu-card>
  </mu-container>
</template>

<script>
    import Editor from 'wangeditor';
    import hljs from 'highlight.js';

    export default {
        name: "article-add",
        data() {
            return {
                // 用于编辑时数据显示在wangEditor上
                articleContent: "",
                article: {
                    title: "",
                    topicId: null,
                    content: "",
                },
                topicList: [],

            }
        },
        methods: {
            getContent: function () {
                alert(this.article.content)
            },
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
                blocks.forEach((block)=>{
                    hljs.highlightBlock(block);
                })
            },
        },
        created() {
            this.getAllTopic();
        },
        mounted() {
            let that = this;
            let editor = new Editor(this.$refs.editor);
            editor.customConfig.uploadFileName = 'file';
            editor.customConfig.zIndex = '0';
            editor.customConfig.uploadImgServer = ajaxUrl + '/article/upload';
            editor.customConfig.onchange = (html) => {
                that.article.content = html;
                that.highlight();
            };
            editor.create();
        }
    }
</script>

<style lang="less">
  .w-e-toolbar {
    background-color: rgb(255,255,255) !important;
    border: none !important;
    display: block;
    height: 31px;
  }
  .w-e-menu {
    float: left;
  }
  .w-e-text-container {
    border: none !important;
  }
</style>
