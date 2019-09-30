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
        <div ref="editor" style="text-align:left" v-highlight></div>
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
                editor: null,
                article: {
                    id: null,
                    title: "",
                    topicId: null,
                    content: "",
                },
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
                blocks.forEach((block)=>{
                    hljs.highlightBlock(block);
                })
            },
            getArticle() {
                this.$requests.get("/article/getById", {id: this.article.id}).then(res => {
                    if (res.data.code === 0) {
                        if(res.data.data != null) {
                            this.article.topicId = res.data.data.topicId;
                            this.article.title = res.data.data.title;
                            this.wangEditor(res.data.data.content);
                        }
                    }
                })
            },
            wangEditor(content) {
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
                editor.txt.html(content);
            },
        },
        created() {
            this.getAllTopic();
        },
        mounted() {
            this.article.id = this.$route.query.id;
            if(this.article.id){
                this.getArticle();
            }else{
                this.wangEditor();
            }
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
