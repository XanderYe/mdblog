<template>
  <div ref="editor" style="text-align:left" v-highlight></div>
</template>

<script>
  export default {
    name: "wangeditor",
    data() {
      return {

      }
    },
    props: {
      content: {
        type: String,
        default: ""
      }
    },
    methods: {},
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
      editor.txt.html(that.content);
    }
  }
</script>

<style scoped>

</style>
