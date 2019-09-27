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
      <mu-card-text class="mu-typo" v-highlight>
      </mu-card-text>
      <mu-card-actions style="overflow: hidden">
        <mu-button color="secondary" @click="" style="float: right">发表</mu-button>
      </mu-card-actions>
    </mu-card>
  </mu-container>
</template>

<script>
    export default {
        name: "article-add",
        data() {
            return {
                article: {
                    title: "",
                    topicId: null,
                    content: "",
                },
                topicList: [],
                options: [
                    'Option 1', 'Option 2', 'Option 3', 'Option 4',
                    'Option 5', 'Option 6', 'Option 7', 'Option 8',
                    'Option 9', 'Option 10'
                ],

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
        },
        created() {
            this.getAllTopic();
        }
    }
</script>

<style scoped lang="less">

</style>
